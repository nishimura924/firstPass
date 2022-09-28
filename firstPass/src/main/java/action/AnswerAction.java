package action;
/**
 * 回答が正解かどうかを判断するプログラム
 * 問題ごとのコメントも取得する
 * @author　島田
 * @version　1.0.0
 */

import bean.Answer;
import bean.Comment;
import bean.Question;
import bean.User;
import dao.CommentDAO;
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
		
		
		Answer answer = new Answer();
		
		//実績用に正解不正解を格納
		//ボタン押さななかった場合は不正解
		if(request.getParameter("choice")==null)
		{
			answer.setCorrect("0");
		}
		//アが選ばれた場合
		else if(request.getParameter("choice").equals("ア"))
		{
			answer.setCorrect(questionOfSet.get(0).getChoice1().getIsCorrect());
			request.setAttribute("choice", "ア");
		}
		//イが選ばれた場合
		else if(request.getParameter("choice").equals("イ"))
		{
			answer.setCorrect(questionOfSet.get(0).getChoice2().getIsCorrect());
			request.setAttribute("choice", "イ");
		}
		//ウが選ばれた場合
		else if(request.getParameter("choice").equals("ウ"))
		{
			answer.setCorrect(questionOfSet.get(0).getChoice3().getIsCorrect());
			request.setAttribute("choice", "ウ");
		}
		//エが選ばれた場合
		else if(request.getParameter("choice").equals("エ"))
		{
			answer.setCorrect(questionOfSet.get(0).getChoice4().getIsCorrect());
			request.setAttribute("choice", "エ");
		}
		//その他の値が入っても不正解(入らない想定)
		else
		{
			answer.setCorrect("0");
		}
		
		//正解の選択肢を問題から抽出
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
		}
	
		//コメントの取得(ログインユーザのみ)
		//answer.setAllComment(List<Comment>);
		User user = (User)session.getAttribute("user");
		
		if(user != null)
		{
			CommentDAO dao = new CommentDAO();
			Comment comment = new Comment();
			comment.setYear(questionOfSet.get(0).getYear());
			comment.setQuestionNo(questionOfSet.get(0).getQuestionNo());
			
			List<Comment> allComment = new ArrayList<Comment>();
			allComment = dao.search(comment);
			
			if(allComment.size() ==  0)
			{
				//コメントなしなのでスルー
			}
			else
			{
				answer.setAllComment(allComment);
			}
			
		}
				
		session.setAttribute("answer", answer);
		
		return "showQuestion.jsp";
	}
	
	
}