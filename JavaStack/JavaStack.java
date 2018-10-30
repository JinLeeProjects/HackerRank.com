package JavaStack;

import java.util.Stack;

public class JavaStack 
{

	public static void main(String[] args) 
	{
		String test1 = "{{{}"; //false
		String test2 = "{{}}"; //true
		String test3 = "}}}}"; //false
		String[] testCases = {test1,test2,test3};
		
		
		for(int i=0; i < testCases.length; i++)
		{
			System.out.println(stringBalance(testCases[i]));
		}
	}// end of main
		
	public static boolean stringBalance(String input)
	{
		// we are going to assume that string is balance unless proven otherwise
		boolean isBalance = true;
		
		// Base check, if lenght of string is odd or zero, string is not balance
		if(input.length() == 0 || input.length()%2 != 0)
		{
			isBalance = false;
		}
		else
		{  	//declare stack to hold leftelements {,[,(
			Stack<Character> leftValue = new Stack<>();
			
			// quick checks on the last element of the string
			// for cases such as {{{{ or {}{}[{
			char lastElement = input.charAt(input.length()-1);
			if (lastElement == '(' || lastElement == '{' || lastElement == '[')
			{
				isBalance = false;
			}
			else
			{
				for(int i=0; i<input.length(); i++)
				{	
					char currentElement = input.charAt(i);
					if (currentElement == '(' || currentElement == '{' || currentElement == '[')
					{	// if currentElement is a leftElement, push to stack
						leftValue.push(currentElement);
					}	// if currentElement is a rightElement and stack is not empty
					else if ( (currentElement == ')' && !leftValue.empty() ) || 
						      (currentElement == '}' && !leftValue.empty() ) || 
						      (currentElement == ']' && !leftValue.empty() ) )
					{
						char stackTop = leftValue.pop();
						// compare stackTop and currentElement to see if they make a pair
						// also if last element is a rightElement but stack size is more than one, string is not balance
						// case such as {{{}
						if(bracketPairs(stackTop,currentElement) == false || i == input.length()-1 && leftValue.size() > 1 )
						{
							isBalance = false;
							break;
						}
					}
					else
					{
						isBalance = false;
						break;
					}
				}//end of forLoop
			}
		}
		return isBalance;
	}//end of stringBalance
	public static boolean bracketPairs(char left, char right)
	{	// if left and right make a pair, return true, else return false
		if ( (left == '(' && right ==')' ) ||
			 (left == '{' && right =='}' ) ||
			 (left == '[' && right ==']' ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}//end of bracketPairs

}
