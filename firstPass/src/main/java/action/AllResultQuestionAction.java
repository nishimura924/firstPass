package firstPass;

/**
* ランキングの出力条件表示のアクション（分野をDBから取得）
* @author　森下
* @version　1.0.0
*/

import dao.QuestionDAO;
import tool.Action;
import javax.servlet.http.*;
import java.util.List;


public class AllResultQuestionAction extends Action
{	
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		QuestionDAO dao = new QuestionDAO();
		
		//DB内の分野項目を取得する
		List<String> genreList = dao.getGenre();
		
		//リクエスト属性genreListに分野リストをセット
		request.setAttribute("genreList", genreList);
		
		return "allResult.jsp";	
	}
}
