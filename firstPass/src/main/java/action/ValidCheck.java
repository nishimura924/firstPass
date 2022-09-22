package action;

public class ValidCheck
{
	//ユーザーIDの入力値チェック（半角英数字１００文字以内）
	public static boolean validUserId(String userId)
	{
		return userId.matches("[0-9a-zA-Z]{1,100}");
	}
	//ユーザー名の入力値チェック（１００文字以内）
	public static boolean validUserName(String userName)
	{
		return userName.matches("[0-9a-zA-Zａ-ｚＡ-Ｚあ-ん一-龠々～？！]{1,100}");
	}
	//ユーザーパスワードの入力値チェック（半角英数字、８文字以上）
	public static boolean validUserPass(String userPass)
	{
		return userPass.matches("[0-9a-zA-Z]{8,100}");
	}
	
	//コメントの入力値チェック（５００文字以内）
	public static boolean validComment(String comment)
	{
		return comment.matches("[0-9a-zA-Zａ-ｚＡ-Ｚあ-ん一-龠々～？！]{1,5}");
	}
}