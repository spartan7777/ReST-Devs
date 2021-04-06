package util;

import java.util.*;

import matc.edu.entity.IndustryDataItem;
import matc.edu.entity.PlaceDataItem;
import matc.edu.entity.Industry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IndustriesRest {

    private List<IndustryDataItem> industryData = new ArrayList<>();
    private Map<String, String> industryNameAndId = new TreeMap<>();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public IndustriesRest() {
        putIndustryNameAndIdIntoMap();
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
}
