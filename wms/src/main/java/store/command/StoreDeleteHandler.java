package store.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.service.StoreListService;

public class StoreDeleteHandler implements CommandHandler{

	public StoreListService storeListService = new StoreListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String store = req.getParameter("store_no");
		
		storeListService.delete(store);
		//delete機能を呼び出す
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('削除出来ました。');");
		out.println("location.href='list.do';");
		out.println("</script>");
		return null;
	}
	
}
