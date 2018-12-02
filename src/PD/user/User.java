package user;

public class User implements java.io.Serializable {
	private String id;
	private String passwd;
	private String name;
	private Boolean gender;//True-male False-female
	private String address;
	private String contact;//ø¨∂Ù√≥
	
	public User() {
	}
	public User(String id, String passwd, String name, Boolean gender, String address, String contact) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.contact = contact;
	}
	//set, get ID
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	//set, get Passwd
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd() {
		return passwd;
	}
	//set, get Name
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	//set, get Gender
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Boolean getGender() {
		return gender;
	}
	//set, get Address
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	//set, get contact
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
}
