package porder.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;
import ware.model.Ware;
import ware.service.WareListService;

// 발주 목록을 보여주는 핸들러 클래스
// 発注一覧を表示するハンドラークラス
public class PorderHandler implements CommandHandler {

    // 발주 목록을 보여줄 JSP 경로
    // 発注一覧を表示するJSPのパス
    private static final String FORM_VIEW = "/WEB-INF/view/pOrderList.jsp";

    // 발주 관련 기능을 처리하는 서비스
    // 発注関連の処理を行うサービス
    private PorderService pOrderService = new PorderService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        
        // 서비스에서 발주 목록을 가져옴 (DB 조회)  / サービスから発注一覧を取得 (DB照会)
        List<Porder> pOrderList = pOrderService.select();

        // JSP에서 사용하도록 request 객체에 저장  /JSPで使えるようにrequestオブジェクトに保存
        request.setAttribute("pOrderList", pOrderList);

        // 발주 목록 화면(JSP)으로 이동 / 発注一覧画面(JSP)へ移動
        return FORM_VIEW;
    }
}
