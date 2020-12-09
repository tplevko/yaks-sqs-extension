package dev.yaks.testing.sqs.standard;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = { "com.consol.citrus.cucumber.CitrusReporter" }
)
public class MyStepsTest {
}
