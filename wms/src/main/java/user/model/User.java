package user.model;

public class User {
	private String userCd;
	private String userNm;
	private String deptNm;
	private String phone;
	private String email;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @param userCd
	 * @param userNm
	 * @param deptNm
	 * @param phone
	 * @param email
	 */
	public User(String userCd, String userNm, String deptNm, String phone, String email) {
		super();
		this.userCd = userCd;
		this.userNm = userNm;
		this.deptNm = deptNm;
		this.phone = phone;
		this.email = email;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}