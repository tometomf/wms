package order.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import index.model.ShipExpect;
import index.model.WareItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

import order.model.Order;
import ware.model.Ware;
import jdbc.JdbcUtil;

public class OrderDao {

	public List<Order> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select rownum no, order_no, order_nm, a.item_cd, b.item_nm, qty, order_price\r\n"
					+ "     , order_dept, order_user, to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd\r\n"
					+ "from wms_order a join wms_item b on a.item_cd = b.item_cd");
			rs = pstmt.executeQuery(); // クエリを実行 (쿼리 실행)
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				orderList.add(orderListConvert(rs)); 
			}
			return orderList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public String selectOrderNo(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select NVL('SO' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(order_no, 3))), 0) + 1), 3, '0'), 'SO001') AS order_no from wms_order");
			rs = pstmt.executeQuery();
			String orderNo = null;
			
			if (rs.next()) {
				orderNo = rs.getString("order_no");
			}
			return orderNo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Order selectByOrderNo(Connection conn, String orderNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select order_no, order_nm, a.item_cd, b.item_nm, qty, order_price, order_dept, order_user, descr \r\n"
					+ "from wms_order a join wms_item b on a.item_cd = b.item_cd\r\n"
					+ "where order_no = ?");
			pstmt.setString(1, orderNo);
			rs = pstmt.executeQuery();
			Order order = null;
			
			if (rs.next()) {
				order = new Order(
						null
					  , rs.getString("order_no")
					  , rs.getString("order_nm")
					  , rs.getString("item_cd")
					  , rs.getString("item_nm")
					  , rs.getString("qty")
					  , rs.getString("order_price")
					  , rs.getString("order_dept")
					  , rs.getString("order_user")
					  , rs.getString("descr")
					  , null);
			}
			return order;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Order order) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_order values (?, ?, ?, ?, ?, ?, ?, ?, sysdate)")) {
			pstmt.setString(1, order.getOrder_No());
			pstmt.setString(2, order.getOrder_Nm());
			pstmt.setString(3, order.getItem_Cd());
			pstmt.setString(4, order.getQty());
			pstmt.setString(5, order.getOrder_Price());
			pstmt.setString(6, order.getOrder_Dept());
			pstmt.setString(7, order.getOrder_User());
			pstmt.setString(8, order.getDescr());
			pstmt.executeUpdate(); // データ登録実行 (데이터 등록 실행)
		}
	}
	
	public void update(Connection conn, Order order) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update wms_order\r\n"
				+ "set order_nm = ?\r\n"
				+ "  , qty = ?\r\n"
				+ "  , order_price = ?\r\n"
				+ "  , order_dept = ?\r\n"
				+ "  , order_user = ?\r\n"
				+ "  , descr = ?\r\n"
				+ "where order_no = ?")) {
			pstmt.setString(1, order.getOrder_Nm());
			pstmt.setString(2, order.getQty());
			pstmt.setString(3, order.getOrder_Price());
			pstmt.setString(4, order.getOrder_Dept());
			pstmt.setString(5, order.getOrder_User());
			pstmt.setString(6, order.getDescr());
			pstmt.setString(7, order.getOrder_No());
			pstmt.executeUpdate(); // データ更新実行 (데이터 갱신 실행)
		}
	}
	
	public int delete(Connection conn, String orderNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_order where order_no = ?");
			pstmt.setString(1, orderNo);
			return pstmt.executeUpdate(); // データ削除実行 (데이터 삭제 실행)
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Order orderListConvert(ResultSet rs) throws SQLException {
		return new Order(
				rs.getString("no")
			  , rs.getString("order_no")
	 	 	  , rs.getString("order_nm")
	 	 	  , rs.getString("item_cd")
	 	 	  , rs.getString("item_nm")
	 	 	  , rs.getString("qty")
	 	 	  , rs.getString("order_price")
	 	 	  , rs.getString("order_dept")
	 	 	  , rs.getString("order_user")
	 	 	  , null
	 	 	  , rs.getString("reg_ymd"));
	}
}