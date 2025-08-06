package ware.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ware.service.WareListService;

public class WareDeleteHandler implements CommandHandler {

	private WareListService wareListService = new WareListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String ware = req.getParameter("wareCd");
		
		wareListService.delete(ware);
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
