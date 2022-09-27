package dao;

import bean.Question;
import bean.User;
import bean.Choice;
import bean.Conditions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionDAO extends DAO
{	
	//引数の値からDBを検索し、リストを返すメソッド　メソッド名仮置き
	public List<Question> setQuestion(Conditions conditions,User user)throws Exception
	{
		//戻り値用のリストの作成
		List<Question> question = new ArrayList<Question>();
		
		//引数の条件Beanの年度リストを取得し
		String[] year = conditions.getYear();
		//年度条件を連結
		String yearCond = joinCond(year);
		
		//引数の条件Beanの分野リストを取得
		String[] genre = conditions.getGenre();
		//分野条件を連結
		String genreCond = joinCond(genre);
		
		//ユーザIDを設定
		String userId="";
		//引数のUserがnullでなければ、ユーザIDをセット
		if(user!=null)
		{
			userId = user.getUserId();
		}
		
		//nullで初期化
		Connection con = null;
		
		try
		{
			//コネクションの取得
			con = getConnection();
		
			PreparedStatement st;
			st=con.prepareStatement("SELECT QUESTION.YEAR,QUESTION.QUESTION_NO,QUESTION.GENRE,QUESTION.QUESTION,QUESTION.QUESTION_FILE_NAME,QUESTION.CORRECT,QUESTION.INCORRECT_1,QUESTION.INCORRECT_2,QUESTION.INCORRECT_3,QUESTION.IS_SELECT_FILE,BOOKMARK.USER_ID FROM QUESTION LEFT JOIN BOOKMARK ON QUESTION.QUESTION_NO = BOOKMARK.QUESTION_NO AND QUESTION.YEAR = BOOKMARK.YEAR  WHERE QUESTION.YEAR IN("+ yearCond +") and QUESTION.GENRE IN("+ genreCond +") ");
			
			//SQLの実行と結果の取得
			ResultSet rs = st.executeQuery();
		
		
			//出題問題数条件の取得
			int questionCount = conditions.getQuestionCount();
			//ブックマーク条件の取得
			String bookmarkOnly = conditions.getBookmarkOnly();
			//難易度条件の取得
			String difficulty = conditions.getDifficulty();
		
			//取得結果全件格納リストを作成
			List<Question> allRs = new ArrayList<Question>();
			
			//rsの空確認用変数
			int num=0;
			
			//全件をBeanとして生成
			while(rs.next())
			{
				num++;
				
				//QuestionBeanの生成
				Question q = new Question();
				q.setYear(rs.getString("YEAR"));
				q.setQuestionNo(rs.getInt("QUESTION_NO"));
				q.setGenre(rs.getString("GENRE"));
				q.setQuestion(rs.getString("QUESTION"));
				q.setQuestionPic(rs.getString("QUESTION_FILE_NAME"));
				q.setChoicePicFlg(rs.getString("IS_SELECT_FILE"));
				
				//ブックマークフラグの判定と設定
				//ユーザIDが一致すれば、登録あり、一致しなければ登録なし
				if(userId.equals(rs.getString("USER_ID")))
				{
					q.setBookmarkFlg("1"); //登録あり
				}
				else
				{
					q.setBookmarkFlg("0"); //登録なし				
				}
			
				//正解ChoiceBeanの生成
				Choice collect = new Choice();
				collect.setChoice(rs.getString("CORRECT"));
				collect.setIsCorrect("1");
				
				//不正解ChoiceBean1の生成
				Choice inc1 = new Choice();
				inc1.setChoice(rs.getString("INCORRECT_1"));
				inc1.setIsCorrect("0");
				
				//不正解ChoiceBean2の生成
				Choice inc2 = new Choice();
				inc2.setChoice(rs.getString("INCORRECT_2"));
				inc2.setIsCorrect("0");
			
				//不正解ChoceBean3の生成
				Choice inc3 = new Choice();
				inc3.setChoice(rs.getString("INCORRECT_3"));
				inc3.setIsCorrect("0");
				
				//不正解ChoiceBean格納用リスト
				List<Choice> incorrect = new ArrayList<Choice>();
				incorrect.add(inc1);
				incorrect.add(inc2);
				incorrect.add(inc3);
				
				//不正解リストをシャッフル
				Collections.shuffle(incorrect);
				
				//最終的な選択肢格納Set
				List<Choice> choiceList = new ArrayList<Choice>();
				
				//正解選択肢をchoiceSetに格納
				choiceList.add(collect);
							
				//難易度による選択肢数の決定
				//easy
				if(difficulty.equals("1"))
				{
					//不正解を1つchoiceListに格納
					//不正解リストshuffle後なので、常に0で取得
					choiceList.add(incorrect.get(0));
					
				}
				//normal
				else if(difficulty.equals("0"))
				{
					//不正解を全てchoiceSetに格納
					for(int i=0; i<incorrect.size(); i++)
					{
						choiceList.add(incorrect.get(i));
					}
				}
				
				//choiceListの中身をshuffle(正解も不正解もまとめてshuffle)
				Collections.shuffle(choiceList);
				
				//QuestionBeanの選択肢にセット
				if(difficulty.equals("1"))
				{
					q.setChoice1(choiceList.get(0));
					q.setChoice2(choiceList.get(1));
				}
				else if(difficulty.equals("0"))
				{
					q.setChoice1(choiceList.get(0));
					q.setChoice2(choiceList.get(1));
					q.setChoice3(choiceList.get(2));
					q.setChoice4(choiceList.get(3));
				}
				//↑ここまでで、問題をBeanにする処理完了			
				//取得結果全件格納リストに仮格納
				allRs.add(q);
				
			}
			
			//取得結果全件格納リストをshuffleし、Questionへのセット順をランダムにする
			Collections.shuffle(allRs);
			
			
			//rs空確認用変数が0のままだったら、空のquestionリストを返す
			//サーブレット側でエラー判定となる
			if(num==0)
			{
				return question;
			}
			
			//ブックマークからのみ出題希望の場合
			if(bookmarkOnly!=null)
			{
				//ブックマーク登録問題用リスト
				List<Question> bookmark = new ArrayList<Question>();
				
				//取得結果全件格納リストでループ
				for(int i=0; i<allRs.size(); i++)
				{
					//QuestionBeanのブックマークフラグを取得
					String bookmarkFlg = allRs.get(i).getBookmarkFlg();
					
					//ブックマーク登録ありだったら、
					if(bookmarkFlg.equals("1"))
					{
						//ブックマーク登録問題用リストに仮格納
						bookmark.add(allRs.get(i));
					}
					else
					{
						//bookmarkリストには追加しない
					}
					
				}
				
				//bookmark登録済み問題がなければ、空のquestionリストを返却
				//サーブレット側で該当問題なしエラーーを表示
				if(bookmark.size()==0)
				{
					return question;
				}
				
				//ブックマークリストの長さと問題数条件の小さいほうを取得
				//問題数条件の数よりもブックマークTBLに入っているデータのほうが少ない場合は、問題数条件未満の件数で出題する
				int minLength = Math.min(questionCount, bookmark.size());
				
				//戻り値リストに問題を入れる
				for(int i =0; i<minLength; i++)
				{
					question.add(bookmark.get(i));
				}
				
			}
			//ブックマーク問題のみ出題希望なしの場合
			else
			{
				//取得結果全件格納リストの長さと問題数条件の小さいほうを取得
				//問題数条件の数よりも、取得結果全件格納リストのデータのほうが少ない場合は、問題数条件未満の件数で出題する。
				int minLength = Math.min(questionCount, allRs.size());
				
				//出題問題数条件の数だけquestionに追加
				for(int i=0; i<minLength; i++)
				{
					question.add(allRs.get(i));
				}
			}
			st.close();
			//戻り値用リスト返却
			return question;
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			//空のリストを返却
			List<Question> dummy = new ArrayList<Question>();
			return dummy;
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
	
	
	//DBに登録されている年度を返すメソッド
	public List<String> getYear()throws Exception
	{
		//戻り値用のリスト作成
		List<String> year = new ArrayList<String>();
		
		Connection con =null;
		try
		{
			//コネクションの取得
			con = getConnection();
		
			//問題TBLに登録されている年度を降順で取得
			PreparedStatement st;
			st=con.prepareStatement("SELECT YEAR FROM QUESTION GROUP BY YEAR ORDER BY YEAR DESC;");
			
			//SQLの実行と結果の取得
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				year.add(rs.getString("YEAR"));
			}
			
			st.close();
			return year;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
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
	
	//DBに登録されている分野を返すメソッド
	public List<String> getGenre()throws Exception
	{
		//戻り値用のリスト作成
		List<String> genre = new ArrayList<String>();
		
		Connection con = null;
		try
		{
			//コネクションの取得
			con = getConnection();
		
			PreparedStatement st;
			st=con.prepareStatement("SELECT GENRE FROM QUESTION GROUP BY GENRE;");
		
			//SQLの実行と結果の取得
			ResultSet rs = st.executeQuery();
		
			while(rs.next())
			{
				genre.add(rs.getString("GENRE"));
			}
			
			st.close();
			return genre;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
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