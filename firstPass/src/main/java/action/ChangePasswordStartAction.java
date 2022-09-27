package action;
/**
 * パスワード変更時に現在のパスワードが正しいかどうか判定するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangePasswordStartAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * daoでselectしてsession格納後、changePasswordConfirm.jspに遷移する
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
		String errorMessage = "";
		
		
		//現在のパスワードが入力なしだとパスワード変更開始画面へ遷移
		if(password == null || password.equals(""))
		{
			errorMessage = "現在のパスワードが未入力です";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
		
		//新しいパスワード(1回目)が入力なしだとパスワード変更開始画面へ遷移
		String newPassword = request.getParameter("newPassword");
		if(newPassword == null || newPassword.equals(""))
		{
			errorMessage = "新しいパスワードが未入力です";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
		
		//新しいパスワード(1回目)が半角英数字、８文字以上でないとパスワード変更開始画面へ遷移
		if(! ValidCheck.validUserPass(newPassword))
		{
			errorMessage = "新しいパスワードが誤りです。（最低８文字、半角英数字のみ）";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
		
		//新しいパスワード(2回目)が入力なしだとパスワード変更開始画面へ遷移
		String newPasswordCheck = request.getParameter("newPasswordCheck");
		if(newPasswordCheck == null || newPasswordCheck.equals(""))
		{
			errorMessage = "確認用のパスワードが未入力です";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
		
		//新しいパスワードの1回目と2回目だとパスワード変更開始画面へ遷移
		if(! newPassword.equals(newPasswordCheck))
		{
			errorMessage = "新しいパスワードが不一致です";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
	
		UserDAO dao = new UserDAO();
		
		//画面入力のパスワードが登録済と異なるとパスワード変更開始画面へ遷移
		if(1 != dao.userPasswordSearch(user, password))
		{
			errorMessage = "登録済のパスワードと異なります";
			request.setAttribute("errorMessage", errorMessage);
			return "changePasswordStart.jsp";
		}
				
		//問題なければパスワード変更確認画面へ遷移
		request.setAttribute("password",password);
		request.setAttribute("newPassword",newPassword);
		return "changePasswordConfirm.jsp";
	}
}
	