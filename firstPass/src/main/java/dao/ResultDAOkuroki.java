package dao;

import bean.UnitResult;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultDAOkuroki extends DAO
{
	//引数の値からDBを検索し、累積実施回数を返す　メソッド名仮置き
	public int getMaxCountUnit(String userId)throws Exception
	{
		//戻り用変数 0で初期化
		int countUnit =0;
		
		//コネクションの取得
		Connection con = getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT MAX(COUNT_UNIT) AS COUNT_UNIT FROM RESULT");
		
		//SQLの実行と結果の取得
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			countUnit = rs.getInt("COUNT_UNIT");
		}
		
		st.close();
		con.close();
		
		return countUnit;
	}
	
	
	//個人過去実績取得用
	public List<UnitResult> getPersonalResult(String userId)
	{
		//戻り値用リストの生成
		List<UnitResult> urList = new ArrayList<UnitResult>();
		
		try
		{
			//コネクションの取得
			Connection con = getConnection();
			
			PreparedStatement st;
			st = con.prepareStatement("SELECT COUNT_UNIT,GENRE,DIFFICULTY, COUNT(*),SUM(IS_CORRECT) FROM RESULT WHERE USER_ID =? GROUP BY COUNT_UNIT,GENRE,DIFFICULTY ORDER BY COUNT_UNIT DESC, GENRE;");
			
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
			con.close();
			return urList;
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{

			//st.close();
			//con.close();
		}
			
		
		return urList;
	}
}