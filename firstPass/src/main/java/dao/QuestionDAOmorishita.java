package dao;

import bean.Question;
import bean.Choice;
import bean.Conditions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class QuestionDAOmorishita extends DAO
{
	//DBに登録されている分野を返すメソッド
	public List<String> getGenre()throws Exception
	{
		//戻り値用のリスト作成
		List<String> genre = new ArrayList<String>();
		
		//コネクションの取得
		Connection con = getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT GENRE FROM QUESTION GROUP BY GENRE;");
		
		//SQLの実行と結果の取得
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			genre.add(rs.getString("GENRE"));
		}
		
		st.close();
		con.close();
		
		return genre;
		
	}
	

	//文字列連結メソッド（年度と分野の連結に使用）
	public String joinCond(String[] cond)
	{
		//返却用変数
		String joinCond = "'";
		
		for(int i =0; i<cond.length; i++)
		{
			if(i<cond.length-1)
			{
				joinCond = joinCond + cond[i] +"','";
			}
			else
			{
				joinCond = joinCond + cond[i] + "'";
			}
		}
		
		return joinCond;
	}
}