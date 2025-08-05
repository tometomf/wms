package ware.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ware.model.Ware;
import ware.service.WareListService;

public class WareRegistHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/wareRegist.jsp";
	private WareListService wareListService = new WareListService();

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
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Ware ware = wareListService.getWareCd();
		req.setAttribute("wareCd", ware);
		
		return "/WEB-INF/view/wareRegist.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Ware ware = new Ware();
		
		ware.setWareCd(req.getParameter("warecd"));
		ware.setWareNm(req.getParameter("warenm"));
		ware.setWareGubun(req.getParameter("waregb"));
		ware.setUseYn(req.getParameter("useyn"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		// ware.validate(errors);
		
//		if (!errors.isEmpty()) {
//			Ware ware1 = wareListService.getWareCd();
//			req.setAttribute("wareCd", ware1);
//			
//			return FORM_VIEW;
//		}
		try {
			wareListService.insert(ware);
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
