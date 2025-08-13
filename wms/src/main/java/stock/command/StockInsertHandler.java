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

public class StockInsertHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/stockForm.jsp";
    private StockService insertService = new StockService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        // POST 방식으로 폼 데이터가 넘어왔는지 확인 / POST方式でフォームデータが渡ってきたか確認
        if (req.getMethod().equalsIgnoreCase("GET")) {
        	return processForm(req,res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Stock stock = insertService.getstockNo();
		req.setAttribute("stockNo", stock);
		return "/WEB-INF/view/stockForm.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Stock stock = new Stock();
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			
		stock.setStock_No(Integer.parseInt(req.getParameter("stockNo").trim()));
		stock.setItem_Cd(req.getParameter("itemCd"));
		stock.setQty(Integer.parseInt(req.getParameter("qty")));
		stock.setWare_Cd(req.getParameter("wareCd"));
	
		// Date타입 형변환  / Dateタイプ変換
		try {
		    stock.setReg_Ymd(
		        new java.text.SimpleDateFormat("yyyy-MM-dd")
		            .parse(req.getParameter("regYmd"))
		    );
		   // 에러처리 / エラー処理
		} catch (ParseException e) {
		    errors.put("invalidDate", Boolean.TRUE);
		    return FORM_VIEW; // 잘못된 날짜면 등록 페이지로 다시 이동 / 間違った日付の場合は登録ページに再度移動
		}

		// 등록 완료 알림창 / 登録完了のお知らせ
			insertService.insert(stock);
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('등록이 완료되었습니다.');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
			
		} catch (Exception e) {
			errors.put("insertError", Boolean.TRUE);
			return FORM_VIEW; // 등록 실패시 등록 페이지로 다시 이동 / 登録に失敗した場合、登録ページに再度移動
		}
    }
}
