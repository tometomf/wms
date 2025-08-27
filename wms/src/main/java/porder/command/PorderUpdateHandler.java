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

// 발주 수정 핸들러
// 発注修正ハンドラー
public class PorderUpdateHandler implements CommandHandler {

	// 수정 화면 경로 / 修正画面パス
	private static final String FORM_VIEW = "/WEB-INF/view/pOrderUpdate.jsp";
	private PorderService pOrderService = new PorderService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// GET → 수정 화면 표시 / GET → 修正フォーム表示
		// POST → 수정 실행 / POST → 修正処理
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 잘못된 메소드 / 不正メソッド
			return null;
		}
	}

	// 수정 폼 보여주기 / 修正フォームを表示
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String purchaseNo = req.getParameter("purchase_No"); // 발주번호 가져오기 / 発注番号を取得
		Porder pOrder = pOrderService.selectByPurchaseNo(purchaseNo); // 발주 검색 / 発注を検索

		req.setAttribute("pOrder", pOrder); // JSP로 전달 / JSPへ渡す

		return "/WEB-INF/view/pOrderUpdate.jsp"; // 수정 화면으로 / 修正画面へ
	}

	// 수정 처리 / 修正処理
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Porder pOrder = new Porder();

		// 입력값을 Porder 모델에 담기 / 入力値をPorderモデルに入れる
		pOrder.setPurchase_No(req.getParameter("purchase_no")); // 발주번호 / 発注番号
		pOrder.setPurchase_Nm(req.getParameter("purchase_nm")); // 발주명 / 発注名
		pOrder.setQty(req.getParameter("qty")); // 수량 / 数量
		pOrder.setPurchase_Dept(req.getParameter("purchase_dept")); // 부서 / 部署
		pOrder.setPurchase_User(req.getParameter("purchase_user")); // 담당자 / 担当者
		pOrder.setDescr(req.getParameter("descr")); // 비고 / 備考
		pOrder.setReg_Ymd(req.getParameter("reg_ymd")); // 등록일 / 登録日

		try {
			pOrderService.update(pOrder); // 수정 실행 / 修正実行
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('修正できました。');"); // 수정 성공 메시지 / 修正成功メッセージ
			out.println("location.href='list.do';"); // 목록으로 이동 / 一覧へ移動
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW; // 실패 시 다시 폼 / 失敗時はフォーム再表示
		}
	}
}
