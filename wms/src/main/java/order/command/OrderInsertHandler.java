package order.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.model.Order;
import order.service.OrderService;
import ware.model.Ware;
import ware.service.WareListService;

public class OrderInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/wareInsert.jsp";
	private OrderService orderService = new OrderService();

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
	
	// 등록 전 창고 pk 조회
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Order order = orderService.getOrderCd();
		req.setAttribute("orderNo", order);
		
		return "/WEB-INF/view/orderInsert.jsp";
	}
	
	// 등록 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Order order = new Order();
		
		order.setOrder_No(req.getParameter("order_no"));
		order.setOrder_Nm(req.getParameter("order_nm"));
		order.setItem_Cd(req.getParameter("item_cd"));
		order.setOrder_Price(req.getParameter("order_price"));
		order.setOrder_Dept(req.getParameter("order_dept"));
		order.setOrder_User(req.getParameter("order_user"));
		order.setOrder_Gubun(req.getParameter("order_gubun"));
		order.setStore_Yn(req.getParameter("store_yn"));
		order.setDescr(req.getParameter("descr"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
//		ware.validate(errors);
//		
//		if (!errors.isEmpty()) {
//			Ware ware1 = wareListService.getWareCd();
//			req.setAttribute("wareCd", ware1);
//			
//			return FORM_VIEW;
//		}
		try {
			orderService.insert(order);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('등록이 완료되었습니다.');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
