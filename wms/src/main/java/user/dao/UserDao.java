package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import user.model.User;

public class UserDao {

	public int insert(Connection conn, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into wms_user (user_cd, user_nm, dept_nm, phone,  email) values(?,?,?,?,?)");
			pstmt.setString(1, user.getUserCd());
			pstmt.setString(2, user.getUserNm());
			pstmt.setString(3, user.getDeptNm());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getEmail());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<User> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select*from wms_user");
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<>();
			while (rs.next()) {
				userList.add(makeUserFromResultSet(rs)); 
			}
			return userList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public User selectUserCd(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select 'U' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(user_cd, 2))), 0) + 1), 3, '0') AS next_user_cd from wms_user");
			rs = pstmt.executeQuery();
			User user = null;
			
			if (rs.next()) {
				user = new User(
						rs.getString("next_user_cd")
					  , null
					  , null
					  , null
					  , null);
			}
			return user;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_user set user_cd=?, user_nm=?, dept_nm=?, phone=?,  email=?");
			pstmt.setString(1, user.getUserCd());
			pstmt.setString(2, user.getUserNm());
			pstmt.setString(3, user.getDeptNm());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getEmail());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, String userCd) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_user where user_cd=?");
			pstmt.setString(1, userCd);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private User makeUserFromResultSet(ResultSet rs) throws SQLException {
		return new User(rs.getString("user_cd"), rs.getString("user_nm"), rs.getString("dept_nm"), rs.getString("phone"), rs.getString("email"));
	}
}
