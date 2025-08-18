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
    private final ShipRegiService shipService = new ShipRegiService(); // ← サービスタイプ/名前一致

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

    // 修正フォーム表示
    private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
    	String shipNoParam = req.getParameter("shipNo");

        if (shipNoParam == null || shipNoParam.isEmpty()) {
        	
            res.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = res.getWriter()) {
                out.println("<script>alert('出庫番号がありません'); location.href='list.do';</script>");
            }
            return null;
        }

        // String으로 바꿔서 필요 X
//        int shipNo;
//        try {
//            shipNo = Integer.parseInt(shipNoParam); 
//        } catch (NumberFormatException e) {
//            res.setContentType("text/html; charset=UTF-8");
//            try (PrintWriter out = res.getWriter()) {
//                out.println("<script>alert('出庫番号の形式が正しくありません'); location.href='list.do';</script>");
//            }
//            return null;
//        }

        ShipViewModel s = shipService.selectOne(shipNoParam); 
        req.setAttribute("ship", s);
        return FORM_VIEW;
    }

    // 修正処理
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");

        ShipViewModel ship = new ShipViewModel();
        ship.setShipNo(req.getParameter("shipNo")); 
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
                out.println("<script>alert('修正が完了しました'); location.href='list.do';</script>");
            }
            return null;
        } catch (RuntimeException e) { 
            req.setAttribute("errors", Map.of("dbError", Boolean.TRUE));
            return FORM_VIEW;
        }
    }
}