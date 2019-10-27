package pl.edu.pjatk.tau.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.Domain.IOTimes;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class IOTimesServiceTest {
    private IOTimesService ioTimesService;
    private PentestingSession session;

    @Before
    public void setUp() {
        ioTimesService = spy(IOTimesService.class);
        when(ioTimesService.getCurrentTime()).thenReturn(LocalDateTime.of(2019, 10, 25, 15, 56));

        session = new PentestingSession(30, 1);
    }

    @Test
    public void shouldSetInsertTimeInSessionInstanceTest() {
        ioTimesService.setInsertTime(session);

        Assert.assertEquals(session.getInsertTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
    }

    @Test
    public void shouldSetLastReadTimeInSessionInstanceTest() {
        ioTimesService.setlastReadTime(session);

        Assert.assertEquals(session.getLastReadTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
    }

    @Test
    public void shouldSetLastUpdateTimeInSessionInstanceTest() {
        ioTimesService.setlastUpdateTime(session);

        Assert.assertEquals(session.getLastUpdateTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
    }

    @Test
    public void shouldGetIOTimesRecordTest() {
        IOTimes times = ioTimesService.getIOTimesForRecord(session);

        verify(ioTimesService, never()).getCurrentTime();

        Assert.assertEquals(times.getLastReadTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
        Assert.assertEquals(times.getInsertTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
        Assert.assertEquals(times.getLastUpdateTime(), LocalDateTime.of(2019, 10, 25, 15, 56));
    }

}