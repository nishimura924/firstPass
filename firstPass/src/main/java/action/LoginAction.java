package action;

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;
/**
* ログイン用アクション
* @author　kuroki
* @version　1.0.0
*/

public class LoginAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		//セッションの取得、生成
		HttpSession session = request.getSession();
				
		//リクエストパラメーターの取得
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		
		//セッションuserが取得出来た場合
		if(session.getAttribute("user") != null)
		{
			//ログイン中エラー画面に遷移する。
			return "login-error.jsp";
		}
		
			
		//ログインIDかパスワードが未入力だったら、エラーメッセージをセットしてログイン画面に戻す。
		if(userId.isEmpty())
		{
			request.setAttribute("errorMsg", "ログインIDを入力してください。");
			return "login.jsp";
		}
		else if(password.isEmpty())
		{
			request.setAttribute("errorMsg", "パスワードを入力してください。");
			return "login.jsp";
		}
		
		//UserDAOの検索メソッド呼び出し（引数にリクエストパラメータの値をセット）
		//UserDAOで生成したBeanをuserで受ける
		UserDAO dao =new UserDAO();
		User user =dao.doLogin(userId, password);
	
		//userBeanからuserIDが取得できる場合、セッションに格納してメニュー画面を表示
		if(user.getUserId()!=null)
		{
			session.setAttribute("user",user);
			return "index.jsp";
		}
		else
		//取得できなければエラーメッセージを返し、ログイン画面を表示する。
		{
			request.setAttribute("errorMsg", "IDまたはパスワードに誤りがあります。");
			return "login.jsp";
		}
		
		
	}
}