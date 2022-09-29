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
 * ChangeUserNameStart.actionでの入力値チェックを踏まえて、実際にDBを更新する
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
		
		String userName = request.getParameter("userName");
		String newUserName = request.getParameter("newUserName");
		UserDAO dao = new UserDAO();
		
		//画面入力のユーザ名がユーザ名変更開始画面時ではDBの値と一致にも関わらず、DBの値と異なる場合はエラー画面へ遷移
		//ブラウザのマルチタブでの操作などによるエラーを想定
		if(! dao.userNameUpdate(user,  userName, newUserName))
		{
			return "changeUserName-error.jsp";
		}
		
		//問題なければsession更新後ユーザ名変更完了画面へ遷移
		user.setUserName(newUserName);
		return "changeUserNameFinish.jsp";
	}
}
	