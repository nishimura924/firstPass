package dao;

import bean.Result;
import bean.AllResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
//import java.sql.Date;
import javax.servlet.http.*;


public class ResultDAO extends DAO
{
	public List<AllResult> selectResult(String difficulty,String sort,Date answerDateFrom,Date answerDateTo,AllResult allResult)throws Exception
	{
		//リストの取得
		List<AllResult> list = new ArrayList<>();
		//DBとの接続
		Connection con = getConnection();
		
		//引数の条件Beanの分野リストを取得
		String[] genre = allResult.getGenre();
		//分野条件を連結
		String genreCond = joinCond(genre);	
		
		//難易度を「normalのみで選択した場合」
		if(difficulty.equals("0"))
		{
			//回答数でソートした場合
			if(sort.equals("SUM(COUNT_UNIT)"))
			{
				PreparedStatement st;
				st=con.prepareStatement("SELECT USER_ID,SUM(COUNT_UNIT),COUNT(IS_CORRECT) FROM RESULT WHERE DIFFICULTY like ? AND GENRE IN("+ genreCond +")AND IS_CORRECT='1'AND ANSWER_DATE >= ? AND ANSWER_DATE <= ? GROUP BY USER_ID ORDER BY SUM(COUNT_UNIT) DESC;");
				st.setString(1,"%"+difficulty+"%");
				st.setDate(2,(java.sql.Date) answerDateFrom);
				st.setDate(3,(java.sql.Date) answerDateTo);
				ResultSet rs =st.executeQuery();
		
				//session.setAttribute("answerDateFrom",(java.sql.Date) answerDateFrom);
				
				
				while(rs.next())
				{
					
					//AllResult allResult = new AllResult();
					allResult.setUserId(rs.getString("USER_ID"));
					allResult.setAnswerCount(rs.getString("SUM(COUNT_UNIT)"));
					allResult.setCorrectCount(rs.getString("COUNT(IS_CORRECT)"));
					list.add(allResult);
					
					
				}
		
				st.close();
				}
			//正答数でソートした場合
			else if(sort.equals("COUNT(IS_CORRECT)"))
			{
				PreparedStatement st;
				st=con.prepareStatement("SELECT USER_ID,SUM(COUNT_UNIT),COUNT(IS_CORRECT) FROM RESULT WHERE DIFFICULTY like ? AND GENRE IN("+ genreCond +") AND IS_CORRECT='1'AND ANSWER_DATE >=? AND ANSWER_DATE <= ? GROUP BY USER_ID ORDER BY SUM(COUNT_UNIT) DESC;");
				st.setString(1,"%"+difficulty+"%");
				st.setDate(2,(java.sql.Date) answerDateFrom);
				st.setDate(3,(java.sql.Date) answerDateTo);
				ResultSet rs =st.executeQuery();
			
				while(rs.next())
				{
					//AllResult allResult = new AllResult();
					allResult.setUserId(rs.getString("USER_ID"));
					allResult.setAnswerCount(rs.getString("SUM(COUNT_UNIT)"));
					allResult.setCorrectCount(rs.getString("COUNT(IS_CORRECT)"));
					list.add(allResult);
				}
				st.close();
			}
		}
		else if(difficulty.equals("1"))
		{
			//回答数でソートした場合
			if(sort.equals("SUM(COUNT_UNIT)"))
			{
				PreparedStatement st;
				st=con.prepareStatement("SELECT USER_ID,SUM(COUNT_UNIT),COUNT(IS_CORRECT) FROM RESULT WHERE GENRE IN("+ genreCond +")AND IS_CORRECT='1'AND ANSWER_DATE >= ? AND ANSWER_DATE <= ? GROUP BY USER_ID ORDER BY SUM(COUNT_UNIT) DESC;");
				//st.setString(1,"%"+difficulty+"%");
				st.setDate(1,(java.sql.Date) answerDateFrom);
				st.setDate(2,(java.sql.Date) answerDateTo);
				ResultSet rs =st.executeQuery();
		
				//session.setAttribute("answerDateFrom",(java.sql.Date) answerDateFrom);
		
				while(rs.next())
				{
					//AllResult allResult = new AllResult();
					allResult.setUserId(rs.getString("USER_ID"));
					allResult.setAnswerCount(rs.getString("SUM(COUNT_UNIT)"));
					allResult.setCorrectCount(rs.getString("COUNT(IS_CORRECT)"));
					list.add(allResult);
				}
				st.close();
				}
			//正答数でソートした場合
			else if(sort.equals("COUNT(IS_CORRECT)"))
			{
				PreparedStatement st;
				st=con.prepareStatement("SELECT USER_ID,SUM(COUNT_UNIT),COUNT(IS_CORRECT) FROM RESULT WHERE GENRE IN("+ genreCond +") AND IS_CORRECT='1'AND ANSWER_DATE >=? AND ANSWER_DATE <= ? GROUP BY USER_ID ORDER BY SUM(COUNT_UNIT) DESC;");
				//st.setString(1,"%"+difficulty+"%");
				st.setDate(1,(java.sql.Date) answerDateFrom);
				st.setDate(2,(java.sql.Date) answerDateTo);
				ResultSet rs =st.executeQuery();
			
				while(rs.next())
				{
					//AllResult allResult = new AllResult();
					allResult.setUserId(rs.getString("USER_ID"));
					allResult.setAnswerCount(rs.getString("SUM(COUNT_UNIT)"));
					allResult.setCorrectCount(rs.getString("COUNT(IS_CORRECT)"));
					list.add(allResult);
				}
				st.close();
			}
		}
		con.close();
		return list;
	}
	
	//分野の連結メソッド
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
