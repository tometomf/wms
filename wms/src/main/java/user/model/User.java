package user.model;

public class User {
	private String userCd;
	private String phone;
	private String userNm;
	private String email;
	private String deptNm;
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userCd
	 * @param phone
	 * @param userNm
	 * @param email
	 * @param deptNm
	 */
	public User(String userCd, String phone, String userNm, String email, String deptNm) {
		super();
		this.userCd = userCd;
		this.phone = phone;
		this.userNm = userNm;
		this.email = email;
		this.deptNm = deptNm;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
}
