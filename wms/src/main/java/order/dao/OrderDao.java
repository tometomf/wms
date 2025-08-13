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

	public List<Order> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select order_no, order_nm\r\n"
					+ "     , item_cd, (select item_nm from wms_item where item_cd = a.item_cd) as item_nm\r\n"
					+ "     , order_price, order_dept, order_gubun, order_user, to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd"
					+ "		, case when store_yn = 'Y' then '完了' else '未完了' end as store_yn\r\n"
					+ "from wms_order a");
			rs = pstmt.executeQuery();//クエリを実行
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
	
	public Order selectOrderCd(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select nvl(max(order_no) + 1, 1) as order_no from wms_order");
			rs = pstmt.executeQuery();
			Order order = null;
			
			if (rs.next()) {
				order = new Order(
						rs.getString("order_no")
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null);
			}
			return order;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Order selectByOrderNo(Connection conn, String orderNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select order_no, order_nm, item_cd, order_price, order_dept, order_user, order_gubun\r\n"
					+ "     , store_yn, descr\r\n"
					+ "from wms_order\r\n"
					+ "where order_no = ?");
			pstmt.setString(1, orderNo);
			rs = pstmt.executeQuery();
			Order order = null;
			
			if (rs.next()) {
				order = new Order(
						rs.getString("order_no")
					  , rs.getString("order_nm")
					  , rs.getString("item_cd")
					  , null
					  , rs.getString("order_price")
					  , rs.getString("order_dept")
					  , rs.getString("order_user")
					  , rs.getString("order_gubun")
					  , rs.getString("descr")
					  , null
					  , null
					  , rs.getString("store_yn"));
			}
			return order;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Order order) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_order values (?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate, ?)")) {
			pstmt.setString(1, order.getOrder_No());
			pstmt.setString(2, order.getOrder_Nm());
			pstmt.setString(3, order.getItem_Cd());
			pstmt.setString(4, order.getOrder_Price());
			pstmt.setString(5, order.getOrder_Dept());
			pstmt.setString(6, order.getOrder_User());
			pstmt.setString(7, order.getOrder_Gubun());
			pstmt.setString(8, order.getDescr());
			pstmt.setString(9, order.getStore_Yn());
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, Order order) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update wms_order\r\n"
				+ "set order_nm = ?\r\n"
				+ "  , item_cd = ?\r\n"
				+ "  , order_price = ?\r\n"
				+ "  , order_dept = ?\r\n"
				+ "  , order_user = ?\r\n"
				+ "  , order_gubun = ?\r\n"
				+ "  , descr = ?\r\n"
				+ "  , upd_ymd = sysdate\r\n"
				+ "  , store_yn = ?\r\n"
				+ "where order_no = ?")) {
			pstmt.setString(1, order.getOrder_Nm());
			pstmt.setString(2, order.getItem_Cd());
			pstmt.setString(3, order.getOrder_Price());
			pstmt.setString(4, order.getOrder_Dept());
			pstmt.setString(5, order.getOrder_User());
			pstmt.setString(6, order.getOrder_Gubun());
			pstmt.setString(7, order.getDescr());
			pstmt.setString(8, order.getStore_Yn());
			pstmt.setString(9, order.getOrder_No());
			pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, String orderNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_order where order_no = ?");
			pstmt.setString(1, orderNo);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Order orderListConvert(ResultSet rs) throws SQLException {
		return new Order(rs.getString("order_no")
					 	 	, rs.getString("order_nm")
					 	 	, rs.getString("item_cd")
					 	 	, rs.getString("item_nm")
					 	 	, rs.getString("order_price")
					 	 	, rs.getString("order_dept")
					 	 	, rs.getString("order_user")
					 	 	, rs.getString("order_gubun")
					 	 	, null
					 	 	, rs.getString("reg_ymd")
					 	 	, null
					 	 	, rs.getString("store_yn"));
	}
}
