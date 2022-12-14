package action;

public class ValidCheck
{
	//ユーザーIDの入力値チェック（半角英数字１００文字以内）
	public static boolean validUserId(String userId)
	{
		return userId.matches("[0-9a-zA-Z]{1,100}");
	}
	//ユーザー名の入力値チェック（半角・全角英数字、漢字・かな・カナ、記号（！？ー～＿＊※＠）１００文字以内）
	public static boolean validUserName(String userName)
	{
		return userName.matches("[\u3000-\u303F\u4E00-\u9FFF\u3005-\u30070-9０-９a-zA-Zａ-ｚＡ-Ｚあ-んア-ンー－―‐龠々「」～~_＿！!？?※:：＊*＠@+・,./／・]{1,100}");
	}
	//ユーザーパスワードの入力値チェック（半角英数字、８文字以上）
	public static boolean validUserPass(String userPass)
	{
		return userPass.matches("[0-9a-zA-Z]{8,100}");
	}
	
	//コメントの入力値チェック（半角・全角英数字、漢字・かな・カナ、記号（！？ー～＿＊＠）)
	public static boolean validComment(String comment)
	{
		return comment.matches("[\\￥\\¥\"\'\\[\\]\u3000-\u303F\u4E00-\u9FFF\u3005-\u30070-9０-９a-zA-Zａ-ｚＡ-Ｚあ-んア-ンー－―‐龠々↑↓→←「」～~_＿！!？?※:：＊*＠@+・,./／・|｜＝≠=<>＜＞【】#＃%％””’’＆&＄$^｛｝{}\\\\]{1,}");

	}
	
	//コメントの入力値文字数チェック（正規表現部分はvalidCommentと同じ、500文字超は末尾削除するためのチェック）
	public static boolean validCommentLength(String comment)
	{
		return comment.matches("[\\￥\\¥\"\'\\[\\]\u3000-\u303F\u4E00-\u9FFF\u3005-\u30070-9０-９a-zA-Zａ-ｚＡ-Ｚあ-んア-ンー－―‐龠々↑↓→←「」～~_＿！!？?※:：＊*＠@+・,./／・|｜＝≠=<>＜＞【】#＃%％””’’＆&＄$^｛｝{}\\\\]{1,500}");

	}
	
}