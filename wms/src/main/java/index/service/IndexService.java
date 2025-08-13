package index.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import index.dao.IndexDao;
import index.model.ShipExpect;
import index.model.StoreExpect;
import index.model.WareItem;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class IndexService {

	private IndexDao indexDao = new IndexDao();

	public List<WareItem> select2() {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			return indexDao.select2(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<StoreExpect> select3() {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			return indexDao.select3(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<ShipExpect> select4() {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			return indexDao.select4(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
