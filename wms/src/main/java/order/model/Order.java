package order.model;

import java.util.Date;

public class Order {

	public String order_No;
	public String order_Nm;
	public String item_Cd;
	public String item_Nm;
	public String order_Price;
	public String order_Dept;
	public String order_User;
	public String order_Gubun;
	public String descr;
	public String reg_Ymd;
	public String upd_ymd;
	public String store_Yn;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(String order_No, String order_Nm, String item_Cd, String item_Nm, String order_Price, String order_Dept,
			String order_User, String order_Gubun, String descr, String reg_Ymd, String upd_ymd, String store_Yn) {
		super();
		this.order_No = order_No;
		this.order_Nm = order_Nm;
		this.item_Cd = item_Cd;
		this.item_Nm = item_Nm;
		this.order_Price = order_Price;
		this.order_Dept = order_Dept;
		this.order_User = order_User;
		this.order_Gubun = order_Gubun;
		this.descr = descr;
		this.reg_Ymd = reg_Ymd;
		this.upd_ymd = upd_ymd;
		this.store_Yn = store_Yn;
	}

	public String getItem_Nm() {
		return item_Nm;
	}

	public void setItem_Nm(String item_Nm) {
		this.item_Nm = item_Nm;
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

	public String getOrder_Gubun() {
		return order_Gubun;
	}

	public void setOrder_Gubun(String order_Gubun) {
		this.order_Gubun = order_Gubun;
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

	public String getUpd_ymd() {
		return upd_ymd;
	}

	public void setUpd_ymd(String upd_ymd) {
		this.upd_ymd = upd_ymd;
	}

	public String getStore_Yn() {
		return store_Yn;
	}

	public void setStore_Yn(String store_Yn) {
		this.store_Yn = store_Yn;
	}
}