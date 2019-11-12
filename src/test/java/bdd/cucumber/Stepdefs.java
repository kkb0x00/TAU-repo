package bdd.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import gherkin.ast.DataTable;
import org.jbehave.core.model.ExamplesTable;
import pl.edu.pjatk.tau.Controller.SearchController;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Repository.PentestRepository;
import pl.edu.pjatk.tau.Service.IOTimesService;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class Stepdefs {
    private SearchController searchController;
    private String regexp;

    @Given("^initial data$")
    public void initial_data(DataTable list) {
        PentestingSessionService sessionsService = new PentestingSessionService(
                new PentestRepository(toPentestSessionsList(list)),
                new IOTimesService()
        );

        searchController = new SearchController(sessionsService);
    }

    @When("^using regexp in search like (.*)$")
    public void using_regexp_in_search_like(String regexp) {
        this.regexp = regexp;
    }

    @Then("^it should return a list$")
    public void it_should_return_a_list(List<PentestingSession> list) {
        assertEquals(list, searchController.searchByRegexp(regexp));
    }

    private List<PentestingSession> toPentestSessionsList(DataTable table) {
        List<Map<String, String>> list = table.asMaps(String.class, String.class);
        List<PentestingSession> sessions = new ArrayList<>();

        for(int i=0; i<list.size(); i++) {
            int id = Integer.parseInt(list.get(i).get("id"));
            String title = list.get(i).get("title");
            String riskType = list.get(i).get("riskType");

            int time = Integer.parseInt(list.get(i).get("time"));
            float weight = Float.parseFloat(list.get(i).get("weight"));

            PentestingSession session = new PentestingSession(title);
            session.setId(id);
            session.setRiskType(riskType);
            session.setTimeInMinutes(time);
            session.setWeight(weight);

            sessions.add(session);
        }

        return sessions;
    }
}
