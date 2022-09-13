package bean;

//ブックマーク登録用のBean
public class Bookmark implements java.io.Serializable
{
	private String userId;//ユーザID
	private String year;//年度
	private int questionNo;//問題番号

	//getter
	public String getUserId()
	{
		return userId;
	}
	public String getYear()
	{
		return year;
	}
	public int getQuestionNo()
	{
		return questionNo;
	}
	
	//setter
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setYear(String year)
	{
		this.year=year;
	}
	public void setQuestionNo(int questionNo)
	{
		this.questionNo=questionNo;
	}
}
