package util;

import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.*;
import java.lang.Math;
import matc.edu.entity.Place;
import matc.edu.entity.PlaceDataItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlacesRest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String tempIndustry = "5415"; //Computer Systems Design, because why not, right?
    private final int MIN_POPULATION = 1000;
    private final int MIN_RECORD_COUNT = 20;
    private List<PlaceDataItem> placesData;

    //TODO remove this logic from constructor once main method no longer needed
    public PlacesRest() {
        try {
            getPlaces(tempIndustry);
        } catch (Exception e) {
            logger.info(e);
        }

    }

    public void getPlaces(String industryId) throws Exception {
        String targetString =
                "https://datausa.io/api/data?PUMS%20Industry=" +
                        industryId +
                        "&drilldowns=PUMA&measure=Total%20Population,ygipop%20RCA,Record%20Count";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Place resultList = mapper.readValue(response, Place.class);
        placesData = resultList.getData();
    }

    private void putPlaceNameStateAndPopulationIntoJSON() {
        Set<String> sortedSet = new TreeSet<>();
        DecimalFormat df = new DecimalFormat("#.####"); //for formatting of companies per capita to usable sig figures
        for (PlaceDataItem place : placesData) {
            //Weeds out negligible results and keeps our results manageable
            if ((place.getTotalPopulation() > MIN_POPULATION) && (place.getRecordCount() > MIN_RECORD_COUNT)) {
                //Create JSON string
                String jsonObjectString = "{"
                        + "\""
                        + place.getPUMA().substring(0, place.getPUMA().length() - 9) //takes state abbrev and PUMA off
                        + "\""
                        + ": {\"State\": "
                        + "\""
                        + place.getPUMA().substring((place.getPUMA().length() - 2)) //gets state abbrev
                        + "\""
                        + ", \"Population\": "
                        + "\""
                        + place.getTotalPopulation()
                        + "\""
                        + ", \"Record Count\": "
                        + "\""
                        + place.getRecordCount()
                        + "\""
                        + ", \"Companies Per Capita\": "
                        + "\""
                        + df.format(((double) place.getRecordCount() / (double) place.getTotalPopulation()))
                        + "\""
                        + "}}";
                sortedSet.add(jsonObjectString);
            }
        }
        JSONParser parser = new JSONParser();
        try {
            JSONArray sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
            logger.info("Sorted json " + sortedJSON);
        } catch (ParseException e) {
            logger.info(e);
        }
    }

    public static void main(String[] args) {
        PlacesRest test = new PlacesRest();
        test.putPlaceNameStateAndPopulationIntoJSON();
    }
}
