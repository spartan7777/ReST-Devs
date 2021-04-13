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
        String minWage = null;
        String maxWage = null;
        JSONArray results = industries.putIndustriesIntoJSON(minWage, maxWage);
        assertNotNull(results);
        String expected = "{\"Accounting, tax preparation, bookkeeping & payroll services\":{\"Average Wage\":\"54571.88985488508\",\"id\":\"5412\"}}";
        String actual = results.get(0).toString();
        assertEquals(expected, actual);
    }

//    @Test
//   void testGetJSON() throws Exception {
//        PlacesRest places = new PlacesRest();
//        String industryId = Integer.toString(44);
//        String minPop = Integer.toString(114412);
//        String minJobs = Integer.toString(0);
//        String maxPop = Integer.toString(0);
//        JSONArray results = places.getJSON(industryId, minPop, minJobs, maxPop);
//        String expected =
//                "{\"Aberdeen & Havre de Grace Cities, MD\":{\"Companies Per Thousand People\":\"9.3784\",\"State\":\"MD\",\"Record Count\":\"1073\",\"Population\":\"114412\"}}";
//        String actual = results.get(0).toString();
//        assertEquals(expected, actual);
//    }

    //@Test
    //void returnJSON() {
    //}

    //@Test
    //@JsonIgnoreProperties(ignoreUnknown = true)
    //public void testGetJSON() throws Exception {
   //     PlacesRest places = new PlacesRest();
    //    String industryId = Integer.toString(44);
   //     String minPopulation = Integer.toString(114412);
   //     String minJobs = Integer.toString(0);
   //     JSONArray results = places.getJSON("44", "114412", "0");
    //    assertNotNull(results);
    //    System.out.println(results.get(0));
        //String expected = "{Accounting, tax preparation, bookkeeping & payroll services:{Average Wage:54571.88985488508,id:5412}}";
        //String actual = results.get(0).toString();
        //assertEquals(expected, actual);
   // }
}
