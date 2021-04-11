package matc.edu.entity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import matc.edu.entity.Industry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestServiceClient {

    @Test
    public void testIndustryJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://datausa.io/api/data?measure=Average%20Wage&drilldowns=Industry%20Group&Year=2019");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Industry industries = mapper.readValue(response, Industry.class);
        assertNotNull(industries);
        assertEquals("112", industries.getData().get(1).getiDIndustryGroup());
    }

    @Test
    public void testPlaceJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://datausa.io/api/data?PUMS%20Industry=5415&drilldowns=PUMA&measure=Total%20Population,ygipop%20RCA,Record%20Count");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Place places = mapper.readValue(response, Place.class);
        assertNotNull(places);
        assertEquals("5415", places.getData().get(0).getIDPUMSIndustry());
    }

}

