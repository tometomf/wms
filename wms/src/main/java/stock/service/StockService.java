package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

//재고 서비스 클래스 / 在庫一覧のサービスクラス
public class StockService {

	private StockDao stockDao = new StockDao(); // DAO와 연결 / DAOを呼び出す準備

	// 재고 전체 조회
	public List<Stock> getSelectAll() { // 전체 재고 리스트를 가져오는 메서드 / 全在庫リストを取得するメソッド
		 Connection conn = null;

		try {
				conn = ConnectionProvider.getConnection();
			return stockDao.selectAll(conn); // DAO 호출하여 데이터 가져오기 / DAOを通じてデータを取得
		} catch (SQLException e) {
			throw new RuntimeException(e); // 예외 처리 / 例外処理
		   } finally {
	            JdbcUtil.close(conn);
		}
	}
	
	// 재고번호 조회
	public Stock getstockNo() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return stockDao.selectStockNo(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	//재고 등록
	public void insert(Stock stock) {
	    Connection conn = null;
	    try {
	        conn = ConnectionProvider.getConnection();
	        conn.setAutoCommit(false);

	        stockDao.insert(conn, new Stock(
	            stock.getStock_No(),
	            stock.getItem_Cd(),
	            stock.getQty(),
	            stock.getWare_Cd(),
	            stock.getReg_Ymd()
	        ));
	        conn.commit();
	    } catch (SQLException e) {
	        JdbcUtil.rollback(conn);
	        throw new RuntimeException(e);
	    } finally {
	        JdbcUtil.close(conn);
	    }
	}
	}

