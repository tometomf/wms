package stock.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import stock.model.Stock;
import stock.service.StockService;
import ware.model.Ware;
import ware.service.WareListService;

public class StockInsertHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/stockForm.jsp";
    private StockService insertService = new StockService();
    private ItemListService itemService = new ItemListService();
    private WareListService wareService = new WareListService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // GET 요청이면 등록 폼 화면 표시 / GETリクエストなら登録フォーム画面を表示
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        // POST 요청이면 등록 처리 / POSTリクエストなら登録処理
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // 등록 폼으로 이동 / 登録フォームへ移動
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        String stock = insertService.getStockNo(); // 신규 재고번호 조회 / 新しい在庫番号を取得
        List<Item> itemList = itemService.getItemList();
        List<Ware> wareList = wareService.select();
        
        req.setAttribute("stockNo", stock);
        req.setAttribute("itemList", itemList);
        req.setAttribute("wareList", wareList);
        
        return FORM_VIEW;
    }

    // 등록 처리 / 登録処理
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

//        Map<String, Boolean> errors = new HashMap<>();
//        req.setAttribute("errors", errors);
    	
        Stock stock = new Stock();

        // 필드 세팅 / その他のフィールドを設定
        stock.setStock_No(req.getParameter("stockNo"));
        stock.setItem_Cd(req.getParameter("itemCd"));
        stock.setWare_Cd(req.getParameter("wareCd"));
        stock.setDescr(req.getParameter("descr"));
        
        // ===== 필수값 유효성 체크 / 必須項目のバリデーション =====
        String qtyStr = req.getParameter("qty");
        String regYmdStr = req.getParameter("regYmd");

//        if (itemCd == null || itemCd.trim().isEmpty()) {
//            errors.put("itemCd", true); // 품목코드 누락 / 品目コード未入力
//        }
//        if (qtyStr == null || qtyStr.trim().isEmpty()) {
//            errors.put("qty", true); // 재고수량 누락 / 在庫数量未入力
//        }
//        if (wareCd == null || wareCd.trim().isEmpty()) {
//            errors.put("wareCd", true); // 창고코드 누락 / 倉庫コード未入力
//        }
//        if (regYmdStr == null || regYmdStr.trim().isEmpty()) {
//            errors.put("regYmd", true); // 등록일 누락 / 登録日未入力
//        }
//
//        // 필수값 누락 시 다시 폼으로 이동 + 재고번호 세팅 / 必須項目が欠けている場合、フォームへ戻る＋在庫番号再設定
//        if (!errors.isEmpty()) {
//            req.setAttribute("stockNo", insertService.getStockNo());
//            return FORM_VIEW;
//        }

        // ===== 숫자 변환 + 음수 방지 / 数値変換＋マイナス防止 =====
        try {
            int qty = Integer.parseInt(qtyStr.trim());
            if (qty < 0) {
                // errors.put("invalidQtyNegative", true); // 수량이 음수인 경우 / 数量がマイナスの場合
                req.setAttribute("stockNo", insertService.getStockNo());
                System.out.println("a");
                return FORM_VIEW;
            }
            stock.setQty(qty);
        } catch (NumberFormatException e) {
            // errors.put("invalidNumber", true); // 숫자 형식 오류 / 数値形式エラー
            req.setAttribute("stockNo", insertService.getStockNo());
            System.out.println("b");
            return FORM_VIEW;
        }
        
        // ===== 날짜 형식 변환 / 日付形式変換 =====
        try {
            stock.setReg_Ymd(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(regYmdStr));
        } catch (ParseException e) {
            // errors.put("invalidDate", Boolean.TRUE); // 날짜 형식 오류 / 日付形式エラー
            req.setAttribute("stockNo", insertService.getStockNo());
            System.out.println("c");
            return FORM_VIEW;
        }
        
        try {
            // ===== 등록 완료 처리 / 登録完了処理 =====
            insertService.insert(stock);
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('登録できました。');"); // 등록 완료 알림 / 登録完了メッセージ
            out.println("location.href='list.do';"); // 목록으로 이동 / 一覧へ移動
            out.println("</script>");
            out.close();
            return null;
        } catch (DuplicateFormatFlagsException e) {
        	System.out.println("a");
            return FORM_VIEW;
        }
    }
}
