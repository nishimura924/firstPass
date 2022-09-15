//出題条件選択画面を表示するためのサーブレット

import dao.QuestionDAO;
import bean.Conditions;
import bean.User;
import bean.SummaryOfResult;
import tool.Action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import java.util.*;


public class SelectQuestionAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//セッションの開始、取得
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		//出題条件の取得（リクエストパラメータ）
		String bookmark = request.getParameter("bookmark");
		String[] year = request.getParameterValues("year");
		String[] genre = request.getParameterValues("genre");
		String difficulty = request.getParameter("difficulty");
		int questionCount = Integer.parseInt(request.getParameter("questionCount"));
		
		//条件Beanを生成し、セッションに入れる。
		Conditions conditions = new Conditions();
		conditions.setYear(year);
		conditions.setGenre(genre);
		conditions.setDifficulty(difficulty);
		conditions.setQuestionCount(questionCount);
		conditions.setBookmarkOnly(bookmark);
		
		session.setAttribute("conditions", conditions);
		
		
		//年度　もしくは　分野　が未設定の場合、エラーメッセージとともにjspへ戻す
		if(year==null || genre==null)
		{
			request.setAttribute("errorMsg", "該当する問題はありません");
		}
		
		//ブックマークのみから出題にチェックが入っている場合
		/*if(bookmark.equals("1"))
		{
			//ブックマークTBLに登録されている問題を問題TBLより検索
			QuestionDAO qdao = new QuestionDAO();
			
			
		}
		else
		{
			//条件にあった問題を問題TBLより検索
			List<Question>originalQuestion = qDao.(cnd);
		}*/
			
		
		//問題リストをセッションに格納
		session.setAttribute("questionOfSet", question);
		
		//ユーザの取得(ログイン中か否か）
		User user = (User)session.getAttribute("user");
		if(user!=null)
		{
			//ユーザIDの取得
			String userId = user.getUserId();
			
			//累計実施回数を取得し、+1をしてセッションに格納
			ResultDAO rDao= new ResultDAO();
			int countUnit = rDao.(userId); //メソッド名追加
			countUnit++;
			
			session.setAttribute("countUnit", countUnit);
		}
		
		//実績サマリリストを作成し、sessionに追加
		List<SummaryOfResult> summary = new ArrayList<SummaryOfResult>();
		session.setAttribute("summary", summary);
		
		
		//出題jspを表示
		return"showQuestion.jsp";
	}
	
}