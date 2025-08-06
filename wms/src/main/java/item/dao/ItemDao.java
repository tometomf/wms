package item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import item.model.Item;
import jdbc.JdbcUtil;

public class ItemDao {
	public int insert(Connection conn, Item item) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into wms_item (item_cd, item_nm, spec, item_gubun, unit, use_yn, manufacturer, store_price, shipment_price) values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, item.getItemCd());
			pstmt.setString(2, item.getItemNm());
			pstmt.setString(3, item.getSpec());
			pstmt.setString(4, item.getItemGubun());
			pstmt.setString(5, item.getUnit());
			pstmt.setString(6, item.getUseYn());			
			pstmt.setString(7, item.getManufacturer());
			pstmt.setInt(8, item.getStorePrice());
			pstmt.setInt(9, item.getShipmentPrice());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Item> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select*from wms_item");
			rs = pstmt.executeQuery();
			List<Item> itemList = new ArrayList<>();
			while (rs.next()) {
				itemList.add(makeItemFromResultSet(rs)); 
			}
			return itemList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Item selectItemCd(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select 'i' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(item_cd, 2))), 0) + 1), 3, '0') AS next_item_cd from wms_item");
			rs = pstmt.executeQuery();
			Item item = null;
			
			if (rs.next()) {
				item = new Item(
						rs.getString("next_item_cd")
					  , null
					  , null
					  , null
					  , null
					  , null
					  , null
					  , 0
					  , 0);
			}
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, Item item) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update wms_item set item_cd=?, item_nm=?, spec=?, item_gubun=?, unit=?, use_yn=?, manufacturer=?, store_price=?, shipment_price=?");
			pstmt.setString(1, item.getItemCd());
			pstmt.setString(2, item.getItemNm());
			pstmt.setString(4, item.getSpec());
			pstmt.setString(5, item.getItemGubun());
			pstmt.setString(6, item.getUnit());
			pstmt.setString(7, item.getManufacturer());
			pstmt.setInt(8, item.getStorePrice());
			pstmt.setInt(9, item.getShipmentPrice());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, String itemCd) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete wms_item where item_cd=?");
			pstmt.setString(1, itemCd);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Item makeItemFromResultSet(ResultSet rs) throws SQLException {
		return new Item(rs.getString("item_cd"),rs.getString("item_nm"),rs.getString("spec"),rs.getString("item_gubun"),rs.getString("unit"),rs.getString("use_yn"),rs.getString("manufacturer"),rs.getInt("store_price"),rs.getInt("shipment_price"));
	}
}
