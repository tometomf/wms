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

    public List<ShipViewModel> selectAll(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                "SELECT a.ship_no, a.ship_nm, a.ship_dept, a.ship_user, a.descr, " +
                "       a.reg_ymd, a.upd_ymd, a.ship_yn, " +
                "       b.item_cd, b.ship_price, b.ship_qty, b.ship_gubun " +
                "  FROM wms_ship_master a " +
                "  JOIN wms_ship_detail b ON a.ship_no = b.ship_no " +
                " ORDER BY a.ship_no, b.item_cd"
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

    private ShipViewModel makeShipFromResultSet(ResultSet rs) throws SQLException {
        ShipViewModel dto = new ShipViewModel();
        dto.setShipNo(rs.getInt("ship_no"));
        dto.setShipNm(rs.getString("ship_nm"));
        dto.setShipDept(rs.getString("ship_dept"));
        dto.setShipUser(rs.getString("ship_user"));
        dto.setDescr(rs.getString("descr"));
        dto.setRegYmd(rs.getDate("reg_ymd"));
        dto.setUpdYmd(rs.getDate("upd_ymd"));
        dto.setShipYn(rs.getString("ship_yn"));
        dto.setItemCd(rs.getString("item_cd"));
        dto.setShipPrice(rs.getInt("ship_price"));
        dto.setShipQty(rs.getInt("ship_qty"));
        dto.setShipGubun(rs.getString("ship_gubun"));
        return dto;
    }
}