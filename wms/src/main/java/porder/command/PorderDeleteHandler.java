package porder.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import porder.service.PorderService;

// 발주 삭제를 담당하는 핸들러 클래스
// 発注削除を担当するハンドラークラス
public class PorderDeleteHandler implements CommandHandler {

    // 발주 관련 서비스 객체 생성 / 発注関連のサービスオブジェクトを生成
    private PorderService pOrderService = new PorderService(); 
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        // 요청 파라미터에서 발주번호 가져오기 / リクエストパラメータから発注番号を取得 
        String purchase_No = req.getParameter("purchaseNo"); 
        
        // 서비스에 삭제 실행을 요청 / サービスに削除実行を依頼
        pOrderService.delete(purchase_No); 

        // 응답을 HTML로 보내고, 알림창을 띄운 뒤 목록으로 이동 / レスポンスをHTMLで返し、アラートを出して一覧へ移動
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>");
        out.println("alert('削除できました。');"); // 삭제 완료 메시지 / 削除完了メッセージ
        out.println("location.href='list.do';"); // 목록으로 돌아가기 / 一覧へ戻る
        out.println("</script>");
        out.close();

        return null; 
    }
}
