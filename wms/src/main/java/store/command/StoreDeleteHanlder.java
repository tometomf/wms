package store.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.service.StoreListService;

public class StoreDeleteHanlder {

	public StoreListService storeListService = new StoreListService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String store = req.getParameter("store_no");
		
		storeListService.delete(store);
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다.');");
		out.println("location.href='list.do';");
		out.println("</script>");
		return null;
	}
	
}
