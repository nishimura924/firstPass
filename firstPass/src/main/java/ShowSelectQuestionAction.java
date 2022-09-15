//出題条件選択画面を表示するためのサーブレット

import dao.QuestionDAO;
import tool.Action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import java.util.*;


public class ShowSelectQuestionAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//TBLに登録されている全ての年度を取得する
		QuestionDAO dao = new QuestionDAO();
		List<String> yearList = dao.(); //メソッド名追記
		
		//リクエスト属性yearListに年度リストをセット
		request.setAttribute("yearList", yearList);
		
		//TBLに登録されている全ての分野を取得する
		List<String> genreList = dao.();//メソッド名追記
		
		//リクエスト属性genreListに分野リストをセット
		request.setAttribute("genre", genreList);
		
		return "SelectQuestion.jsp";
	}
}