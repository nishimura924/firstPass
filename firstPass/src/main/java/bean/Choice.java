package bean;

public class Choice implements java.io.Serializable
{
	//フィールド
	//選択肢の内容、正解選択肢か否か
	private String choice;
	private String isCorrect;
	
	//getter
	public String getChoice()
	{
		return this.choice;
	}
	
	public String getIsCorrect()
	{
		return this.isCorrect;
	}
	
	//setter
	public void setChoice(String choice)
	{
		this.choice = choice;
	}
	
	public void setIsCorrect(String isCorrect)
	{
		this.isCorrect = isCorrect;
	}
}