package ware.model;

import java.util.Date;

public class WareStockDTO {
	//창고 정보
	private String wareCd;
	private String wareNm;
	
	//재고 정보
	private int stockNo;
	private String itemCd;
	private int qty;
	private Date regYmd;
	/**
	 * 
	 */
	public WareStockDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param wareCd
	 * @param wareNm
	 * @param stockNo
	 * @param itemCd
	 * @param qty
	 * @param regYmd
	 */
	public WareStockDTO(String wareCd, String wareNm, int stockNo, String itemCd, int qty, Date regYmd) {
		super();
		this.wareCd = wareCd;
		this.wareNm = wareNm;
		this.stockNo = stockNo;
		this.itemCd = itemCd;
		this.qty = qty;
		this.regYmd = regYmd;
	}
	public String getWareCd() {
		return wareCd;
	}
	public void setWareCd(String wareCd) {
		this.wareCd = wareCd;
	}
	public String getWareNm() {
		return wareNm;
	}
	public void setWareNm(String wareNm) {
		this.wareNm = wareNm;
	}
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getRegYmd() {
		return regYmd;
	}
	public void setRegYmd(Date regYmd) {
		this.regYmd = regYmd;
	}
	
}