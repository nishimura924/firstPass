package bean;

import java.util.Date;

//実績登録用のBean
public class Result implements java.io.Serializable
{
	private int countUnit;//累積実施回数
	private String year;//年度
	private int questionNo;//問題番号
	private String genle;//分野
	private String userId;//ユーザID
	private String correct;//正誤
	private String difficulty;//難易度
	private Date answerDate;//解答日
	
	//getter
	public int getCountUnit()
	{
		return countUnit;
	}
	public String getYear()
	{
		return year;
	}
	public int getQuestionNo()
	{
		return questionNo;
	}
	public String getGenle()
	{
		return genle;
	}
	public String getUserId()
	{
		return userId;
	}
	public String getCorrect()
	{
		return correct;
	}
	public String getDifficulty()
	{
		return difficulty;
	}
	public Date getAnswerDate()
	{
		return answerDate;
	}
	
	
	//setter
	public void setCountUnit(int countUnit)
	{
		this.countUnit=countUnit;
	}
	public void setYear(String year)
	{
		this.year=year;
	}
	public void setQuestionNo(int questionNo)
	{
		this.questionNo=questionNo;
	}
	public void setGenle(String genle)
	{
		this.genle=genle;
	}
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setCorrect(String correct)
	{
		this.correct=correct;
	}
	public void setdifficulty(String difficulty)
	{
		this.difficulty=difficulty;
	}
	public void answerDate(Date answerDate)
	{
		this.answerDate=answerDate;
	}
}