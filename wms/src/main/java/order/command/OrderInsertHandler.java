package order.command;

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
import order.model.Order;
import order.service.OrderService;
import ware.model.Ware;
import ware.service.WareListService;

public class OrderInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/wareInsert.jsp"; // フォーム画面パス (폼 화면 경로)
	private OrderService orderService = new OrderService();
	private ItemListService itemService = new ItemListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);   // フォーム表示 (폼 화면 표시)
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // 登録処理 (등록 처리)
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	// フォーム表示処理 (폼 표시 처리)
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String orderNo = orderService.selectOrderNo();   // 注文番号取得 (주문번호 조회)
		List<Item> itemList = itemService.getItemList(); // 品目リスト取得 (품목 리스트 조회)
		
		req.setAttribute("orderNo", orderNo);
		req.setAttribute("itemList", itemList);
		
		return "/WEB-INF/view/orderInsert.jsp";
	}
	
	// 登録処理 (등록 처리)
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Order order = new Order();
		
		order.setOrder_No(req.getParameter("order_no"));   // 入力値セット (입력값 설정)
		order.setOrder_Nm(req.getParameter("order_nm"));
		order.setItem_Cd(req.getParameter("item_cd"));
		order.setQty(req.getParameter("qty"));
		order.setOrder_Price(req.getParameter("order_price"));
		order.setOrder_Dept(req.getParameter("order_dept"));
		order.setOrder_User(req.getParameter("order_user"));
		order.setDescr(req.getParameter("descr"));
		
		try {
			orderService.insert(order); // DB登録 (DB 등록)
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('登録できました。');");   // 成功メッセージ (성공 메시지)
			out.println("location.href='list.do';");    // 一覧画面へ (목록 화면으로 이동)
			out.println("</script>");
			out.close();
			return null;
		} catch (DuplicateFormatFlagsException e) {
			return FORM_VIEW; // エラー時フォームへ戻る (에러 시 폼 화면으로 복귀)
		}
	}

}