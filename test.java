import java.util.*;
public class test{
	public static void  main(String args[]){

		String inString = "[{asdfsdf}, {asdfsdf}]";
		//String[] n = s.split("\\[|\\]");
		/*String sub = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
		System.out.println("\'" + sub + "\'");*/
		int lineStart = inString.indexOf('[');
		int lineEnd = inString.indexOf("}]");
		if (lineStart > -1 && lineEnd > -1) {
			String actorList = inString.substring(lineStart + 1, lineEnd);
			System.out.println("\'" + actorList + "\'");
		}



	}

}