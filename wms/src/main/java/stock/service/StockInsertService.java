package stock.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import stock.dao.StockDao;
import stock.model.Stock;

// 재고 등록 서비스 클래스 / 在庫登録サービスクラス
public class StockInsertService {

    // DAO와 연결할 객체 준비 / DAOと接続するオブジェクト
    private StockDao stockDao = new StockDao();

    // 재고 등록을 처리하는 메서드 / 在庫登録を処理するメソッド
    public boolean insert(Stock stock) {
        try (
        		Connection conn = ConnectionProvider.getConnection()) {
            // DAO의 insert 메서드를 호출하여 DB에 저장 시도 / DAOのinsertメソッドでDBに保存
            int result = stockDao.insert(conn, stock);

            // insert 결과가 1 이상이면 성공(1건 이상 등록) / 1件以上なら成功
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 예외 발생 시 실패 처리 / 例外発生時は失敗
        }
    }
}
