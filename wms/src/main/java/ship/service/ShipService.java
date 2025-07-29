package ship.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import ship.dao.ShipDAO;
import ship.model.ShipViewModel;

public class ShipService {

    private ShipDAO shipDAO = new ShipDAO();

    public List<ShipViewModel> getShipList() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return shipDAO.selectAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}