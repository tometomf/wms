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
					"select rownum no, stock_no, a.item_cd, b.item_nm, qty, a.ware_cd, c.ware_nm, to_char(a.reg_ymd, 'yyyy-MM-dd') as reg_ymd, nvl(a.descr, ' ') as descr\r\n"
					+ "from wms_stock a join wms_item b on a.item_cd = b.item_cd join wms_ware c on a.ware_cd = c.ware_cd\r\n"
					+ "order by stock_no desc" );
			rs = pstmt.executeQuery();
			List<StockPlus> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertStockPlus(rs)); // 결과를 Stock 객체로 변환하여 리스트에 추가 / 結果をStockオブジェクトに変換してリストに追加
			}
			return result;

		} finally {
			JdbcUtil.close(rs); // ResultSet 닫기 / 結果セットを閉じる
			JdbcUtil.close(pstmt); // Statement 닫기 / ステートメントを閉じる
		}
	}

	// 재고번호 1씩 증가 / 在庫番号1ずつ増加
	public String selectStockNo(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select NVL('K' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(stock_no, 2))), 0) + 1), 3, '0'), 'K001') AS stock_no from wms_stock");
			rs = pstmt.executeQuery();
			
			String stock_No = null;
			
			if (rs.next()) {
				stock_No = rs.getString("stock_no");
			}
			
			return stock_No;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 재고번호로 검색해서 재고 정보, 품목명, 창고명을 함께 가져오는 기능  / 在庫番号で検索して在庫情報、品目名、倉庫名を一緒に取得する機能
	public Stock selectByStockNo(Connection conn, String stockNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select rownum no, stock_no, b.item_nm as item_cd, qty, c.ware_nm as ware_cd, a.reg_ymd, a.descr\r\n"
					+ "from wms_stock a join wms_item b on a.item_cd = b.item_cd join wms_ware c on a.ware_cd = c.ware_cd \r\n"
					+ "where stock_no = ?");
			pstmt.setString(1, stockNo);
			rs = pstmt.executeQuery();
			
			Stock stock = null;

			if (rs.next()) {
				stock = new Stock(rs.getString("stock_no"), rs.getString("item_cd"), rs.getInt("qty"),
						rs.getString("ware_cd"), rs.getDate("reg_ymd"), rs.getString("descr"));
			}
			return stock;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 재고 신규 등록 / 在庫新規登録
	public void insert(Connection conn, Stock stock) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into wms_stock values(?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, stock.getStock_No()); // 품목코드 / 品目コード
			pstmt.setString(2, stock.getItem_Cd()); // 품목코드 / 品目コード
			pstmt.setInt(3, stock.getQty()); // 수량 / 数量
			pstmt.setString(4, stock.getWare_Cd()); // 창고코드 / 倉庫コード
			pstmt.setString(5, stock.getDescr()); // 등록일 / 登録日
			pstmt.setDate(6, new java.sql.Date(stock.getReg_Ymd().getTime())); // 등록일 / 登録日
			pstmt.executeUpdate();
		}
	}

	// 재고 수정 / 在庫修正
	public void update(Connection conn, Stock stock) throws SQLException {
		
		System.out.println(stock.getQty());
		System.out.println(stock.getWare_Cd());
		System.out.println(new java.sql.Date(stock.getReg_Ymd().getTime()));
		System.out.println(stock.getDescr());
		System.out.println(stock.getStock_No());
		
		String sql = "UPDATE wms_stock SET qty = ?, reg_ymd = ?, descr = ? WHERE stock_no = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, stock.getQty());
			pstmt.setDate(2, new java.sql.Date(stock.getReg_Ymd().getTime()));
			pstmt.setString(3, stock.getDescr());
			pstmt.setString(4, stock.getStock_No());
			pstmt.executeUpdate();
		}
	}
	
	// 재고 삭제 / 在庫削除
	public int delete(Connection conn, String stockNo) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_stock where stock_no=?");
			pstmt.setString(1, stockNo);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 컬럼 추가된 재고 조회 / カラム追加された在庫照会
	public StockPlus convertStockPlus(ResultSet rs) throws SQLException {
		return new StockPlus(rs.getString("no")
					 	   , rs.getString("stock_no")
					 	   , rs.getString("item_cd")
					 	   , rs.getString("item_nm")
					 	   , rs.getInt("qty")
					 	   , rs.getString("ware_cd")
					 	   , rs.getString("ware_nm")
					 	   , rs.getDate("reg_ymd")
					 	   , rs.getString("descr"));
	}
}
