package ship.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ship.service.ShipRegiService;

public class ShipDeleteHandler implements CommandHandler {

    private final ShipRegiService shipService = new ShipRegiService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // パラメータ抽出
        String shipNoParam = req.getParameter("shipNo");
        
        if (shipNoParam == null || shipNoParam.isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('出庫番号がありません');");
            out.println("location.href='list.do';");
            out.println("</script>");
            out.close();
            return null;
        }

        // int変換（shipNoが数字PKなので）
//        int shipNo;
//        try {
//            shipNo = Integer.parseInt(shipNoParam.trim());
//        } catch (NumberFormatException e) {
//            res.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = res.getWriter();
//            out.println("<script>");
//            out.println("alert('無効な出庫番号です');");
//            out.println("location.href='list.do';");
//            out.println("</script>");
//            out.close();
//            return null;
//        }

        // 削除実行
        shipService.delete(shipNoParam);
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>");
        out.println("alert('削除できました。');");
        out.println("location.href='list.do';");
        out.println("</script>");
        out.close();
        return null;
    }
}