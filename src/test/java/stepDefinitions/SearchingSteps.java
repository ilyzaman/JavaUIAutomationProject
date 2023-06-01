package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.HomePage;

import java.io.IOException;

public class SearchingSteps extends BaseTest {
        HomePage homePage;


        @Given("^I am on ecommerce page$")
        public void i_am_on_ecommerce_page() throws IOException {

            homePage = launchApplication();
        }

        @When("^I search for (.+) that does not exist$")
        public void searching_for(String article) {
            homePage.searchArticle(article);
        }

        @Then("^\"([^\"]*)\" alert is displayed$")
        public void error_alert_is_displayed(String message) {
            Assert.assertTrue(homePage.getErrorMessage().contains(message));
        }

        @When("^I search for (.+) that exists$")
        public void i_search_for_that_exists(String article){
            homePage.showSuggestions(article);
            System.out.println(homePage.getSuggestionsList());
        }

        @Then("^\"([^\"]*)\" suggestion is displayed$")
        public void something_suggestion_is_displayed(String suggestion) {
            System.out.println(suggestion);
            System.out.println(homePage.matchMySuggestion(suggestion));
            Assert.assertTrue(homePage.matchMySuggestion(suggestion));
        }

}

