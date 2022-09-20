package action;

import bean.User;
import dao.UserDAOmorishita;
import tool.Action;
import java.util.List;
import javax.servlet.http.*;


public class RegistUserStartAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
		
	{   
		//リクエストパラメータの取得
		String userId=request.getParameter("userId");
		String userPass=request.getParameter("userPass");
		String passwordConfirm=request.getParameter("passwordConfirm");
		String userName=request.getParameter("userName");
		
		//いずれかの項目が未入力だったら、エラー画面へ遷移
		if(userId==null)
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "ユーザIDを入力してください。");
			return "registUserStart.jsp";
		}
		else if(userPass==null)
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "パスワードを入力してください。");
			return "registUserStart.jsp";
		}
		else if(passwordConfirm==null)
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "パスワード（確認用）を入力してください。");
			return "registUserStart.jsp";
		}
		else if(userName==null)
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "ユーザ名を入力してください。");
			return "registUserStart.jsp";
		}
		
		//パスワードと確認用パスワードが不一致の場合は、エラー画面へ遷移
		if(!(userPass.equals(passwordConfirm)))
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "パスワードが一致しません。");
			return "registUserStart.jsp";
		}
		/*else
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("userName", userName);
			return "registUserConfirm.jsp";
		}*/


		UserDAOmorishita dao = new UserDAOmorishita();
		int count = dao.countUser(userId,userName);
		//request.setAttribute("user", user);
		//return "registUserConfirm.jsp";
		
		//重複しなければ、新規登録確認へ進む
		if(count == 0)
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("userName", userName);
			return "registUserConfirm.jsp";
		}
		else 
		{
			request.setAttribute("userId", userId);
			request.setAttribute("userPass", userPass);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("userName", userName);
			request.setAttribute("errorMessage", "登録済み情報と重複しています。");
			return "registUserStart.jsp";
		}
	}
}