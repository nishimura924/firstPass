package bean;

//個人実績をDBから抽出し、サーブレットに渡す用のBean（リストにしてサーブレットに返す）
public class UnitResult implements java.io.Serializable
{
	//フィールド
	//累計実施回数、分野、正誤、難易度、データ数
	private int countUnit;
	private String genre;
	private String isCorrect;
	private String difficulty;
	private int countData;
	
	
	//getter
	public int getCountUnit()
	{
		return this.countUnit;
				
	}
	
	public String getGenre()
	{
		return this.genre;
	}
	
	public String getIsCorrect()
	{
		return this.isCorrect;
	}
	
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public int getCountDate()
	{
		return this.countData;
	}
	
	//setter
	public void setCountUnit(int countUnit)
	{
		this.countUnit = countUnit;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	
	public void setIsCorrect(String isCorrect)
	{
		this.isCorrect = isCorrect;
	}
	
	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setCountDate(int countData)
	{
		this.countData = countData;
	}
	
	
}