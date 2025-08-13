package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import index.model.WareItem;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;
import ware.model.Ware;

public class OrderService {

	private OrderDao orderDao = new OrderDao();

	public List<Order> selectAll() {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			return orderDao.selectAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Order getOrderCd() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return orderDao.selectOrderCd(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public Order selectByOrderNo(String orderNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return orderDao.selectByOrderNo(conn, orderNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public void insert(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			orderDao.insert(conn, new Order(
								order.getOrder_No()
							  ,	order.getOrder_Nm()
							  ,	order.getItem_Cd()
							  , order.getItem_Nm()
							  ,	order.getOrder_Price()
							  ,	order.getOrder_Dept()
							  ,	order.getOrder_User()
							  ,	order.getOrder_Gubun()
							  ,	order.getDescr()
							  ,	order.getReg_Ymd()
							  ,	order.getUpd_ymd()
							  ,	order.getStore_Yn())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void update(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			orderDao.update(conn, new Order(
								order.getOrder_No()
							  ,	order.getOrder_Nm()
							  ,	order.getItem_Cd()
							  ,	order.getItem_Nm()
							  ,	order.getOrder_Price()
							  ,	order.getOrder_Dept()
							  ,	order.getOrder_User()
							  ,	order.getOrder_Gubun()
							  ,	order.getDescr()
							  ,	order.getReg_Ymd()
							  ,	order.getUpd_ymd()
							  ,	order.getStore_Yn())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void delete(String orderNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
        	orderDao.delete(conn, orderNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}

}
