package stock.command;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        // POST 방식으로 폼 데이터가 넘어왔는지 확인
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
		
		stock.setStock_No(Integer.parseInt(req.getParameter("stockNo").trim()));
		stock.setItem_Cd(req.getParameter("itemCd"));
		stock.setQty(Integer.parseInt(req.getParameter("qty")));
		stock.setWare_Cd(req.getParameter("wareCd"));
		//Date타입 형변환 + 에러처리
		try {
		    stock.setReg_Ymd(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("regYmd")));
		} catch (java.text.ParseException e) {
		    // 에러 처리 (예: 에러 메시지 출력, 폼으로 다시 이동 등)
		    e.printStackTrace();
		    // 또는 사용자에게 에러 메시지 전달하는 코드 작성
		}

		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try {
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
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
    }
}
