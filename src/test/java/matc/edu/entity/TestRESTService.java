package matc.edu.entity;

import org.junit.jupiter.api.Test;
import util.IndustriesRest;
import util.PlacesRest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestRESTService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void testIndustryResults() {
        IndustriesRest test = new IndustriesRest();
        assertEquals(
                "{\"Accounting, tax preparation, bookkeeping & payroll services\":{\"Average Wage\":\"54571.88985488508\",\"id\":\"5412\"}}",
                test.putIndustriesIntoJSON("30000", "100000").get(0).toString());
    }

}
