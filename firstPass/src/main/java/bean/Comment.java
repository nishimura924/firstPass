package bean;

import java.time.*;

//コメント入力用Bean
public class Comment implements java.io.Serializable
{
	private String year;//年度
	private int questionNo;//問題番号
	//private LocalDateTime commentDate;//コメント日時
	private String commentDate;//コメント日時、フォーマット変換するためString
	private String userId;//ユーザID
	private String comment;//コメント内容
	
	//getter
	public String getYear()
	{
		return year;
	}
	public int getQuestionNo()
	{
		return questionNo;
	}
	
	//public LocalDateTime getCommentDate()
	public String getCommentDate()
	{
		return commentDate;
	}
	public String getUserId()
	{
		return userId;
	}
	public String getComment()
	{
		return comment;
	}
	
	//setter
	public void setYear(String year)
	{
		this.year=year;
	}
	public void setQuestionNo(int questionNo)
	{
		this.questionNo=questionNo;
	}
	//public void setCommentDate(LocalDateTime commentDate)
	public void setCommentDate(String commentDate)
	{
		this.commentDate=commentDate;
	}
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setComment(String comment)
	{
		this.comment=comment;
	}
}
	