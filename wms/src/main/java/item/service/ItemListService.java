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
	
	//SelectAll로 가져온 DB의 item테이블의 데이터를 List로 변환
	//SelectAllで取得したitemテーブルのデータをListとして変換
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
	
	public List<Item> getItemListByItemCd(String itemCd) {
		 Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();
	            return itemDao.selectAllByItemCd(conn, itemCd); 
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            JdbcUtil.close(conn);
	        }
	}
	
	//PK를 가져옴
	//PKを取得
	public Item getItemCd() {
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
	
	//PK로 Item을 가져옴
	//PKで Item取得
	public Item selectByItemCd(String itemCd) {
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
	
	public int delete(String itemCd) {
		Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
        	return itemDao.delete(conn, itemCd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
	}
}
