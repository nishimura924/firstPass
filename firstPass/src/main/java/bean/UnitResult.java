package bean;

//個人実績をDBから抽出し、サーブレットに渡す用のBean（リストにしてサーブレットに返す）
public class UnitResult implements java.io.Serializable
{
	//フィールド
	//累計実施回数、分野、正誤、難易度、データ数
	private int countUnit;
	private String genre;
	private int isCorrectCount;
	private String difficulty;
	private int totalCountOfGenre;
	
	
	//getter
	public int getCountUnit()
	{
		return this.countUnit;
				
	}
	
	public String getGenre()
	{
		return this.genre;
	}
	
	public int getIsCorrectCount()
	{
		return this.isCorrectCount;
	}
	
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public int getTotalCountOfGenre()
	{
		return this.totalCountOfGenre;
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
	
	public void setIsCorrectCount(int isCorrectCount)
	{
		this.isCorrectCount = isCorrectCount;
	}
	
	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setTotalCountOfGenre(int totalCountOfGenre)
	{
		this.totalCountOfGenre = totalCountOfGenre;
	}
	
	
}