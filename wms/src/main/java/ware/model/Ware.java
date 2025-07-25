package ware.model;

public class Ware {
	private String wareCd;
	private String wareBubun;
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
	 * @param wareBubun
	 * @param useYn
	 * @param wareNm
	 */
	public Ware(String wareCd, String wareBubun, String useYn, String wareNm) {
		super();
		this.wareCd = wareCd;
		this.wareBubun = wareBubun;
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
		return wareBubun;
	}
	public void setWareBubun(String wareBubun) {
		this.wareBubun = wareBubun;
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
