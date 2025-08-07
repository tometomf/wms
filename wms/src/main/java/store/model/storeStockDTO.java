package store.model;

import java.util.Date;

public class storeStockDTO {

	private Integer store_no;
    private String store_nm;

    private String store_dept;
    private String store_user; 
    private String descr;
    private Date reg_ymd;
    
    public storeStockDTO(Integer store_no, String store_nm, String store_dept, String store_user, String descr,
            Date reg_ymd) {
    	super();
    
    	this.store_no = store_no;
		this.store_nm = store_nm;
		this.store_dept = store_dept;
		this.store_user = store_user;
		this.descr = descr;
		this.reg_ymd = reg_ymd;
	}

	public Integer getStore_no() {
		return store_no;
	}

	public String getStore_nm() {
		return store_nm;
	}

	public String getStore_dept() {
		return store_dept;
	}

	public String getStore_user() {
		return store_user;
	}

	public String getDescr() {
		return descr;
	}

	public Date getReg_ymd() {
		return reg_ymd;
	}

	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}

	public void setStore_nm(String store_nm) {
		this.store_nm = store_nm;
	}

	public void setStore_dept(String store_dept) {
		this.store_dept = store_dept;
	}

	public void setStore_user(String store_user) {
		this.store_user = store_user;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setReg_ymd(Date reg_ymd) {
		this.reg_ymd = reg_ymd;
	}

}
