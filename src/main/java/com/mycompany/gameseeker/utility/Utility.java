package com.mycompany.gameseeker.utility;



public class Utility {

	public static String clearSpecialCharacter(String string)
	{
		String clearString = "";
		clearString = string.replaceAll("[^a-zA-Z]+", " ");
		return clearString;
	}
	
	public static String normalizeSearchQuery(String searchQuery, boolean lowercase)
	{
		String normalizedSearchQuery = searchQuery.replace(" ", "+");
		if (lowercase) {
			
			normalizedSearchQuery = normalizedSearchQuery.toLowerCase();
		}
		
		return normalizedSearchQuery;
	}
}
