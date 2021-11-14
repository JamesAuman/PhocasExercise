package javaExercise;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class JavaExerciseTest {
	
	public List<JSONObject> generateValidJsonList() {
		JSONObject user1 = new JSONObject("{\"firstName\":\"Samuel\",\"lastName\":\"Joseph\",\"country\":\"Dominican Republic\",\"id\":0,\"age\":82}");
		JSONObject user2 = new JSONObject("{\"firstName\":\"Brian\",\"lastName\":\"Jack\",\"country\":\"Faroe Islands\",\"id\":1,\"age\":42}");
		JSONObject user3 = new JSONObject("{\"firstName\":\"Brandon\",\"lastName\":\"George\",\"country\":\"Grenada\",\"id\":2,\"age\":8}");
		JSONObject user4 = new JSONObject("{\"firstName\":\"Anthony\",\"lastName\":\"Timothy\",\"country\":\"South Korea\",\"id\":3,\"age\":12}");
		JSONObject user5 = new JSONObject("{\"firstName\":\"Dennis\",\"lastName\":\"Christopher\",\"country\":\"Antigua & Barbuda\",\"id\":4,\"age\":42}");
		List<JSONObject> validJsonList = new ArrayList<JSONObject>();
		validJsonList.add(user1);
		validJsonList.add(user2);
		validJsonList.add(user3);
		validJsonList.add(user4);
		validJsonList.add(user5);
		return validJsonList;
	}
			
	@Test
	public void generateCountries_toBeComplete() {
		List<String> countryArray = JavaExercise.generateCountries();
		assertEquals(249, countryArray.size());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"New Zealand", "Australia", "Germany", "Netherlands"})
	public void countryExists_validCountries(String country) {
		assertTrue(JavaExercise.countryExists(country));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Winterfell", "Narnia", "Mordor", "Hogwarts"})
	public void countryExists_invalidCountries(String country) {
		assertFalse(JavaExercise.countryExists(country));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Dominican Republic", "Faroe Islands", "Grenada", "South Korea", "Antigua & Barbuda"})
	public void countryInArray_validCountriesInArray(String country) {
		List<JSONObject> validJsonList = generateValidJsonList();
		assertTrue(JavaExercise.countryInArray(country, validJsonList));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"China", "New Zealand", "Australia", "Germany", "Netherlands"})
	public void countryInArray_validCountriesNotInArray(String country) {
		List<JSONObject> validJsonList = generateValidJsonList();
		assertFalse(JavaExercise.countryInArray(country, validJsonList));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"5", "1", "10", "20", "100", "2000"})
	public void generateCountries_generateCorrectNumRows(String numRows) {
		List<JSONObject> dataArray = JavaExercise.generateDataSet(numRows);
		assertEquals(Integer.parseInt(numRows), dataArray.size());
	}
}
