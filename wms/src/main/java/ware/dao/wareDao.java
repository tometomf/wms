package ware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ware.model.Ware;
import ware.model.WareStockDTO;
import jdbc.JdbcUtil;

public class wareDao {
	
//	public int insert(Connection conn, Ware ware) throws SQLException {
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn
//					.prepareStatement("insert into wms_ware (ware_cd, ware_nm, ware_gubun, use_yn) values(?,?,?,?)");
//			pstmt.setString(1, ware.getWareCd());
//			pstmt.setString(2, ware.getWareNm());
//			pstmt.setString(3, ware.getWareGubun());
//			pstmt.setString(4, ware.getUseYn());
//			return pstmt.executeUpdate();
//		} finally {
//			JdbcUtil.close(pstmt);
//		}
//	}

	public List<Ware> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select ware_cd, ware_nm, ware_gubun, descr, case when use_yn = 'Y' then '利用あり' else '利用なし' end as use_yn from wms_ware");
			rs = pstmt.executeQuery();
			List<Ware> wareList = new ArrayList<>();
			while (rs.next()) {
				wareList.add(makeWareFromResultSet(rs));
			}
			return wareList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<WareStockDTO> selectWareStock(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select a.stock_no       -- 재고번호\r\n" + "     , a.item_cd        -- 품목코드\r\n"
					+ "     , a.item_nm        -- 품목명\r\n" + "     , a.qty            -- 재고수량\r\n"
					+ "     , a.spec           -- 규격\r\n" + "     , a.item_gubun     -- 분류\r\n"
					+ "     , a.manufacturer   -- 제조사\r\n" + "     , a.ware_cd        -- 창고코드\r\n"
					+ "     , b.ware_nm        -- 창고명\r\n" + "     , a.reg_ymd        -- 재고등록일\r\n" + "from (\r\n"
					+ "    select a.stock_no, a.item_cd, a.qty, a.ware_cd, a.reg_ymd\r\n"
					+ "         , b.item_nm, b.spec, b.item_gubun, b.unit, b.use_yn, b.manufacturer\r\n"
					+ "         , b.store_price, b.shipment_price\r\n" + "    from wms_stock a, wms_item b\r\n"
					+ "    where a.item_cd = b.item_cd\r\n" + ") a, wms_ware b\r\n" + "where a.ware_cd = b.ware_cd;");
			rs = pstmt.executeQuery();
			List<WareStockDTO> wareStockList = new ArrayList<>();
			while (rs.next()) {
				wareStockList.add(makeWareStockFromResultSet(rs));
			}
			return wareStockList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Ware selectWareCd(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select NVL('W' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(ware_cd, 2))), 0) + 1), 3, '0'), 'W001') AS next_ware_cd from wms_ware");
			rs = pstmt.executeQuery();
			Ware ware = null;
			
			if (rs.next()) {
				ware = new Ware(
						rs.getString("next_ware_cd")
					  , null
					  , null
					  , null
					  , null);
			}
			return ware;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Ware selectByWareCd(Connection conn, String wareCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT WARE_CD, WARE_NM, ware_gubun, USE_YN, DESCR FROM WMS_WARE WHERE WARE_CD = ?");
			pstmt.setString(1, wareCd);
			rs = pstmt.executeQuery();
			Ware ware = null;
			
			if (rs.next()) {
				ware = new Ware(
						rs.getString("ware_cd")
					  , rs.getString("ware_nm")
					  , rs.getString("ware_gubun")
					  , rs.getString("use_yn")
					  , rs.getString("descr"));
			}
			return ware;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 신규 등록
	public void insert(Connection conn, Ware ware) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_ware values(?, ?, ?, ?, ?)")) {
			pstmt.setString(1, ware.getWareCd());
			pstmt.setString(2, ware.getWareNm());
			pstmt.setString(3, ware.getWareGubun());
			pstmt.setString(4, ware.getDescr());
			pstmt.setString(5, ware.getUseYn());
			pstmt.executeUpdate();
		}
	}

	public int update(Connection conn, Ware ware) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_ware set ware_nm=?, ware_gubun=?, use_yn=?, descr = ? where ware_cd = ?");
			pstmt.setString(1, ware.getWareNm());
			pstmt.setString(2, ware.getWareGubun());
			pstmt.setString(3, ware.getUseYn());
			pstmt.setString(4, ware.getDescr());
			pstmt.setString(5, ware.getWareCd());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, String wareCd) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_ware where ware_cd=?");
			pstmt.setString(1, wareCd);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	private Ware makeWareFromResultSet(ResultSet rs) throws SQLException {
		return new Ware(rs.getString("ware_cd"), rs.getString("ware_nm"), rs.getString("ware_gubun"),
				rs.getString("use_yn"), rs.getString("descr"));
	}

	private WareStockDTO makeWareStockFromResultSet(ResultSet rs) throws SQLException {
		return new WareStockDTO(rs.getString("ware_cd"),rs.getString("ware_nm"),rs.getInt("stock_no"),rs.getString("item.cd"),rs.getInt("qty"),rs.getDate("req_ymd"));
	}
}
