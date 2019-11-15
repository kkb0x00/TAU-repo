package bdd.jBehave;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.jbehave.core.model.ExamplesTable;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import pl.edu.pjatk.tau.Controller.SearchController;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Repository.PentestRepository;
import pl.edu.pjatk.tau.Service.IOTimesService;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchTestSteps {
    private SearchController searchController;
    private String regexp;


    @Given("a search functionality with initial data: $table")
    public void searchControllerSetup(ExamplesTable table){
        PentestingSessionService sessionsService = new PentestingSessionService(
                new PentestRepository(toPentestSessionsList(table)),
                new IOTimesService()
        );

        searchController = new SearchController(sessionsService);
    }

    @When("using regexp in search like $regexp")
    public void setPhrase(String phrase){
        regexp = phrase;
    }

    @Then("it should return a list: $results")
    public void shouldReturnSearch(ExamplesTable results){
        List<PentestingSession> expected = toPentestSessionsList(results);
        List<PentestingSession> actual = searchController.searchByRegexp(regexp);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    private List<PentestingSession> toPentestSessionsList(ExamplesTable table) {
        List<PentestingSession> sessions = new ArrayList<>();

        for (Map<String,String> row : table.getRows()) {
            int id = Integer.parseInt(row.get("id"));
            String title = row.get("title");
            String riskType = row.get("riskType");
            int time = Integer.parseInt(row.get("time"));
            float weight = Float.parseFloat(row.get("weight"));

            PentestingSession session = new PentestingSession(title);
            session.setId(id);
            session.setRiskType(riskType);
            session.setTime(time);
            session.setWeight(weight);

            sessions.add(session);
        }

        return sessions;
    }
}
