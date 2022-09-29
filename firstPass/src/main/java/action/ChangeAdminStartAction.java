package action;
/**
 * 管理者権限変更時に現在の権限を確認するプログラム
 * @author　島田
 * @version　1.0.0
 */

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class ChangeAdminStartAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * 入力値チェック及びDBとのデータ整合チェックを行う
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
		
		//変更対象のユーザ
		User userChangeAdmin = new User();
		userChangeAdmin.setUserId(request.getParameter("userId"));
		userChangeAdmin.setAdminFlag(request.getParameter("adminFlag"));
		
		String errorMessage = "";
		UserDAO dao = new UserDAO();
		
		//欠値チェック
		if(request.getParameter("userId") == null || request.getParameter("userId").equals(""))
		{
			errorMessage = "ユーザIDを入力してください。";
			request.setAttribute("errorMessage", errorMessage);
			return "changeAdminStart.jsp";
		}
		
		//自身の管理者権限変更は不可
		if(request.getParameter("userId").equals(user.getUserId()))
		{
			errorMessage = "自身の管理者権限変更は不可となります。";
			request.setAttribute("errorMessage", errorMessage);
			return "changeAdminStart.jsp";
		}
		
		//ユーザIDがDBにない、もしくは権限が異なるかを確認
		if(1 != dao.adminSearch(userChangeAdmin))
		{
			errorMessage = "ユーザIDがない、もしくは変更前の権限が異なります。";
			request.setAttribute("errorMessage", errorMessage);
			return "changeAdminStart.jsp";
		}
				
		
		//問題なければ管理者権限変更確認画面へ遷移
		request.setAttribute("userAdmin", request.getParameter("adminFlag"));
		request.setAttribute("userChangeAdmin",userChangeAdmin);
		return "changeAdminConfirm.jsp";
	}
}
	