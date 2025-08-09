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
import stock.model.StockPlus;

//DB에서 재고 데이터를 가져오는 클래스 / データベースから在庫データを取得するクラス
public class StockDao {

	// 전체 재고 목록을 조회하는 메서드 / 全在庫リストを取得するメソッド
	public List<StockPlus> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select a.item_cd, a.item_nm, a.spec, a.item_gubun, a.manufacturer, a.qty, a.ware_cd, b.ware_nm, a.reg_ymd\r\n"
					+ "from (\r\n"
					+ "    select a.item_cd, b.item_nm, b.spec, b.item_gubun, b.manufacturer, qty, ware_cd, reg_ymd\r\n"
					+ "    from wms_stock a, wms_item b\r\n"
					+ "    where a.item_cd = b.item_cd\r\n"
					+ ") a, wms_ware b\r\n"
					+ "where a.ware_cd = b.ware_cd");
			rs = pstmt.executeQuery();
			List<StockPlus> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStockPlus(rs)); // 결과를 Stock 객체로 변환하여 리스트에 추가 / 結果をStockオブジェクトに変換してリストに追加
			}
			return result;

		} finally {
			JdbcUtil.close(rs);			// ResultSet 닫기 / 結果セットを閉じる
			JdbcUtil.close(pstmt);	// Statement 닫기 / ステートメントを閉じる
		}
	}

	//재고번호 1씩 증가
	public Stock selectStockNo(Connection conn) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(
	            "SELECT NVL(MAX(stock_no), 0) + 1 AS next_stock_no FROM wms_stock"
	        );
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return new Stock(
	                rs.getInt("next_stock_no"),
	                null, // item_Cd
	                0,    // qty
	                null, // ware_Cd
	                null  // reg_Ymd
	            );  }
	        return null;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}

	//재고번호로 조회
	public Stock selectByStockNo(Connection conn, int stockNo) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(
	            "SELECT stock_no, item_cd, qty, ware_cd, reg_ymd FROM wms_stock WHERE stock_no = ?" );
	        pstmt.setInt(1, stockNo);
	        rs = pstmt.executeQuery();
	        Stock stock = null;

	        if (rs.next()) {
	            stock = new Stock(
	                rs.getInt("stock_no"),
	                rs.getString("item_cd"),
	                rs.getInt("qty"),
	                rs.getString("ware_cd"),
	                rs.getDate("reg_ymd")
	            );
	        }
	        return stock;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}
	
	// 재고 신규 등록
		public void insert(Connection conn, Stock stock) throws SQLException {
			try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_stock values(?, ?, ?, ?, ?)")) {
				pstmt.setInt(1, stock.getStock_No());  // 품목코드 / 品目コード
			    pstmt.setString(2, stock.getItem_Cd());  // 품목코드 / 品目コード
	            pstmt.setInt(3, stock.getQty());         // 수량 / 数量
	            pstmt.setString(4, stock.getWare_Cd());  // 창고코드 / 倉庫コード
	            pstmt.setDate(5, new java.sql.Date(stock.getReg_Ymd().getTime())); // 등록일 / 登録日

				pstmt.executeUpdate();
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
	
	private StockPlus convertStockPlus(ResultSet rs) throws SQLException {
	    return new StockPlus(
	        rs.getString("item_cd"),
	        rs.getString("item_nm"),
	        rs.getString("spec"),
	        rs.getString("item_gubun"),
	        rs.getString("manufacturer"),
	        rs.getInt("qty"),
	        rs.getString("ware_cd"),
	        rs.getString("ware_nm"),
	        rs.getDate("reg_ymd")
	    );
    }
}
	

