package bean;

//個人正答率計算結果を、JSPに渡す用のBean（リストにしてJSPに返す）
public class PersonalResult implements java.io.Serializable
{
	//フィールド
	//累計実施回数、実施問題数、難易度、総合正答率、ストラテジ系正答率、テクノロジ系正答率、マネジメント系正答率
	private int countUnit;
	private int totalQuestionCount;
	private String difficulty;
	private String correctOnTotal;
	private String correctOnStrategy;
	private String correctOnTech;
	private String correctOnManage;
	
	
	//getter
	public int getCountUnit()
	{
		return this.countUnit;
	}
	
	public int getTotalQuestionCount()
	{
		return this.totalQuestionCount;
	}
	
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public String getCorrectOnTotal()
	{
		return this.correctOnTotal;
	}
	
	public String getCorrectOnStrategy()
	{
		return this.correctOnStrategy;
	}
	
	public String getCorrectOnTech()
	{
		return this.correctOnTech;
	}
	
	public String getCorrectOnManage()
	{
		return this.correctOnManage;
	}
	
	
	//setter
	public void setCountUnit(int countUnit)
	{
		this.countUnit = countUnit;
	}
	
	public void setTotalQuestionCount(int totalQuestionCount)
	{
		this.totalQuestionCount = totalQuestionCount;
	}
	
	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setCorrectOnTotal(String correctOnTotal)
	{
		this.correctOnTotal = correctOnTotal;
	}
	
	public void setCorrectOnStrategy(String correctOnStrategy)
	{
		this.correctOnStrategy = correctOnStrategy;
	}
	
	public void setCorrectOnTech(String correctOnTech)
	{
		this.correctOnTech = correctOnTech;
	}
	
	public void setCorrectOnManage(String correctOnManage)
	{
		this.correctOnManage = correctOnManage;
	}
	
}