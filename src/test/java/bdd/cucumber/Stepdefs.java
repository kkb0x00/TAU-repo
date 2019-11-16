package bdd.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pl.edu.pjatk.tau.Controller.PentestController;
import pl.edu.pjatk.tau.Controller.SearchController;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Repository.PentestRepository;
import pl.edu.pjatk.tau.Service.DBTimeService;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Stepdefs {
    private SearchController searchController;
    private PentestController pentestController;
    private PentestingSessionService sessionsService;

    private List<PentestingSession> actual;

    @Given("^initial data")
    public void initial_data(List<PentestingSession> sessions) {
        sessionsService = new PentestingSessionService(
                new PentestRepository(sessions),
                new DBTimeService()
        );
    }

    @And("^using search functionality")
    public void setup_search() {
        searchController = new SearchController(sessionsService);
    }

    @And("^using pentest functionality")
    public void setup_pentest() {
        pentestController = new PentestController(sessionsService);
    }

    @When("^using regexp in search like (.*)$")
    public void using_regexp_in_search_like(String arg) {
        actual = searchController.searchByRegexp(arg);
    }

    @When("^providing list of ids to delete like")
    public void delete_by_list_of_ids(List<Integer> list) {
        pentestController.deleteByIds(list);
        actual = sessionsService.getAll();
    }

    @Then("it should return a list")
    public void it_should_return_a_list(List<PentestingSession> list) {
        // nie wiem jak prościej zrobić porównanie dwóch list pod kątem wartości obiektów w nich.
        // niby taka podstawowa rzecz a nigdzie nie ma informacji

        int i = 0;
        for (PentestingSession session: list) {

            assertEquals(session.getId(), actual.get(i).getId());
            assertEquals(session.getTitle(), actual.get(i).getTitle());
            assertEquals(session.getRiskType(), actual.get(i).getRiskType());
            assertEquals(session.getTime(), actual.get(i).getTime());
            assertEquals(session.getWeight(), actual.get(i).getWeight());

            i++;
        }

    }
}
