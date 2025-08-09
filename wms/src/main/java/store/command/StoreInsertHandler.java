package store.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.model.Store;
import store.service.StoreListService;

public class StoreInsertHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/storeInsert.jsp";
	private StoreListService storeListService = new StoreListService();

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
		int nextStoreNo = storeListService.getNewStoreNo();
		req.setAttribute("store_no", nextStoreNo);
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Store store = new Store(null, null, null, null, null, null, null, null);
		
		store.setStore_no(Integer.parseInt(req.getParameter("store_no")));
		store.setStore_nm(req.getParameter("store_nm"));
		store.setItem_cd(req.getParameter("item_cd"));		
		store.setItem_qty(Integer.parseInt(req.getParameter("item_qty")));
		store.setStore_dept(req.getParameter("store_dept"));
		store.setStore_user(req.getParameter("store_user"));
		store.setDescr(req.getParameter("descr"));
		
		String regYmdStr = req.getParameter("reg_ymd");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if (regYmdStr == null || regYmdStr.isEmpty()) {
			errors.put("regYmd", Boolean.TRUE);
		} else {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date regYmdUtilDate = dateFormat.parse(regYmdStr);
				
				store.setReg_ymd(new java.sql.Date(regYmdUtilDate.getTime()));
			} catch (ParseException e) {
				errors.put("regYmdFormat", Boolean.TRUE);
			}
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			storeListService.insert(store);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('등록이 완료되었습니다.');");
			out.println("location.href='list.do';");
			out.println("</script>");
			out.close();
			return null;
		} catch (RuntimeException e) {
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException) {
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('이미 사용된 입고 번호입니다. 다시 시도해 주세요.');");
				out.println("location.href='insert.do';");
				out.println("</script>");
				out.close();
				return null;
			}
			throw e;
		}
	}
}
