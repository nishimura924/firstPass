package dao;

import bean.User;
import java.sql.*;
import java.util.*;

public class UserDAOshimada extends DAO
{
	public int search(String userId, String userPassword, String userName) throws Exception
	{
		
		Connection con = getConnection();
		
		//一旦ダミーのSQL文格納
		PreparedStatement st = con.prepareStatement("SELECT * FROM USER");
		
		
		if(userId.equals("")) {
			st.close();
			con.close();
			return -1;
		}
		//重複確認
		else if(userPassword.equals(""))
		{
			st = con.prepareStatement("SELECT * FROM USER where USER_ID!=? AND USER_NAME=?");
			st.setString(1, userId);
			st.setString(2, userName);
			
		}
		//一致確認
		else if(userName.equals(""))
		{
			st = con.prepareStatement("SELECT * FROM USER where USER_ID=? AND USER_PASSWORD=?");
			st.setString(1, userId);
			st.setString(2, userPassword);
		}
		else
		{
			st.close();
			con.close();
			return -1;
		}

		ResultSet rs = st.executeQuery();
		int line = 0;
		
		while (rs.next())
		{
			line += 1;
		}
		
		st.close();
		con.close();
		
		return line;
	}
	
	
	public boolean update(String userId, String userPassword, String userName) throws Exception
	{
		Connection con = getConnection();
		con.setAutoCommit(false);
		
		//一旦ダミーのSQL文格納
		PreparedStatement st = con.prepareStatement("UPDATE USER SET USER_NAME='dummy' WHERE USER_ID='dummy'");
		
		if(userId.equals("")) {
			st.close();
			con.close();
			return false;
		}
		//ユーザ名更新
		else if(userPassword.equals(""))
		{
			st = con.prepareStatement("UPDATE USER SET USER_NAME=? WHERE USER_ID=?");
			st.setString(1, userName);
			st.setString(2, userId);
		}
		//ユーザパスワード更新
		else if(userName.equals(""))
		{
			st = con.prepareStatement("UPDATE USER SET USER_PASSWORD=? WHERE USER_ID=?");
			st.setString(1, userPassword);
			st.setString(2, userId);
		}
		else
		{
			st.close();
			con.close();
			return false;
		}
		
		int line = st.executeUpdate();
		st.close();
			
		if(line != 1)
		{
			con.rollback();
			con.setAutoCommit(true);
			con.close();
			return false;
		}
		
		con.commit();
		con.setAutoCommit(true);
		con.close();
		return true;
	}
}