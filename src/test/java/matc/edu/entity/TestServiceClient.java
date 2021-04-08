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
    public void testIndustryDataItemJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://datausa.io/api/data?PUMS%20Industry=INDUSTRY&drilldowns=PUMA&measure=Total%20Population,ygipop%20RCA,Record%20Count");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        IndustryDataItem industry = mapper.readValue(response, IndustryDataItem.class);
        assertNotNull(industry);
        assertEquals("?????", industry.getIndustryGroup());
    }

    @Test
    public void testPlaceDataItemJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://datausa.io/api/data?PUMS%20Industry=25&drilldowns=PUMA&measure=Total%20Population,ygipop%20RCA,Record%20Count");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PlaceDataItem place = mapper.readValue(response, PlaceDataItem.class);
        assertNotNull(place);
        assertEquals("?????", place.getPUMSIndustry());
    }

}

