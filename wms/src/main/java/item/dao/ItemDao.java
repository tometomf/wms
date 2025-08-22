package item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	//全体取得
	// item테이블 안의 데이터를 List에 넣어서 모두 반환함.
	// itemテーブルの全データをListとして返します
	public List<Item> selectAllByItemNm(Connection conn, String itemCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// null 공백 치환
		String value = Objects.toString(itemCd, "");
		
		try {
			pstmt = conn.prepareStatement(
					"select rownum no, item_cd, item_nm, spec, item_gubun, unit, use_yn, manufacturer, store_price, shipment_price from wms_item where item_nm like ?");
			pstmt.setString(1, "%" + value + "%"); // Java에서 % 붙이기
			rs = pstmt.executeQuery();//쿼리 실행하는 부분 クエリを実行
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
	
	public List<Item> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select rownum no, item_cd, item_nm, spec, item_gubun, unit, use_yn, manufacturer, store_price, shipment_price from wms_item");
			rs = pstmt.executeQuery();//쿼리 실행하는 부분 クエリを実行
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
	
	//PK를 가져오는 메서드
	//PKを取得する
	public Item selectItemCd(Connection conn) throws SQLException {//PK PKを取得する
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select 'I' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(item_cd, 2))), 0) + 1), 3, '0') AS next_item_cd from wms_item");
			rs = pstmt.executeQuery();
			Item item = null;

			if (rs.next()) {
				item = new Item(rs.getString("next_item_cd"), null, null, null, null, null, null, 0, 0);
			}
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//PK를 받아 Item을 출력하는 메서드
	// PKで Item取得
	public Item selectByItemCd(Connection conn, String itemCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT item_cd, item_nm, spec, item_gubun, unit, use_yn, manufacturer, store_price, shipment_price FROM wms_item WHERE item_cd = ?");
			pstmt.setString(1, itemCd);
			rs = pstmt.executeQuery();
			Item item = null;

			if (rs.next()) {
				item = new Item(rs.getString("item_cd"), rs.getString("item_nm"), rs.getString("spec"),
						rs.getString("item_gubun"), rs.getString("unit"), rs.getString("use_yn"),
						rs.getString("manufacturer"), rs.getInt("store_price"), rs.getInt("shipment_price"));
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
			pstmt = conn.prepareStatement(
					"update wms_item set item_nm=?, spec=?, item_gubun=?, unit=?, use_yn=?, manufacturer=?, store_price=?, shipment_price=? where item_cd=?");
			pstmt.setString(1, item.getItemNm());
			pstmt.setString(2, item.getSpec());
			pstmt.setString(3, item.getItemGubun());
			pstmt.setString(4, item.getUnit());
			pstmt.setString(5, item.getUseYn());
			pstmt.setString(6, item.getManufacturer());
			pstmt.setInt(7, item.getStorePrice());
			pstmt.setInt(8, item.getShipmentPrice());
			pstmt.setString(9, item.getItemCd());
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
		} catch (SQLIntegrityConstraintViolationException  e) {
            // throw new RuntimeException("データを削除できません。", e); 
            return 0;
        } finally {
			JdbcUtil.close(pstmt);
		}
	}

	//Resultset형태를 Item으로 변환하는 메서드
	//Resultset形のデータをItemインスタンスに変換
	private Item makeItemFromResultSet(ResultSet rs) throws SQLException {
		return new Item(rs.getString("no"),rs.getString("item_cd"), rs.getString("item_nm"), rs.getString("spec"),
				rs.getString("item_gubun"), rs.getString("unit"), rs.getString("use_yn"), rs.getString("manufacturer"),
				rs.getInt("store_price"), rs.getInt("shipment_price"));
	}
}
