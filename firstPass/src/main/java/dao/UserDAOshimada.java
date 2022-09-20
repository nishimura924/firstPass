package dao;

import bean.User;
import java.sql.*;
import java.util.*;

public class UserDAOshimada extends DAO
{
	public int search(String userId, String userPassword, String userName) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			
			if(userId.equals("")) {
				st.close();
				line =  -1;
			}
			//ユーザID重複確認
			else if(userPassword.equals(""))
			{
				st = con.prepareStatement("SELECT * FROM USER where USER_ID!=? AND USER_NAME=?");
				st.setString(1, userId);
				st.setString(2, userName);	
				ResultSet rs = st.executeQuery();
				st.close();
				while (rs.next())
				{
					line += 1;
				}
			}
			//パスワード一致確認
			else if(userName.equals(""))
			{
				st = con.prepareStatement("SELECT * FROM USER where USER_ID=? AND USER_PASSWORD=?");
				st.setString(1, userId);
				st.setString(2, userPassword);
				ResultSet rs = st.executeQuery();
				st.close();
				while (rs.next())
				{
					line += 1;
				}
			}
			else
			{
				st.close();
				line =  -1;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			
		}finally
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
		
		return line;
	}
	
	
	public boolean update(String userId, String userPassword, String userName) throws Exception
	{
		Connection con = null;
		boolean isOK = false;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			con.setAutoCommit(false);
			
			if(userId.equals("")) {
				st.close();
				isOK =  false;
			}
			//ユーザ名更新
			else if(userPassword.equals(""))
			{
				st = con.prepareStatement("UPDATE USER SET USER_NAME=? WHERE USER_ID=?");
				st.setString(1, userName);
				st.setString(2, userId);
				int line = st.executeUpdate();
				st.close();
				if(line != 1)
				{
					con.rollback();
					isOK = false;
				}else
				{
					con.commit();
					isOK = true;
				}
				
			}
			//ユーザパスワード更新
			else if(userName.equals(""))
			{
				st = con.prepareStatement("UPDATE USER SET USER_PASSWORD=? WHERE USER_ID=?");
				st.setString(1, userPassword);
				st.setString(2, userId);
				int line = st.executeUpdate();
				st.close();
				if(line != 1)
				{
					con.rollback();
					isOK =  false;
				}else
				{
					con.commit();
					isOK = true;
				}
			}
			else
			{
				st.close();
				isOK =  false;
			}
		}
		catch(SQLException e)
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
		
		return isOK;
	}
}