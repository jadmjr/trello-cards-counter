package run;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:caracteristicas", tags = {
		"@PesquisaGoogle" }, glue = "controlador", monochrome = true, dryRun = false, plugin = {
				"html:target/cucumber-html-report" })

public class Run {
}
