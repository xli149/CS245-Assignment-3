
/* Thanks for teaching me this semester Profressor David, 
 * I really enjoyed your class and you are an amazing person!:)
 */

/*
 * The purpose of this class is to retrieve the names from file(tmdb_5000_credits.csv) 
 * and add edges between two actors
 * The source code of json was download from 
 * hclttps://github.com/stleary/JSON-java
 * @author Chris Li
 */


import java.io.File;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.*;

public class Graph {
	//Data members
	private HashMap<String, Set<String>> hashMap;
	/** Constructor creating a hashmap for name of actors and
	 * their connected actors
	 */ 	
	public Graph(){
		hashMap = new HashMap<>(1000);
	}
	/** This function parses the input file and get the substring of each line 
	 *	by checking '[' as the start of a string and "}]" as the end of a string, 
	 *	and then split the string by checking "},". 
	 * @param file: the file to be parsed
	 */
	public void readNames(String file) {

		File inFile = new File(file);
		String inString = "";
		if(!inFile.isFile()){
			System.exit(1);
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			inString = reader.readLine();

			while ((inString = reader.readLine()) != null) {
				try{
					String nameList = inString.substring(inString.indexOf("[") + 1, inString.indexOf("}]"));
					if(!nameList.isEmpty()){
						String[] actorsNames = nameList.split("},");
						findConnections(actorsNames);
					}
				}catch(Exception e){
					continue;
				}
				
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("No such file..");
		}
	}

	public HashMap<String, Set<String>> getMap() {

		return hashMap;
	}
	/** Function for determining the connections between to
	 * actors, it loops through the splited array and try to find
	 * actors' name each by each and add a connection between 
	 * two different names
	 * @param actorsNames: an splited array that contains infromation of different actors
	 */
	private void findConnections(String[] actorsNames){
		for (int i = 0; i < actorsNames.length; i++) {

			String newString = actorsNames[i];
			newString = (newString + '}').trim().replaceAll("}}", "}");
			newString = newString.replaceAll("\"\"", "\"");

			if (newString.startsWith("{")) {

				JSONObject dataJson = new JSONObject(newString);

				String name1 = dataJson.getString("name");
				for (int j = 0; j < actorsNames.length; j++) {
					String newString2 = actorsNames[j];
					newString2 = (newString2 + '}').trim().replaceAll("}}", "}");
					newString2 = newString2.replaceAll("\"\"", "\"");
					if (newString2.startsWith("{")) {

						// checking the name are different

						if (i != j) {
							JSONObject data = new JSONObject(newString2);

							String name = data.getString("name");
							addConnections(name, name1);

							
						}

					}

				}

			}

		}


	}
	/** Function for adding connections between two actors
	 * it cheack if the hashmap already has the actor's name
	 * and if it is, then just add the second actor to its connections
	 * otherwise create a new to hold the connection and update the hashmap
	 * @param name1: the name of first actor
	 * @param name2: the name of second actor
	 */
	private void addConnections(String name1, String name2) {
		if (hashMap.containsKey(name1)) {
			Set<String> set = hashMap.get(name1);
			set.add(name2);
		} else {
			Set<String> set = new HashSet<>(10);
			set.add(name2);
			hashMap.put(name1, set);

		}

	}

	
}
