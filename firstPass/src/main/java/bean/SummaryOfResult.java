package bean;

//実績サマリのBean
public class SummaryOfResult implements java.io.Serializable
{
	private String year;//年度
	private int questionNo;//問題番号
	private String genle;//分野
	private String collect;//正誤
	
	//getter
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
	public String getCollect()
	{
		return collect;
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
	public void setGenle(String genle)
	{
		this.genle=genle;
	}
	public void setCollect(String collect)
	{
		this.collect=collect;
	}
	
}