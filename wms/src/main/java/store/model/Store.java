package store.model;

import java.util.Date;

public class Store {

	private String no;
	private String store_no;
	private String store_nm;
	private String item_cd;
	private String item_nm;
	private Integer qty;
	private Integer store_price;
	private String ware_cd;
	private String ware_nm;
	private String store_dept;
	private String store_user;
	private String descr;
	private String reg_ymd;
	
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(String no, String store_no, String store_nm, String item_cd, String item_nm, Integer qty,
			Integer store_price, String ware_cd, String ware_nm, String store_dept, String store_user, String descr,
			String reg_ymd) {
		super();
		this.no = no;
		this.store_no = store_no;
		this.store_nm = store_nm;
		this.item_cd = item_cd;
		this.item_nm = item_nm;
		this.qty = qty;
		this.store_price = store_price;
		this.ware_cd = ware_cd;
		this.ware_nm = ware_nm;
		this.store_dept = store_dept;
		this.store_user = store_user;
		this.descr = descr;
		this.reg_ymd = reg_ymd;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStore_no() {
		return store_no;
	}

	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}

	public String getStore_nm() {
		return store_nm;
	}

	public void setStore_nm(String store_nm) {
		this.store_nm = store_nm;
	}

	public String getItem_cd() {
		return item_cd;
	}

	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}

	public String getItem_nm() {
		return item_nm;
	}

	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getStore_price() {
		return store_price;
	}

	public void setStore_price(Integer store_price) {
		this.store_price = store_price;
	}

	public String getWare_cd() {
		return ware_cd;
	}

	public void setWare_cd(String ware_cd) {
		this.ware_cd = ware_cd;
	}

	public String getWare_nm() {
		return ware_nm;
	}

	public void setWare_nm(String ware_nm) {
		this.ware_nm = ware_nm;
	}

	public String getStore_dept() {
		return store_dept;
	}

	public void setStore_dept(String store_dept) {
		this.store_dept = store_dept;
	}

	public String getStore_user() {
		return store_user;
	}

	public void setStore_user(String store_user) {
		this.store_user = store_user;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getReg_ymd() {
		return reg_ymd;
	}

	public void setReg_ymd(String reg_ymd) {
		this.reg_ymd = reg_ymd;
	}
}