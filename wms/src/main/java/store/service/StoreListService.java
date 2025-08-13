package store.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;

public class StoreListService {
	private StoreDao storeDao = new StoreDao();

	
	//StoreモデルのデータをListとして持ってくる
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

	//入庫コード照会
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
	
	//新しいstore_no獲得のためのメソッド
	public int getNewStoreNo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return storeDao.getNewStoreNo(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 新規入庫登録
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

	// 入庫の内訳修正
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

	// 入庫マスター削除
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
