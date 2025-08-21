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
		
		int fkCheck = wareListService.delete(ware);
		
		// 외래키 예외처리, 外来キーの例外処理
		if (fkCheck == 0) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('使用中の倉庫は削除できません。');");
			out.println("location.href='update.do?wareCd=" + ware + "';");
			out.println("</script>");
			out.close();
			return null;
		} else {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('削除できました。');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}
