package ship.command;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ship.model.ShipViewModel;
import ship.service.ShipRegiService; // ← 서비스 클래스 이름 맞춤

public class ShipUpdateHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/ship_update.jsp";
    private final ShipRegiService shipService = new ShipRegiService(); // ← 서비스 타입/이름 일치

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET"))  {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // 수정 폼 표시
    private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String shipNoParam = req.getParameter("shipNo");
        if (shipNoParam == null || shipNoParam.isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = res.getWriter()) {
                out.println("<script>alert('출고번호가 없습니다.'); location.href='list.do';</script>");
            }
            return null;
        }

        int shipNo;
        try {
            shipNo = Integer.parseInt(shipNoParam); 
        } catch (NumberFormatException e) {
            res.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = res.getWriter()) {
                out.println("<script>alert('출고번호 형식이 올바르지 않습니다.'); location.href='list.do';</script>");
            }
            return null;
        }

        ShipViewModel s = shipService.selectOne(shipNo); 
        req.setAttribute("ship", s);
        return FORM_VIEW;
    }

    // 수정 처리
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");

        ShipViewModel ship = new ShipViewModel();
        ship.setShipNo(Integer.parseInt(req.getParameter("shipNo"))); 
        ship.setShipNm(req.getParameter("shipNm"));
        ship.setItemCd(req.getParameter("itemCd"));
        ship.setShipPrice(Integer.parseInt(req.getParameter("shipPrice")));
        ship.setShipQty(Integer.parseInt(req.getParameter("shipQty")));
        ship.setShipDept(req.getParameter("shipDept"));
        ship.setShipUser(req.getParameter("shipUser"));
        ship.setDescr(req.getParameter("descr"));
        ship.setShipYn(req.getParameter("shipYn"));

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        try {
            shipService.update(ship);
            res.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = res.getWriter()) {
                out.println("<script>alert('수정이 완료되었습니다.'); location.href='list.do';</script>");
            }
            return null;
        } catch (RuntimeException e) { 
            req.setAttribute("errors", Map.of("dbError", Boolean.TRUE));
            return FORM_VIEW;
        }
    }
}