package ship.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ship.model.ShipViewModel;
import ship.service.ShipService;

public class ShipHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        // サービス呼び出し
        List<ShipViewModel> shipList = new ShipService().getShipList();

        // request scopeに保存
        request.setAttribute("shipList", shipList);

        // ビュー·リターン
        return "/WEB-INF/view/ship_list.jsp";
    }
}