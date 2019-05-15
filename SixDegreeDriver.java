
/* Thanks for teaching me this semester Profressor David, 
 * I really enjoyed your class and you are an amazing person!:)
 */

/*
 * The purpose of this class is a driver for 
 * runing the progrom
 * @author Chris Li
 */

import java.util.*;

public class SixDegreeDriver {
	public static void main(String[] args) {
		// Checking if user passing the name of the file as a parameter
		if (args.length != 1) {
			System.out.println("No enough parameters");
			System.exit(1);
		}
		// Data members
		
		Graph graph = new Graph();
		HashMap<String, Set<String>> map = graph.getMap();
		Bfs search = new Bfs();
		Scanner scan = new Scanner(System.in);
		boolean flag = false;
		// Passing the name of the file to Graph class 
		graph.readNames(args[0]);
		/* while loop keeps looping to get input from user
		 * about the name of the firs and the second actor
		 */
		while (true) {

			System.out.print("Actor 1 name: ");
			String actor1 = scan.nextLine();
			for (String s: map.keySet()) {
				if (s.equalsIgnoreCase(actor1)) {
					actor1 = s;
					flag = true;
				}
			}
			if(!flag){
				System.out.println("No such actor.");
				continue;
			}
            
			System.out.print("Actor 2 name: ");
			String actor2 = scan.nextLine();
			for (String s: map.keySet()) {
				if (s.equalsIgnoreCase(actor2)) {
					actor2 = s;
					flag = true;
				}
			}
			if(!flag){
				System.out.println("No such actor.");
				continue;
			}
			/* Using function bfs in Bfs class to find the shortest path between
			 * two actors and call printPath function to print it out
			 */
			LinkedList<String> path = search.bfs(actor1, actor2, map, map.size());
			printPath(actor1, actor2,path);
		}
	}
	/** Function for printing the shortest path between the first and the second 
	 * actor.
	 * @param actor1: the name of the starting actor
	 * @param actor2: the name of the ending actor
	 * @param path: the linked list that contains the names of actors, which denotes the 
	 *	shortest path btween two actors
	 */	
	public static void printPath(String actor1, String actor2, LinkedList<String> path) {

	if (path == null) {

			System.out.println("There is no such path between " + actor1 + "and " + actor2);

		} else {

			System.out.print("Path between " + actor1 + " and  " + actor2 + "is: " + path.get(0));

			for (int i = 1; i < path.size(); i++) {

				System.out.print(" --> " + path.get(i));

			}

			System.out.println();
		}

	}
}
