package dao;

import bean.Bookmark;
import java.sql.*;

public class BookmarkDAOshimada extends DAO
{
	public int insert(Bookmark bookmark) throws Exception
	{
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
		
			st = con.prepareStatement("INSERT INTO BOOKMARK VALUES(?, ?, ?)");
			st.setString(1, bookmark.getUserId());
			st.setString(2, bookmark.getYear());
			st.setInt(3, bookmark.getQuestionNo());
		
			line = st.executeUpdate();
			
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
	

	public int delete(Bookmark bookmark) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
		
			st = con.prepareStatement("DELETE FROM BOOKMARK WHERE USER_ID=? AND YEAR=? AND QUESTION_NO=?");
			st.setString(1, bookmark.getUserId());
			st.setString(2, bookmark.getYear());
			st.setInt(3, bookmark.getQuestionNo());
		
			line = st.executeUpdate();
			
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