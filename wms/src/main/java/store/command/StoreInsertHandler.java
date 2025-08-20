package store.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import store.model.Store;
import store.service.StoreListService;
import ware.model.Ware;
import ware.service.WareListService;

public class StoreInsertHandler implements CommandHandler {

	//入庫登録画面のファイル経路。
	private static final String FORM_VIEW = "/WEB-INF/view/storeInsert.jsp";
	private StoreListService storeListService = new StoreListService();
	private ItemListService itemService = new ItemListService();
	 private WareListService wareService = new WareListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			//入庫登録フォームの画面を表す。
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			//フォームからのデータをDBにセーブ。
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String nextStoreNo = storeListService.getNewStoreNo();
		List<Item> itemList = itemService.getItemList();
		List<Ware> wareList = wareService.select();

		//新しいstore_noを獲得するメソッドを呼び出す。
		req.setAttribute("store_no", nextStoreNo);
		req.setAttribute("itemList", itemList);
		req.setAttribute("wareList", wareList);
		
		return FORM_VIEW;
	}
	
	//Post要請を処理するメソッド
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Store store = new Store();
		
		store.setStore_no(req.getParameter("store_no"));
		store.setStore_nm(req.getParameter("store_nm"));
		store.setItem_cd(req.getParameter("item_cd"));		
		store.setQty(Integer.parseInt(req.getParameter("item_qty")));
		store.setStore_price(Integer.parseInt(req.getParameter("store_price")));
		store.setWare_cd(req.getParameter("ware_cd"));
		store.setStore_dept(req.getParameter("store_dept"));
		store.setStore_user(req.getParameter("store_user"));
		store.setDescr(req.getParameter("descr"));
		store.setReg_ymd(req.getParameter("reg_ymd"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
//		if (regYmdStr == null || regYmdStr.isEmpty()) {
//			errors.put("regYmd", Boolean.TRUE);
//		} else {
//			try {
//				//文字列の日付をDateに変換。
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Date regYmdUtilDate = dateFormat.parse(regYmdStr);
//				
//				store.setReg_ymd(new java.sql.Date(regYmdUtilDate.getTime()));
//			} catch (ParseException e) {
//				errors.put("regYmdFormat", Boolean.TRUE);
//			}
//		}
		
		//エラー発生時フォーム画面へ戻る。
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			//問題なければサービスメソッドを通じてDBへデータを入れる。
			storeListService.insert(store);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');");
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
				out.println("alert('使用済みの入庫番号です。別の番号をお使いください。');");
				out.println("location.href='insert.do';");
				out.println("</script>");
				out.close();
				return null;
			}
			throw e;
		}
	}
}
