package action;
/**
 * パスワード変更するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAOshimada;
import tool.Action;
import javax.servlet.http.*;

public class ChangePasswordConfirmAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * daoでupdate後、changePasswordFinish.jspに遷移する
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
		
		//入力値チェックは不要
		String newPassword = request.getParameter("newPassword");
		
		
		//パスワード更新のためSQLの実行
		UserDAOshimada dao = new UserDAOshimada();

		
		//画面入力のパスワードがDBの値と異なるエラー画面へ遷移
		if(! dao.update(userId, newPassword, ""))
		{
			return "changePasswordConfirm-error.jsp";
		}
		
		//問題なければパスワード変更完了画面へ遷移
		return "changePasswordFinish.jsp";
	}
}
	