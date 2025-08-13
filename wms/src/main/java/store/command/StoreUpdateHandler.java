package store.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.model.Store;
import store.service.StoreListService;

public class StoreUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/storeUpdate.jsp";
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
	    try {
	        String noVal = req.getParameter("store_no");

	        Store store = storeListService.getStore_no(noVal); 

	        req.setAttribute("store", store);
	        
	        return "/WEB-INF/view/storeUpdate.jsp";
	    } catch (NumberFormatException e) {
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        return null;
	    }
	}	
	//WebリクエストからのデータをStoreに変換してセーブする。
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Store store = new Store(null, null, null, null, null, null, null, null);
		
		try {
			store.setStore_no(Integer.parseInt(req.getParameter("store_no")));
		} catch (NumberFormatException e) {
			Map<String, Boolean> errors = new HashMap<>();
			errors.put("invalidNo", Boolean.TRUE);
			req.setAttribute("errors", errors);
			return FORM_VIEW;
		}
		store.setStore_nm(req.getParameter("store_nm"));
		store.setItem_cd(req.getParameter("item_cd"));
		store.setItem_qty(Integer.parseInt(req.getParameter("item_qty")));
		store.setStore_dept(req.getParameter("store_dept"));
		store.setStore_user(req.getParameter("store_user"));
		store.setDescr(req.getParameter("descr"));
		
		String regYmdStr = req.getParameter("reg_ymd");
		if (regYmdStr != null && !regYmdStr.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date regYmd = dateFormat.parse(regYmdStr);
				store.setReg_ymd(regYmd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		//データが登録されたかどうかを確認するメソッド
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		try {
			storeListService.update(store);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('수정이 완료되었습니다.');");
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