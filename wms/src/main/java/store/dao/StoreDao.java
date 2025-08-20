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
import order.model.Order;
import store.model.Store;

public class StoreDao {

	public List<Store> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSet,Pre... インスタンス初期化
		try {
			pstmt = conn.prepareStatement("select rownum no, store_no, store_nm, a.item_cd, b.item_nm\r\n"
					+ "     , qty, a.store_price, ware_cd, ware_cd as ware_nm, store_dept, store_user, nvl(descr, ' ') as descr, to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd\r\n"
					+ "from wms_store a join wms_item b on a.item_cd = b.item_cd");
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
	        		"select store_no, store_nm, b.item_nm as item_cd, qty, a.store_price, (select ware_nm from wms_ware where ware_cd = a.ware_cd) as ware_cd, store_dept, store_user, descr, to_char(reg_ymd, 'yyyy-MM-dd') as reg_ymd\r\n"
	        		+ "from wms_store a join wms_item b on a.item_cd = b.item_cd\r\n"
	        		+ "where store_no = ?");
	        pstmt.setString(1, storeNo);
	        rs = pstmt.executeQuery();
	        Store store = null;
	        if (rs.next()) {
	            store = new Store(
							null
						  , rs.getString("store_no")
						  , rs.getString("store_nm")
						  , rs.getString("item_cd")
						  , null
						  , rs.getInt("qty")
						  , rs.getInt("store_price")
						  , rs.getString("ware_cd")
						  , null
						  , rs.getString("store_dept")
						  , rs.getString("store_user")
						  , rs.getString("descr")
						  , rs.getString("reg_ymd"));
	        }
	        return store;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}


	public String getNewStoreNo(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select NVL('S' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(store_no, 2))), 0) + 1), 3, '0'), 'S001') AS store_no from wms_store");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("store_no");
			}
			return "";
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public static void insert(Connection conn, Store store) throws SQLException {
		
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_store values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, store.getStore_no());
			pstmt.setString(2, store.getStore_nm());
			pstmt.setString(3, store.getItem_cd());
			pstmt.setInt(4, store.getQty());
			pstmt.setInt(5, store.getStore_price());
			pstmt.setString(6, store.getWare_cd());
			pstmt.setString(7, store.getStore_dept());
			pstmt.setString(8, store.getStore_user());
			pstmt.setString(9, store.getDescr());
			pstmt.setString(10, store.getReg_ymd());
			pstmt.executeUpdate();
		}
		
		// 재고등록 PK 조회
		PreparedStatement pstmt1 = conn.prepareStatement("select NVL('K' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(stock_no, 2))), 0) + 1), 3, '0'), 'K001') AS stock_no from wms_stock");
		ResultSet rs = null;
		
		rs = pstmt1.executeQuery();
		
		// 입고 등록 시 재고 자동 등록
		if (rs.next()) {
			try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_stock values(?, ?, ?, ?, ?, sysdate)")) {
				pstmt.setString(1, rs.getString("stock_no"));
				pstmt.setString(2, store.getItem_cd());
				pstmt.setInt(3, store.getQty());
				pstmt.setString(4, store.getWare_cd());
				pstmt.setString(5, "");
				pstmt.executeUpdate();
			}
		}
	}

	//データ修正のメソッド
	public int update(Connection conn, Store store) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update wms_store set store_nm = ?, qty = ?, store_price = ?, store_dept=?, store_user = ?, descr = ?, reg_ymd = ? where store_no = ?");
			pstmt.setString(1, store.getStore_nm());
	        pstmt.setInt(2, store.getQty());
	        pstmt.setInt(3, store.getStore_price());
	        pstmt.setString(4, store.getStore_dept());
	        pstmt.setString(5, store.getStore_user());
	        pstmt.setString(6, store.getDescr());
	        pstmt.setString(7, store.getReg_ymd());
	        pstmt.setString(8, store.getStore_no()); 
			
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	//データ削除のメソッド
	public int delete(Connection conn, String store_No) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_store where store_no = ?");
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
    		rs.getString("no"),
	        rs.getString("STORE_NO"),            // store_no
	        rs.getString("STORE_NM"),            // store_nm
	        rs.getString("ITEM_CD"),             // item_cd
	        rs.getString("ITEM_NM"),             // item_cd
	        rs.getInt("QTY"),            		 // item_cd
	        rs.getInt("STORE_PRICE"),            // item_cd
	        rs.getString("WARE_CD"),            // item_cd
	        rs.getString("WARE_NM"),            // item_cd
	        rs.getString("STORE_DEPT"),          // store_dept
	        rs.getString("STORE_USER"),          // store_user
	        rs.getString("DESCR"),               // descr
	        rs.getString("REG_YMD")   // reg_ymd
	    );
	}

	private static Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

}