package bdd.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pl.edu.pjatk.tau.Controller.SearchController;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Repository.PentestRepository;
import pl.edu.pjatk.tau.Service.IOTimesService;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Stepdefs {
    private SearchController searchController;
    private String regexp;

    @Given("^initial data$")
    public void initial_data(List<PentestingSession> list) {
        PentestingSessionService sessionsService = new PentestingSessionService(
                new PentestRepository(list),
                new IOTimesService()
        );

        searchController = new SearchController(sessionsService);
    }

    @When("^using regexp in search like (.*)$")
    public void using_regexp_in_search_like(String arg) {
        this.regexp = arg;

    }

    @Then("it should return a list")
    public void it_should_return_a_list(List<PentestingSession> list) {
        assertEquals(list, searchController.searchByRegexp(regexp));
    }
}
