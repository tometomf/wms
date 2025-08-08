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
		//１．入庫リストを呼び出す。
		List<Store> storeList = storeListService.getStoreList();
		
		//２．そのリストをJSPで使用できるよう、Requestに入れる
		req.setAttribute("storeList", storeList);
		//３．入庫登録画面のJSPページへ移動する。
		return "/WEB-INF/view/storeUpdate.jsp";
	}
	
	//WebリクエストからのデータをStoreに変換してセーブする。
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Store store = new Store(null, null, null, null, null, null);
		
		store.setStore_no(req.getParameter("store_no"));
		store.setStore_nm(req.getParameter("store_nm"));
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

		
		
		
		
		
		
		
		
		
		
		
		
		/*User user = (User)req.getSession(false).getAttribute("authUser");
		InsertRequest insertReq = createInsertRequest(user, req);
		insertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newStoreNo = insertService.insert(insertReq);
		req.setAttribute("newStoreNo", newStoreNo);

		return"/WEB-INF/view/newStoreSuccess.jsp";
	}*/
	
