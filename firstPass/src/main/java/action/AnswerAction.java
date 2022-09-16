package action;
/**
 * 回答が正解かどうかを判断するプログラム
 * 問題ごとのコメントも取得する
 * @author　島田
 * @version　1.0.0
 */

import bean.Answer;
import bean.Question;
import bean.User;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;

public class AnswerAction extends Action
{
	@SuppressWarnings("unchecked")
	
	/**
	 * mainメソッド
	 * daoでsearch後、showQuestion.jspに遷移する
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		
		//URL直接入力のエラー処理
		List<Question> questionOfSet = (List<Question>)session.getAttribute("questionOfSet");
		if(questionOfSet == null)
		{
			return "access-error.jsp";
		}
		
		
		//正解の判定
		Answer answer = new Answer();
		answer.setCorrect(request.getParameter("choice"));
		
		if (questionOfSet.get(0).getChoice1().getIsCorrect() == "1")
		{
			answer.setCorrectChar('ア');
		}
		else if(questionOfSet.get(0).getChoice2().getIsCorrect() == "1")
		{
			answer.setCorrectChar('イ');
		}
		else if(questionOfSet.get(0).getChoice3().getIsCorrect() == "1")
		{
			answer.setCorrectChar('ウ');
		}
		else if(questionOfSet.get(0).getChoice4().getIsCorrect() == "1")
		{
			answer.setCorrectChar('エ');
		}
		else
		{
			//エラーに飛ばす？
		}
	
		//コメントの取得
		//あとでつくる
		//answer.setAllComment(List<Comment>);
		
		//sessionに変更
		session.setAttribute("answer", answer);
		
		return "showQuestion.jsp";
	}
	
	
}