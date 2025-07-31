package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ItemListService {
	
	private ItemDao itemDao = new ItemDao();
	
	public List<Item> getItemList() {
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return itemDao.selectAll(conn);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
}
