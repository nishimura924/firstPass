package firstPass;

import bean.SummaryOfResult;
import tool.Action;
import javax.servlet.http.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class UnitResultAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{ 
		//セッションを取得、生成
		HttpSession session = request.getSession();
		
		//セッション「実績サマリ」を取得
		List<SummaryOfResult> summary=(List<SummaryOfResult>)session.getAttribute("summary");
		
		//問題数
		int questionCount = 0;
		//正解数
		int correctCount = 0;
		//正答率
		int correctRate = 0;
		
		//Listの長さ（問題数）をカウントアップ
		for(int i=0; i<=summary.size(); i++)
		{
			if(summary.get(i).getCorrect().equals("1"))
			{
				correctCount++;
			}
		}
		correctRate = correctCount/summary.size();
		
		//最後の問題の解答時刻を取得
		Date answerDate = summary.get(summary.size()-1).;
		
		
		request.setAttribute("answerDate", answerDate);
		//問題数をリクエストで取得
		request.setAttribute("questionCount",summary.size());
		//正答数をリクエストで取得
		request.setAttribute("correctCount", correctCount);
		//正答率をリクエストで取得
		request.setAttribute("correctRate", correctRate);
		//jspへ渡す
		return "unitResult.jsp";
	}
}
