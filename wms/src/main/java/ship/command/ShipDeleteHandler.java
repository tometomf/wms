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

        // 1) 파라미터 추출
        String shipNoParam = req.getParameter("shipNo");
        if (shipNoParam == null || shipNoParam.isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('출고번호가 없습니다.');");
            out.println("location.href='list.do';");
            out.println("</script>");
            out.close();
            return null;
        }

        // 2) int 변환 (shipNo가 숫자 PK이므로)
        int shipNo;
        try {
            shipNo = Integer.parseInt(shipNoParam.trim());
        } catch (NumberFormatException e) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('유효하지 않은 출고번호입니다.');");
            out.println("location.href='list.do';");
            out.println("</script>");
            out.close();
            return null;
        }

        // 3) 삭제 실행
        shipService.delete(shipNo);

        
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>");
        out.println("alert('삭제되었습니다.');");
        out.println("location.href='list.do';");
        out.println("</script>");
        out.close();
        return null;
    }
}