package util;

import java.util.*;

import matc.edu.entity.IndustryDataItem;
import matc.edu.entity.Industry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;

public class IndustriesRest {

    private List<IndustryDataItem> industryData = new ArrayList<>();
    private Map<String, String> industryNameAndId = new TreeMap<>();
    //data will be structured as a map of industry names as keys which correspond to a list of maps,
    //one map for
    private Map<String, List<Map<String, String>>> industryNameIdAvgWage = new TreeMap<>();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public IndustriesRest() {
        //putIndustryNameAndIdIntoMap();
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

    private void putIndustryNameAndIdIntoMap() {
        try {
            getIndustries();
            for(IndustryDataItem industry : industryData) {
                industryNameAndId.put(industry.getIndustryGroup(), industry.getiDIndustryGroup());
            }
            logger.info(industryNameAndId);
        } catch (Exception e) {
            logger.info(e);
        }
    }

    private void putIndustryNameIdAndAvgWageIntoJSON() {
        try {
            getIndustries();
            JSONObject industriesJSON = new JSONObject();
            JSONObject idAndAvgWageJSON = new JSONObject();
            String JSONString = "Industries: [";
            Set<String> sortedSet = new TreeSet<>();
            for (IndustryDataItem industry : industryData) {
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

                idAndAvgWageJSON.put("Industry ID", industry.getiDIndustryGroup());
                idAndAvgWageJSON.put("Industry Average Wage", industry.getAverageWage());
                industriesJSON.put(industry.getIndustryGroup(), idAndAvgWageJSON);

            }
            JSONParser parser = new JSONParser();
            JSONArray sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
            logger.info("Sorted json " + sortedJSON);
        } catch (Exception e) {
            logger.info(e);
        }
    }
    public static void main(String[] args) {
        IndustriesRest test = new IndustriesRest();
        test.putIndustryNameIdAndAvgWageIntoJSON();
    }
}
