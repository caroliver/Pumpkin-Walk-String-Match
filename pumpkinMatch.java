import java.util.*;

/**
 * Caroline Oliver
 * Assignment 6
 * CSCI 310
 * October 31st, 2016
 * 
 * This program is designed to find how many pumpkins (if any) from Sebastian's list of pumpkins
 * appear in the pumpkin walk.
 * 
 * Input: Two strings, one representing Sebastian's list of pumpkins and the second representing
 * 		  the pumpkins found in the pumpkin walk.
 * 
 * Output: Output will read "TRUE - all pumpkins have been found" if all pumpkins from Sebastian's
 * 		   list are found in the pumpkin walk list. If not all pumpkins are found, the program will 
 * 		   produce the number of pumpkins from Sebastian's list that were found in the pumpkin walk.
 * 		   If no pumpkins from Sebastian's list are found, the output will read:
 * 		   "No pumpkins from Sebastian's list are found.".
 * 
 * I certify that this code is entirely my own work. 
 */

public class pumpkinMatch {
	
	private static ArrayList<ArrayList<Integer>> sebRows = new ArrayList<ArrayList<Integer>>();

	public pumpkinMatch() {
		
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("**TEST ONE**");
		System.out.println();
		String sebList = "ASAB";
		String walkList = "AJSADB";
		
		exceptionCheck(sebList, walkList);
		
		System.out.println("Sebastian's List: " + sebList);
		System.out.println("Pumpkin Walk List: " + walkList);
		System.out.println();
		createGraph(sebList, walkList);
		int answer = match(sebList, walkList);
		getAnswer(answer, sebList);
		System.out.println();
		
		System.out.println("**TEST TWO**");
		System.out.println();
		String sebList2 = "ASAB";
		String walkList2 = "HDSAJSW";
		
		exceptionCheck(sebList2, walkList2);
		
		System.out.println("Sebastian's List: " + sebList2);
		System.out.println("Pumpkin Walk List: " + walkList2);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList2, walkList2);
		int answer2 = match(sebList2, walkList2);
		getAnswer(answer2, sebList2);	
		System.out.println();
		
		System.out.println("**TEST THREE**");
		System.out.println();
		String sebList3 = "ASABIBBI";
		String walkList3 = "QARSAGFTRABKPIRBEBNXZIAA";
		
		exceptionCheck(sebList3, walkList3);
		
		System.out.println("Sebastian's List: " + sebList3);
		System.out.println("Pumpkin Walk List: " + walkList3);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList3, walkList3);
		int answer3 = match(sebList3, walkList3);
		getAnswer(answer3, sebList3);
		System.out.println();
		
		System.out.println("**TEST FOUR**");
		System.out.println();
		String sebList4 = "ASAB";
		String walkList4 = "HDSAJWBK";
		
		exceptionCheck(sebList4, walkList4);
		
		System.out.println("Sebastian's List: " + sebList4);
		System.out.println("Pumpkin Walk List: " + walkList4);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList4, walkList4);
		int answer4 = match(sebList4, walkList4);
		getAnswer(answer4, sebList4);
		System.out.println();
		
		System.out.println("**TEST FIVE**");
		System.out.println();
		String sebList5 = "AA";
		String walkList5 = "AAAAAA";
		
		exceptionCheck(sebList5, walkList5);
		
		System.out.println("Sebastian's List: " + sebList5);
		System.out.println("Pumpkin Walk List: " + walkList5);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList5, walkList5);
		int answer5 = match(sebList5, walkList5);
		getAnswer(answer5, sebList5);
		System.out.println();
		
		System.out.println("**TEST SIX**");
		System.out.println();
		String sebList6 = "ASBIBA";
		String walkList6 = "AQRBKBFRA";
		
		exceptionCheck(sebList6, walkList6);
		
		System.out.println("Sebastian's List: " + sebList6);
		System.out.println("Pumpkin Walk List: " + walkList6);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList6, walkList6);
		int answer6 = match(sebList6, walkList6);
		getAnswer(answer6, sebList6);
		System.out.println();
		
		System.out.println("**TEST SEVEN**");
		System.out.println();
		String sebList7 = "SABDI";
		String walkList7 = "QUTYRNCOLPZX";
		
		exceptionCheck(sebList7, walkList7);
		
		System.out.println("Sebastian's List: " + sebList7);
		System.out.println("Pumpkin Walk List: " + walkList7);
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList7, walkList7);
		int answer7 = match(sebList7, walkList7);
		getAnswer(answer7, sebList7);
		System.out.println();
		
		System.out.println("**TEST EIGHT**");
		System.out.println("This test shows the exception catching.");
		System.out.println("An appropriate exception is thrown if either");
		System.out.println("Sebastian's List or the Pumpkin Walk list are empty.");
		System.out.println("In this case, Sebastian's List is empty as you can see below.");
		System.out.println();
		String sebList8 = "";
		String walkList8 = "AHABBAIKLRY";
		
		System.out.println("Sebastian's List: " + sebList8);
		System.out.println("Pumpkin Walk List: " + walkList8);
		
		exceptionCheck(sebList8, walkList8);
		
		System.out.println();
		sebRows = new ArrayList<ArrayList<Integer>>();
		createGraph(sebList8, walkList8);
		int answer8 = match(sebList8, walkList8);
		getAnswer(answer8, sebList8);
		System.out.println();
		
		
	}

	/*
	 * Purpose: find the number of pumpkins from Sebastian's list that match the pumpkin maze list
	 * Input: two strings, one representing Sebastian's list and the other representing the pumpkin maze list
	 * Output: true if all the pumpkins from Sebastian's list are found, or an integer representing
	 * 		   the number of pumpkins from Sebastian's list that are found if not all are located
	 * Preconditions: requires two strings representing the two pumpkin lists
	 * Postcondition: the coordinating output for how many pumpkins from Sebastian's list are found
	 */
	public static int match(String sebList, String walkList)
	{
		ArrayList<Integer> currentRow;
		ArrayList<Integer> rowAbove;
		
		for(int i = 1; i < sebList.length()+1; i++)
		{	
			//current arrayList
			currentRow = sebRows.get(i);
			
			//arrayList above for reference
			rowAbove = sebRows.get(i-1);
			
			//current character in sebList
			Character letter = sebList.charAt(i-1);
			
			for(int j = 1; j < walkList.length()+1; j++)
			{
				//System.out.println("patchList letter: " + patchList.charAt(j-1));
				if(letter.equals(walkList.charAt(j-1))) //letter matches
				{
					int newVal = 1 + rowAbove.get(j-1); //value one up and over the to left
					currentRow.add(j, newVal);
				}
				else 
				{ //letter does not match
				  //value equal max of value one to the left vs one up 
				  int value = Math.max(currentRow.get(j-1), rowAbove.get(j));
				  currentRow.add(j, value);
				}
			}
		}
		
		ArrayList<Integer> answerRow = sebRows.get(sebList.length());
		int finalVal = answerRow.get(walkList.length());
		return finalVal;
	}

	/*
	 * Purpose: create original graph structure to calculate matching pumpkins
	 * Input: two strings, one representing Sebastian's list and the other representing the pumpkin maze list
	 * Output: graph with appropriate number of space
	 * Preconditions: requires two strings representing the two pumpkin lists
	 * Postcondition: graph is created with the specifications provided by the two lists
	 */
	public static void createGraph(String sebList, String walkList)
	{
		for(int i = 0; i <= sebList.length(); i++)
		{
			ArrayList<Integer> walkColumn = new ArrayList<Integer>();
				for(int j = 0; j <= walkList.length(); j++)
				{
					walkColumn.add(0);
				}
				sebRows.add(walkColumn);
				
		}
	}
	
	/*
	 * Purpose: provide correct verbal answer for the pumpkin lists
	 * Input: the answer value derived from pumpkinMatch and the string that represents Sebastian's list
	 * Output: correct verbal answer given the parameters
	 * Preconditions: integer answer value and  string representation of Sebastian's List
	 * Postcondition: verbal answer is printed out
	 */
	public static void getAnswer(int answer, String sebList)
	{

		if(answer == sebList.length())
		{
			System.out.println("TRUE - all pumpkins have been found.");
		}
		else if(answer == 0)
		{
			System.out.println("No pumpkins from Sebastian's list are found.");
		}
		else
		{
			System.out.println("Number of pumpkins found: " + answer);
		}
	}
	
	/*
	 * Purpose: provide exception checking for the program
	 * Input: two strings, one representing Sebastian's list and the other representing the pumpkin maze list
	 * Output: an exception error is thrown if either of the conditions is satisfied
	 * Preconditions: requires two strings representing the two pumpkin lists
	 * Postcondition: if either Sebastian's List or the Pumpkin Walk List is empty, the appropriate
	 * 				  error message is thrown
	 */
	public static void exceptionCheck(String sebList, String walkList) throws Exception
	{
		if(sebList.length() == 0)
		{
			throw new Exception("Sebastian's List is empty");
		}
		
		if(walkList.length() == 0)
		{
			throw new Exception("Pumpkin Walk List is empty");
		}
	}
}
