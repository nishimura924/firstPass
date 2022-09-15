package bean;

public class Conditions implements java.io.Serializable
{
	//フィールド
	//年度、分野、難易度、問題数、ブックマークのみフラグ
	private String[] year;
	private String[] genre;
	private String difficulty;
	private int questionCount;
	private String bookmarkOnly;
	
	//getter
	public String[] getYear()
	{
		return this.year;
	}
	
	public String[] getGenre()
	{
		return this.genre;
	}
	
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public int getQuestionCount()
	{
		return this.questionCount;
	}
	
	public String getBookmarkOnly()
	{
		return this.bookmarkOnly;
	}
	
	
	//setter
	public void setYear(String[] year)
	{
		this.year = year;
	}
	
	public void setGenre(String[] genre)
	{
		this.genre = genre;
	}
	
	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setQuestionCount(int questionCount)
	{
		this.questionCount = questionCount;
	}
	
	public void setBookmarkOnly(String bookmarkOnly)
	{
		this.bookmarkOnly = bookmarkOnly;
	}
	
	
}