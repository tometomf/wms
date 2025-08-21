package index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import index.model.ShipExpect;
import index.model.StoreExpect;
import index.model.WareItem;
import jdbc.JdbcUtil;

public class IndexDao {
	
	//itemテーブルの全データをListとして返します
	public List<WareItem> select2(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select item_cd, item_nm, ware_cd, ware_nm, sum(qty) as qty\r\n"
					+ "from (\r\n"
					+ "    select a.item_cd, b.item_nm, a.ware_cd, \r\n"
					+ "           (select ware_nm from wms_ware where a.ware_cd = ware_cd) as ware_nm, a.qty\r\n"
					+ "    from wms_stock a, wms_item b\r\n"
					+ "    where a.item_cd = b.item_cd\r\n"
					+ ") a\r\n"
					+ "group by item_cd, item_nm, ware_cd, ware_nm");
			rs = pstmt.executeQuery();//クエリを実行
			List<WareItem> wareItemList = new ArrayList<>();
			while (rs.next()) {
				wareItemList.add(wareItemListConvert(rs)); 
			}
			return wareItemList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<StoreExpect> select3(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select a.item_cd, b.item_nm, a.qty as item_qty, b.manufacturer, b.store_price, to_char(a.reg_ymd, 'yyyy-MM-dd') as reg_ymd\r\n"
					+ "from wms_store a, wms_item b\r\n"
					+ "where a.item_cd = b.item_cd");
			rs = pstmt.executeQuery();//クエリを実行
			List<StoreExpect> storeExpect = new ArrayList<>();
			while (rs.next()) {
				storeExpect.add(storeExpectListConvert(rs)); 
			}
			return storeExpect;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<ShipExpect> select4(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select a.item_cd, b.item_nm, a.ship_qty, b.manufacturer, a.ship_price, to_char(a.reg_ymd, 'yyyy-MM-dd') as reg_ymd\r\n"
					+ "from wms_ship a, wms_item b\r\n"
					+ "where a.item_cd = b.item_cd");
			rs = pstmt.executeQuery();//クエリを実行
			List<ShipExpect> shipExpect = new ArrayList<>();
			while (rs.next()) {
				shipExpect.add(shipExpectListConvert(rs)); 
			}
			return shipExpect;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
		
	private WareItem wareItemListConvert(ResultSet rs) throws SQLException {
		return new WareItem(rs.getString("item_cd"),rs.getString("item_nm"),rs.getString("ware_cd"),rs.getString("ware_nm"),rs.getString("qty"));
	}
	
	private StoreExpect storeExpectListConvert(ResultSet rs) throws SQLException {
		return new StoreExpect(rs.getString("item_cd")
					 		 , rs.getString("item_nm")
					 		 , rs.getString("item_qty")
					 		 , rs.getString("manufacturer")
					 		 , rs.getString("store_price")
					 		 , rs.getString("reg_ymd"));
	}
	
	private ShipExpect shipExpectListConvert(ResultSet rs) throws SQLException {
		return new ShipExpect(rs.getString("item_cd")
					 	 	, rs.getString("item_nm")
					 	 	, rs.getString("ship_qty")
					 	 	, rs.getString("manufacturer")
					 	 	, rs.getString("ship_price")
					 	 	, rs.getString("reg_ymd"));
	}
}
