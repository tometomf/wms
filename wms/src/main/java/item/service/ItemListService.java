package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import item.model.Item;

public class ItemListService {
	
	private ItemDao itemDao = new ItemDao();
	
	public List<Item> getItemList() {//itemテーブルのデータがListとして返される
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
	
	public Item getItemCd() {//PKを取得
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return itemDao.selectItemCd(conn); 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public Item selectByItemCd(String itemCd) {//PKで Item取得
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            return itemDao.selectByItemCd(conn, itemCd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
	
	public void insert(Item item) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			itemDao.insert(conn, new Item(
								item.getItemCd()
							  ,	item.getItemNm()
							  ,	item.getSpec()
							  ,	item.getItemGubun()
							  ,item.getUnit()
							  ,item.getUseYn()
							  ,item.getManufacturer()
							  ,item.getStorePrice()
							  ,item.getShipmentPrice())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void update(Item item) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			itemDao.update(conn, new Item(
							item.getItemCd(),
							item.getItemNm(),
							item.getSpec(),
							item.getItemGubun(),
							item.getUnit(),
							item.getUseYn(),
							item.getManufacturer(),
							item.getStorePrice(),
							item.getShipmentPrice())
			);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void delete(String itemCd) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
        	itemDao.delete(conn, itemCd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
}
