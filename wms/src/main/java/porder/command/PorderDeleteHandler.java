package porder.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import porder.service.PorderService;
import ware.service.WareListService;

public class PorderDeleteHandler implements CommandHandler {

	private PorderService pOrderService = new PorderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String purchase_No = req.getParameter("purchaseNo");
		
		pOrderService.delete(purchase_No);
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
