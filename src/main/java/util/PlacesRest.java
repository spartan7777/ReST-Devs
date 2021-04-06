package util;

import java.util.*;

import matc.edu.entity.Place;
import matc.edu.entity.PlaceDataItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PlacesRest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String tempIndustry = "5415";
    public PlacesRest() {

    }

    public List<PlaceDataItem> getPlaces() throws Exception {
        String targetString =
                "https://datausa.io/api/data?PUMS%20Industry=" +
                tempIndustry +
                "&drilldowns=PUMA&measure=Total%20Population,ygipop%20RCA,Record%20Count";
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(targetString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Place resultList = mapper.readValue(response, Place.class);
        logger.info(resultList.getData());
        return resultList.getData();
    }

}
