

import bean.User;
import dao.UserDAO;
import tool.Action;
import javax.servlet.http.*;

public class LoginAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		//セッションの取得、生成
		HttpSession session = request.getSession();
				
		//リクエストパラメーターの取得
		String userId = request.getParameter("useId");
		String password = request.getParameter("password");
		
		//ログインIDかパスワードが未入力だったら、エラーメッセージをセットしてログイン画面に戻す。
		if(userId==null)
		{
			request.setAttribute("errorMsg", "ログインIDを入力してください。");
			return "login.jsp";
		}
		else if(password==null)
		{
			request.setAttribute("errorMsg", "パスワードを入力してください。");
			return "login.jsp";
		}
		
		//UserDAOの検索メソッド呼び出し（引数にリクエストパラメータの値をセット）
		//UserDAOで生成したBeanをuserで受ける
		UserDAO dao =new UserDAO();
		User user =dao.seach(userId, password);
		
		//利用者情報TBLから利用者情報が取れた場合、セッションに格納してメニュー画面を表示
		if(user!=null)
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