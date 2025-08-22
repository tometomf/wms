package porder.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import porder.dao.PorderDao;
import porder.model.Porder;

// PorderService: 비즈니스 로직을 처리하는 서비스 클래스 / PorderService: ビジネスロジックを処理するサービスクラス
public class PorderService {
	
	private PorderDao porderDao = new PorderDao(); 
	// DAO 객체  / DAOオブジェクト 
	
	// 전체 발주 목록 가져오기 / 発注リストを全部取得
	public List<Porder> select(){
		Connection conn = null;
	    try {
	        conn = ConnectionProvider.getConnection(); // DB 연결 / DB接続
	        return porderDao.select(conn); // DAO에 조회 시킴 / DAOに検索させる
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        JdbcUtil.close(conn); // 연결 닫기 / 接続を閉じる
	    }
	}
	
	// 새 발주번호 만들기 / 新しい発注番号を作成
	public Porder getPurchaseNo() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return porderDao.getPurchaseNo(conn); // DAO한테 번호 받아오기
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 특정 발주번호로 검색 / 発注番号で検索
	public Porder selectByPurchaseNo(String purchaseNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return porderDao.selectByPurchaseNo(conn, purchaseNo); // DAO한테 요청
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 발주 등록하기 / 発注を登録する
	public void insert(Porder pOrder) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 자동 커밋 끄기 / 自動コミットをOFF

			// DAO에 실제 등록 시킴 / DAOに登録を実行させる
			porderDao.insert(conn, new Porder(
				null,
				pOrder.getPurchase_No(),
				pOrder.getPurchase_Nm(),
				pOrder.getItem_Cd(),
				null, 		// item_nm은 DAO에서 join으로 가져오기 때문에 null
				pOrder.getQty(),
				pOrder.getPurchase_Dept(),
				pOrder.getPurchase_User(),
				pOrder.getDescr(),
				pOrder.getReg_Ymd()
			));

			conn.commit(); // 성공하면 저장 확정 / 成功ならコミット
		} catch (SQLException e) {
			JdbcUtil.rollback(conn); // 실패하면 되돌리기 / 失敗ならロールバック
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 발주 수정하기 / 発注を修正する
	public void update(Porder pOrder) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 수정할 내용 DAO에 전달 / 修正内容をDAOに渡す
			porderDao.update(conn, new Porder(
				null,
				pOrder.getPurchase_No(),
				pOrder.getPurchase_Nm(),
				null,
				null, 				// item_cd, item_nm은 수정 안함 / 修正しない
				pOrder.getQty(),
				pOrder.getPurchase_Dept(),
				pOrder.getPurchase_User(),
				pOrder.getDescr(),
				pOrder.getReg_Ymd()
			));

			conn.commit(); 
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 발주 삭제하기 / 発注を削除する
	public void delete(String purchaseNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            porderDao.delete(conn, purchaseNo); // DAO에게 삭제 시킴
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
}
