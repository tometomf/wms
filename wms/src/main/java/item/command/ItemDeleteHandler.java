package item.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemListService;
import mvc.command.CommandHandler;

public class ItemDeleteHandler implements CommandHandler {

	private ItemListService itemListService = new ItemListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String item = req.getParameter("itemCd");
		
		itemListService.delete(item);
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
