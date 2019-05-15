/* Thanks for teaching me this semester Profressor David, 
 * I really enjoyed your class and you are an amazing person!:)
 */


/* 
 * This class is for finding the shortes path between
 * two actors by using breadth first search
 * @author Chris Li
 */

import java.util.*;

public class Bfs {
		//Data members
		String actor1;
		String actor2;
		HashMap<String, Set<String>> maps;
		int size;
	//Constructor
	public Bfs(){

	}

	/** Public function for assigning valuses to data members and pass the 
	 * paramters to the overload Bfs Function below and return a linkedlist
	 * that contains names of actors which denotes the shortes path btween 
	 * two target actors
	 * @param actor1: the name of the starting actor
	 * @param actor2: the name of the ending acto
	 * @param maps: a hashmap that contains the name of all actors and their connections
	 * @param size: the size of the the hash map
	 */
	public LinkedList<String> bfs(String actor1, String actor2, HashMap<String, Set<String>> maps, int size) {
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.maps = maps;
		this.size = size;
		LinkedList<String> list =  bfs(actor1, actor2, maps);
		return list;
	}
	/** Overload Bfs function which handle finding the shortest path between two
	 * actors by using Breadth first search
	 * @param actor1: the name of the starting actor
	 * @param actor2: the name of the ending actor
	 * @param maps: a hashmap that contains the names of all actors and their connections
	 */
	private LinkedList<String> bfs(String actor1, String actor2, HashMap<String, Set<String>> maps) {
		HashMap<String, String> actors = new HashMap<>();
		Queue<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<>();
		//add the src to queue		
		queue.add(actor1);
		while (!queue.isEmpty()) {

			// Dequeue one element from the queue
			String actor = queue.remove();
			/*loop through actor's neighbor, if equals actor2, then return list or if not 
			 * found, then return null at the end of the function
			 */
			if (actor.equals(actor2)) {

				LinkedList<String> list = new LinkedList<>();
				//check and add neighbors to list
				while (!actor.equals(actor1)) {

					list.addFirst(actor);
					actor = actors.get(actor);
				}
				list.addFirst(actor);
				return list;
			}

			Set<String> set = maps.get(actor);
			// Iterator through the set and update hashmap and other data members
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String str = it.next();
				//update path and visited table
				if (!visited.contains(str)) {

					actors.put(str, actor);
					queue.add(str);
					visited.add(str);
				}

			}

		}

		return null;
	}	
}