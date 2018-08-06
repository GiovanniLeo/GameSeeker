package utility;



public class Utitlity {

	public static String clearSpecialCharacter(String string)
	{
		String clearString = "";
		clearString = string.replaceAll("[^a-zA-Z]+", " ");
		return clearString;
	}
}
