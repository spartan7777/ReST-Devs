package util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;
import matc.edu.entity.Place;
import matc.edu.entity.PlaceDataItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This class contains the methods for retrieving, formatting, and returning place data
 * @author Robert Adams
 */
public class PlacesRest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String tempIndustry = "5415"; //Computer Systems Design, because why not, right?
    private final int MIN_POPULATION = 1000;
    private final int MIN_RECORD_COUNT = 20;
    private List<PlaceDataItem> placesData;

    public PlacesRest() {

    }

    /**
     * Gets place data from consumed service. Sets placesData list as returned result.
     * @param industryId id of the industry to search
     * @throws Exception
     */
    private void getPlaces(String industryId) throws Exception {
        String mainString = "https://datausa.io/api/data?";
        String query = "PUMS%2520Industry="
                + industryId
                + "&drilldowns=PUMA&measure=Total Population,ygipop RCA,Record Count";
        String url = mainString + URLEncoder.encode(query, StandardCharsets.UTF_8);
        logger.info(url);
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Place resultList = mapper.readValue(response, Place.class);
        placesData = resultList.getData();
    }

    /**
     * Formats, sorts, and returns json data
     * @return our formatted json data
     * @throws Exception
     */
    private JSONArray putPlaceNameStateAndPopulationIntoJSON(String industryId, String minPop, String minJobs) throws Exception {
        if (minPop == null) { minPop = "0"; }
        if (minJobs == null) { minJobs = "0"; }
        getPlaces(industryId);
        JSONArray sortedJSON = null;
        Set<String> sortedSet = new TreeSet<>();
        DecimalFormat df = new DecimalFormat("#.####"); //for formatting of companies per capita to usable sig figures
        for (PlaceDataItem place : placesData) {
            //Weeds out negligible results and keeps our results manageable
            if ((place.getTotalPopulation() > Integer.parseInt(minPop)) && (place.getRecordCount() > Integer.parseInt(minJobs))) {
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
                        + ", \"Companies Per Thousand People\": "
                        + "\""
                        + df.format(((double) place.getRecordCount() / (double) place.getTotalPopulation()) * 1000)
                        + "\""
                        + "}}";
                sortedSet.add(jsonObjectString);
            }
        }
        JSONParser parser = new JSONParser();
        try {
            sortedJSON = (JSONArray) parser.parse(sortedSet.toString());
            logger.info("Sorted json " + sortedJSON);
        } catch (ParseException e) {
            logger.info(e);
        }
        return sortedJSON;
    }

    /**
     * Returns the data for REST
     * @return our data
     * @throws Exception
     */
    @GET
    @Path("/places")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray returnJSON(
            @QueryParam("industry") String industryId,
            @QueryParam("minPopulation") String minPopulation,
            @QueryParam("minJobs") String minJobs
                ) throws Exception{
        return putPlaceNameStateAndPopulationIntoJSON(industryId, minPopulation, minJobs);
    }
}
