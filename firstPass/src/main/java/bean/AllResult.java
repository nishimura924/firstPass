package bean;

//利用者情報のBean
public class AllResult implements java.io.Serializable
{
	private String userId;//ユーザID
	private String answerCount;//回答数
	private String correctCount;//正答数
	private String[] genre;
	private double correctRate;//正答率
	private int rank;//順位

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
	public double getCorrectRate()
	{
		return correctRate;
	}
	public String[] getGenre()
	{
		return this.genre;
	}
	public int getRank()
	{
		return this.rank;
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
	public void setCorrectRate(double correctRate)
	{
		this.correctRate=correctRate;
	}
	public void setGenre(String[] genre)
	{
		this.genre = genre;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
}