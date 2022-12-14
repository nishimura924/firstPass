package bean;

//実績サマリのBean
public class SummaryOfResult implements java.io.Serializable
{
	private String year;//年度
	private int questionNo;//問題番号
	private String genre;//分野
	private String correct;//正誤
	
	//getter
	public String getYear()
	{
		return year;
	}
	public int getQuestionNo()
	{
		return questionNo;
	}
	public String getGenre()
	{
		return genre;
	}
	public String getCorrect()
	{
		return correct;
	}
	
	//setter
	public void setYear(String year)
	{
		this.year=year;
	}
	public void setQuestionNo(int questionNo)
	{
		this.questionNo=questionNo;
	}
	public void setGenre(String genre)
	{
		this.genre=genre;
	}
	public void setCorrect(String correct)
	{
		this.correct=correct;
	}
	
}