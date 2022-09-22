package action;

/**
* 問題解答結果のアクション
* @author　森下
* @version　1.0.0
*/

import bean.SummaryOfResult;
import bean.Answer;
import bean.Result;
import tool.Action;
import javax.servlet.http.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class UnitResultAction extends Action
{
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{ 
		//セッションを取得、生成
		HttpSession session = request.getSession();
		
		//問題数
		int questionCount = 0;
		//正解数
		int correctCount = 0;
		//正答率
		//int correctRate = 0;
		
		//Date answerDate = null;
				
		//セッション「実績サマリ」を取得
		List<SummaryOfResult> summary=(List<SummaryOfResult>)session.getAttribute("summary");
		//難易度の情報を取得
		
		
		if(summary.size() != 0)
		{
			//Listの長さ（問題数）をカウントアップ
			for(int i=0; i<summary.size(); i++)
			{
				questionCount++;
				
				if(summary.get(i).getCorrect().equals("1"))
				{
					correctCount++;
				}
			}
			
			//エラー制御
			if(questionCount == 0)
			{
				request.setAttribute("questionCount","出力エラー");
				request.setAttribute("correctRate", "出力エラー");
			}
			else if(correctCount == 0)
			{
				request.setAttribute("correctCount", "出力エラー");
			}
			
			//問題数をリクエストで取得
			request.setAttribute("questionCount",questionCount);
			//正答数をリクエストで取得
			request.setAttribute("correctCount", correctCount);
			
			double cr =(double)correctCount;
			double qc = (double)questionCount;
			String correctRate = String.valueOf(Math.round((cr/qc)*100));
			//正答率をリクエストで取得
			request.setAttribute("correctRate", correctRate);
		}

		return "unitResult.jsp";
	}
}
