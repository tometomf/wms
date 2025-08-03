package order.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.model.Order;
import order.service.OrderListService;

public class OrderHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	OrderListService orderListService = new OrderListService();	
	List<Order> orderList = orderListService.getOrderList();
	req.setAttribute("orderList", orderList);
	return "/WEB-INF/view/orderList.jsp";
}
}

