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

    // GET: 등록 화면 요청
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        ShipViewModel ship = shipRegiService.getShipNo(); // 次の出庫番号を受け取る
        List<Item> itemList = itemService.getItemList();
        
        req.setAttribute("shipNo", ship); // JSPに渡して「input」valueとして使用
        req.setAttribute("itemList", itemList);
        
        return FORM_VIEW;
    }

    // POST:登録処理
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        // ユーザー入力値をモデルに変換
        ShipViewModel ship = createShipFromRequest(req);

        try {
            // 登録サービス実行
            int i = shipRegiService.register(ship);
            
            if (i == 1) {
            	// 登録成功後、alertを表示してlist.do に移動
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>");
                out.println("alert('登録できました。');");
                out.println("location.href='list.do';");
                out.println("</script>");
                out.close();
                return null;
            } else {
            	// 등록실패 시 alertを表示してinsert.do に移動
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

    // ユーザー入力→DTO変換
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