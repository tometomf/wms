package store.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;

public class StoreListService {
	private StoreDao storeDao = new StoreDao();
	
	
		public List<Store> getStoreList(){
			Connection conn = null;
			 	try {
			 		conn = ConnectionProvider.getConnection();
			 		return storeDao.selectAll(conn);
			 	}	catch (SQLException e) {
			 		throw new RuntimeException(e);
			 	}	finally {
			 			JdbcUtil.close(conn);
			 	}
		}
	}	
