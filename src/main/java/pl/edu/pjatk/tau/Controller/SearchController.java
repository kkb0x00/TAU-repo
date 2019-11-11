package pl.edu.pjatk.tau.Controller;

import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Service.PentestingSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchController {
    PentestingSessionService pentestSessionService;

    public SearchController(PentestingSessionService sessionsService) {
        pentestSessionService = sessionsService;
    }

    public List<PentestingSession> searchByRegexp(String regexp) {
        List<PentestingSession> sessions = pentestSessionService.getAll();
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
