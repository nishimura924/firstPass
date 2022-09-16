package dao;

import java.sql.*;
import java.util.*;
import bean.Result;

public class ResultDAOshimada extends DAO
{
	//引数の値からDBを検索し、累積実施回数を返す　メソッド名仮置き
	public int getCountUnit(String userId)throws Exception
	{
		//戻り用変数 0で初期化
		int countUnit =0;
		
		//コネクションの取得
		Connection con = getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT MAX(COUNT_UNIT) AS COUNT_UNIT");
		
		//SQLの実行と結果の取得
		ResultSet rs = st.executeQuery();
		
		if(rs!=null)
		{
			countUnit = rs.getInt("COUNT_UNIT");
		}
		
		st.close();
		con.close();
		
		return countUnit;
	}
	
	public int insert(Result result) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		
		try {
			con = getConnection();
			con.setAutoCommit(false);
		
			PreparedStatement st = con.prepareStatement("INSERT INTO RESULT VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		
			st.setInt(1, result.getCountUnit());
			st.setString(2, result.getYear());
			st.setInt(3, result.getQuestionNo());
			st.setString(4, result.getGenre());
			st.setString(5, result.getUserId());
			st.setString(6, result.getCorrect());
			st.setString(7, result.getDifficulty());
			st.setDate(8, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		
			line = st.executeUpdate();
			
			st.close();
			
			if(line != 1)
			{
				con.rollback();
				con.setAutoCommit(true);
				con.close();
				return 0;
			}
			
			con.commit();
		
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