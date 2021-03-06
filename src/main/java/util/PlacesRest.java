package util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;
import matc.edu.entity.Places;
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
    private final int YEAR = 2019;
    private final int NUM_PEOPLE = 1000;
    private List<PlaceDataItem> placesData;

    /**
     * constructor
     */
    public PlacesRest() {

    }

    /**
     * Gets place data from consumed service. Sets placesData list as returned result.
     * @param industryId id of the industry to search
     * @throws Exception exception
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
        Places resultList = mapper.readValue(response, Places.class);
        placesData = resultList.getData();
    }

    /**
     * Formats, sorts, and returns json data
     * @return our formatted json data
     * @throws Exception exception
     */
    public JSONArray getJSON(String industryId, String minPop, String minCompanies, String maxPop) throws Exception {
        if (minPop == null) { minPop = "0"; }
        if (minCompanies == null) { minCompanies = "0"; }
        if (maxPop == null) { maxPop = "330000000";}
        getPlaces(industryId);
        JSONArray sortedJSON = null;
        Set<String> sortedSet = putEachPlaceIntoSet(minPop, minCompanies, maxPop);
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
     * Loops through each place, formats, and puts into sorted set
     * @param minPop minimum population
     * @param minCompanies minimum companies
     * @return
     */
    private Set<String> putEachPlaceIntoSet(String minPop, String minCompanies, String maxPop) {
        Set<String> sortedSet = new TreeSet<>();
        DecimalFormat df = new DecimalFormat("#.####"); //for formatting of companies per capita to usable sig figures
        for (PlaceDataItem place : placesData) {
            String name = place.getPUMA().substring(0, place.getPUMA().length() - 9);//takes state abbrev and PUMA off
            String state = place.getPUMA().substring((place.getPUMA().length() - 2));//gets state abbrev
            int pop = place.getTotalPopulation();
            int records = place.getRecordCount();
            int year = place.getIDYear();
            String recordsPer = df.format(((double) records / (double) pop) * NUM_PEOPLE);
            //Weeds out negligible results and keeps our results manageable

            if ((pop >= Integer.parseInt(minPop)) && (pop <= Integer.parseInt(maxPop)) && (records >= Integer.parseInt(minCompanies)) && (year == YEAR)) {
                //Create JSON string
                sortedSet.add(buildJSONString(name, state, pop, records, recordsPer));
            }
        }
        return sortedSet;
    }

    /**
     * Builds the JSON string to be returned by our service
     * @param name place name
     * @param state place state
     * @param pop place population
     * @param records number of records (companies)
     * @param recordsPer records (companies) per specified number of people
     * @return
     */
    private String buildJSONString(String name, String state, int pop, int records, String recordsPer) {
        String jsonString = "{"
                + "\""
                + name + ", " + state
                + "\""
                + ": {\"State\": "
                + "\""
                + state
                + "\""
                + ", \"Population\": "
                + "\""
                + pop
                + "\""
                + ", \"Record Count\": "
                + "\""
                + records
                + "\""
                + ", \"Companies Per Thousand People\": "
                + "\""
                + recordsPer
                + "\""
                + "}}";
        return jsonString;
    }

    /**
     * Returns the data for REST
     * @return our data
     * @throws Exception exception
     */
    @GET
    @Path("/places")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray returnJSON(
            @QueryParam("industry") String industryId,
            @QueryParam("minPopulation") String minPopulation,
            @QueryParam("minCompanies") String minCompanies,
            @QueryParam("maxPopulation") String maxPopulation
    ) throws Exception{
        return getJSON(industryId, minPopulation, minCompanies, maxPopulation);
    }
}