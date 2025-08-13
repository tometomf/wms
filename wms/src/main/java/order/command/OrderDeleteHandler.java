package order.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.service.OrderService;
import ware.service.WareListService;

public class OrderDeleteHandler implements CommandHandler {

	private OrderService orderService = new OrderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String orderNo = req.getParameter("orderNo");
		
		orderService.delete(orderNo);
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
