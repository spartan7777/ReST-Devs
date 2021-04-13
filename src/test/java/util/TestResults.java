package util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResults {

    @Test
    public void testGetIndustryJSON() {
        IndustriesRest industries = new IndustriesRest();
        JSONArray results = industries.putIndustriesIntoJSON();
        assertNotNull(results);
        String expected = "{\"Accounting, tax preparation, bookkeeping & payroll services\":{\"Average Wage\":\"54571.88985488508\",\"id\":\"5412\"}}";
        String actual = results.get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void testGetJSON() throws Exception {
        PlacesRest places = new PlacesRest();
        String industryId = Integer.toString(44);
        String minPopulation = Integer.toString(114412);
        String minJobs = Integer.toString(0);

        //getPlaces(industryId);
        JSONArray sortedJSON = null;
        //Set<String> sortedSet = places.putEachPlaceIntoSet(minPopulation, minJobs);
        JSONParser parser = new JSONParser();
        JSONArray results = places.getJSON("44", "114412", "0");
        sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        assertNotNull(results);
        System.out.println(results.get(0));
        //String expected = "{Accounting, tax preparation, bookkeeping & payroll services:{Average Wage:54571.88985488508,id:5412}}";
        //String actual = results.get(0).toString();
        //assertEquals(expected, actual);
    }
}
