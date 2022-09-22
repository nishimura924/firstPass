package action;
/**
 * 回答実績・コメント登録用のプログラム
 * 問題は解く毎にsessionのリスト上から削除する
 * @author　島田
 * @version　1.0.0
 */

import bean.Answer;
import bean.Bookmark;
import bean.Comment;
import bean.Conditions;
import bean.Question;
import bean.Result;
import bean.SummaryOfResult;
import bean.User;
import dao.BookmarkDAO;
import dao.CommentDAO;
import dao.ResultDAO;
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
		
		//コメント登録の場合は、実績登録などはまだしない(「次へ」ボタンのときにする)
		if(request.getParameter("submitComment") != null)
		{
			request.setAttribute("comment", request.getParameter("comment"));
			return "RegistComment.action";
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
		
		// 実績DB用の格納(ログインユーザのみ)
		if(user != null)
		{
			//実績DB用
			result.setYear(questionOfSet.get(0).getYear());
			result.setQuestionNo(questionOfSet.get(0).getQuestionNo());
			result.setGenre(questionOfSet.get(0).getGenre());
			result.setCorrect(answer.getCorrect());
			//日時はdaoの中でsql.Dateで取得
			//result.setAnswerDate(new Date());
			
			//　実績DBに書き込み
			ResultDAO dao = new ResultDAO();
			
			if(dao.insert(result) != 1)
			{
				String errorMessage = "実績登録ができません。";
				request.setAttribute("errorMessage", errorMessage);
			}
		}
		else
		{
			//ゲストユーザは実績DB登録なし
			
		}

		//結果画面用(ログインユーザ・ゲストユーザ両方)
		SummaryOfResult summaryThis = new SummaryOfResult();
		summaryThis.setYear(questionOfSet.get(0).getYear());
		summaryThis.setQuestionNo(questionOfSet.get(0).getQuestionNo());
		summaryThis.setGenre(questionOfSet.get(0).getGenre());
		summaryThis.setCorrect(answer.getCorrect());
		
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
		
		
		//　コメントの登録(ログインユーザのみ)
		if(user != null)
		{
			CommentDAO dao2 = new CommentDAO();
			Comment comment = new Comment();
			comment.setYear(questionOfSet.get(0).getYear());
			comment.setQuestionNo(questionOfSet.get(0).getQuestionNo());
			comment.setUserId(user.getUserId());
			
			//コメントなければ何もしない
			if(request.getParameter("comment") == null || request.getParameter("comment").trim().isEmpty())
			{
				
			}
			//コメントがあれば登録
			else
			{
				String commentString = request.getParameter("comment");
				
				//500文字超はカット
				if(! ValidCheck.validComment(commentString))
				{
					commentString = commentString.substring(0,500);
				}
				
				comment.setComment(commentString);
				if(dao2.insert(comment) != 1)
				{
					//DB登録処理のエラー
					String errorMessage = "コメント登録ができません。";
					request.setAttribute("errorMessage", errorMessage);
				}
				else
				{
					//ok
				}
			}
			
		}
		
		
		// ブックマークの処理(ログインユーザのみ)
		if(user != null)
		{
			BookmarkDAO dao3 = new BookmarkDAO();
			Bookmark bookmark = new Bookmark();
			bookmark.setUserId(user.getUserId());
			bookmark.setYear(questionOfSet.get(0).getYear());
			bookmark.setQuestionNo(questionOfSet.get(0).getQuestionNo());	
			
			
			if(questionOfSet.get(0).getBookmarkFlg() == null)
			{
				//0or1が必ず入るのでエラー
			}
			//既存DBにブックマーク登録ない場合
			else if(questionOfSet.get(0).getBookmarkFlg().equals("0"))
			{
				//リクエストがnull = チェックがついていないのでスルー
				if(request.getParameter("bookmark") == null)
				{	
				}
				//リクエストがブックマーク登録なのでDB登録実施
				else if( request.getParameter("bookmark").equals("1"))
				{
					if(dao3.insert(bookmark) != 1)
					{
						//DBの登録失敗→エラー
						String errorMessage = "ブックマーク登録ができません。";
						request.setAttribute("errorMessage", errorMessage);
					}
					else
					{
						//OK
					}
				}
				else
				{
				}
			}
			//既存DBにブックマーク登録ある場合
			else if(questionOfSet.get(0).getBookmarkFlg().equals("1"))
			{
				//リクエストがnull = チェックがついていないので削除
				if(request.getParameter("bookmark") == null)
				{	
					if(dao3.delete(bookmark) != 1)
					{
						//DBの登録失敗→エラー
						String errorMessage = "ブックマーク解除ができません。";
						request.setAttribute("errorMessage", errorMessage);
					}
					else
					{
						//ok
					}
				}
				//登録継続はスルー
				else if( request.getParameter("bookmark").equals("1"))
				{
				}
				else
				{
				}
			}
			//SelectQuesiton.jspで設定するブックマーク登録フラグは0or1のみ
			else
			{
			}
			
		}
		
		//先頭の問題を削除して、残りが0がどうかで遷移先異なる(ログインユーザ・ゲストユーザ両方)
		questionOfSet.remove(0);
		request.setAttribute("questionOfSet", questionOfSet);
		
		session.removeAttribute("answer");
		
		if(questionOfSet.size() == 0)
		{
			//得点結果・分析画面へ遷移
			return "UnitResult.action";
		}
		//途中終了
		else if(request.getParameter("submitFinish") != null)
		{
			//得点結果・分析画面へ遷移
			session.removeAttribute("questionOfSet");
			return "UnitResult.action";
		}
		
		return "showQuestion.jsp";
	}
	
	
}