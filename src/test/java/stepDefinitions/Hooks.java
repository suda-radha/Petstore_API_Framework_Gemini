package stepDefinitions;


import com.petstore.utilities.VideoManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Before
    public void setup(Scenario scenario) throws Exception {
        System.out.println(">>> Starting Scenario Execution");
     // Only start recording for UI tagged tests if you want to save space
        if (scenario.getSourceTagNames().contains("@ui")) {
            VideoManager.startRecording(scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
    	
        // This is where you would put driver.quit() in a real framework
     // If the scenario is a UI test and it fails, take a screenshot
//        if (scenario.isFailed()) {
//            // Assuming you have a way to access your driver here
//            // final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            // scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot");
//        }
        System.out.println(">>> Scenario Finished: " + scenario.getName());

        VideoManager.stopRecording();
        // If the scenario failed, embed the video link into the Spark Report
        if (scenario.isFailed()) {
            String videoName = scenario.getName().replaceAll(" ", "_") + ".avi";
            // Relative path from SparkReport/Index.html to the video folder
            String relativePath = "../test-output/Videos/" + videoName;
            
            String htmlLink = "<a href='" + relativePath + "' target='_blank'>Click here to watch the failure video</a>";
            
            // This attaches the link as a log entry in the report
            scenario.log(htmlLink);
        }
    }

}
