package porder.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;

public class PorderInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/pOrderInsert.jsp";
	private PorderService pOrderService = new PorderService(); // 発注登録サービス
	private ItemListService itemService = new ItemListService(); // 品目一覧取得サービス

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// HTTPメソッドで分岐（GET=フォーム表示, POST=登録）
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 未対応メソッド
			return null;
		}
	}
	
	// フォーム表示：次の発注番号と品目一覧を用意
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Porder purchaseNo = pOrderService.getPurchaseNo(); // 次の発注番号
		List<Item> itemList = itemService.getItemList();   // 品目一覧
		
		req.setAttribute("purchaseNo", purchaseNo);
		req.setAttribute("itemList", itemList);
		return FORM_VIEW;
	}
	
	// 登録処理：パラメータ → モデル詰め替え → 登録
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();
		pOrder.setPurchase_No(req.getParameter("purchase_no"));
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm"));
		pOrder.setItem_Cd(req.getParameter("item_cd"));
		pOrder.setQty(req.getParameter("qty"));
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept"));
		pOrder.setPurchase_User(req.getParameter("purchase_user"));
		pOrder.setDescr(req.getParameter("descr"));
		pOrder.setReg_Ymd(req.getParameter("reg_ymd"));
			
		try {
			pOrderService.insert(pOrder); // 登録実行
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');"); // 成功メッセージ
			out.println("location.href='list.do';");       // 一覧へ遷移
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) { // 失敗時：フォーム再表示
			return FORM_VIEW;
		}
	}
}