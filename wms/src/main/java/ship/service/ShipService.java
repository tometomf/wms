package ship.service;

import java.util.List;
import ship.dao.ShipDAO;
import ship.model.ShipViewModel;

public class ShipService {

    private ShipDAO shipDAO = new ShipDAO();

    // ship_master + ship_detail join 데이터 조회
    public List<ShipViewModel> getShipList() {
        return shipDAO.selectShipList();
    }
}