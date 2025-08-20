package porder.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import porder.service.PorderService;

public class PorderDeleteHandler implements CommandHandler {

    private PorderService pOrderService = new PorderService(); // 発注削除サービス
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        String purchase_No = req.getParameter("purchaseNo"); // 発注番号取得
        pOrderService.delete(purchase_No); // 削除実行

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>");
        out.println("alert('削除できました。');"); // 削除完了メッセージ
        out.println("location.href='list.do';"); // 一覧へ戻る
        out.println("</script>");
        out.close();

        return null; // スクリプトで遷移
    }
}