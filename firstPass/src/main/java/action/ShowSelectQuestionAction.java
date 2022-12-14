package action;

import dao.QuestionDAO;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;
/**
* 出題条件選択画面表示用アクション（年度と分野をDBから取得）
* @author　kuroki
* @version　1.0.0
*/

public class ShowSelectQuestionAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//TBLに登録されている全ての年度を取得する
		QuestionDAO dao = new QuestionDAO();
		List<String> yearList = dao.getYear();
		
		//リクエスト属性yearListに年度リストをセット
		request.setAttribute("yearList", yearList);
		
		//TBLに登録されている全ての分野を取得する
		List<String> genreList = dao.getGenre();
		
		//リクエスト属性genreListに分野リストをセット
		request.setAttribute("genreList", genreList);
		
		return "selectQuestion.jsp";
	}
}