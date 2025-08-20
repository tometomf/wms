package porder.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemListService;
import mvc.command.CommandHandler;
import porder.model.Porder;
import porder.service.PorderService;
import ware.model.Ware;
import ware.service.WareListService;

public class PorderUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/wareUpdate.jsp";
	private PorderService pOrderService = new PorderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// GET → 수정フォーム表示 / POST → 更新処理
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 不正メソッド
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		String purchaseNo = req.getParameter("purchase_No");  // パラメータから発注番号取得
		Porder pOrder = pOrderService.selectByPurchaseNo(purchaseNo); // 該当発注を検索
		
		req.setAttribute("pOrder", pOrder); // JSPに渡す
		
		return "/WEB-INF/view/pOrderUpdate.jsp"; // 更新フォームへ
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();
		
		// フォームから受け取った値をモデルに設定
		pOrder.setPurchase_No(req.getParameter("purchase_no"));
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm"));
		pOrder.setQty(req.getParameter("qty"));
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept"));
		pOrder.setPurchase_User(req.getParameter("purchase_user"));
		pOrder.setDescr(req.getParameter("descr"));
		pOrder.setReg_Ymd(req.getParameter("reg_ymd"));
		
		try {
			pOrderService.update(pOrder); // 更新実行
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('修正が完了しました。');"); // 成功メッセージ
			out.println("location.href='list.do';");      // 一覧へ戻る
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW; // 失敗時はフォーム再表示
		}
	}
}