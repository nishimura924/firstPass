package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultDAOkuroki extends DAO
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
}