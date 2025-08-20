package stock.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;
import stock.model.StockPlus;

//재고 서비스 클래스 / 在庫一覧のサービスクラス
public class StockService {

	private StockDao stockDao = new StockDao(); // DAO와 연결 / DAOを呼び出す準備

	// 재고 전체 조회
	public List<StockPlus> getSelectAll() { // 전체 재고 리스트를 가져오는 메서드 / 全在庫リストを取得するメソッド
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
	
	// 다음에 등록될 재고번호 조회 / 次に登録される在庫番号の照会
	public String getStockNo() {
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
	
	// 재고 등록 / 在庫登録
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
	            stock.getReg_Ymd(),
	            stock.getDescr()
	        ));
	        conn.commit();
	    } catch (SQLException e) {
	        JdbcUtil.rollback(conn);
	        throw new RuntimeException(e);
	    } finally {
	        JdbcUtil.close(conn);
	    }
	}
	// 재고번호로 데이터 조회 / 在庫番号でデータ照会
	public Stock getStockByNo(String stockNo) {
	    Connection conn = null;
	    try {
	        conn = ConnectionProvider.getConnection();
	        return stockDao.selectByStockNo(conn, stockNo);
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        JdbcUtil.close(conn);
	    }
	}

	// 재고 수정 / 在庫修正
	public void update(Stock stock) {
	    Connection conn = null;
	    try {
	        conn = ConnectionProvider.getConnection();
	        conn.setAutoCommit(false);

	        stockDao.update(conn, stock);

	        conn.commit();
	    } catch (SQLException e) {
	        JdbcUtil.rollback(conn);
	        throw new RuntimeException(e);
	    } finally {
	        JdbcUtil.close(conn);
	    }
	}

	// 재고 삭제 / 在庫削除
	public void delete(String stockNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
        	stockDao.delete(conn, stockNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	}

