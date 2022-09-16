package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
//import java.util.List;
//import java.util.ArrayList;

public class UserDAO extends DAO
{
	public int countUser(String userId,String userName)throws Exception
	{
		Connection con = getConnection();
		int count = 0;
		
		try
		{
			PreparedStatement st = con.prepareStatement("SELECT COUNT(*) AS kensu FROM USER WHERE USER_ID=? OR USER_NAME=? ");
			st.setString(1, userId);
			st.setString(2, userName);
			//SQLの実行・結果
			ResultSet rs = st.executeQuery();
			
			rs.next();
			count = rs.getInt("kensu");
			
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
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return count;
		
	}
	
	public int addUser (User user)throws Exception
	{
		Connection con = getConnection();
		int line = 0;
		try
		{
			PreparedStatement st = con.prepareStatement("INSERT INTO USER VALUES(?,?,?,?)");
			st.setString(1,user.getUserId());
			st.setString(2,user.getUserPass());
			st.setString(3, user.getUserName());
			st.setString(4,user.getAdminFlag());
			line = st.executeUpdate();
			
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
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return line;
	}
}