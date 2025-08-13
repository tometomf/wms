package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import store.model.Store;

public class StoreDao {

	public List<Store> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSet,Pre... インスタンス初期化
		try {
			pstmt = conn.prepareStatement("select*from wms_store");
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

	// リストサービスに使うセレクトバイストアーナンバーメソッド
	public Store selectByStoreNo(Connection conn, String storeNo) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(
	            "select store_no, store_nm, item_cd, item_qty, store_dept, store_user, descr, reg_ymd " +
	            "from wms_store where store_no = ?");
	        pstmt.setInt(1, Integer.parseInt(storeNo));
	        rs = pstmt.executeQuery();
	        Store store = null;
	        if (rs.next()) {
	            store = new Store(
	                rs.getInt("store_no"),              // store_no
	                rs.getString("store_nm"),           // store_nm
	                rs.getString("store_dept"),         // store_dept
	                rs.getString("store_user"),         // store_user
	                rs.getString("descr"),              // descr
	                rs.getDate("reg_ymd"),               // reg_ymd
	                rs.getString("item_cd"),            // item_cd
	                rs.getInt("item_qty")                // item_qty
	            );
	        }
	        return store;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}


	public int getNewStoreNo(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select nvl(max(STORE_NO), 0) + 1 as store_no from wms_store");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("store_no");
			}
			return 1;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public static void insert(Connection conn, Store store) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_store values(?,?,?,?,?,?,?,?)")) {
			pstmt.setInt(1, store.getStore_no());
			pstmt.setString(2, store.getStore_nm());
			pstmt.setString(3, store.getItem_cd());
			pstmt.setInt(4, store.getItem_qty());
			pstmt.setString(5, store.getStore_dept());
			pstmt.setString(6, store.getStore_user());
			pstmt.setString(7, store.getDescr());
			pstmt.setDate(8, new java.sql.Date(store.getReg_ymd().getTime()));

			pstmt.executeUpdate();
		}
	}

	//データ修正のメソッド
	public int update(Connection conn, Store store) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update wms_store set store_nm=?, store_dept=?, store_user=?, descr=?, reg_ymd=? where store_no=?");
			pstmt.setString(1, store.getStore_nm());
			pstmt.setString(2, store.getItem_cd());
			pstmt.setInt(3, store.getItem_qty());
			pstmt.setString(4, store.getStore_dept());
			pstmt.setString(5, store.getStore_user());
			pstmt.setString(6, store.getDescr());
			pstmt.setDate(7, (java.sql.Date) store.getReg_ymd());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//データ削除のメソッド
	public int delete(Connection conn, String store_No) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_store where store_no=?");
			pstmt.setString(1, store_No);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		// store_nmからそのデータを削除する。
	}

	// 教科書を参考にしたコード。selectByStoreNoを使うために作ったが、selectAllから必要とされているため残す。
	private static Store convertStore(ResultSet rs) throws SQLException {
	    return new Store(
	        rs.getInt("STORE_NO"),               // store_no
	        rs.getString("STORE_NM"),            // store_nm
	        rs.getString("STORE_DEPT"),          // store_dept
	        rs.getString("STORE_USER"),          // store_user
	        rs.getString("DESCR"),               // descr
	        toDate(rs.getTimestamp("REG_YMD")),  // reg_ymd
	        rs.getString("ITEM_CD"),             // item_cd
	        rs.getInt("ITEM_QTY")                 // item_qty
	    );
	}

	private static Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

}