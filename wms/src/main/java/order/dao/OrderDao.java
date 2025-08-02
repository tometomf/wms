package order.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

import order.model.Order;
import jdbc.JdbcUtil;

public class OrderDao{
	
	public int insert(Connection conn, String order_nm) throws SQLException {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select*from wms_order_master");
	if(rs.next()) {
		return rs.getInt(1);
	}
	return 0;
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
	} 
}

public List<Order> selectAll(Connection conn) throws SQLException {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement("select*from wms_order_master");
		
		rs = pstmt.executeQuery();
		List<Order> result = new ArrayList<>();
		while (rs.next()) {
			result.add((Order) (rs));
		}
		return result;
		} finally {
		JdbcUtil.close(pstmt);
	}
}

public Order findStoreByNo(Connection conn, int no) throws SQLException {
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement("select*from wms_order_master where order_no =?");
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		Order order = null;
		if (rs.next()) {
			//結果があれば、convertStoreメソッドを呼び出してrsのデータをstoreインスタンスにて変換する。
			order = convertOrder(rs);
		}
		return order;
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
}

private Order convertOrder(ResultSet rs) {
	// TODO Auto-generated method stub
	return null;
}

public int delete(Connection conn, String order_nm) throws SQLException {
	PreparedStatement pstmt = null;
	try {
		pstmt = conn.prepareStatement("delete wms_orer_master where order_nm=?");
		pstmt.setString(1, order_nm);
		return pstmt.executeUpdate();
	} finally {
		JdbcUtil.close(pstmt);
	}
}

private Order convertOrder1(ResultSet rs) throws SQLException {
	return new Order(
			rs.getInt("ORDER_NO"), 
			rs.getString("ORDER_NM"), 
			rs.getString("ORDER_DEPT"), 
			rs.getString("ORDER_USER"),
            rs.getString("DESCR"), 
            toDate(rs.getTimestamp("REG_YMD")));
}

private Date toDate(Timestamp date) {
	return date == null ? null : new Date(date.getTime());
}

}