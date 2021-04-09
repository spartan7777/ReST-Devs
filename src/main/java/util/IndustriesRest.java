package util;

import java.util.*;

import matc.edu.entity.IndustryDataItem;
import matc.edu.entity.Industry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;

/**
 * This class contains the methods for retrieving, formatting, and returning industry data for our service
 * @author Robert Adams
 */
public class IndustriesRest {

    private List<IndustryDataItem> industryData;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Constructor
     */
    public IndustriesRest() {

    }


    /**
     * Gets industries from consumed service. Sets the class instance variable with the result.
     * @throws Exception
     */
    public void getIndustries() throws Exception {
        String targetString = "https://datausa.io/api/data?measure=Average%20Wage&drilldowns=Industry%20Group&Year=2019";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Industry resultList = mapper.readValue(response, Industry.class);
        industryData = resultList.getData();
    }

    /**
     * Formats consumed data into friendly, sorted JSON
     * @return
     */
    private JSONArray putIndustriesIntoJSON() {
        JSONArray sortedJSON = new JSONArray();
        try {
            //gets all industries and puts them in instance variable industryData
            getIndustries();

            //TreeSet used for natural-order sorting
            Set<String> sortedSet = putAllIndustriesIntoSet();
            JSONParser parser = new JSONParser();
            sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
        } catch (Exception e) {
            logger.info(e);
        }
        return sortedJSON;
    }

    private Set<String> putAllIndustriesIntoSet() {
        Set<String> sortedSet = new TreeSet<>();
        //Create string containing our data to be converted into JSON.
        //Put resulting JSON strings into a sorted set
        for (IndustryDataItem industry : industryData) {
            String industryName = industry.getIndustryGroup();
            String industryId = industry.getiDIndustryGroup();
            Double averageWage = industry.getAverageWage();
            String jsonObjectString = buildJSONString(industryName, industryId, averageWage);
            sortedSet.add(jsonObjectString);
        }
        return sortedSet;
    }

    private String buildJSONString(String name, String id, Double avgWage) {
        return "{"
                + "\""
                + name
                + "\""
                + ": {\"id\": "
                + "\""
                + id
                + "\""
                + ", \"Average Wage\": "
                + "\""
                + avgWage
                + "\""
                + "}}";
    }

    /**
     * Returns our produced JSON data for REST service
     * @return
     */
    @GET
    @Path("/industries")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getIndustryJSON() {
        return putIndustriesIntoJSON();
    }

}
