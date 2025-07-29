package stock.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.service.StockList;
import stock.service.StockService;

public class StockHandler implements CommandHandler {

	private StockService stockService = new StockService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		StockList stockList = stockService.getSelectAll();
		req.setAttribute("stockList", stockList);
		return "/WEB-INF/view/stockList.jsp";
	}  

}
