package ship.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import ship.model.ShipViewModel;

public class ShipDAO {

    // 全リスト照会
    public List<ShipViewModel> selectAll(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                "SELECT rownum no, ship_no, ship_nm, a.item_cd, b.item_nm, ship_qty, ship_price, ship_dept, ship_user\r\n"
                + "     , case when ship_yn = 'Y' then '出庫完了' else '未出庫' end as ship_yn, descr, reg_ymd\r\n"
                + "FROM wms_ship a join wms_item b on a.item_cd = b.item_cd\r\n"
                + "ORDER BY ship_no"
            );

            rs = pstmt.executeQuery();
            List<ShipViewModel> list = new ArrayList<>();
            while (rs.next()) {
                list.add(makeShipFromResultSet(rs));
            }

            return list;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 照会結果をDTOに変換
    private ShipViewModel makeShipFromResultSet(ResultSet rs) throws SQLException {
        
    	ShipViewModel dto = new ShipViewModel();
        
        dto.setNo(rs.getString("no"));
        dto.setShipNo(rs.getString("ship_no"));
        dto.setShipNm(rs.getString("ship_nm"));
        dto.setItemCd(rs.getString("item_cd"));
        dto.setItemNm(rs.getString("item_nm"));
        dto.setShipQty(rs.getInt("ship_qty"));
        dto.setShipPrice(rs.getInt("ship_price"));
        dto.setShipDept(rs.getString("ship_dept"));
        dto.setShipUser(rs.getString("ship_user"));
        dto.setShipYn(rs.getString("ship_yn"));
        dto.setDescr(rs.getString("descr"));
        dto.setRegYmd(rs.getTimestamp("reg_ymd"));
        
        return dto;
    }

    // 次の出荷番号を取得
    public ShipViewModel selectShipNo(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                "select 'D' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(ship_no, 2))), 0) + 1), 3, '0') AS ship_no from wms_ship"
            );
            rs = pstmt.executeQuery();

            ShipViewModel ship = null;
            if (rs.next()) {
                ship = new ShipViewModel();
                ship.setShipNo(rs.getString("ship_no")); 
            }

            return ship;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 出庫登録
    public String insert(Connection conn, ShipViewModel ship) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 出庫番号を生成
        	String seqSql = "select 'D' || LPAD(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(ship_no, 2))), 0) + 1), 3, '0') AS ship_no from wms_ship";
        	pstmt = conn.prepareStatement(seqSql);
        	rs = pstmt.executeQuery();
        	String newShipNo = "";
        	if (rs.next()) {
        	    newShipNo = rs.getString("ship_no"); 
        	} else {
        	    throw new SQLException("出庫番号シーケンス作成失敗");
        	}
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);

            String insertSql = "INSERT INTO wms_ship " +
                "(ship_no, ship_nm, item_cd, ship_price, ship_qty, ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, newShipNo);
            pstmt.setString(2, ship.getShipNm());
            pstmt.setString(3, ship.getItemCd());
            pstmt.setInt(4, ship.getShipPrice());
            pstmt.setInt(5, ship.getShipQty());
            pstmt.setString(6, ship.getShipDept());
            pstmt.setString(7, ship.getShipUser());
            pstmt.setString(8, ship.getDescr());
            pstmt.setString(9, ship.getShipYn());

            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new SQLException("出庫登録失敗");
            }

            return newShipNo;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
    
    public ShipViewModel selectOne(Connection conn, String shipNo) throws SQLException {
        PreparedStatement ps = null; ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
              "select ship_no, ship_nm, b.item_nm as item_cd, ship_price, ship_qty, " +
              "       ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn " +
              " from wms_ship a join wms_item b on a.item_cd = b.item_cd" + 
              " where ship_no = ?");
            ps.setString(1, shipNo);
            rs = ps.executeQuery();
            if (!rs.next()) return null;
            ShipViewModel v = new ShipViewModel();
            v.setShipNo(rs.getString("ship_no"));
            v.setShipNm(rs.getString("ship_nm"));
            v.setItemCd(rs.getString("item_cd"));
            v.setShipPrice(rs.getInt("ship_price"));
            v.setShipQty(rs.getInt("ship_qty"));
            v.setShipDept(rs.getString("ship_dept"));
            v.setShipUser(rs.getString("ship_user"));
            v.setDescr(rs.getString("descr"));
            v.setRegYmd(rs.getDate("reg_ymd"));
            v.setUpdYmd(rs.getDate("upd_ymd"));
            v.setShipYn(rs.getString("ship_yn"));
            return v;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }
    }
    
    
    public int update(Connection conn, ShipViewModel ship) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("update wms_ship set ship_nm=?, ship_price=?, ship_qty=?, ship_dept=?, ship_user=?, descr=?, ship_yn=?, upd_ymd = sysdate where ship_no=?");
            pstmt.setString(1, ship.getShipNm());
            pstmt.setInt(2, ship.getShipPrice());
            pstmt.setInt(3, ship.getShipQty());
            pstmt.setString(4, ship.getShipDept());
            pstmt.setString(5, ship.getShipUser());
            pstmt.setString(6, ship.getDescr());
            pstmt.setString(7, ship.getShipYn());
            pstmt.setString(8, ship.getShipNo());
            return pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
    
    
    public int delete(Connection conn, String shipNo) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("delete wms_ship where ship_no=?");
            pstmt.setString(1, shipNo);
            return pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
}