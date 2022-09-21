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


public class ResultDAOmorishita extends DAO
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
		//SQL設定を初期化
		PreparedStatement st = null;
		
		
		//難易度を「normalのみ」で選択した場合
		if(difficulty.equals("0"))
		{
			//回答数でソートした場合
			if(sort.equals("COUNT(COUNT_UNIT)"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) ,(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE DIFFICULTY =? AND RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");	
				//st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE DIFFICULTY =? AND RESULT.GENRE IN("+ genreCond +")AND  RESULT.ANSWER_DATE >=? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");
			}
			//正答数でソートした場合
			else if(sort.equals("SUM(IS_CORRECT)"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) ,(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE DIFFICULTY =? AND RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY SUM(RESULT.IS_CORRECT) DESC;");	
				//st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE DIFFICULTY =? AND RESULT.GENRE IN("+ genreCond +")AND  RESULT.ANSWER_DATE >=? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY SUM(RESULT.IS_CORRECT) DESC;");	
			}
			//正答率でソートした場合
			else if(sort.equals("collectRate"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) ,(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE DIFFICULTY =? AND RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY (100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) DESC;");	
			}	
			
				st.setString(1,difficulty);
				st.setDate(2,(java.sql.Date) answerDateFrom);
				st.setDate(3,(java.sql.Date) answerDateTo);
				
		}
		//難易度を「normal＆easy」で選択した場合
		else if(difficulty.equals("1"))
		{
			//回答数でソートした場合
			if(sort.equals("COUNT(COUNT_UNIT)"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) ,(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");	
				//st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND  RESULT.ANSWER_DATE >=? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");
			}	
			//正答数でソートした場合
			else if(sort.equals("SUM(IS_CORRECT)"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT),(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY SUM(RESULT.IS_CORRECT='1') DESC;");	
				//st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT) FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND  RESULT.ANSWER_DATE >=? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY SUM(RESULT.IS_CORRECT='1') DESC;");
			}	
			//正答率でソートした場合
			else if(sort.equals("collectRate"))
			{
				st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT),SUM(RESULT.IS_CORRECT),(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY (100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) DESC;");	
			}	
				st.setDate(1,(java.sql.Date) answerDateFrom);
				st.setDate(2,(java.sql.Date) answerDateTo);
		}
		//メソッド共通機能（beanへの設定）
		ResultSet rs =st.executeQuery();
		while(rs.next())
		{
			AllResult showResult = new AllResult();
			showResult.setUserId(rs.getString("USER.USER_NAME"));
			showResult.setAnswerCount(rs.getString("COUNT(RESULT.COUNT_UNIT)"));
			showResult.setCorrectCount(rs.getString("SUM(RESULT.IS_CORRECT)"));
			showResult.setCorrectRate(rs.getDouble("rate"));
			list.add(showResult);
		}
		st.close();
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
