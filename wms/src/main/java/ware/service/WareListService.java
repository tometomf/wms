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
}
