package stock.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.model.Stock;
import stock.service.StockService;

//재고 수정 요청을 처리하는 핸들러 / 在庫修正リクエストを処理するハンドラ
public class StockUpdateHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/stockUpdateForm.jsp";
    private StockService stockService = new StockService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // 수정 폼 보여주기 (기존 데이터 세팅) / 修正フォームを表示 (既存のデータセット)
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
     
    	 String stockNoParam = req.getParameter("stockNo");

    	    if (stockNoParam == null || stockNoParam.trim().isEmpty()) {
    	        throw new IllegalArgumentException("stockNo 파라미터가 없습니다.");
    	    }
    	
    	int stockNo = Integer.parseInt(req.getParameter("stockNo"));
        Stock stock = stockService.getStockByNo(stockNo); // 기존 데이터 조회 / 既存データ照会
        req.setAttribute("stock", stock);

        return FORM_VIEW;
    }

    // 수정 처리 / 修正処理
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        Stock stock = new Stock();
        try {
            stock.setStock_No(Integer.parseInt(req.getParameter("stockNo").trim()));
            stock.setItem_Cd(req.getParameter("itemCd"));
            stock.setQty(Integer.parseInt(req.getParameter("qty")));
            stock.setWare_Cd(req.getParameter("wareCd"));
            stock.setReg_Ymd(new java.text.SimpleDateFormat("yyyy-MM-dd")
                                .parse(req.getParameter("regYmd")));
        } catch (NumberFormatException | ParseException e) {
            errors.put("invalidInput", true);
            return FORM_VIEW;
        }

     // DAO 통해 DB 수정 / DAOを通じてDBを修正
        try {
            stockService.update(stock); 
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('수정이 완료되었습니다.');");
            out.println("location.href='list.do';");
            out.println("</script>");
            out.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("updateFail", true);
            return FORM_VIEW;
        }
    }
}
