package action;

import dao.ResultDAOmorishita;
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
		ResultDAOmorishita dao = new ResultDAOmorishita();
		AllResult allResult = new AllResult();
		allResult.setGenre(genre);
		List<AllResult> list = dao.selectResult(difficulty,sort,answerDateFrom,answerDateTo,allResult);
				
		//ソート別の算出方法の分岐
		
		session.setAttribute("list", list);
		
		return "allResult.jsp";
		
	}
}