package store.dao;

import java.sql.Statement;
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

	public int selectCount(Connection conn, Store store) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select*from wms_store_master");
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		} 
	}
	
	public List<Store> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		//ResultSet,Pre... インスタンス初期化
		try {
			pstmt = conn.prepareStatement("select*from wms_store_master");
			//wms_store_masterに存在するコラムを全て紹介するQueryを用意
			
			rs = pstmt.executeQuery();
			List<Store> result = new ArrayList<>();
			//storeインスタンスを入れるリストを作る
			//ResultSetから次の行がある限り繰り返す
			while (rs.next()) {				
				//現在行のデータをstoreインスタンスに変化し、リストに追加
				result.add(convertStore(rs));
			}
			return result;	
			//紹介したStoreリストを返還
			} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Store findStoreByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select*from wms_store_master where store_no =?");
			//テーブルからストアナンバーで照会する
			pstmt.setInt(1, no);
			//クオリーの初めての媒介変数にno変数の結果をintとして設定。
			rs = pstmt.executeQuery();
			//クオリー実行、その結果をResultSetに入れる
			Store store = null;
			//ストアーインスタンスを宣言し、nullに初期化
			if (rs.next()) {
				//結果があれば、convertStoreメソッドを呼び出してrsのデータをstoreインスタンスにて変換する。
				store = convertStore(rs);
			}
			return store;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		//エラーやコードが終了される場合、リソースを解除する。
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
		//store_nmからそのデータを削除する。
	}

	private Store convertStore(ResultSet rs) throws SQLException {
		return new Store(
				rs.getInt("STORE_NO"), 
				rs.getString("STORE_NM"), 
				rs.getString("STORE_DEPT"), 
				rs.getString("STORE_USER"),
	            rs.getString("DESCR"), 
	            toDate(rs.getTimestamp("REG_YMD")));
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
}