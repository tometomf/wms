package store.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;
import store.model.storeStockDTO;

public class StoreListService {
	private StoreDao storeDao = new StoreDao();

	public List<Store> getStoreList() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return storeDao.selectAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<storeStockDTO> getselectByStoreNo(String storeNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return (List<storeStockDTO>) storeDao.selectByStoreNo(conn, storeNo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고코드 조회
	public Store getStore_no(String storeNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			return storeDao.selectByStoreNo(conn, storeNo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고 마스터 조회
	public Store findStoreByNo(String storeNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
				return StoreDao.selectByStoreNo(conn, storeNo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고 신규 등록
	public void insert(Store store) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			StoreDao.insert(conn, store);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고내역 수정
	public void update(Store store) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			storeDao.update(conn, store);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 입고 마스터 삭제
	public void delete(String storeNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			storeDao.delete(conn, storeNo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
