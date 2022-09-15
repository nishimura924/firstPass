package bean;

//個人正答率計算結果を、JSPに渡す用のBean（リストにしてJSPに返す）
public class PersonalResult implements java.io.Serializable
{
	//フィールド
	//累計実施回数、実施問題数、難易度、総合正答率、ストラテジ系正答率、テクノロジ系正答率、マネジメント系正答率
	private int countUnit;
	private int totalQuestionCount;
	private String difficulty;
	private int correctOnTotal;
	private int correctOnStrategy;
	private int correctOnTech;
	private int correctOnManage;
	
	
	//getter
	public int getCountUnit()
	{
		return this.countUnit;
	}
	
	public int getTotalQuestinCount()
	{
		return this.totalQuestionCount;
	}
	
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public int getCorrectOnTotal()
	{
		return this.correctOnTotal;
	}
	
	public int getCorrectOnStrategy()
	{
		return this.correctOnStrategy;
	}
	
	public int getCorrectOnTech()
	{
		return this.correctOnTech;
	}
	
	public int getCorrectOnManage()
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
	
	public void setCorrectOnTotal(int correctOnTotal)
	{
		this.correctOnTotal = correctOnTotal;
	}
	
	public void setCorrectOnStrategy(int correctOnStrategy)
	{
		this.correctOnStrategy = correctOnStrategy;
	}
	
	public void setCorrectOnTech(int correctOnTech)
	{
		this.correctOnTech = correctOnTech;
	}
	
	public void setCorrectOnManage(int correctOnManage)
	{
		this.correctOnManage = correctOnManage;
	}
	
}