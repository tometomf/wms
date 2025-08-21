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

	private OrderDao orderDao = new OrderDao(); // DAO

	// 全件取得
	public List<Order> select() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return orderDao.select(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 注文番号取得
	public String selectOrderNo() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return orderDao.selectOrderNo(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 注文番号で検索
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
	
	// 登録
	public void insert(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			orderDao.insert(conn, new Order(
							    null
						      , order.getOrder_No()
							  ,	order.getOrder_Nm()
							  ,	order.getItem_Cd()
							  , order.getItem_Nm()
							  , order.getQty()
							  ,	order.getOrder_Price()
							  ,	order.getOrder_Dept()
							  ,	order.getOrder_User()
							  ,	order.getDescr()
							  , null)
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 更新
	public void update(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			orderDao.update(conn, new Order(
									null
							      , order.getOrder_No()
								  ,	order.getOrder_Nm()
								  ,	order.getItem_Cd()
								  , order.getItem_Nm()
								  , order.getQty()
								  ,	order.getOrder_Price()
								  ,	order.getOrder_Dept()
								  ,	order.getOrder_User()
								  ,	order.getDescr()
								  ,	order.getReg_Ymd())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 削除
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