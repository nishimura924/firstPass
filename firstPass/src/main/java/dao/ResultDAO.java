package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import bean.AllResult;
import bean.Result;
import bean.UnitResult;

public class ResultDAO extends DAO
{
	//一般USERの回答結果を登録するメソッド
	public int insert(Result result) throws Exception
	{
		
		int line = 0;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			//コネクション接続
			con = getConnection();
			con.setAutoCommit(false);
		
			//SQL文の作成
			st = con.prepareStatement("INSERT INTO RESULT"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, now())");
			st.setInt(1, result.getCountUnit());
			st.setString(2, result.getYear());
			st.setInt(3, result.getQuestionNo());
			st.setString(4, result.getGenre());
			st.setString(5, result.getUserId());
			st.setString(6, result.getCorrect());
			st.setString(7, result.getDifficulty());
			
			//SQL実行
			line = st.executeUpdate();
			
			//登録成否の判定
			if(line != 1)
			{
				con.rollback();
				line = 0;
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
					//コネクション切断
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
	
	//引数の値からDBを検索し、累積実施回数を返す　メソッド名仮置き
		public int getMaxCountUnit(String userId)throws Exception
		{
			//戻り用変数 0で初期化
			int countUnit =0;
			//nullで初期化
			Connection con = null;
			try
			{
				//コネクションの取得
				con = getConnection();
			
				PreparedStatement st;
				st=con.prepareStatement("SELECT"
						+ " MAX(COUNT_UNIT) AS COUNT_UNIT"
						+ " FROM RESULT"
						+ " WHERE USER_ID=?");
				
				//userIdをセット
				st.setString(1, userId);
				
				//SQLの実行と結果の取得
				ResultSet rs = st.executeQuery();
			
				while(rs.next())
				{
					countUnit = rs.getInt("COUNT_UNIT");
				}
			
				st.close();
				return countUnit;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return countUnit;
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
		
		
		//個人過去実績取得用
		public List<UnitResult> getPersonalResult(String userId)throws Exception
		{
			//戻り値用リストの生成
			List<UnitResult> urList = new ArrayList<UnitResult>();
			
			//nullで初期化
			Connection con = null;
			try
			{
				//コネクションの取得
				con = getConnection();
				
				PreparedStatement st;
				st = con.prepareStatement("SELECT"
						+ " COUNT_UNIT"
						+ ",GENRE,DIFFICULTY"
						+ ",COUNT(*)"
						+ ",SUM(IS_CORRECT)"
						+ " FROM RESULT"
						+ " WHERE USER_ID =?"
						+ " GROUP BY　COUNT_UNIT"
						+ ",GENRE"
						+ ",DIFFICULTY"
						+ " ORDER BY COUNT_UNIT DESC"
						+ ",GENRE;");
				
				//userIdをセット
				st.setString(1, userId);
				
				//SQLの実行と結果の取得
				ResultSet rs = st.executeQuery();
				
				//取得件数分、UnitResultBeanを生成
				while(rs.next())
				{
					UnitResult unitRs = new UnitResult();
					unitRs.setCountUnit(rs.getInt("COUNT_UNIT")); //累計実施回数
					unitRs.setGenre(rs.getString("GENRE")); //分野
					unitRs.setIsCorrectCount(rs.getInt("SUM(IS_CORRECT)")); //正答数
					unitRs.setTotalCountOfGenre(rs.getInt("COUNT(*)")); //解答数
					unitRs.setDifficulty(rs.getString("DIFFICULTY")); //難易度
					
					//戻り値用リストに入れる
					urList.add(unitRs);
				}
				
				st.close();
				return urList;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				List<UnitResult> dummy = new ArrayList<UnitResult>();
				return dummy;
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
		//「ランキング」用のメソッド
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
					st=con.prepareStatement("SELECT"
							+ " USER.USER_NAME"
							+ ",COUNT(RESULT.COUNT_UNIT) AS 'count'"
							+ ",SUM(RESULT.IS_CORRECT)  AS 'sum'"
							+ ",(100.0*(SUM(RESULT.IS_CORRECT))/ ( COUNT(RESULT.COUNT_UNIT))) AS 'rate'"
							+ " FROM RESULT"
							+ " INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID"
							+ " WHERE　DIFFICULTY =?"
							+ " AND　RESULT.GENRE"
							+ " IN("+ genreCond +")"
									+ "　AND RESULT.ANSWER_DATE >= ?"
									+ " AND RESULT.ANSWER_DATE <= ?"
									+ " GROUP BY USER.USER_NAME"
									+ " ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");	
				}
				//正答数でソートした場合
				else if(sort.equals("SUM(IS_CORRECT)"))
				{
					st=con.prepareStatement("SELECT"
							+ " USER.USER_NAME"
							+ ",COUNT(RESULT.COUNT_UNIT) AS 'count'"
							+ ",SUM(RESULT.IS_CORRECT) AS 'sum'"
							+ ",(100.0*(SUM(RESULT.IS_CORRECT))/ ( COUNT(RESULT.COUNT_UNIT))) AS 'rate'"
							+ " FROM RESULT"
							+ " INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID"
							+ " WHERE DIFFICULTY =?"
							+ " AND RESULT.GENRE"
							+ " IN("+ genreCond +")"
									+ "　AND RESULT.ANSWER_DATE >= ?"
									+ " AND RESULT.ANSWER_DATE <= ?"
									+ " GROUP BY USER.USER_NAME"
									+ " ORDER BY SUM(RESULT.IS_CORRECT) DESC;");	
				}
				//正答率でソートした場合
				else if(sort.equals("collectRate"))
				{
					st=con.prepareStatement("SELECT"
							+ " USER.USER_NAME"
							+ ",COUNT(RESULT.COUNT_UNIT) AS 'count'"
							+ ",SUM(RESULT.IS_CORRECT) AS 'sum'"
							+ ",(100.0*(SUM(RESULT.IS_CORRECT))/ ( COUNT(RESULT.COUNT_UNIT))) AS 'rate'"
							+ " FROM RESULT"
							+ " INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID"
							+ " WHERE DIFFICULTY =?"
							+ " AND RESULT.GENRE"
							+ " IN("+ genreCond +")"
									+ "　AND RESULT.ANSWER_DATE >= ?"
									+ " AND RESULT.ANSWER_DATE <= ?"
									+ " GROUP BY USER.USER_NAME"
									+ " ORDER BY (100 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) DESC;");	
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
					st=con.prepareStatement("SELECT"
							+ " USER.USER_NAME"
							+ ",COUNT(RESULT.COUNT_UNIT) AS 'count'"
							+ ",SUM(RESULT.IS_CORRECT) AS 'sum'"
							+ ",(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate'"
							+ " FROM RESULT"
							+ " INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID"
							+ " WHERE RESULT.GENRE"
							+ " IN("+ genreCond +")"
									+ "　AND RESULT.ANSWER_DATE >= ?"
									+ " AND RESULT.ANSWER_DATE <= ?"
									+ " GROUP BY USER.USER_NAME"
									+ " ORDER BY COUNT(RESULT.COUNT_UNIT) DESC;");	
				}	
				//正答数でソートした場合
				else if(sort.equals("SUM(IS_CORRECT)"))
				{
					st=con.prepareStatement("SELECT"
							+ " USER.USER_NAME"
							+ ",COUNT(RESULT.COUNT_UNIT) AS 'count'"
							+ ",SUM(RESULT.IS_CORRECT) AS 'sum'"
							+ ",(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate'"
							+ " FROM RESULT INNER"
							+ " JOIN USER ON RESULT.USER_ID=USER.USER_ID"
							+ " WHERE RESULT.GENRE"
							+ " IN("+ genreCond +")"
									+ "　AND RESULT.ANSWER_DATE >= ?"
									+ " AND RESULT.ANSWER_DATE <= ?"
									+ " GROUP BY USER.USER_NAME"
									+ " ORDER BY SUM(RESULT.IS_CORRECT='1') DESC;");	
				}	
				//正答率でソートした場合
				else if(sort.equals("collectRate"))
				{
					st=con.prepareStatement("SELECT USER.USER_NAME,COUNT(RESULT.COUNT_UNIT) AS 'count',SUM(RESULT.IS_CORRECT) AS 'sum',(100.0 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) AS 'rate' FROM RESULT INNER JOIN USER ON RESULT.USER_ID=USER.USER_ID WHERE RESULT.GENRE IN("+ genreCond +")AND RESULT.ANSWER_DATE >= ? AND RESULT.ANSWER_DATE <= ? GROUP BY USER.USER_NAME ORDER BY (100 * (SUM(RESULT.IS_CORRECT)) / ( COUNT(RESULT.COUNT_UNIT))) DESC;");	
				}	
					st.setDate(1,(java.sql.Date) answerDateFrom);
					st.setDate(2,(java.sql.Date) answerDateTo);
			}
			//メソッド共通機能（beanへの設定）
			ResultSet rs =st.executeQuery();
			int rank = 1;
			while(rs.next())
			{
				AllResult showResult = new AllResult();
				showResult.setRank(rank);
				showResult.setUserId(rs.getString("USER.USER_NAME"));
				//showResult.setAnswerCount(rs.getInt("COUNT(RESULT.COUNT_UNIT)"));
				showResult.setAnswerCount(rs.getInt("count"));
				//showResult.setCorrectCount(rs.getInt("SUM(RESULT.IS_CORRECT)")+"");
				showResult.setCorrectCount(rs.getInt("sum"));
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