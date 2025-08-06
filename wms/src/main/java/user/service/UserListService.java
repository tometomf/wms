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
	
	public User getUserCd() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return userDao.selectUserCd(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public void insert(User user) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userDao.insert(conn, new User(
								user.getUserCd()
							  , user.getUserNm()
							  , user.getDeptNm()
							  , user.getPhone()
							  , user.getEmail())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
