package stock.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import stock.model.Stock;

//DB에서 재고 데이터를 가져오는 클래스 / データベースから在庫データを取得するクラス
public class StockDao {

	// 전체 재고 목록을 조회하는 메서드 / 全在庫リストを取得するメソッド
	public List<Stock> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT stock_no, item_cd, qty, ware_cd, reg_ymd from wms_stock");
			rs = pstmt.executeQuery();
			List<Stock> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStock(rs)); // 결과를 Stock 객체로 변환하여 리스트에 추가 / 結果をStockオブジェクトに変換してリストに追加
			}
			return result;

		} finally {
			JdbcUtil.close(rs);			// ResultSet 닫기 / 結果セットを閉じる
			JdbcUtil.close(pstmt);	// Statement 닫기 / ステートメントを閉じる
		}
	}

	// ResultSet 를 Stock 객체로 변환 / ResultSet を Stock に変換
	private Stock convertStock(ResultSet rs) throws SQLException {

		return new Stock(rs.getInt("stock_no"), 
				rs.getString("item_cd"), 
				rs.getInt("qty"), 
				rs.getString("ware_cd"),
				toDate(rs.getTimestamp("reg_ymd")));
	}
	
	// Timestamp를 Date로 변환하는 메서드 / タイムスタンプを日付に変換するメソッド
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
	
}
