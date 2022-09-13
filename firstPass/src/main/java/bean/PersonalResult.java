package bean;

//個人正答率計算結果を、JSPに渡す用のBean（リストにしてJSPに返す）
public class PersonalResult implements java.io.Serializable
{
	//フィールド
	//累計実施回数、実施問題数、難易度、総合正答率、ストラテジ系正答率、テクノロジ系正答率、マネジメント系正答率
	private int countUnit;
	private int totalQuestionCount;
	private String difficulty;
	private int collectOnTotal;
	private int collectOnStrategy;
	private int collectOnTech;
	private int collectOnManage;
	
	
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
	
	public int getCollectOnTotal()
	{
		return this.collectOnTotal;
	}
	
	public int getCollectOnStrategy()
	{
		return this.collectOnStrategy;
	}
	
	public int getCollectOnTech()
	{
		return this.collectOnTech;
	}
	
	public int getCollectOnManage()
	{
		return this.collectOnManage;
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
	
	public void setCollectOnTotal(int collectOnTotal)
	{
		this.collectOnTotal = collectOnTotal;
	}
	
	public void setCollectOnStrategy(int collectOnStrategy)
	{
		this.collectOnStrategy = collectOnStrategy;
	}
	
	public void setCollectOnTech(int collectOnTech)
	{
		this.collectOnTech = collectOnTech;
	}
	
	public void setCollectOnManage(int collectOnManage)
	{
		this.collectOnManage = collectOnManage;
	}
	
}