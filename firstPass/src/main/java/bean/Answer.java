package bean;

import java.util.*;

public class Answer implements java.io.Serializable
{
	//フィールド
	//正誤判定結果、正解の選択肢、過去コメントのリスト
	private String collect;
	private char collectChar;
	private List<Comment> allComment;
	
	//getter
	public String getCollect()
	{
		return this.collect;
	}
	
	public char getCollectChar()
	{
		return this.collectChar;
	}
	
	public List<Comment> getAllComment()
	{
		return this.allComment;
	}
	
	//setter
	public void setCollect(String collect)
	{
		this.collect = collect;
	}
	
	public void setCollectChar(char collectChar)
	{
		this.collectChar = collectChar;
	}
	
	public void setAllComment(List<Comment> allComment)
	{
		this.allComment = allComment;
	}
}