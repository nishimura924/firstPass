package action;

public class ValidCheck
{
	//ユーザーIDの入力値チェック（半角英数字１００文字以内）
	public static boolean ValidUserId(String userId)
	{
		return userId.matches("[0-9a-zA-Z]{1,100}");
	}
	//ユーザー名の入力値チェック（１００文字以内）
	public static boolean ValidUserName(String userName)
	{
		return userName.matches("[0-9a-zA-Zａ-ｚＡ-Ｚあ-ん]{1,100}");
	}
	//ユーザーパスワードの入力値チェック（半角英数字、８文字以上）
	public static boolean ValidUserPass(String userPass)
	{
		return userPass.matches("[0-9a-zA-Z]{8,100}");
	}
}