package action;

import bean.PersonalResult;
import bean.UnitResult;
import bean.User;
import dao.ResultDAOkuroki;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;

/**
* 個人過去実績用アクション（マイページ内）
* @author　kuroki
* @version　1.0.0
*/

public class PersonalResultAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		//セッションの取得
		HttpSession session = request.getSession();
		
		//セッション属性userを取得
		User user = (User)session.getAttribute("user");
		//セッション属性userが取得できなければ、エラー画面へ遷移
		if(user==null)
		{
			return"access-error.jsp";
		}
		
		//ユーザIDを取得
		String userId = user.getUserId();
		
		//実績TBLに問い合わせ
		ResultDAOkuroki dao = new ResultDAOkuroki();
		List<UnitResult> urList = dao.getPersonalResult(userId);//メソッド名追記すること
				
		//実績TBLから降順で取得しているため、リストの0番目が最大実施回数を示す
		int maxCountUnit = urList.get(0).getCountUnit();
		
		//最大実施回数分の配列を作成
		PersonalResult[] prArray = new PersonalResult[maxCountUnit];
		
		//総合問題数格納用配列
		int[] totalQuestionCount = new int[maxCountUnit];
		//総合正解数格納用配列
		int[] totalCorrectCount = new int[maxCountUnit];

		//ストラテジ系問題数格納用配列
		int[] straQuestionCount = new int[maxCountUnit];
		//ストラテジ系正解数格納用配列
		int[] straCorrectCount = new int[maxCountUnit];
		
		//テクノロジ系問題数格納用配列
		int[] techQuestionCount = new int[maxCountUnit];
		//テクノロジ系正解数格納用配列
		int[] techCorrectCount = new int[maxCountUnit];
		
		//マネジメント系問題数格納用配列
		int[] manaQuestionCount = new int[maxCountUnit];
		//マネジメント系正解数格納用配列
		int[] manaCorrectCount = new int[maxCountUnit];
		
		
		//実施回数一致確認用変数
		int unitCount=0;
		
		//Listの長さでループ
		for(int i=0; i<urList.size(); i++)
		{
			int index = urList.get(i).getCountUnit()-1;
			
			//実施回数が違う場合、Beanを生成
			if(unitCount != urList.get(i).getCountUnit())
			{				
				//新しいユニット毎結果格納Beanの生成
				PersonalResult pr = new PersonalResult();
				//累計実施回数をセット
				pr.setCountUnit(urList.get(i).getCountUnit()); 
				//難易度を取得
				String difficult = urList.get(i).getDifficulty();
				//表示用に変換し、難易度をセット
				if(difficult.equals("0"))
				{
					pr.setDifficulty("normal");
				}
				else if(difficult.equals("1"))
				{
					pr.setDifficulty("easy");
				}
				
				//実施回数-1の添え字配列にオブジェクト格納（4回目のデータは、prArray[3]に）
				prArray[index] = pr;
				
				//ユニット確認用変数を更新
				unitCount = urList.get(i).getCountUnit();
			}

			//実施回数が同じだったら、

			//index=実施回数-1 に値格納
			//実施問題数の足しこみ
			totalQuestionCount[index] = totalQuestionCount[index] + urList.get(i).getTotalCountOfGenre();
		
			//総合正解数の足しこみ
			totalCorrectCount[index] = totalCorrectCount[index] + urList.get(i).getIsCorrectCount();
			
			
			//分野毎の問題数・正解数の足しこみ
			if(urList.get(i).getGenre().equals("ストラテジ"))
			{
				//問題数
				straQuestionCount[index] = straQuestionCount[index] + urList.get(i).getTotalCountOfGenre();
				//正解数
				straCorrectCount[index] = straCorrectCount[index] + urList.get(i).getIsCorrectCount();
			}
			else if(urList.get(i).getGenre().equals("テクノロジ"))
			{
				//問題数
				techQuestionCount[index] = techQuestionCount[index] + urList.get(i).getTotalCountOfGenre();
				//正解数
				techCorrectCount[index] = techCorrectCount[index] + urList.get(i).getIsCorrectCount();
			}
			else if(urList.get(i).getGenre().equals("マネジメント"))
			{
				//問題数
				manaQuestionCount[index] = manaQuestionCount[index] + urList.get(i).getTotalCountOfGenre();
				//正解数
				manaCorrectCount[index] = manaCorrectCount[index] + urList.get(i).getIsCorrectCount();
			}
		}

		
		//↓ここから、実施回数毎にデータを格納
		for(int i=0; i<prArray.length; i++)
		{
			//実施回数-1の添え字に入っているオブジェクトを取り出し
			PersonalResult pr = prArray[i];
			
			//総合問題回数のセット
			pr.setTotalQuestionCount(totalQuestionCount[i]);
			
			//総合正答率のセット
			//総合問題数が0の場合、正答率には"-"をセット
			if(totalQuestionCount[i]==0)
			{
				pr.setCorrectOnTotal("-");
			}
			else
			{
				//double型に変換
				double tcc = (double)(totalCorrectCount[i]);
				double tqc = (double)(totalQuestionCount[i]);
				//総合正答率を算出
				String correctOnTotal = String.valueOf(Math.round((tcc/tqc)*100));
				//総合正答率をセット
				pr.setCorrectOnTotal(correctOnTotal);
			}
			
			//ストラ正答率のセット
			if(straQuestionCount[i]==0)
			{
				pr.setCorrectOnStrategy("-");
			}
			else
			{
				//double型に変換
				double scc = (double)(straCorrectCount[i]);
				double sqc = (double)(straQuestionCount[i]);
				//ストラテジ正答率を算出
				String correctOnStrategy = String.valueOf(Math.round((scc/sqc)*100));
				//ストラテジ正答率をセット
				pr.setCorrectOnStrategy(correctOnStrategy);
			}
			
			//テクノ正答率のセット
			if(techQuestionCount[i]==0)
			{
				pr.setCorrectOnTech("-");
			}
			else
			{
				//double型に変換
				double tcc = (double)(techCorrectCount[i]);
				double tqc = (double)(techQuestionCount[i]);
				//テクノロジ正答率を算出
				String correctOnTech = String.valueOf(Math.round((tcc/tqc)*100));			
				//テクノロジ正答率をセット
				pr.setCorrectOnTech(correctOnTech);
			}
			
			//マネ正答率のセット
			if(manaQuestionCount[i]==0)
			{
				pr.setCorrectOnManage("-");
			}
			else
			{
				//double型に変換
				double mcc = (double)(manaCorrectCount[i]);
				double mqc = (double)(manaQuestionCount[i]);
				//正答率を算出
				String correctOnManage = String.valueOf(Math.round((mcc/mqc)*100));	
				//マネジメント正答率をセット
				pr.setCorrectOnManage(correctOnManage);
			}
			
		}

		//リクエスト属性にリストをセット
		request.setAttribute("PersonalResult", prArray);
		
		//jspにフォワード
		return "personalResult.jsp";
	}
	
}