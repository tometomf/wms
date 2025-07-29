package stock.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import stock.model.Stock;
import stock.service.StockService;

public class StockHandler implements CommandHandler {

	private StockService stockService = new StockService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<Stock> stockList = stockService.getSelectAll();
		req.setAttribute("stockList", stockList);
		return "/WEB-INF/view/stockList.jsp";
	}  

}
