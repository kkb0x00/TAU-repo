package pl.edu.pjatk.tau.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class SearchController {

    @Autowired
    PentestingSessionService pentestSessionService;

    public SearchController(PentestingSessionService pentestSessionService) {
        this.pentestSessionService = pentestSessionService;
    }

    @GetMapping(value = "/search/{regexp}")
    public List<PentestingSession> searchByRegexp(@PathVariable("regexp") String regexp) {
        Iterable<PentestingSession> sessions = pentestSessionService.getAll();
        List<PentestingSession> matched = new ArrayList<>();

        for (PentestingSession session: sessions) {
            String title = session.getTitle();
            String riskType = session.getRiskType();

            if(Pattern.matches(regexp, title) || Pattern.matches(regexp, riskType)) {
               matched.add(session);
            }
        }

        return matched;
    }
}
