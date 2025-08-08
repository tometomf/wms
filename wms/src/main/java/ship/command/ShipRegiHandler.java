package ship.command;

import java.io.IOException;
import java.io.PrintWriter;
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
    public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // GET: 등록 화면 요청
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        ShipViewModel ship = shipRegiService.getShipNo(); // 다음 출고번호 받아오기
        req.setAttribute("shipNo", ship); // JSP에 넘겨서 <input> value로 사용
        return FORM_VIEW;
    }

    // POST: 등록 처리
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        // 사용자 입력값을 모델로 변환
        ShipViewModel ship = createShipFromRequest(req);

        try {
            // 등록 서비스 실행
            shipRegiService.register(ship);

            // 등록 성공 후 alert 띄우고 list.do로 이동
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('등록이 완료되었습니다.');");
            out.println("location.href='list.do';");
            out.println("</script>");
            out.close();
            return null;

        } catch (Exception e) {
            errors.put("registerFailed", Boolean.TRUE);
            return FORM_VIEW;
        }
    }

    // 사용자 입력 → DTO 변환
    private ShipViewModel createShipFromRequest(HttpServletRequest req) {
        ShipViewModel ship = new ShipViewModel();

        ship.setShipNm(req.getParameter("shipNm"));
        ship.setShipDept(req.getParameter("shipDept"));
        ship.setShipUser(req.getParameter("shipUser"));
        ship.setDescr(req.getParameter("descr"));
        ship.setItemCd(req.getParameter("itemCd"));
        ship.setShipYn(req.getParameter("shipYn"));

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

        return ship;
    }
}