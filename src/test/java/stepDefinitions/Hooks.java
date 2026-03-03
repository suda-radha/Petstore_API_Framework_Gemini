package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	@Before
    public void setup() {
        System.out.println(">>> Starting Scenario Execution");
    }

    @After
    public void tearDown() {
        System.out.println(">>> Scenario Execution Finished");
        // This is where you would put driver.quit() in a real framework
    }

}
