package store.dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import store.model.Store;

public class StoreDao {

	public Store selectById(Connection conn, String store_nm) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select*from store_no where wms_store_master");
			pstmt.setString(1, store_nm);
			rs = pstmt.executeQuery();
			Store store = null;
			{
				store = new Store(0, rs.getString("store_nm"), rs.getString("store_dept"), rs.getString("store_user"),
						rs.getString("store_descr"), toDate(rs.getTimestamp("store_reg_ymd")));
			}
			return store;
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
		private Date toDate(Timestamp date) {
			return date == null?null : new Date(date.getTime());
		}
		
		public void insert(Connection conn, Member mem) throws SQLException {
			try(PreparedStatement pstmt =
					conn.prepareStatement("insert into store values(?,?,?,?,?,?,?)")){
		}
	}
}
