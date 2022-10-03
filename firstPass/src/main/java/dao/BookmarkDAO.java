package dao;

import bean.Bookmark;
import java.sql.*;

public class BookmarkDAO extends DAO
{
	//ブックマーク登録用のメソッド
	public int insert(Bookmark bookmark) throws Exception
	{
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			//コネクション接続
			con = getConnection();
			con.setAutoCommit(false);
			
			//SQL文の定義
			st = con.prepareStatement("INSERT INTO BOOKMARK VALUES(?, ?, ?)");
			st.setString(1, bookmark.getUserId());
			st.setString(2, bookmark.getYear());
			st.setInt(3, bookmark.getQuestionNo());
			
			//SQLの実行
			line = st.executeUpdate();
			
			//実行成否の判定
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
	
	//ブックマーク登録解除用のメソッド
	public int delete(Bookmark bookmark) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			//コネクション接続
			con = getConnection();
			con.setAutoCommit(false);
			
			//SQL文の作成
			st = con.prepareStatement("DELETE FROM BOOKMARK WHERE USER_ID=? AND YEAR=? AND QUESTION_NO=?");
			st.setString(1, bookmark.getUserId());
			st.setString(2, bookmark.getYear());
			st.setInt(3, bookmark.getQuestionNo());
		
			//SQL実行
			line = st.executeUpdate();
			
			//SQL成否の判定
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