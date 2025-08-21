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
        // サービス呼び出し(서비스 호출)
        List<ShipViewModel> shipList = new ShipService().getShipList();
        
        // request scopeに保存(에 보존)
        request.setAttribute("shipList", shipList);
        
        // ビュー·リターン(뷰 리턴)
        return "/WEB-INF/view/ship_list.jsp";
    }
}