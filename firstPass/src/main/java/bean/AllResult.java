package bean;

//利用者情報のBean
public class AllResult implements java.io.Serializable
{
	private String userId;//ユーザID
	private int answerCount;//回答数
	private int correctCount;//正答数
	private String[] genre;
	private double correctRate;//正答率
	private int rank;//順位

	//getter
	public String getUserId()
	{
		return userId;
	}
	public int getAnswerCount()
	{
		return answerCount;
	}
	public int getCorrectCount()
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
	public void setAnswerCount(int answerCount)
	{
		this.answerCount=answerCount;
	}
	public void setCorrectCount(int correctCount)
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