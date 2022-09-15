

import bean.PersonalResult;
import bean.UnitResult;
import bean.User;
import dao.ResultDAO;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.IntStream;

public class PersonalResultAction extends Action
{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		//セッションの取得
		HttpSession session = request.getSession();
		
		//セッション属性userを取得
		User user = (User)session.getAttribute("user");
		//セッション属性userが取得できなければ、エラー画面へ遷移
		if(user==null)
		{
			return"access-error.jsp";
		}
		
		//ユーザIDを取得
		String userId = user.getUserId();
		
		//実績TBLに問い合わせ
		ResultDAO dao = new ResultDAO();
		List<UnitResult> urList = dao.(userId);//メソッド名追記すること
		
		//セッションに格納するリストの生成
		List<PersonalResult> list = new ArrayList<PersonalResult>();
		
		//PersonalResultBean用
		int countUnit =0;
		int totalQuestionCount =0;
		String difficult;
		int collectOnTotal =0;
		int collectOnStrategy=0;
		int collectOnTech =0;
		int collectOnManage =0;
			
		//Listの長さでループ
		for(int i=0; i<=urList.size(); i++)
		{
			for(int j =(i+1); j<=urList.size(); j++)
			{
				while(urList.get(i).getCountUnit() == urList.get(j).getCountUnit())
				{
					
				}
			}

			
			//データ数を取得
			int countData = urList.get(i).getCountDate();
			
			
		}
		
		
		
		
		//リクエスト属性にリストをセット
		request.setAttribute("PersonalResult", list);
		
		//jspにフォワード
		return "PersonalResult.jsp";
	}
	
}