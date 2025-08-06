package ware.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ware.model.Ware;
import ware.service.WareListService;

public class WareUpdateHandler implements CommandHandler {

	private WareListService wareListService = new WareListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return "";
			// return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String wareCd = req.getParameter("wareCd");  // 여기서 파라미터 읽음
		
		Ware ware = wareListService.selectByWareCd(wareCd);
		req.setAttribute("ware", ware);
		
		return "/WEB-INF/view/wareUpdate.jsp";
	}
}
