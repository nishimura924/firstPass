package bean;

//利用者情報のBean
public class User implements java.io.Serializable
{
	private String userId;//ユーザID
	private String userName;//ユーザ名
	private String userPass;//ユーザパスワード
	private String adminFlag;//管理者フラグ
	private int countUnit; //累計実施回数

	//getter
	public String getUserId()
	{
		return userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getUserPass()
	{
		return userPass;
	}
	public String getAdminFlag()
	{
		return adminFlag;
	}
	
	public int getCountUnit()
	{
		return this.countUnit;
	}
	
	//setter
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	public void setUserPass(String userPass)
	{
		this.userPass=userPass;
	}
	public void setAdminFlag(String adminFlag)
	{
		this.adminFlag=adminFlag;
	}
	
	public void setCountUnit(int countUnit)
	{
		this.countUnit = countUnit;
	}
}