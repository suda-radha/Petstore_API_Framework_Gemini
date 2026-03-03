package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Before
    public void setup() {
        System.out.println(">>> Starting Scenario Execution");
    }

    @After
    public void tearDown(Scenario scenario) {
        // This is where you would put driver.quit() in a real framework
     // If the scenario is a UI test and it fails, take a screenshot
        if (scenario.isFailed()) {
            // Assuming you have a way to access your driver here
            // final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot");
        }
        System.out.println(">>> Scenario Finished: " + scenario.getName());
    }

}
