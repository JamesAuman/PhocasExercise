package javaExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import org.json.JSONObject;

public class JavaExercise {

	static String[] names = {
			"James", 
			"Robert",
			"John",
			"Michael",
			"William",
			"David",
			"Richard",
			"Joseph",
			"Thomas",
			"Charles",
			"Christopher",
			"Daniel",
			"Matthew",
			"Anthony",
			"Mark",
			"Donald",
			"Steven",
			"Paul",
			"Andrew",
			"Joshua",
			"Kenneth",
			"Kevin",
			"Brian",
			"George",
			"Edward",
			"Ronald",
			"Timothy",
			"Jason",
			"Jeffrey",
			"Ryan",
			"Jacob",
			"Gary",
			"Nicholas",
			"Eric",
			"Jonathan",
			"Stephen",
			"Larry",
			"Justin",
			"Scott",
			"Brandon",
			"Benjamin",
			"Samuel",
			"Gregory",
			"Frank",
			"Alexander",
			"Raymond",
			"Patrick",
			"Jack",
			"Dennis",
			"Jerry"
	};
	static List<String> namesList = Arrays.asList(names);

	static List<String> generateCountries() {
		List<String> countryArray = new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countryArray.add(obj.getDisplayCountry());
		}
		return countryArray;
	}

	static List<JSONObject> generateDataSet(String numRows) {
		List<JSONObject> dataArray = new ArrayList<JSONObject>();
		List<String> countryArray = generateCountries();
		Random rand = new Random();
		for (int i = 0; i < Integer.parseInt(numRows); i++) {
			int firstIndex = rand.nextInt(namesList.size());
			int lastIndex = rand.nextInt(namesList.size());
			int countryIndex = rand.nextInt(countryArray.size());
			int age = rand.nextInt(100);
			JSONObject obj = new JSONObject();
			obj.put("firstName", namesList.get(firstIndex));
			obj.put("lastName", namesList.get(lastIndex));
			obj.put("age", age);
			obj.put("id", i);
			obj.put("country", countryArray.get(countryIndex));
			dataArray.add(obj);
		}
		return dataArray;
	}

	static String waitInput(String message, boolean toClose) {
		Scanner userInput = new Scanner(System.in);
		while(true) {
			System.out.println(message);
			String input = userInput.nextLine();
			if (toClose) {
				userInput.close();
			} else {
				if (!input.matches("-?\\d+")) {
					System.out.println(input + " is not a valid input.");
					System.exit(0);
				}
			}
			return input;
		}
	}

	static void printDataSet(List<JSONObject> dataArray) {
		dataArray.forEach((dataEntry) -> {
			System.out.println(dataEntry);
		});
	}

	static void printOldest(List<JSONObject> dataArray) {
		int oldestAge = 0;
		JSONObject oldestPerson = new JSONObject();
		for (int i = 0; i < dataArray.size(); i++) {
			int age = dataArray.get(i).getInt("age");
			if (age > oldestAge) {
				oldestAge = age;
				oldestPerson = dataArray.get(i);
			}
		}
		String firstName = oldestPerson.getString("firstName");
		String lastName = oldestPerson.getString("lastName");
		int age = oldestPerson.getInt("age");
		String country = oldestPerson.getString("country");
		System.out.println("The oldest person is " + firstName + " " + lastName + " who is " + age + " year/s old hailing from " + country);
	};

	static void groupByCountry(List<JSONObject> dataArray) {
		Map<String, Integer> countryList = new HashMap<String, Integer>();
		for (int i = 0; i < dataArray.size(); i++) {
			String country = dataArray.get(i).getString("country");
			int count = countryList.containsKey(country) ? countryList.get(country) : 0;
			countryList.put(country, count + 1);
		} 

		TreeMap<String, Integer> sortedCountries = new TreeMap<>();
		sortedCountries.putAll(countryList);
		for (Map.Entry<String, Integer> country : sortedCountries.entrySet()) {
			System.out.println(country.getKey() + " | " + country.getValue());
		}
	}

	static void getMostCommonName(List<JSONObject> dataArray) {
		Map<String, Integer> nameList = new HashMap<String, Integer>();
		for (int i = 0; i < dataArray.size(); i++) {
			String firstName = dataArray.get(i).getString("firstName");
			int count = nameList.containsKey(firstName) ? nameList.get(firstName) : 0;
			nameList.put(firstName, count + 1);
		}
		for (int i = 0; i < dataArray.size(); i++) {
			String lastName = dataArray.get(i).getString("lastName");
			int count = nameList.containsKey(lastName) ? nameList.get(lastName) : 0;
			nameList.put(lastName, count + 1);
		}
		int highestCount = 0;
		String mostCommon = "";
		for (Map.Entry<String, Integer> person : nameList.entrySet()) {
			String name = person.getKey();
			int count = person.getValue();
			if (count > highestCount) {
				highestCount = count;
				mostCommon = name;
			}
		}
		if (highestCount == 1) {
			System.out.println("All names in this dataset are unique.");
		} else {
			System.out.println("The most common name in the data set is: " + mostCommon + " which shows up " + highestCount + " times.");
		}
	}
	
	static boolean countryExists(String country) {
		List<String> countryList = generateCountries();
		for (int i = 0; i < countryList.size(); i++) {
			if (countryList.get(i).equals(country)) {
				return true;
			}
		}
		return false;
	}

	static boolean countryInArray(String country, List<JSONObject> dataArray) {
		for (int i = 0; i < dataArray.size(); i++) {
			if (dataArray.get(i).getString("country").equals(country)) {
				return true;
			}
		}
		return false;
	}

	static void getCountryAgeGroup(String country, List<JSONObject> dataArray) {
		if (!countryExists(country)) {
			System.out.println(country + " is not a country in the ISO 3166 standard or is not a valid command.");
			System.exit(0);
		}
		if (!countryInArray(country, dataArray)) {
			System.out.println("No generated profile is from " + country + ".");
			System.exit(0);
		}
		Map<String, Integer> ageGroups = new HashMap<String, Integer>();
		ageGroups.put("0-10", 0);
		ageGroups.put("11-20", 0);
		ageGroups.put("21-30", 0);
		ageGroups.put("31-40", 0);
		ageGroups.put("41-50", 0);
		ageGroups.put("51-60", 0);
		ageGroups.put("61-70", 0);
		ageGroups.put("71-80", 0);
		ageGroups.put("81-90", 0);
		ageGroups.put("91-100", 0);
		for (int i = 0; i < dataArray.size(); i++) {
			if (dataArray.get(i).getString("country").equals(country)) {
				int age = dataArray.get(i).getInt("age");
				if (age >= 0 && age <= 10) {
					String ageGroup = "0-10";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 11 && age <= 20) {
					String ageGroup = "11-20";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 21 && age <= 30) {
					String ageGroup = "21-30";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 31 && age <= 40) {
					String ageGroup = "31-40";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 41 && age <= 50) {
					String ageGroup = "41-50";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 51 && age <= 60) {
					String ageGroup = "51-60";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 61 && age <= 70) {
					String ageGroup = "61-70";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 71 && age <= 80) {
					String ageGroup = "71-80";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 81 && age <= 90) {
					String ageGroup = "81-90";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				} else if (age >= 91 && age <= 100) {
					String ageGroup = "91-100";
					ageGroups.put(ageGroup, ageGroups.get(ageGroup) + 1);
				}
			}
		}
		System.out.println("AgeGroup | Count");
		TreeMap<String, Integer> sortedAgeGroups = new TreeMap<>();
		sortedAgeGroups.putAll(ageGroups);
		for (Map.Entry<String, Integer> ageGroup : sortedAgeGroups.entrySet()) {
			System.out.println(ageGroup.getKey() + " | " + ageGroup.getValue());
		}
	}

	static void queryData(String queryMode, List<JSONObject> dataArray) {
		if (queryMode.equals("1")) {
			printOldest(dataArray);
		} else if (queryMode.equals("2")) {
			groupByCountry(dataArray);
		} else if (queryMode.equals("3")) {
			getMostCommonName(dataArray);
		} else {
			getCountryAgeGroup(queryMode, dataArray);
		}
	}


	public static void main(String[] args) {
		String numRows = waitInput("Please enter the number of rows to generate and then press enter to continue.", false);
		List<JSONObject> dataSet = generateDataSet(numRows);
		printDataSet(dataSet);
		System.out.println("\n" + numRows + " rows generated.\n");
		String queryString = "Press 1 to find the oldest person.\nPress 2 to count the entries per country.\nPress 3 to find the name which occurs the most in the data set.\nOR Enter a country to find the count per age group.\n";
		String queryMode = waitInput(queryString, true);
		queryData(queryMode, dataSet);	
	}
}