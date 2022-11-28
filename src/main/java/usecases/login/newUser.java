package usecases.login;

public class newUser {
	
	private String fName;
	private String lName;
	private String userName;
	private String password;
	private double budget;
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public newUser(String fName, String lName, String userName, String password, double budget) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.password = password;
		this.budget = budget;
	}
	
	


}
