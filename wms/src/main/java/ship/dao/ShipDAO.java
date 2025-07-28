package ship.dao;

import java.sql.*;
import java.util.*;

import ship.model.ShipViewModel;
import jdbc.connection.ConnectionProvider;

public class ShipDAO {

    // 마스터 + 디테일 조인 조회
    public List<ShipViewModel> selectShipList() {
        List<ShipViewModel> list = new ArrayList<>();

        String sql = """
            SELECT a.ship_no, a.ship_nm, a.ship_dept, a.ship_user, a.descr,
                   a.reg_ymd, a.upd_ymd, a.ship_yn,
                   b.item_cd, b.ship_price, b.ship_qty, b.ship_gubun
              FROM wms_ship_master a
              JOIN wms_ship_detail b ON a.ship_no = b.ship_no
              ORDER BY a.ship_no, b.item_cd
        """;

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}