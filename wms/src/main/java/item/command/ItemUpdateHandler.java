package item.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.Item;
import item.service.ItemListService;
import mvc.command.CommandHandler;

public class ItemUpdateHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/itemUpdate.jsp";
	private ItemListService itemListService = new ItemListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);//GETリクエストの場合、FORM画面を表示
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String itemCd = req.getParameter("itemCd");  
		
		Item item = itemListService.selectByItemCd(itemCd);
		req.setAttribute("item", item);
		
		return "/WEB-INF/view/itemUpdate.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Item item = new Item();
		
		item.setItemCd(req.getParameter("itemCd"));
		item.setItemNm(req.getParameter("itemNm"));
		item.setSpec(req.getParameter("spec"));
		item.setItemGubun(req.getParameter("itemGubun"));
		item.setUnit(req.getParameter("unit"));
		item.setUseYn(req.getParameter("useYn"));
		item.setManufacturer(req.getParameter("manufacturer"));
		item.setStorePrice(Integer.parseInt(req.getParameter("storePrice")));
		item.setShipmentPrice(Integer.parseInt(req.getParameter("shipmentPrice")));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
//		if (itemCd == null || itemCd.trim().isEmpty()) {
//      errors.put("itemCd", true); //品目コード未入力
//	}
//	if (itemNm == null || itemNm.trim().isEmpty()) {
//  errors.put("itemNm", true); //品目名未入力
//}
//	if (spec == null || spec.trim().isEmpty()) {
//  errors.put("spec", true); //規格未入力
//}
//	if (itemGubun == null || itemGubun.trim().isEmpty()) {
//  errors.put("itemGubun", true); // 品目コード未入力
//}
//	if (unit == null || unit.trim().isEmpty()) {
//  errors.put("unit", true); // 品目コード未入力
//}
//	if (useYn == null || useYn.trim().isEmpty()) {
//  errors.put("useYn", true); // 品目コード未入力
//}
//	if (manufacturer == null || manufacturer.trim().isEmpty()) {
//  errors.put("manufacturer", true); // 品目コード未入力
//}
//	if (storePrice == null || storePrice.trim().isEmpty()) {
//  errors.put("storePrice", true); // 品目コード未入力
//}
//	if (shipmentPrice == null || shipmentPrice.trim().isEmpty()) {
//  errors.put("shipmentPrice", true); // 品目コード未入力
//}
//  if (!errors.isEmpty()) {
//  req.setAttribute("itemCd", insertService.getItemCd());
//  return FORM_VIEW;
//}

		try {
			itemListService.update(item);
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('修正できました。');");
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
