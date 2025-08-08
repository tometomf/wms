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

    // 전체 목록 조회
    public List<ShipViewModel> selectAll(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                "SELECT ship_no, ship_nm, item_cd, ship_price, ship_qty, " +
                "       ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn " +
                "  FROM wms_ship " +
                " ORDER BY ship_no"
            );

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

    // 조회 결과를 DTO로 변환
    private ShipViewModel makeShipFromResultSet(ResultSet rs) throws SQLException {
        ShipViewModel dto = new ShipViewModel();
        dto.setShipNo(rs.getInt("ship_no"));
        dto.setShipNm(rs.getString("ship_nm"));
        dto.setItemCd(rs.getString("item_cd"));
        dto.setShipPrice(rs.getInt("ship_price"));
        dto.setShipQty(rs.getInt("ship_qty"));
        dto.setShipDept(rs.getString("ship_dept"));
        dto.setShipUser(rs.getString("ship_user"));
        dto.setDescr(rs.getString("descr"));
        dto.setRegYmd(rs.getDate("reg_ymd"));
        dto.setUpdYmd(rs.getDate("upd_ymd"));
        dto.setShipYn(rs.getString("ship_yn"));
        return dto;
    }

    // 다음 출고번호 가져오기
    public ShipViewModel selectShipNo(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                "SELECT NVL(MAX(ship_no) + 1, 1) AS ship_no FROM wms_ship"
            );
            rs = pstmt.executeQuery();

            ShipViewModel ship = null;
            if (rs.next()) {
                ship = new ShipViewModel();
                ship.setShipNo(rs.getInt("ship_no")); 
            }

            return ship;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 출고 등록
    public int insert(Connection conn, ShipViewModel ship) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 출고번호 생성
        	String seqSql = "SELECT NVL(MAX(ship_no) + 1, 1) AS ship_no FROM wms_ship";
        	pstmt = conn.prepareStatement(seqSql);
        	rs = pstmt.executeQuery();
        	int newShipNo = 0;
        	if (rs.next()) {
        	    newShipNo = rs.getInt("ship_no"); 
        	} else {
        	    throw new SQLException("출고번호 시퀀스 생성 실패");
        	}
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);

            String insertSql = "INSERT INTO wms_ship " +
                "(ship_no, ship_nm, item_cd, ship_price, ship_qty, ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, newShipNo);
            pstmt.setString(2, ship.getShipNm());
            pstmt.setString(3, ship.getItemCd());
            pstmt.setInt(4, ship.getShipPrice());
            pstmt.setInt(5, ship.getShipQty());
            pstmt.setString(6, ship.getShipDept());
            pstmt.setString(7, ship.getShipUser());
            pstmt.setString(8, ship.getDescr());
            pstmt.setString(9, ship.getShipYn());

            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new SQLException("출고 등록 실패");
            }

            return newShipNo;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
}