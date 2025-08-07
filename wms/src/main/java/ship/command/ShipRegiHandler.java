package ship.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ship.model.ShipViewModel;
import ship.service.ShipRegiService;

public class ShipRegiHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/ship_regist.jsp";
    private ShipRegiService shipRegiService = new ShipRegiService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        ShipViewModel ship = shipRegiService.getShipNo(); // 출고번호 포함 객체
        req.setAttribute("shipNo", ship);                 // JSP로 넘김
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        // 사용자가 입력한 값들 받아서 ShipViewModel에 담기
        ShipViewModel ship = createShipFromRequest(req);


        // 등록 처리
        int newShipNo = shipRegiService.register(ship);
        req.setAttribute("newShipNo", newShipNo);

        return "/WEB-INF/view/ship_regist_success.jsp";
    }

    private ShipViewModel createShipFromRequest(HttpServletRequest req) {
        ShipViewModel ship = new ShipViewModel();

        ship.setShipNm(req.getParameter("shipNm"));
        ship.setShipDept(req.getParameter("shipDept"));
        ship.setShipUser(req.getParameter("shipUser"));
        ship.setDescr(req.getParameter("descr"));
        ship.setItemCd(req.getParameter("itemCd"));

        // 숫자 파싱은 try-catch로 안전하게
        try {
            ship.setShipPrice(Integer.parseInt(req.getParameter("shipPrice")));
        } catch (NumberFormatException e) {
            ship.setShipPrice(0);
        }

        try {
            ship.setShipQty(Integer.parseInt(req.getParameter("shipQty")));
        } catch (NumberFormatException e) {
            ship.setShipQty(0);
        }

        ship.setShipGubun(req.getParameter("shipGubun"));

        // 나머지 값들 (SHIP_NO, REG_YMD 등)은 DB에서 자동 처리
        return ship;
    }
}