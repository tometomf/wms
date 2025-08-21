package order.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import order.model.Order;
import order.service.OrderService;
import ware.model.Ware;
import ware.service.WareListService;

public class OrderUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/wareUpdate.jsp"; // 更新フォーム画面 (갱신 폼 화면)
	private OrderService orderService = new OrderService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);   // フォーム表示 (폼 화면 표시)
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // 更新処理 (갱신 처리)
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		String orderNo = req.getParameter("OrderNo");  // リクエストから注文番号を取得 (요청에서 주문번호를 가져옴)
		Order order = orderService.selectByOrderNo(orderNo); // 注文情報を検索 (주문 정보를 조회)
		
		req.setAttribute("order", order); // JSP に渡す (JSP에 전달)
		
		return "/WEB-INF/view/orderUpdate.jsp"; // 更新画面へ (갱신 화면으로 이동)
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Order order = new Order();
		
		// フォーム入力値をセット (폼 입력값을 설정)
		order.setOrder_No(req.getParameter("order_no"));
		order.setOrder_Nm(req.getParameter("order_nm"));
		order.setItem_Cd(req.getParameter("item_cd"));
		order.setQty(req.getParameter("qty"));
		order.setOrder_Price(req.getParameter("order_price"));
		order.setOrder_Dept(req.getParameter("order_dept"));
		order.setOrder_User(req.getParameter("order_user"));
		order.setDescr(req.getParameter("descr"));

		try {
			orderService.update(order); // DB更新 (DB 갱신)
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('修正できました。');"); // 更新成功メッセージ (수정 성공 메시지)
			out.println("location.href='list.do';");   // 一覧画面へ遷移 (목록 화면으로 이동)
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW; // エラー時フォームへ戻る (에러 시 폼 화면으로 복귀)
		}
	}
}