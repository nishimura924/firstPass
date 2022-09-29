package action;
/**
 * パスワード変更するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangePasswordConfirmAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * ChangePasswordStart.actionでの入力値チェックを踏まえて、実際にDBを更新する
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
		
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		UserDAO dao = new UserDAO();

		
		//画面入力のパスワードがパスワード変更開始画面時ではDBの値と一致にも関わらず、DBの値と異なる場合はエラー画面へ遷移
		//ブラウザのマルチタブでの操作などによるエラーを想定
		if(! dao.userPasswordUpdate(user, password, newPassword))
		{
			return "changePassword-error.jsp";
		}
		
		//問題なければパスワード変更完了画面へ遷移
		return "changePasswordFinish.jsp";
	}
}
	