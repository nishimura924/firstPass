package bean;

import java.util.*;

public class Answer implements java.io.Serializable
{
	//フィールド
	//正誤判定結果、正解の選択肢、過去コメントのリスト
	private String correct;
	private char correctChar;
	private List<Comment> allComment;
	
	//getter
	public String getCorrect()
	{
		return this.correct;
	}
	
	public char getCorrectChar()
	{
		return this.correctChar;
	}
	
	public List<Comment> getAllComment()
	{
		return this.allComment;
	}
	
	//setter
	public void setCorrect(String correct)
	{
		this.correct = correct;
	}
	
	public void setCorrectChar(char correctChar)
	{
		this.correctChar = correctChar;
	}
	
	public void setAllComment(List<Comment> allComment)
	{
		this.allComment = allComment;
	}
}