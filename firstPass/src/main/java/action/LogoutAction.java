package action;

import tool.Action;
import javax.servlet.http.*;
/**
* ログアウト用アクション
* @author　kuroki
* @version　1.0.0
*/
public class LogoutAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		//セッションの取得
		HttpSession session = request.getSession();
		
		//セッションuserが取得出来た場合
		if(session.getAttribute("user") != null)
		{
			//セッションuserを破棄、トップに遷移
			session.removeAttribute("user");
			return "index.jsp";
		}
		//セッションuserが取得できない場合、エラー画面へ遷移
		else
		{
			return "access-error.jsp";
		}
	}
}