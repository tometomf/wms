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

// 발주 등록 핸들러 / 発注登録ハンドラー
public class PorderInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/pOrderInsert.jsp";
	private PorderService pOrderService = new PorderService(); // 발주 서비스 / 発注サービス
	private ItemListService itemService = new ItemListService(); // 품목 서비스 / 品目サービス

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// GET → 폼 보여주기 / GET → フォーム表示
		// POST → 등록 실행 / POST → 登録実行
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 지원 안 함 / 未対応メソッド
			return null;
		}
	}
	
	// 폼 화면 준비 / フォーム画面準備
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Porder purchaseNo = pOrderService.getPurchaseNo(); // 다음 발주번호 / 次の発注番号
		List<Item> itemList = itemService.getItemList();   // 품목 목록 / 品目一覧
		
		req.setAttribute("purchaseNo", purchaseNo);
		req.setAttribute("itemList", itemList);
		return FORM_VIEW;
	}
	
	// 등록 처리 / 登録処理
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();
		pOrder.setPurchase_No(req.getParameter("purchase_no"));     // 발주번호 / 発注番号
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm"));     // 발주명 / 発注名
		pOrder.setItem_Cd(req.getParameter("item_cd"));             // 품목코드 / 品目コード
		pOrder.setQty(req.getParameter("qty"));                     // 수량 / 数量
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept")); // 부서 / 部署
		pOrder.setPurchase_User(req.getParameter("purchase_user")); // 담당자 / 担当者
		pOrder.setDescr(req.getParameter("descr"));                 // 비고 / 備考
		pOrder.setReg_Ymd(req.getParameter("reg_ymd"));             // 등록일 / 登録日
			
		try {
			pOrderService.insert(pOrder); // 등록 실행 / 登録実行
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');"); // 등록 완료 / 登録完了
			out.println("location.href='list.do';"); // 목록으로 이동 / 一覧へ移動
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			// 실패 → 다시 폼 / 失敗 → フォーム再表示
			return FORM_VIEW;
		}
	}
}
