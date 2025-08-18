package ware.model;

public class Ware {
	
	private String no;
	private String wareCd;
	private String wareNm;
	private String wareGubun;
	private String useYn;
	private String descr;
	/**
	 * 
	 */
	public Ware() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ware(String no, String wareCd, String wareNm, String wareGubun, String useYn, String descr) {
		this.no = no;
		this.wareCd = wareCd;
		this.wareNm = wareNm;
		this.wareGubun = wareGubun;
		this.useYn = useYn;
		this.descr = descr;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public String getWareGubun() {
		return wareGubun;
	}
	public void setWareGubun(String wareGubun) {
		this.wareGubun = wareGubun;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

}