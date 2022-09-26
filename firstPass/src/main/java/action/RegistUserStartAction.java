package action;

/**
 * ユーザの入力を開始するためのアクション
 * @author　森下
 * @version　1.0.0
 */
import bean.User;
import dao.UserDAO;
import tool.Action;
import java.util.List;
import javax.servlet.http.*;
import java.util.*;


public class RegistUserStartAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
		
	{   
		//セッションの接続
		HttpSession session = request.getSession();
		//userがnullならエラー画面に遷移(URL直接入力)
		User user = (User)session.getAttribute("user");
		if(user != null)
		{
			return "registUserError.jsp";	
		}
		
		//リクエストパラメータの取得
		String userId=request.getParameter("userId");
		String userPass=request.getParameter("userPass");
		String passwordConfirm=request.getParameter("passwordConfirm");
		String userName=request.getParameter("userName");
		
		
		
		//ユーザーIDの入力値チェック
        Boolean idCheck = ValidCheck.validUserId(userId);
        if(idCheck==false)
        {
        	request.setAttribute("errorMessage", "ユーザーIDが誤りです。（１００文字以下）");
			return "registUserStart.jsp";
        }
		//ユーザー名の入力値チェック
        Boolean nameCheck = ValidCheck.validUserName(userName);
        if(nameCheck==false)
        {
        	request.setAttribute("errorMessage", "ユーザー名が誤りです。（１００文字以下）");
			return "registUserStart.jsp";
        }
		//パスワード入力値チェック（最低８文字、半角英数字のみ）
        Boolean passCheck = ValidCheck.validUserPass(userPass);
        if(passCheck==false)
        {
        	request.setAttribute("errorMessage", "パスワードが誤りです。（最低８文字、半角英数字のみ）");
			return "registUserStart.jsp";
        }
			
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


		UserDAO dao = new UserDAO();
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