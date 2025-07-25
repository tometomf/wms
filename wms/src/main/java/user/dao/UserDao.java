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
					"insert into wms_user (user_cd, phone, user_nm, email, dept_nm) values(?,?,?,?,?)");
			pstmt.setString(1, user.getUserCd());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getUserNm());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getDeptNm());
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
				userList.add(makeUserFromResultSet(rs)); // 서비스 외부 메서드로 분리 가능
			}
			return userList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_user set user_cd=?, phone=?, user_nm=?, email=?, dept_nm=?");
			pstmt.setString(1, user.getUserCd());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getUserNm());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getDeptNm());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, String userCd) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_user set user_cd=?, phone=?, user_nm=?, email=?, dept_nm=?");
			pstmt.setString(1, userCd);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private User makeUserFromResultSet(ResultSet rs) throws SQLException {
		return new User(rs.getString("user_cd"), rs.getString("phone"), rs.getString("user_nm"), rs.getString("email"), rs.getString("dept_nm"));
	}
}
