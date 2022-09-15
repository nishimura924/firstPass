package dao;

import bean.Question;
import bean.Choice;
import bean.Conditions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class QuestionDAO extends DAO
{
	//引数の値からDBを検索し、リストを返すメソッド　メソッド名仮置き
	public List<Question> seach(Conditions conditions)throws Exception
	{
		//戻り値用のリストの作成
		List<Question> question = new ArrayList<Question>();
		
		//コネクションの取得
		Connection con = getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"SELECT QUESTION.YEAR,QUESTION.QUESTION_NO,QUESTION.GENRE,QUESTION.QUESTION,"
				+ "QUESTION.COLLECT,QUESTION.INCOLLECT_1,QUESTION.INCOLLECT_2,QUESTION.INCOLLECT_3,BOOKMARK.USER_ID"
				+ "FROM QUESTION LEFT JOIN BOOKMARK "
				+ "ON QUESTION.QUESTION_NO = BOOKMARK.QUESTION_NO "
				+ "AND QUESTION.YEAR = BOOKMARK.YEAR "
				+ "WHERE QUESTION.YEAR IN(?) and QUESTION.GENRE IN(?);");
		
		//引数の条件Beanの年度リストを取得し
		String[] year = conditions.getYear();
		//年度条件を連結
		String yearCond = joinCond(year);
		//1つ目にセット
		st.setString(1, yearCond);
		
		//引数の条件Beanの分野リストを取得
		String[] genre = conditions.getGenre();
		//分野条件を連結
		String genreCond = joinCond(genre);
		//2つ目にセット
		st.setString(2, genreCond);
		
		//SQLの実行と結果の取得
		ResultSet rs = st.executeQuery();
		
		//出題問題数条件の取得
		int questionCount = conditions.getQuestionCount();
		//ブックマーク条件の取得
		String bookmarkOnly = conditions.getBookmarkOnly();
		//難易度条件の取得
		String difficulty = conditions.getDifficulty();
		
		//取得結果全件格納リストを作成
		List<Question> all = new ArrayList<Question>();
		
		//全件をBeanとして生成
		while(rs.next())
		{
			//QuestionBeanの生成（インデックスつけるのは一番最後）
			Question q = new Question();
			q.setYear(rs.getString("YEAR"));
			q.setQuestionNo(rs.getInt("QUESTION_NO"));
			q.setGenre(rs.getString("GENRE"));
			q.setQuestion(rs.getString("QUESTION"));
			q.setQuestionPic(rs.getString("QUESTION_FILE_NAME"));
			q.setChoicePicFlg(rs.getString("IS_SELECT_FILE"));
			
			//ブックマークフラグの判定と格納
			//ユーザIDがnullなら登録なし、nullでなければ登録あり
			if(rs.getString("USER_ID")==null)
			{
				q.setBookmarkFlg("0"); //登録なし
			}
			else
			{
				q.setBookmarkFlg("1"); //登録あり				
			}
			
			//正解ChoiceBeanの生成
			Choice collect = new Choice();
			collect.setChoice(rs.getString("COLLECT"));
			collect.setIsCollect("1");
			
			//不正解ChoiceBean1の生成
			Choice inc1 = new Choice();
			inc1.setChoice(rs.getString("INCOLLECT_1"));
			inc1.setIsCollect("0");
			
			//不正解ChoiceBean2の生成
			Choice inc2 = new Choice();
			inc2.setChoice(rs.getString("INCOLLECT_2"));
			inc2.setIsCollect("0");
			
			//不正解ChoceBean3の生成
			Choice inc3 = new Choice();
			inc3.setChoice(rs.getString("INCOLLECT_3"));
			inc3.setIsCollect("0");
			
			//不正解ChoiceBean格納用リスト
			List<Choice> incollect = new ArrayList<Choice>();
			incollect.add(inc1);
			incollect.add(inc2);
			incollect.add(inc3);
			
			//不正解リストをシャッフル
			Collections.shuffle(incollect);
			
			//最終的な選択肢格納Set
			List<Choice> choiceList = new ArrayList<Choice>();
			
			//正解選択肢をchoiceSetに格納
			choiceList.add(collect);
						
			//難易度による選択肢数の決定
			if(difficulty.equals("easy"))
			{
				//不正解を1つcchoiceListに格納
				//不正解リストshuffle後なので、常に0で取得
				choiceList.add(incollect.get(0));
				
			}
			else if(difficulty.equals("normal"))
			{
				//不正解を全てchoiceSetに格納
				for(int i=0; i<=incollect.size(); i++)
				{
					choiceList.add(incollect.get(i));
				}
			}
			
			//choiceListの中身をshuffle(正解も不正解もまとめてshuffle)
			Collections.shuffle(choiceList);
			
			//QuestionBeanの選択肢にセット
			if(difficulty.equals("easy"))
			{
				q.setChoice1(choiceList.get(0));
				q.setChoice2(choiceList.get(1));
			}
			else if(difficulty.equals("normal"))
			{
				q.setChoice1(choiceList.get(0));
				q.setChoice2(choiceList.get(1));
				q.setChoice3(choiceList.get(2));
				q.setChoice4(choiceList.get(3));
			}
			//↑ここまでで、問題をBeanにする処理完了			
			//取得結果全件格納リストに仮格納
			all.add(q);
			
		}
		
		//↓ここからブックマーク有無の絞り込み
		//ブックマークからのみ出題希望の場合
		if(bookmarkOnly!=null)
		{
			//取得結果全件格納リストでループ
			for(int i=0; i<=all.size(); i++)
			{
				//QuestionBeanのブックマークフラグを取得
				String bookmarkFlg = all.get(i).getBookmartFlg();
				
				//ブックマーク登録ありだったら、
				if(bookmarkFlg.equals("1"))
				{
					//
					question.add(all.get(i));
				}
				else
				{
					//questionリストには追加しない
				}
			}
			
			
		}
		else
		{
			for(int i=0; i<=questionCount; i++)
		}
		
		
	
		
		
		
		
		st.close();
		con.close();
		
		return question;
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