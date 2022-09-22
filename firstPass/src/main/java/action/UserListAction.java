package action;
/**
 * パスワード変更時に現在のパスワードが正しいかどうか判定するプログラム
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
		
		session.setAttribute("adminUserList", adminUserList);
		
		return "userList.jsp";
	}
	
	
}