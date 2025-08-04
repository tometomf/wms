package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

//재고 조회 서비스 클래스 / 在庫一覧のサービスクラス
public class StockService {

	private StockDao stockDao = new StockDao();	 // DAO와 연결 / DAOを呼び出す準備
	
	// 전체 재고 리스트를 가져오는 메서드 / 全在庫リストを取得するメソッド
	public List<Stock> getSelectAll() {

		List<Stock> content = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			return stockDao.selectAll(conn); // DAO 호출하여 데이터 가져오기 / DAOを通じてデータを取得
		} catch (SQLException e) {
			throw new RuntimeException(e);	// 예외 처리 / 例外処理
		}
		}
	}
