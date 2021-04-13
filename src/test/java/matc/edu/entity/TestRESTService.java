package matc.edu.entity;

import org.junit.jupiter.api.Test;
import util.IndustriesRest;
import util.PlacesRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRESTService {
    @Test
    public void testIndustryResults() throws Exception {
        IndustriesRest test = new IndustriesRest();
        assertEquals(
                "{\"Accounting, tax preparation, bookkeeping & payroll services\":{\"Average Wage\":\"54571.88985488508\",\"id\":\"5412\"}}",
                test.putIndustriesIntoJSON("30000", "100000").get(0).toString());
    }

}
