package ware.model;

public class Ware {
	private String wareCd;
	private String wareNm;
	private String wareGubun;
	private String useYn;
	/**
	 * 
	 */
	public Ware() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param wareCd
	 * @param wareNm
	 * @param wareGubun
	 * @param useYn
	 */
	public Ware(String wareCd, String wareNm, String wareGubun, String useYn) {
		super();
		this.wareCd = wareCd;
		this.wareNm = wareNm;
		this.wareGubun = wareGubun;
		this.useYn = useYn;
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

}