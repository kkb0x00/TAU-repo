package pl.edu.pjatk.tau.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.Domain.IOTimes;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Exceptions.InvalidIODateTypeException;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class IOTimesServiceTest {
    public static final LocalDateTime TEST_DATE = LocalDateTime.of(2019, 10, 25, 15, 56);


    private IOTimesService ioTimesService;
    private PentestingSession session;

    @Before
    public void setUp() {
        ioTimesService = spy(IOTimesService.class);
        when(ioTimesService.getCurrentTime()).thenReturn(TEST_DATE);

        session = new PentestingSession(30, 1);
    }

    @Test
    public void shouldSetInsertTimeInSessionInstanceTest() {
        ioTimesService.setInsertTime(session);

        Assert.assertEquals(session.getInsertTime(), TEST_DATE);
    }

    @Test
    public void shouldSetLastReadTimeInSessionInstanceTest() {
        ioTimesService.setlastReadTime(session);

        Assert.assertEquals(session.getLastReadTime(), TEST_DATE);
    }

    @Test
    public void shouldSetLastUpdateTimeInSessionInstanceTest() {
        ioTimesService.setlastUpdateTime(session);

        Assert.assertEquals(session.getLastUpdateTime(), TEST_DATE);
    }

    @Test
    public void shouldGetIOTimesRecordTest() {
        session.setInsertTime(TEST_DATE);
        session.setLastReadTime(TEST_DATE);
        session.setLastUpdateTime(TEST_DATE);

        IOTimes times = ioTimesService.getIOTimesForRecord(session);

        verify(ioTimesService, never()).getCurrentTime();

        Assert.assertEquals(times.getInsertTime(), TEST_DATE);
        Assert.assertEquals(times.getLastReadTime(), TEST_DATE);
        Assert.assertEquals(times.getLastUpdateTime(), TEST_DATE);
    }

    @Test
    public void shouldAllDatesBeEnabledByDefaultTest() {

        Assert.assertEquals(ioTimesService.getIODateState("InsertTime"), true);
        Assert.assertEquals(ioTimesService.getIODateState("LastUpdateTime"), true);
        Assert.assertEquals(ioTimesService.getIODateState("LastReadTime"), true);
    }

    @Test
    public void shouldDisableInsertTimeTest() {
        ioTimesService.toggle("InsertTime");

        Assert.assertEquals(ioTimesService.getDateState("InsertTime"), false);
    }

    @Test
    public void shouldDisableLastUpdateTimeTest() {
        ioTimesService.toggle("LastUpdateTime");

        Assert.assertEquals(ioTimesService.getDateState("LastUpdateTime"), false);
    }

    @Test
    public void shouldDisableLastReadTimeTest() {
        ioTimesService.toggle("LastReadTime");

        Assert.assertEquals(ioTimesService.getDateState("LastReadTime"), false);
    }

    @org.junit.Test(expected = InvalidIODateTypeException.class)
    public void shouldDisableLastReadTime() throws InvalidIODateTypeException {
        ioTimesService.toggle("invalid");
    }

//    @Test
//    public void shouldNotAddInsertTimeTest() {
//
//
//        ioTimesService.setlastReadTime(session);
//
//        Assert.assertEquals(session.getLastReadTime(), null);
//    }


}