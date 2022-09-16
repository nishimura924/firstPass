package bean;

public class Question implements java.io.Serializable
{
	//フィールド
	//インデックス、年度、問題番号、分野、問題文、問題文画像、選択肢１、選択肢２、選択肢３、選択肢４、選択肢画像フラグ、ブックマーク登録フラグ
	private int index;
	private String year;
	private int questionNo;
	private String genre;
	private String question;
	private String questionPic;
	private Choice choice1;
	private Choice choice2;
	private Choice choice3;
	private Choice choice4;
	private String choicePicFlg;
	private String bookmarkFlg;
	
	
	//getter
	public int getIndex()
	{
		return this.index;
	}
	
	public String getYear()
	{
		return this.year;
	}
	
	public int getQuestionNo()
	{
		return this.questionNo;
	}	
	
	public String getGenre()
	{
		return this.genre;
	}
	
	public String getQuestion()
	{
		return this.question;
	}
	
	public String getQuestionPic()
	{
		return this.questionPic;
	}
	
	public Choice getChoice1()
	{
		return this.choice1;
	}
	
	public Choice getChoice2()
	{
		return this.choice2;
	}
	
	public Choice getChoice3()
	{
		return this.choice3;
	}
	
	public Choice getChoice4()
	{
		return this.choice4;
	}
	
	public String getChoicePicFlg()
	{
		return this.choicePicFlg;
	}
	
	public String getBookmarkFlg()
	{
		return this.bookmarkFlg;
	}
	
	//setter
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setYear(String year)
	{
		this.year = year;
	}
	
	public void setQuestionNo(int questionNo)
	{
		this.questionNo = questionNo;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	
	public void setQuestion(String question)
	{
		this.question = question;
	}
	
	public void setQuestionPic(String questionPic)
	{
		this.questionPic = questionPic;
	}
	
	public void setChoice1(Choice choice1)
	{
		this.choice1 = choice1;
	}
	
	public void setChoice2(Choice choice2)
	{
		this.choice2 = choice2;
	}
	
	public void setChoice3(Choice choice3)
	{
		this.choice3 = choice3;
	}
	
	public void setChoice4(Choice choice4)
	{
		this.choice4 = choice4;
	}
	
	public void setChoicePicFlg(String choicePicFlg)
	{
		this.choicePicFlg = choicePicFlg;
	}
	
	public void setBookmarkFlg(String bookmarkFlg)
	{
		this.bookmarkFlg = bookmarkFlg;
	}
	
}	
	
	