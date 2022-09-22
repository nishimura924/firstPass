package action;
/**
 * ユーザ名変更時に既に登録済か判定するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangeUserNameStartAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * daoでsearchしてrequest格納後、changePasswordConfirm.jspに遷移する
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
		String newUserName = request.getParameter("newUserName");
		String errorMessage = "";
		
		//変更後のユーザ名がが入力なしだとユーザ名変更開始画面へ遷移
		if(newUserName == null || newUserName.equals(""))
		{
			errorMessage = "変更後が未入力です";
			request.setAttribute("errorMessage", errorMessage);
			return  "changeUserNameStart.jsp";
		}
		//変更前後のユーザ名が同じだとユーザ名変更開始画面へ遷移
		else if(newUserName.equals(userName))
		{
			errorMessage = "変更前後が同じです";
			request.setAttribute("errorMessage", errorMessage);
			return  "changeUserNameStart.jsp";
		}
		
		UserDAO dao = new UserDAO();
		
		//変更後のユーザ名が他のユーザにて登録済だとユーザ名変更開始画面に遷移
		if(0 != dao.userNameSearch(newUserName))
		{
			errorMessage = "このユーザ名は既に使われています";
			request.setAttribute("errorMessage", errorMessage);
			return "changeUserNameStart.jsp";
		}
		
		//問題なければユーザ名変更確認画面へ遷移
		request.setAttribute("newUserName",newUserName);
		return "changeUserNameConfirm.jsp";
	}
}
	