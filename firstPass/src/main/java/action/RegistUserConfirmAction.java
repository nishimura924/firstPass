package firstPass;

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;


public class RegistUserConfirmAction extends Action
{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception
	{ 
		
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
		UserDAO dao = new UserDAO();
		int line = dao.insert(user);
		
		if(line>0)
		{
			//リクエスト取得し、完了画面へ渡す	
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("userName", userName);
			return "registUserFinish.jsp";
		}
		else
		{
			request.setAttribute("registError", "登録エラー");
			return "registUserConfirm.jsp";
		}
		
		
	}
}