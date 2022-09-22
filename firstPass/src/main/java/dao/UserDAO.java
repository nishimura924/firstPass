package dao;

import bean.User;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO
{
	public int userNameSearch(String newUserName) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			
			//ユーザ名重複確認
			st = con.prepareStatement("SELECT USER_NAME FROM USER where USER_NAME=? GROUP BY USER_NAME");
			st.setString(1, newUserName);	
			ResultSet rs = st.executeQuery();
			
			while (rs.next())
			{
				line += 1;
			}
			
			st.close();
			
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
	
	public int userPasswordSearch(User user, String password) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			
			//パスワード一致確認
			st = con.prepareStatement("SELECT USER_PASSWORD FROM USER "
					+ "WHERE USER_ID=? AND USER_PASSWORD=?"
					+ "GROUP BY USER_PASSWORD");
			st.setString(1, user.getUserId());
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			while (rs.next())
			{
				line += 1;
			}
			
			st.close();
			
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
	public boolean userNameUpdate(User user,  String newUserName) throws Exception
	{
		Connection con = null;
		boolean isOK = false;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			con.setAutoCommit(false);
			
			st = con.prepareStatement("UPDATE USER SET USER_NAME=? WHERE USER_ID=?");
			st.setString(1, newUserName);
			st.setString(2, user.getUserId());
			int line = st.executeUpdate();
			
			if(line != 1)
			{
				con.rollback();
				isOK = false;
			}else
			{
				con.commit();
				isOK = true;
			}

			st.close();
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
	
	public boolean userPasswordUpdate(User user, String newUserPassword) throws Exception
	{
		Connection con = null;
		boolean isOK = false;
		PreparedStatement st = null;
		
		try
		{
			con = getConnection();
			con.setAutoCommit(false);
			
			st = con.prepareStatement("UPDATE USER SET USER_PASSWORD=? WHERE USER_ID=?");
			st.setString(1, newUserPassword);
			st.setString(2, user.getUserId());
			int line = st.executeUpdate();
			if(line != 1)
			{
				con.rollback();
				isOK =  false;
			}else
			{
				con.commit();
				isOK = true;
			}
			
			st.close();
			
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
	
	//引数の値からDBを検索し、オブジェクトを返すメソッド
		public User doLogin(String userId ,String password)throws Exception
		{
			//戻り値のUserをインスタンス化
			User user = new User();
			
			//初期化
			Connection con =null; 
			try
			{
				//コネクションの取得
				con = getConnection();
				
				PreparedStatement st;
				st=con.prepareStatement("select * from USER where USER_ID=? and USER_PASSWORD=?");
				
				//引数のloginとpasswordを設定
				st.setString(1, userId);
				st.setString(2, password);
				//SQLの実行と結果の取得
				ResultSet rs = st.executeQuery();
				
				while(rs.next())
				{
					//beanの生成・設定
					user.setUserId(rs.getString("USER_ID"));
					user.setUserName(rs.getString("USER_NAME"));
					user.setAdminFlag(rs.getString("ADMIN_FLAG"));
					
				}
			
				st.close();
				//作成したbeanを戻り値として返却
				return user;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return user;
			}
			finally
			{
				if(con!=null)
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
		}
}