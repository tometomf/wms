package order.model;

import java.util.Date;

	
	public class Order { 

		private Integer order_no;
	    private String order_nm;
	    private String order_dept;
	    private String order_user; 
	    private String descr;
	    private Date reg_ymd;

	    public Order(Integer order_no, String order_nm, String order_dept, String order_user, String descr,
                Date reg_ymd) {

		this.order_no = order_no;
		this.order_nm = order_nm;
		this.order_dept = order_dept;
		this.order_user = order_user;
		this.descr = descr;
		this.reg_ymd = reg_ymd;
	}

	public int getStore_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public String getOrder_nm() {
		return order_nm;
	}

	public void setOrder_nm(String order_nm) {
		this.order_nm = order_nm;
	}

	public String getOrder_dept() {
		return order_dept;
	}

	public void setOrder_dept(String order_dept) {
		this.order_dept = order_dept;
	}

	public String getOrder_user() {
		return order_user;
	}

	public void setOrder_user(String order_user) {
		this.order_user = order_user;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getReg_ymd() {
		return reg_ymd;
	}

	public void setReg_ymd(Date reg_ymd) {
		this.reg_ymd = reg_ymd;
	}
}