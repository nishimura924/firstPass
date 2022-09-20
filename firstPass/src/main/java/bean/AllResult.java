package bean;

//利用者情報のBean
public class AllResult implements java.io.Serializable
{
	private String userId;//ユーザID
	private String answerCount;//回答数
	private String correctCount;//正答数
	private int correctRate;//正答率

	//getter
	public String getUserId()
	{
		return userId;
	}
	public String getAnswerCount()
	{
		return answerCount;
	}
	public String getCorrectCount()
	{
		return correctCount;
	}
	public int getCorrectRate()
	{
		return correctRate;
	}
	
	
	//setter
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setAnswerCount(String answerCount)
	{
		this.answerCount=answerCount;
	}
	public void setCorrectCount(String correctCount)
	{
		this.correctCount=correctCount;
	}
	public void setCorrectRate(int correctRate)
	{
		this.correctRate=correctRate;
	}

}