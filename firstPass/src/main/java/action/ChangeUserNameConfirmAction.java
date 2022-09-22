package action;
/**
 * ユーザ名変更のDB更新するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangeUserNameConfirmAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * daoでupdateしてsession格納後、changeUserNameFinish.jspに遷移する
 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//セッションの接続
		HttpSession session = request.getSession();
		
		//userがnullならエラー画面に遷移(URL直接入力)
		User user = (User)session.getAttribute("user");
		if(user == null)
		{
		return "access-error.jsp";	
		}
		
		String userId = user.getUserId();
		String userName = user.getUserName();
		
		//入力値チェックは実施なし
		
		
		String newUserName = request.getParameter("newUserName");
		UserDAO dao = new UserDAO();
		
		//更新失敗のときはエラー画面へ遷移
		if(! dao.update(userId, "", newUserName))
		{
			return "changeUserName-error.jsp";
		}
		
		//問題なければsession更新後ユーザ名変更完了画面へ遷移
		user.setUserName(newUserName);
		return "changeUserNameFinish.jsp";
	}
}
	