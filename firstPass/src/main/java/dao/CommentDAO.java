package dao;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import bean.Comment;


public class CommentDAO extends DAO
{
	//コメントを表示するための検索メソッド
	public  List<Comment>search(Comment comment) throws Exception
	{
		
		Connection con = null;
		PreparedStatement st = null;
		List<Comment> commentList = null;
		
		try
		{
			//コネクション接続
			con = getConnection();
			
			//SQL文作成
			st = con.prepareStatement("SELECT * FROM COMMENT"
					+ " where YEAR=?"
					+ " AND QUESTION_NO=?");
			st.setString(1, comment.getYear());
			st.setInt(2, comment.getQuestionNo());
			
			//SQL実行
			ResultSet rs = st.executeQuery();
			
			//検索結果の格納
			commentList = new ArrayList<Comment>();
			DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			while (rs.next())
			{
				Comment commentDB = new Comment();
				commentDB.setYear(rs.getString("YEAR"));
				commentDB.setQuestionNo(rs.getInt("QUESTION_NO"));
				commentDB.setCommentDate(dtformat.format(rs.getTimestamp("COMMENT_DATE").toLocalDateTime()));
				commentDB.setUserId(rs.getString("USER_ID"));
				commentDB.setComment(rs.getString("COMMENT"));
				commentList.add(commentDB);
			}
			
			st.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try
				{
					//コネクション切断
					con.close();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
		
		return commentList;
		
	}
	
	//コメント登録のメソッド
	public int insert(Comment comment) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			//コネクション接続
			con = getConnection();
			con.setAutoCommit(false);
		
			//SQL文の作成
			st = con.prepareStatement("INSERT INTO COMMENT"
					+ " VALUES(?, ?, now(), ?, ?)");
			st.setString(1, comment.getYear());
			st.setInt(2, comment.getQuestionNo());
			st.setString(3, comment.getUserId());
			st.setString(4, comment.getComment());
			
			//SQL実行
			line = st.executeUpdate();
			
			//登録成否の判定
			if(line != 1)
			{
				con.rollback();
				line =  0;
			}else
			{
				con.commit();
			}
			
			st.close();
			
		}catch(SQLException e)
		{
			try
			{
				con.rollback();
			}
			catch (SQLException e2)
			{
				e2.printStackTrace();
			}
		}
		finally
		{
			if(con != null)
			{
				try
				{
					//コネクション切断
					con.setAutoCommit(true);
					con.close();
				}
				catch (SQLException e3)
				{
					e3.printStackTrace();
				}
			}
		}
		
		return line;
	}
}