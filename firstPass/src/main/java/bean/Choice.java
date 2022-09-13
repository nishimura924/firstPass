package bean;

public class Choice implements java.io.Serializable
{
	//フィールド
	//選択肢の内容、正解選択肢か否か
	private String choice;
	private String isCollect;
	
	//getter
	public String getChoice()
	{
		return this.choice;
	}
	
	public String getIsCollect()
	{
		return this.isCollect;
	}
	
	//setter
	public void setChoice(String choice)
	{
		this.choice = choice;
	}
	
	public void setIsCollect(String isCollect)
	{
		this.isCollect = isCollect;
	}
}