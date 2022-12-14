package action;


import dao.QuestionDAO;
import dao.ResultDAO;
import bean.Conditions;
import bean.User;
import bean.Question;
import bean.SummaryOfResult;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;
/**
* 条件を取得し、問題リストを生成するためのアクション
* @author　kuroki
* @version　1.0.0
*/

public class SelectQuestionAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//セッションの開始、取得
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		//セッション属性userを取得
		//ゲストの場合、userはNull
		User user = (User)session.getAttribute("user");
		//セッション属性userが取得できて、管理者権限フラグが1の場合、管理者メニューのみ表示のためアクセスエラー画面へ遷移
		if(user!=null)
		{
			if(user.getAdminFlag().equals("1"))
			{
				return"access-error.jsp";
			}
			
		}

		
		//answerの削除
		session.removeAttribute("answer");
		
		//出題条件の取得（リクエストパラメータ）
		String bookmark = request.getParameter("bookmark");
		String[] year = request.getParameterValues("year");
		String[] genre = request.getParameterValues("genre");
		String difficulty = request.getParameter("difficulty");
		int questionCount = Integer.parseInt(request.getParameter("questionCount"));
		
		//条件Beanを生成し、セッションに入れる
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
			return "ShowSelectQuestion.action";
		}
		
		
		//問題抽出メソッドの呼び出し
		QuestionDAO dao = new QuestionDAO();
		List<Question> question = dao.setQuestion(conditions,user);
		
		
		if(question.size()==0)
		{
			request.setAttribute("errorMsg", "該当する問題はありません");
			return "ShowSelectQuestion.action";
		}
		
		//出題順をshuffle
		Collections.shuffle(question);
		
		//indexの設定
		//index用変数を1で初期化
		int index =1;
		
		//questionリスト内の各問題にインデックスをつける
		for(int i =0; i<question.size(); i++)
		{
			question.get(i).setIndex(index);
			index++;
		}
			
		
		//問題リストをセッションに格納
		session.setAttribute("questionOfSet", question);
		
		//ユーザの取得(ログイン中か否か）
		if(user!=null)
		{
			//ユーザIDの取得
			String userId = user.getUserId();
			
			//累計実施回数を取得し、+1をしてセッションに格納
			ResultDAO rDao= new ResultDAO();
			int countUnit = rDao.getMaxCountUnit(userId);
			countUnit++;
			
			//ユーザBeanに設定
			user.setCountUnit(countUnit);
			
		}
		
		//実績サマリリストを作成し、sessionに追加
		List<SummaryOfResult> summary = new ArrayList<SummaryOfResult>();
		session.setAttribute("summary", summary);
		
		//出題jspを表示
		return"showQuestion.jsp";
	}
	
}