package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

public class OrderListService {
	private OrderDao orderDao = new OrderDao();
	
	
		public List<Order> getOrderList(){
			Connection conn = null;
			 	try {
			 		conn = ConnectionProvider.getConnection();
			 		return orderDao.selectAll(conn);
			 	}	catch (SQLException e) {
			 		throw new RuntimeException(e);
			 	}	finally {
			 			JdbcUtil.close(conn);
			 	}
		}
	}	
