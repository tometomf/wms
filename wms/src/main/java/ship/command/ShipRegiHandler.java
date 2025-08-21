package ship.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import ship.model.ShipViewModel;
import ship.service.ShipRegiService;

public class ShipRegiHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/ship_regist.jsp";
    private ShipRegiService shipRegiService = new ShipRegiService();
    private ItemListService itemService = new ItemListService();

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

    // GET: 登録画面リクエスト(등록 화면 요청)
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        ShipViewModel ship = shipRegiService.getShipNo(); // 次の出庫番号を受け取る(다음 출고 번호를 받는다)
        List<Item> itemList = itemService.getItemList();
        
        req.setAttribute("shipNo", ship); // JSPに渡して「input」valueとして使用(JSP에 전달하여 "input" value로 사용)
        req.setAttribute("itemList", itemList);
        
        return FORM_VIEW;
    }

    // POST:登録処理(등록 처리)
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        // ユーザー入力値をモデルに変換(사용자 입력 값을 모델로 변환)
        ShipViewModel ship = createShipFromRequest(req);

        try {
            // 登録サービス実行(등록 서비스 실행)
            int i = shipRegiService.register(ship);
            
            if (i == 1) {
            	// 登録成功後、alertを表示してlist.do に移動(등록 성공 후 alert 표시하여 list.do 이동)
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>");
                out.println("alert('登録できました。');");
                out.println("location.href='list.do';");
                out.println("</script>");
                out.close();
                return null;
            } else {
            	// 登録失敗時alertを表示してinsert.do に移動(등록실패시 alert표시하여 insert.do 으로 이동)
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>");
                out.println("alert('出庫量が在庫量より多いです。');");
                out.println("location.href='insert.do';");
                out.println("</script>");
                out.close();
                return null;
            }
        } catch (Exception e) {
            errors.put("registerFailed", Boolean.TRUE);
            return FORM_VIEW;
        }
    }

    // ユーザー入力→DTO変換(사용자입력→DTO변환)
    private ShipViewModel createShipFromRequest(HttpServletRequest req) {
        ShipViewModel ship = new ShipViewModel();

        ship.setShipNo(req.getParameter("shipNo"));
        ship.setShipNm(req.getParameter("shipNm"));
        ship.setItemCd(req.getParameter("itemCd"));
        ship.setShipDept(req.getParameter("shipDept"));
        ship.setShipUser(req.getParameter("shipUser"));
        ship.setShipYn(req.getParameter("shipYn"));
        ship.setDescr(req.getParameter("descr"));

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