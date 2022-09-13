package bean;

//利用者情報のBean
public class User implements java.io.Serializable
{
	private String userId;//ユーザID
	private String userName;//ユーザ名
	private String adminFlag;//管理者フラグ

	//getter
	public String getUserId()
	{
		return userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getAdminFlag()
	{
		return adminFlag;
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
	public void setAdminFlag(String adminFlag)
	{
		this.adminFlag=adminFlag;
	}
}