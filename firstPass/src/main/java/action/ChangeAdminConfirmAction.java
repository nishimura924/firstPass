package action;
/**
 * 管理者権限を変更するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangeAdminConfirmAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * ChangeAdminStart.actionでの入力値チェックを踏まえて、実際にDBを更新する
 * 
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

		//管理者のみ利用可能
		if(! user.getAdminFlag().equals("1"))
		{
			return "index.jsp";
		}
		
		User userChangeAdmin = new User();
		userChangeAdmin.setUserId(request.getParameter("userId"));
		userChangeAdmin.setAdminFlag(request.getParameter("newUserAdmin"));
		String userAdmin = request.getParameter("userAdmin");
		
		
		UserDAO dao = new UserDAO();

		
		//画面入力のパスワードがパスワード変更開始画面時ではDBの値と一致にも関わらず、DBの値と異なる場合はエラー画面へ遷移
		//ブラウザのマルチタブでの操作などによるエラーを想定
		if(! dao.adminUpdate(userChangeAdmin, userAdmin))
		{
			return "changeAdmin-error.jsp";
		}
		
		//問題なければパスワード変更完了画面へ遷移
		return "changeAdminFinish.jsp";
	}
}
	