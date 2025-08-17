package porder.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import porder.dao.PorderDao;
import porder.model.Porder;

public class PorderService {
	
	private PorderDao porderDao = new PorderDao();
	
	public List<Porder> select(){
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return porderDao.select(conn);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
	
	// 발주코드 조회
	public Porder getPurchaseNo() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return porderDao.getPurchaseNo(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 창고 마스터 조회
	public Porder selectByPurchaseNo(String purchaseNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return porderDao.selectByPurchaseNo(conn, purchaseNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 창고 신규 등록
	public void insert(Porder pOrder) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			porderDao.insert(conn, new Porder(
								null
							  ,	pOrder.getPurchase_No()
							  ,	pOrder.getPurchase_Nm()
							  ,	pOrder.getItem_Cd()
							  , null
							  ,	pOrder.getQty()
							  ,	pOrder.getPurchase_Dept()
							  ,	pOrder.getPurchase_User()
							  ,	pOrder.getDescr()
							  ,	pOrder.getReg_Ymd()));
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 창고 수정
	public void update(Porder pOrder) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			porderDao.update(conn, new Porder(
							null
						  ,	pOrder.getPurchase_No()
						  ,	pOrder.getPurchase_Nm()
						  ,	null
						  , null
						  ,	pOrder.getQty()
						  ,	pOrder.getPurchase_Dept()
						  ,	pOrder.getPurchase_User()
						  ,	pOrder.getDescr()
						  ,	pOrder.getReg_Ymd()));
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 창고 마스터 삭제
	public void delete(String purchaseNo) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            porderDao.delete(conn, purchaseNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
}
