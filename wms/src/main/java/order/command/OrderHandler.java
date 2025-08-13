package order.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.model.Order;
import order.service.OrderService;

public class OrderHandler implements CommandHandler {

	private static OrderService orderService = new OrderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Order> orderList = orderService.selectAll();
		
		req.setAttribute("orderList", orderList);
		return "/WEB-INF/view/orderList.jsp";
	}
}
