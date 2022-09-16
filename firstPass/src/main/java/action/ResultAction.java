package action;
/**
 * 回答実績・コメント登録用のプログラム
 * 問題は解く毎にsessionのリスト上から削除する
 * @author　島田
 * @version　1.0.0
 */

import bean.Answer;
import bean.Bookmark;
import bean.Conditions;
import bean.Question;
import bean.Result;
import bean.SummaryOfResult;
import bean.User;
import dao.BookmarkDAOshimada;
import dao.ResultDAOshimada;
import tool.Action;
import javax.servlet.http.*;
import java.util.*;

public class ResultAction extends Action
{
	@SuppressWarnings("unchecked")
	
	/**
	 * mainメソッド
	 * daoでupdate後、showQuestion.jspに遷移する
	 * 問題が残っていないのであれば。unitResult.jspに遷移する
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		
		//URL直接入力のエラー処理
		List<Question> questionOfSet = (List<Question>)session.getAttribute("questionOfSet");
		if(questionOfSet == null)
		{
			return "access-error.jsp";
		}
		
		Answer answer = (Answer)session.getAttribute("answer");
		if(answer == null)
		{
			return "access-error.jsp";
		}
		
		
		//ログインユーザの実績をDB格納するための事前処理
		//セッション名は要確認
		User user = (User)session.getAttribute("user");
		Conditions conditions = (Conditions)session.getAttribute("conditions");
		Result result = new Result();
		
		if(user != null)
		{
			if(conditions != null)
			{
				//ユーザID
				result.setUserId(user.getUserId());
				
				//難易度
				result.setDifficulty(conditions.getDifficulty());
				
				//累積実施回数
				result.setCountUnit(user.getCountUnit());
			}
			else
			{
				//ユーザID持っているのに、難易度がないのでエラー
				return "access-error.jsp";
			}
		}else
		{
				//ゲストユーザの場合は実績DBに格納しないだけでエラーではない
		}
		
		// 実績DB用の格納
		if(user != null)
		{
			//実績DB用
			result.setYear(questionOfSet.get(0).getYear());
			result.setQuestionNo(questionOfSet.get(0).getQuestionNo());
			result.setGenre(questionOfSet.get(0).getGenre());
			result.setCorrect(answer.getCorrect());
			//日時はdaoの中でsql.Dateで取得
			//result.setAnswerDate(new Date());
		}
		else
		{
			//ゲストユーザは実績DB登録なし
			
		}

		//結果画面用はゲストユーザも含めて格納
		SummaryOfResult summaryThis = new SummaryOfResult();
		summaryThis.setYear(questionOfSet.get(0).getYear());
		summaryThis.setQuestionNo(questionOfSet.get(0).getQuestionNo());
		summaryThis.setGenre(questionOfSet.get(0).getGenre());
		summaryThis.setCorrect(answer.getCorrect());
		
		
		//　実績DBに書き込み
		ResultDAOshimada dao = new ResultDAOshimada();
		
		if(dao.insert(result) != 1)
		{
			String errorMessage = "DB更新ができません。";
			request.setAttribute("errorMessage", errorMessage);
		}
		
		//sessionのsummaryに今回分をセット
		List<SummaryOfResult> summary;
		
		if (session.getAttribute("summary") == null)
		{
			//1問目の回答時はリストを作成
			summary = new ArrayList<>();
		}
		else
		{
			//2問目以降はリストに追加
			summary = (List<SummaryOfResult>)session.getAttribute("summary");
		}
		
		summary.add(summaryThis);
		session.setAttribute("summary", summary);
		
		//11　コメントの取得
		//12　コメントの登録
		
		
		// ブックマークの処理
		BookmarkDAOshimada dao2 = new BookmarkDAOshimada();
		Bookmark bookmark = new Bookmark();
		bookmark.setUserId(user.getUserId());
		bookmark.setYear(questionOfSet.get(0).getYear());
		bookmark.setQuestionNo(questionOfSet.get(0).getQuestionNo());	
		
		if(questionOfSet.get(0).getBookmarkFlg().equals("1"))
		{
			if(request.getParameter("bookmark") == null)
			{
				//登録済から解除
				//登録済から解除
				if(dao2.delete(bookmark) != 1)
				{
					//エラーメッセージ
				}
				else
				{
					//ok
				}
				
			}
			else if( request.getParameter("bookmark").equals("1"))
			{
				//登録済かつ解除しないなので処理なし
			}else
			{
				//登録済から解除
				if(dao2.delete(bookmark) != 1)
				{
					//エラーメッセージ
				}
				else
				{
					//ok
				}
			}
		}
		else
		{
			if(request.getParameter("bookmark") == null)
			{
				//エラーなのでスルー(発生しないはず)
			}
			else if( request.getParameter("bookmark").equals("1"))
			{
				//登録なしから今回登録
				if(dao2.insert(bookmark) != 1)
				{
					//エラーメッセージ
				}
				else
				{
					//OK
				}
			}
			else
			{
				//登録なしかつ登録しないなので処理なし
			}
		}
		
		
		//先頭の問題を削除して、残りが0がどうかで遷移先が変わる
		questionOfSet.remove(0);
		request.setAttribute("questionOfSet", questionOfSet);
		
		//answerのクリア
		//これがないと、次の問題表示がおかしくなる
		session.removeAttribute("answer");
		
		if(questionOfSet.size() == 0)
		{
			//本当は別画面
			return "unitResult.jsp";
		}
		
		return "showQuestion.jsp";
	}
	
	
}