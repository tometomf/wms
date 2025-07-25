package ware.model;

public class Ware {
	private String wareCd;
	private String wareGubun;
	private String useYn;
	private String wareNm;
	/**
	 * 
	 */
	public Ware() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param wareCd
	 * @param wareGubun
	 * @param useYn
	 * @param wareNm
	 */
	public Ware(String wareCd, String wareGubun, String useYn, String wareNm) {
		super();
		this.wareCd = wareCd;
		this.wareGubun = wareGubun;
		this.useYn = useYn;
		this.wareNm = wareNm;
	}
	public String getWareCd() {
		return wareCd;
	}
	public void setWareCd(String wareCd) {
		this.wareCd = wareCd;
	}
	public String getWareBubun() {
		return wareGubun;
	}
	public void setWareBubun(String wareBubun) {
		this.wareGubun = wareBubun;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getWareNm() {
		return wareNm;
	}
	public void setWareNm(String wareNm) {
		this.wareNm = wareNm;
	}
	
	
}
