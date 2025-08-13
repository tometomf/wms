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
                "SELECT ship_no, ship_nm, item_cd, ship_price, ship_qty, " +
                "       ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn " +
                "  FROM wms_ship " +
                " ORDER BY ship_no"
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
        dto.setShipNo(rs.getInt("ship_no"));
        dto.setShipNm(rs.getString("ship_nm"));
        dto.setItemCd(rs.getString("item_cd"));
        dto.setShipPrice(rs.getInt("ship_price"));
        dto.setShipQty(rs.getInt("ship_qty"));
        dto.setShipDept(rs.getString("ship_dept"));
        dto.setShipUser(rs.getString("ship_user"));
        dto.setDescr(rs.getString("descr"));
        dto.setRegYmd(rs.getDate("reg_ymd"));
        dto.setUpdYmd(rs.getDate("upd_ymd"));
        dto.setShipYn(rs.getString("ship_yn"));
        return dto;
    }

    // 次の出荷番号を取得
    public ShipViewModel selectShipNo(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                "SELECT NVL(MAX(ship_no) + 1, 1) AS ship_no FROM wms_ship"
            );
            rs = pstmt.executeQuery();

            ShipViewModel ship = null;
            if (rs.next()) {
                ship = new ShipViewModel();
                ship.setShipNo(rs.getInt("ship_no")); 
            }

            return ship;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 出庫登録
    public int insert(Connection conn, ShipViewModel ship) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 出庫番号を生成
        	String seqSql = "SELECT NVL(MAX(ship_no) + 1, 1) AS ship_no FROM wms_ship";
        	pstmt = conn.prepareStatement(seqSql);
        	rs = pstmt.executeQuery();
        	int newShipNo = 0;
        	if (rs.next()) {
        	    newShipNo = rs.getInt("ship_no"); 
        	} else {
        	    throw new SQLException("出庫番号シーケンス作成失敗");
        	}
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);

            String insertSql = "INSERT INTO wms_ship " +
                "(ship_no, ship_nm, item_cd, ship_price, ship_qty, ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setInt(1, newShipNo);
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
    
    public ShipViewModel selectOne(Connection conn, int shipNo) throws SQLException {
        PreparedStatement ps = null; ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
              "select ship_no, ship_nm, item_cd, ship_price, ship_qty, " +
              "       ship_dept, ship_user, descr, reg_ymd, upd_ymd, ship_yn " +
              "  from wms_ship where ship_no=?");
            ps.setInt(1, shipNo);
            rs = ps.executeQuery();
            if (!rs.next()) return null;
            ShipViewModel v = new ShipViewModel();
            v.setShipNo(rs.getInt("ship_no"));
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
            pstmt = conn.prepareStatement("update wms_ship set ship_nm=?, item_cd=?, ship_price=?, ship_qty=?, ship_dept=?, ship_user=?, descr=?, ship_yn=? where ship_no=?");
            pstmt.setString(1, ship.getShipNm());
            pstmt.setString(2, ship.getItemCd());
            pstmt.setInt(3, ship.getShipPrice());
            pstmt.setInt(4, ship.getShipQty());
            pstmt.setString(5, ship.getShipDept());
            pstmt.setString(6, ship.getShipUser());
            pstmt.setString(7, ship.getDescr());
            pstmt.setString(8, ship.getShipYn());
            pstmt.setInt(9, ship.getShipNo());
            return pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
    
    
    public int delete(Connection conn, int shipNo) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("delete wms_ship where ship_no=?");
            pstmt.setInt(1, shipNo);
            return pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
}