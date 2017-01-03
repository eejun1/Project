package member.dto;

public class MemberDTO { // 사용자 정보 담고 있는 DTO 객체

	private String userSeq;
	private String email;
	private String userName;
	private String userpw;
	private String address;
	private String phoneNumber;
	private String admin;

	public MemberDTO(String userSeq, String email, String userName, String userpw, String address, String phoneNumber, String admin) {

		this.userSeq = userSeq;
		this.email = email;
		this.userpw = userpw;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.admin = admin;
	}

	public MemberDTO(String email, String userpw, String userName, String address, String phoneNumber) {
		this.email = email;
		this.userpw = userpw;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public MemberDTO(String userSeq, String email,String userName, String userpw, String address, String phoneNumber){
		this.userSeq = userSeq;
		this.email = email;
		this.userName = userName;
		this.userpw = userpw;
		this.address = address;
		this.phoneNumber = phoneNumber;


	}

	public String getUserSeq() {
		return this.userSeq;
	}

	public String getUserpw() {
		// TODO Auto-generated method stub
		return this.userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdmin() {
		return this.admin;
	}

}
