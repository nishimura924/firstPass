package action;
/**
 * 管理者用のユーザ一覧を取得するためのプログラム
 * @author　島田
 * @version　1.0.0
 */
import javax.servlet.http.*;
import bean.Choice;
import bean.Comment;
import bean.Conditions;
import bean.Question;
import bean.User;
import dao.UserDAO;

import java.util.*;

import tool.Action;

public class UserListAction extends Action
{
	@SuppressWarnings("unchecked")

/**
 * mainメソッド
 * USER TABLEの値を取得してuserList.jspに返す
 */
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//セッションの接続
		HttpSession session = request.getSession();
		
		//userがnullならエラー画面に遷移(URL直接入力)
		User user = (User)session.getAttribute("user");
		if(user == null)
		{
			return "index.jsp";	
		}
		
		//管理者のみ利用可能
		if(! user.getAdminFlag().equals("1"))
		{
			return "index.jsp";
		}
		
		UserDAO dao = new UserDAO();
		
		List<User> adminUserList = new ArrayList<User>();
		adminUserList = dao.adminSearchAll();
		
		request.setAttribute("adminUserList", adminUserList);
		
		return "userList.jsp";
	}
	
	
}