package action;

/**
* ランキングの表示のアクション
* @author　森下
* @version　1.0.0
*/

import dao.ResultDAO;
import bean.Result;
import bean.AllResult;
import tool.Action;
import javax.servlet.http.*;
import java.util.List;
//import java.util.Date;
import java.sql.Date;



public class AllResultAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		HttpSession session = request.getSession();
		//検索条件の取得
		String[] genre = request.getParameterValues("genre");		
		String difficulty = request.getParameter("difficulty");
		//日付の条件設定
		Date answerDateFrom = Date.valueOf(request.getParameter("fromDate"));
		Date answerDateTo = Date.valueOf(request.getParameter("toDate"));
		String sort = request.getParameter("sort");

		
		//session.setAttribute("answerDateFrom",answerDateFrom);
		//if(difficulty.equals("1")) difficulty="";
		if(answerDateFrom == null) 
		{
			request.setAttribute("errorMsgFrom", "日付を選択してください");
			return "allResult.jsp";
		}
		if(answerDateTo == null)
		{
			request.setAttribute("errorMsgTo", "日付を選択してください");
			return "allResult.jsp";
		}

		//結果のDBから値を取得
		ResultDAO dao = new ResultDAO();
		AllResult allResult = new AllResult();
		allResult.setGenre(genre);
		List<AllResult> list = dao.selectResult(difficulty,sort,answerDateFrom,answerDateTo,allResult);
		
		int rank = 1;
		
		//回答数でソートした場合
		if(sort.equals("COUNT(COUNT_UNIT)"))
		{
			for(int i=1; i<list.size();i++)
			{
				if(list.get(i).getAnswerCount()==list.get(i-1).getAnswerCount())
				{
					rank =list.get(i-1).getRank();
					list.get(i).setRank(rank);
				}
				else 
				{
					rank =i+1;
					list.get(i).setRank(rank);
				}
			}
		}
		//正答数でソートした場合
		else if(sort.equals("SUM(IS_CORRECT)"))
		{
			for(int i=1; i<list.size();i++)
			{
				if(list.get(i).getCorrectCount()==list.get(i-1).getCorrectCount())
				{
					rank =list.get(i-1).getRank();
					list.get(i).setRank(rank);
				}
				else
				{
					rank =i+1;
					list.get(i).setRank(rank);
				}
			}
		}
		//正答率でソートした場合
		else if(sort.equals("collectRate"))
		{
			for(int i=1; i<list.size();i++)
			{
				if(list.get(i).getCorrectRate()==list.get(i-1).getCorrectRate())
				{
					rank =list.get(i-1).getRank();
					list.get(i).setRank(rank);
				}
				else
				{
					rank =i+1;
					list.get(i).setRank(rank);
				}
				
			}
		}
		
		//ソート別の算出方法の分岐
		
		session.setAttribute("list", list);
		
		request.setAttribute("difficulty", difficulty);
		request.setAttribute("fromDate", answerDateFrom);
		request.setAttribute("toDate", answerDateTo);
		request.setAttribute("sort", sort);
		
		return "allResult.jsp";
		
	}
}