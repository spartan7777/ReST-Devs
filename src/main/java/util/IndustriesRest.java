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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;

public class IndustriesRest {

    private List<IndustryDataItem> industryData;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public IndustriesRest() {

    }

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

    private JSONArray putIndustryNameIdAndAvgWageIntoJSON() {
        JSONArray sortedJSON = null;
        try {
            getIndustries(); //method gets all industries and puts them in instance variable industryData
            Set<String> sortedSet = new TreeSet<>(); //TreeSet used for natural-order sorting
            for (IndustryDataItem industry : industryData) {
                //Create string containing our data to be converted into JSON
                String jsonObjectString = "{"
                        + "\""
                        + industry.getIndustryGroup()
                        + "\""
                        + ": {\"id\": "
                        + "\""
                        + industry.getiDIndustryGroup()
                        + "\""
                        + ", \"Average Wage\": "
                        + "\""
                        + industry.getAverageWage()
                        + "\""
                        + "}}";
                sortedSet.add(jsonObjectString);
            }
            JSONParser parser = new JSONParser();
            sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
            logger.info("Sorted json " + sortedJSON);

        } catch (Exception e) {
            logger.info(e);
        }
        return sortedJSON;
    }

    public void getIndustryJSON() {

    }

    public static void main(String[] args) {
        IndustriesRest test = new IndustriesRest();
        test.putIndustryNameIdAndAvgWageIntoJSON();
    }
}
