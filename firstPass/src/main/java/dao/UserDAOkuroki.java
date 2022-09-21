package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOkuroki extends DAO
{
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
				user.setAdminFlag(rs.getString("ADMIN_FLG"));
				
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