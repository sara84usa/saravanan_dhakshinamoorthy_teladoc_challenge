package com.stepDefinitions;

import com.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.codehaus.jettison.json.JSONException;

import static org.junit.Assert.assertEquals;

public class MovieTitle {
    private TestContext context;

    public MovieTitle(TestContext context) {
        this.context = context;
    }

    @Given("user has access to movie endpoint {string}")
    public void userHasAccessToMovieEndpoint(String endPoint) {
        context.session.put("endpoint", endPoint);
    }

    @When("user search for the movie title {string}")
    public void userSearchForTheMovieTitle(String movieTitle) {
        context.params.put("Title", movieTitle);
    }

    @Then("user want to view the results from page {string}")
    public void userWantToViewTheResultsFromPage(String page) {
        context.params.put("page", page);
    }


    @Then("user makes a request GET api to view the movie titles")
    public void userMakesARequestGETApiToViewTheMovieTitles() throws JSONException {
        context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
        context.getMovieTitle();
    }

    @Then("user should get the response code {int}")
    public void userShpuldGetTheResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }

    @Then("sort the movie titles in ascending order")
    public void sortTheMovieTitlesInAscendingOrder() throws Exception {
        context.sortMovieTitle();
    }
}
