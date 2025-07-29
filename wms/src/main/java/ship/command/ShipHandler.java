package ship.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import mvc.command.CommandHandler;
import ship.model.ShipViewModel;
import ship.service.ShipService;

public class ShipHandler implements CommandHandler {

    private ShipService shipService = new ShipService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ship_master + ship_detail 조인 결과 조회
        List<ShipViewModel> shipList = shipService.getShipList();

        req.setAttribute("shipList", shipList);

        return "/WEB-INF/view/ship.jsp";
    }
}