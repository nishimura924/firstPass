package action;
import javax.servlet.http.*;
import bean.Choice;
import bean.Conditions;
import bean.Question;
import bean.User;
import java.util.*;

import tool.Action;

public class IndexAction extends Action
{
	//単体テスト用のダミープログラム
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		
		//ログイン系
		User user = new User();
		user.setUserId("id0001");
		user.setUserName("name1");
		user.setCountUnit(6);
		
		session.setAttribute("user", user);
		
		//問題1問目
		Choice choice1 = new Choice();
		Choice choice2 = new Choice();
		Choice choice3 = new Choice();
		Choice choice4 = new Choice();
		Question question = new Question();
		List<Question> questionOfSet = new ArrayList<>();
		
		choice1.setChoice("商品にタグを取り付け、出荷監視をすることによって、在庫管理を自動化する。");
		choice2.setChoice("生産ラインに温度センサを設置し、温度監視を行って、生産に適した温度が維持されるよう制御する。");
		choice3.setChoice("設計情報をデータベース化しておき、設計図面を共有・再利用する。");
		choice4.setChoice("ロボットを利用して生産ラインを自動化し、工場を無人化する。");
		choice1.setIsCorrect("0");
		choice2.setIsCorrect("0");
		choice3.setIsCorrect("1");
		choice4.setIsCorrect("0");
		
		question.setIndex(1);
		question.setYear("2022年秋期");
		question.setQuestionNo(23);
		question.setGenre("ストラテジ系");
		question.setQuestion("CADを活用した業務改善の事例として、適切なものはどれか。");
//		question.setQuestionPic(String questionPic);
		question.setChoice1(choice1);
		question.setChoice2(choice2);
		question.setChoice3(choice3);
		question.setChoice4(choice4);
//		question.setChoicePicFlg(String choicePicFlg);
		question.setBookmarkFlg("1");	//登録なし	
		
		questionOfSet.add(question);
		
		//問題2問目
		Choice choice21 = new Choice();
		Choice choice22 = new Choice();
		Choice choice23 = new Choice();
		Choice choice24 = new Choice();
		Question question2 = new Question();
		choice21.setChoice("PERT図");
		choice22.setChoice("管理図");
		choice23.setChoice("特性要因図");
		choice24.setChoice("パレート図");
		choice21.setIsCorrect("0");
		choice22.setIsCorrect("0");
		choice23.setIsCorrect("0");
		choice24.setIsCorrect("1");
		
		question2.setIndex(2);
		question2.setYear("2022年春期");
		question2.setQuestionNo(31);
		question2.setGenre("ストラテジ系");
		question2.setQuestion("コールセンタの顧客サービスレベルを改善するために，顧客から寄せられたコールセンタ対応に関する苦情を分類集計する。苦情の多い順に，件数を棒グラフ，累積百分率を折れ線グラフで表し，対応の優先度を判断するのに適した図はどれか。");
//		question2.setQuestionPic(String questionPic);
		question2.setChoice1(choice21);
		question2.setChoice2(choice22);
		question2.setChoice3(choice23);
		question2.setChoice4(choice24);
//		question2.setChoicePicFlg(String choicePicFlg);
		question2.setBookmarkFlg("1");	//登録あり	
		
		questionOfSet.add(question2);

		session.setAttribute("questionOfSet", questionOfSet);

		
		//検索条件
		Conditions conditions = new Conditions();
		String[] year =  new String[1];
		year[0] = "2021";
		conditions.setYear(year);
		String[] genre = new String[1];
		genre[0] = "ストラテジ系";
		conditions.setGenre(genre);
		conditions.setDifficulty("1");	//0がeasy, 1がnormal
		conditions.setQuestionCount(1);
		conditions.setBookmarkOnly("0");

		session.setAttribute("conditions", conditions);
		
		
		return "index.jsp";
	}
	
	
}