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

	private OrderDao orderDao = new OrderDao(); // DAO (데이터 접근 객체)

	// 全件取得 (전체 데이터 조회)
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
	
	// 注文番号取得 (주문 번호 조회)
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
	
	// 注文番号で検索 (주문 번호로 검색)
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
	
	// 登録 (등록)
	public void insert(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 自動コミット解除 (자동 커밋 해제)
			
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
			conn.commit(); // コミット (커밋)
		} catch (SQLException e) {
			JdbcUtil.rollback(conn); // ロールバック (롤백)
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 更新 (갱신)
	public void update(Order order) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 自動コミット解除 (자동 커밋 해제)
			
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
			conn.commit(); // コミット (커밋)
		} catch (SQLException e) {
			JdbcUtil.rollback(conn); // ロールバック (롤백)
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 削除 (삭제)
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