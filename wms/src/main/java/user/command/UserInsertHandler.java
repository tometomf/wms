package user.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import user.model.User;
import user.service.UserListService;

public class UserInsertHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/userInsert.jsp";
	private UserListService userListService = new UserListService();

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
		User user = userListService.getUserCd();
		req.setAttribute("userCd", user);
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		User user = new User();
		
		user.setUserCd(req.getParameter("userCd"));
		user.setUserNm(req.getParameter("userNm"));
		user.setDeptNm(req.getParameter("deptNm"));
		user.setPhone(req.getParameter("phone"));
		user.setEmail(req.getParameter("email"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try {
			userListService.insert(user);
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
