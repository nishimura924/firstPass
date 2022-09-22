package action;
/**
 * コメント登録単体用のプログラム
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

public class RegistCommentAction extends Action
{
	@SuppressWarnings("unchecked")
	
	/**
	 * mainメソッド
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
		
		User user = (User)session.getAttribute("user");
		
		//　コメントの登録(ログインユーザのみ)
		if(user != null)
		{
			CommentDAO dao = new CommentDAO();
			Comment comment = new Comment();
			comment.setYear(questionOfSet.get(0).getYear());
			comment.setQuestionNo(questionOfSet.get(0).getQuestionNo());
			comment.setUserId(user.getUserId());
			
			//コメントなければ何もしない
			if(request.getAttribute("comment") == null || request.getAttribute("comment").toString().trim().isEmpty())
			{
				
			}
			//コメントがあれば登録
			else
			{
				String commentString = request.getAttribute("comment").toString();
				
				//500文字超はカット
				if(! ValidCheck.validComment(commentString))
				{
					commentString = commentString.substring(0,500);

				}
				
				comment.setComment(commentString);
				if(dao.insert(comment) != 1)
				{
					//DB登録処理のエラー
					String errorMessage = "コメント登録ができません。";
					request.setAttribute("errorMessage", errorMessage);
				}
				else
				{
					//DB正常登録時、改めて読込み
					List<Comment> allComment = new ArrayList<Comment>();
					allComment = dao.search(comment);
					Answer answer = (Answer)session.getAttribute("answer");
					answer.setAllComment(allComment);
					session.setAttribute("answer", answer);
					
				}
			}
		}
		
	
		return "showQuestion.jsp";
	}
	
	
}