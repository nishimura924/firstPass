package action;

/**
 * ユーザの入力内容を確認表示させる機能
 * @author　森下
 * @version　1.0.0
 */
import bean.User;
import dao.UserDAOmorishita;
import tool.Action;
import javax.servlet.http.*;


public class RegistUserConfirmAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{ 
		//セッションの取得、生成
		HttpSession session = request.getSession();
		
		//userがnullならエラー画面に遷移(URL直接入力)
		User inUser = (User)session.getAttribute("user");
		if(inUser == null)
		{
			return "access-error.jsp";	
		}
		
		//リクエストパラメータの取得
		String userId=request.getParameter("userId");
		String userPass=request.getParameter("userPass");
		String userName=request.getParameter("userName");
		String adminFlag=request.getParameter("adminFlag");
		
		//入力値の設定有無確認
		if(userId==null)
		{
			request.setAttribute("errorMessage", "ユーザIDが不正です。");
			return "registUserStart.jsp";
		}
		else if(userPass==null)
		{
			request.setAttribute("errorMessage", "パスワードが不正です。");
			return "registUserStart.jsp";
		}
		else if(userName==null)
		{
			request.setAttribute("errorMessage", "ユーザ名が不正です。");
			return "registUserStart.jsp";
		}
		
				
		User user=new User();
		user.setUserId(userId);
		user.setUserPass(userPass);
		user.setUserName(userName);
		user.setAdminFlag(adminFlag);
		UserDAOmorishita dao = new UserDAOmorishita();
		int line = dao.addUser(user);
		
		if(line>0)
		{
			//リクエストにユーザIDとユーザ名をセット（確認画面表示用）
			request.setAttribute("userId", userId);
			request.setAttribute("userName", userName);
			
			//新規会員登録後ログイン状態にするため、UserBeanを生成しセッションにセット
			User newUser = new User();
			newUser.setUserId(userId);
			newUser.setUserName(userName);
			newUser.setAdminFlag(adminFlag);
			session.setAttribute("user",newUser);
			
			return "registUserFinish.jsp";
		}
		else
		{
			request.setAttribute("registError", "登録エラー");
			return "registUserConfirm.jsp";
		}
		
		
	}
}