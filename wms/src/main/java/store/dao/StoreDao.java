package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import store.model.Store;
import store.model.storeStockDTO;

public class StoreDao {

	
	//テーブルの全体データの数を数えるメソッド
	public int selectCount(Connection conn, Store store) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select*from wms_store_master");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Store> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSet,Pre... インスタンス初期化
		try {
			pstmt = conn.prepareStatement("select*from wms_store_master");
			// wms_store_masterに存在するコラムを全て紹介するQueryを用意

			rs = pstmt.executeQuery();
			List<Store> result = new ArrayList<>();
			// storeインスタンスを入れるリストを作る
			// ResultSetから次の行がある限り繰り返す
			while (rs.next()) {
				// 現在行のデータをstoreインスタンスに変化し、リストに追加
				result.add(convertStore(rs));
			}
			return result;
			// 紹介したStoreリストを返還
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//リストサービスに使うセレクトバイストアーナンバーメソッド
	public static Store selectByStoreNo(Connection conn, String storeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select store_no, store_nm, store_dept, store_user, descr, reg_ymd from wms_store_master where store_no = ?");
			pstmt.setString(1, storeNo);
			rs = pstmt.executeQuery();
		
			Store store = null;
			if (rs.next()) {
				store = new Store(
						 rs.getString("store_no"),
			                rs.getString("store_nm"),
			                rs.getString("store_dept"),
			                rs.getString("store_user"),
			                rs.getString("descr"),
			                rs.getDate("reg_ymd")
			                );
			}
			return store;
	} finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	}
	
	/*	教科書を参考にしたコード。selectByStoreNoと同じ機能であるため周釈処理。
	public static Store findStoreByNo(Connection conn, String store_no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select*from wms_store_master where store_no =?");
			// テーブルからストアナンバーで照会する
			pstmt.setString(1, store_no);
			// クオリーの初めての媒介変数にno変数の結果をintとして設定。
			rs = pstmt.executeQuery();
			// クオリー実行、その結果をResultSetに入れる
			Store store = null;
			// ストアーインスタンスを宣言し、nullに初期化
			if (rs.next()) {
				// 結果があれば、convertStoreメソッドを呼び出してrsのデータをstoreインスタンスにて変換する。
				store = convertStore(rs);
			}
			return store;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			// エラーやコードが終了される場合、リソースを解除する。
		}
	}*/

	public static void insert(Connection conn, Store store) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_store_master values(?,?,?,?,?,?)")) {
			pstmt.setString(1, store.getStore_no());
			pstmt.setString(2, store.getStore_nm());
			pstmt.setString(3, store.getStore_dept());
			pstmt.setString(4, store.getStore_user());
			pstmt.setString(5, store.getDescr());
			pstmt.setDate(6, (java.sql.Date) store.getReg_ymd());
		}
	}

	public int update(Connection conn, Store store) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update wms_store_master set store_nm=?, store_dept=?, store_user=?, descr=?, Date reg_ymd=? where store_no=?");
			pstmt.setString(1, store.getStore_nm());
			pstmt.setString(2, store.getStore_dept());
			pstmt.setString(3, store.getStore_user());
			pstmt.setString(4, store.getDescr());
			pstmt.setDate(5, (java.sql.Date) store.getReg_ymd());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, String store_nm) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_store_master where store_nm=?");
			pstmt.setString(1, store_nm);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		// store_nmからそのデータを削除する。
	}

		//教科書を参考にしたコード。selectByStoreNoを使うために作ったが、selectAllから必要とされているため残す。
	private static Store convertStore(ResultSet rs) throws SQLException { return
			  new Store(rs.getString("STORE_NO"), rs.getString("STORE_NM"),
			  rs.getString("STORE_DEPT"), rs.getString("STORE_USER"),
			  rs.getString("DESCR"), toDate(rs.getTimestamp("REG_YMD"))); }
			 

	private static Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

}