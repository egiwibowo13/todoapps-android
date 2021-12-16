package co.id.egiwibowo.todoapps.test;

import io.cucumber.android.runner.CucumberAndroidJUnitRunner;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = {"features", "features/todo"},
        plugin = { "pretty" },
        glue = { "co.id.egiwibowo.todoapps.test.stepDefinitions" },
        tags = { "@ui, @smoke, @acceptance" }
)
public class CucumberRunner extends CucumberAndroidJUnitRunner {
}
