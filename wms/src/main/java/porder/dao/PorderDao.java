package porder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import porder.model.Porder;

public class PorderDao {
	
	// 전체 조회
	public List<Porder> select(Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select rownum no, purchase_no, purchase_nm, a.item_cd, b.item_nm, qty, purchase_dept, purchase_user\r\n"
					+ "     , nvl(descr, ' ') as descr, to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd \r\n"
					+ "from wms_porder a, wms_item b\r\n"
					+ "where a.item_cd = b.item_cd");
			rs = pstmt.executeQuery();
			List<Porder> pOrder = new ArrayList<>();
			while (rs.next()) {
				pOrder.add(pOrderConvert(rs));
			}
			return pOrder;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
		
	// 신규 등록 시 창고 코드 조회
	public Porder getPurchaseNo(Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select NVL('PO' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(purchase_no, 3))), 0) + 1), 3, '0'), 'PO001') AS purchase_no from wms_porder");
			rs = pstmt.executeQuery();
			Porder pOrder = null;
			
			if (rs.next()) {
				pOrder = new Porder(
						null
					  ,	rs.getString("purchase_no")
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null);
			}
			return pOrder;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 조회
	public Porder selectByPurchaseNo(Connection conn, String purcahseNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select purchase_no, purchase_nm, a.item_cd, b.item_nm, qty\r\n"
					+ "     , purchase_dept, purchase_user\r\n"
					+ "     , nvl(descr, ' ') as descr\r\n"
					+ "     , to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd \r\n"
					+ "from wms_porder a join wms_item b on a.item_cd = b.item_cd\r\n"
					+ "where purchase_no = ?");
			pstmt.setString(1, purcahseNo);
			rs = pstmt.executeQuery();
			Porder pOrder = null;
			
			if (rs.next()) {
				pOrder = new Porder(
							null
						  ,	rs.getString("purchase_no")
						  , rs.getString("purchase_nm")
						  , rs.getString("item_cd")
						  , rs.getString("item_nm")
						  , rs.getString("qty")
						  , rs.getString("purchase_dept")
						  , rs.getString("purchase_user")
						  , rs.getString("descr")
						  , null);
			}
			return pOrder;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 등록
	public void insert(Connection conn, Porder pOrder) throws SQLException {
		
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_porder values(?, ?, ?, ?, ?, ?, ?, sysdate)")) {
			pstmt.setString(1, pOrder.getPurchase_No());
			pstmt.setString(2, pOrder.getPurchase_Nm());
			pstmt.setString(3, pOrder.getItem_Cd());
			pstmt.setString(4, pOrder.getQty());
			pstmt.setString(5, pOrder.getPurchase_Dept());
			pstmt.setString(6, pOrder.getPurchase_User());
			pstmt.setString(7, pOrder.getDescr());
			pstmt.executeUpdate();
		}
	}

	// 창고 마스터 수정
	public int update(Connection conn, Porder pOrder) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("update wms_porder set purchase_nm = ?, qty = ?, purchase_dept = ?, purchase_user = ?, descr = ? where purchase_no = ?");
			pstmt.setString(1, pOrder.getPurchase_Nm());
			pstmt.setString(2, pOrder.getQty());
			pstmt.setString(3, pOrder.getPurchase_Dept());
			pstmt.setString(4, pOrder.getPurchase_User());
			pstmt.setString(5, pOrder.getDescr());
			pstmt.setString(6, pOrder.getPurchase_No());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// 창고 마스터 삭제
	public int delete(Connection conn, String purchaseNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("delete wms_porder where purchase_no = ?");
			pstmt.setString(1, purchaseNo);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// convert
	private Porder pOrderConvert(ResultSet rs) throws SQLException {
		return new Porder(rs.getString("no")
					    , rs.getString("purchase_no")
					    , rs.getString("purchase_nm")
					    , rs.getString("item_cd")
					    , rs.getString("item_nm")
					    , rs.getString("qty")
					    , rs.getString("purchase_dept")
					    , rs.getString("purchase_user")
					    , rs.getString("descr")
					    , rs.getString("reg_ymd"));
	}
}
