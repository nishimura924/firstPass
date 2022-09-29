package action;
/**
 * コメント登録用のプログラム
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
	 * コメントエリアに記載の内容をDBに登録
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
				//改行は削除
				String commentString = request.getAttribute("comment").toString().replaceAll("\\r\\n|\\r|\\n", "");
				
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
					//DB正常登録時改めて読込んで、過去コメントと併せてshowQuestion.jspに返す
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