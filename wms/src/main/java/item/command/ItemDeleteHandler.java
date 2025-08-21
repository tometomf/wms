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
		
		int fkCheck = itemListService.delete(item);
		
		// 외래키 예외처리, 外来キーの例外処理
		if (fkCheck == 0) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('使用中の品目は削除できません。');");
			out.println("location.href='update.do?itemCd=" + item + "';");
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
