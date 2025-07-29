package user.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.dao.UserDao;
import user.model.User;

public class UserListService {
	private UserDao userDao = new UserDao();
	
	public List<User> getUserList(){
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return userDao.selectAll(conn);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
}
