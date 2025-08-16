package order.model;

public class Order {

	public String no;
	public String order_No;
	public String order_Nm;
	public String item_Cd;
	public String item_Nm;
	public String qty;
	public String order_Price;
	public String order_Dept;
	public String order_User;
	public String descr;
	public String reg_Ymd;
	
	public Order() {

	}

	public Order(String no, String order_No, String order_Nm, String item_Cd, String item_Nm, String qty, String order_Price,
			String order_Dept, String order_User, String descr, String reg_Ymd) {		
		this.no = no;
		this.order_No = order_No;
		this.order_Nm = order_Nm;
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.qty = qty;
		this.order_Price = order_Price;
		this.order_Dept = order_Dept;
		this.order_User = order_User;
		this.descr = descr;
		this.reg_Ymd = reg_Ymd;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOrder_No() {
		return order_No;
	}

	public void setOrder_No(String order_No) {
		this.order_No = order_No;
	}

	public String getOrder_Nm() {
		return order_Nm;
	}

	public void setOrder_Nm(String order_Nm) {
		this.order_Nm = order_Nm;
	}

	public String getItem_Cd() {
		return item_Cd;
	}

	public void setItem_Cd(String item_Cd) {
		this.item_Cd = item_Cd;
	}

	public String getItem_Nm() {
		return item_Nm;
	}

	public void setItem_Nm(String item_Nm) {
		this.item_Nm = item_Nm;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getOrder_Price() {
		return order_Price;
	}

	public void setOrder_Price(String order_Price) {
		this.order_Price = order_Price;
	}

	public String getOrder_Dept() {
		return order_Dept;
	}

	public void setOrder_Dept(String order_Dept) {
		this.order_Dept = order_Dept;
	}

	public String getOrder_User() {
		return order_User;
	}

	public void setOrder_User(String order_User) {
		this.order_User = order_User;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getReg_Ymd() {
		return reg_Ymd;
	}

	public void setReg_Ymd(String reg_Ymd) {
		this.reg_Ymd = reg_Ymd;
	}
}