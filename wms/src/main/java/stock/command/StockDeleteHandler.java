package stock.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockService;

public class StockDeleteHandler implements CommandHandler {

	private StockService stockService = new StockService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String stock = req.getParameter("stockNo");
		
		stockService.delete(stock);
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
