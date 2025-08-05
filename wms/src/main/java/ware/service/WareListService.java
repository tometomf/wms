package ware.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ware.dao.wareDao;
import ware.model.Ware;
import ware.model.WareStockDTO;

public class WareListService {
	
	private wareDao wareDao = new wareDao();
	
	public List<Ware> getWareList(){
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return wareDao.selectAll(conn);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
	
	public List<WareStockDTO> getWareStockList(){
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return wareDao.selectWareStock(conn);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
	
	// 창고코드 조회
	public Ware getWareCd() {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return wareDao.selectWareCd(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	// 창고 신규 등록
	public void insert(Ware ware) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			wareDao.insert(conn, new Ware(
								ware.getWareCd()
							  ,	ware.getWareNm()
							  ,	ware.getWareGubun()
							  ,	ware.getUseYn())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
