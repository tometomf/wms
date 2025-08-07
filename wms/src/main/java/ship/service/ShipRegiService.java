package ship.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ship.dao.ShipDAO;
import ship.model.ShipViewModel;

public class ShipRegiService {

    private ShipDAO shipDao = new ShipDAO();

    public int register(ShipViewModel ship) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            int newShipNo = shipDao.insert(conn, ship);
            if (newShipNo == 0) {
                throw new RuntimeException("출고 등록 실패");
            }

            conn.commit();
            return newShipNo;

        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            JdbcUtil.rollback(conn);
            throw e;
        } finally {
            JdbcUtil.close(conn);
        }
    }
    
    
    public ShipViewModel getShipNo() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return shipDao.selectShipNo(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}