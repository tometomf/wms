package ship.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import ship.model.ShipViewModel;

public class ShipDAO {

	public List<ShipViewModel> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT a.ship_no, a.ship_nm, a.ship_dept, a.ship_user, a.descr, "
					+ "       a.reg_ymd, a.upd_ymd, a.ship_yn, "
					+ "       b.item_cd, b.ship_price, b.ship_qty, b.ship_gubun " + "  FROM wms_ship_master a "
					+ "  JOIN wms_ship_detail b ON a.ship_no = b.ship_no " + " ORDER BY a.ship_no, b.item_cd");

			rs = pstmt.executeQuery();
			List<ShipViewModel> list = new ArrayList<>();
			while (rs.next()) {
				list.add(makeShipFromResultSet(rs));
			}

			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private ShipViewModel makeShipFromResultSet(ResultSet rs) throws SQLException {
		ShipViewModel dto = new ShipViewModel();
		dto.setShipNo(rs.getInt("ship_no"));
		dto.setShipNm(rs.getString("ship_nm"));
		dto.setShipDept(rs.getString("ship_dept"));
		dto.setShipUser(rs.getString("ship_user"));
		dto.setDescr(rs.getString("descr"));
		dto.setRegYmd(rs.getDate("reg_ymd"));
		dto.setUpdYmd(rs.getDate("upd_ymd"));
		dto.setShipYn(rs.getString("ship_yn"));
		dto.setItemCd(rs.getString("item_cd"));
		dto.setShipPrice(rs.getInt("ship_price"));
		dto.setShipQty(rs.getInt("ship_qty"));
		dto.setShipGubun(rs.getString("ship_gubun"));
		return dto;
	}

	public int insert(Connection conn, ShipViewModel ship) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. 시퀀스로 새 출고번호 생성
			String seqSql = "select max(ship_no) + 1 as next_ship_no from wms_ship_master;";
			pstmt = conn.prepareStatement(seqSql);
			rs = pstmt.executeQuery();
			int newShipNo = 0;
			if (rs.next()) {
				newShipNo = rs.getInt("next_ship_no");
			} else {
				throw new SQLException("출고번호 시퀀스 생성 실패");
			}
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

			// 2. 마스터 테이블 등록
			String masterSql = "INSERT INTO wms_ship_master "
					+ "(ship_no, ship_nm, ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn) "
					+ "VALUES (?, ?, ?, ?, ?, SYSDATE, SYSDATE, 'Y')";
			pstmt = conn.prepareStatement(masterSql);
			pstmt.setInt(1, newShipNo);
			pstmt.setString(2, ship.getShipNm());
			pstmt.setString(3, ship.getShipDept());
			pstmt.setString(4, ship.getShipUser());
			pstmt.setString(5, ship.getDescr());
			int masterResult = pstmt.executeUpdate();
			JdbcUtil.close(pstmt);

			if (masterResult == 0) {
				throw new SQLException("출고 마스터 등록 실패");
			}

			// 3. 상세 테이블 등록
			String detailSql = "INSERT INTO wms_ship_detail " + "(ship_no, item_cd, ship_price, ship_qty, ship_gubun) "
					+ "VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(detailSql);
			pstmt.setInt(1, newShipNo);
			pstmt.setString(2, ship.getItemCd());
			pstmt.setInt(3, ship.getShipPrice());
			pstmt.setInt(4, ship.getShipQty());
			pstmt.setString(5, ship.getShipGubun());
			int detailResult = pstmt.executeUpdate();

			if (detailResult == 0) {
				throw new SQLException("출고 상세 등록 실패");
			}

			return newShipNo;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}