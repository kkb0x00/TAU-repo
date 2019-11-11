package pl.edu.pjatk.tau.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.Domain.IOTimes;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Enums.DateType;

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

        session = new PentestingSession("test");
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
        Assert.assertTrue(ioTimesService.getIODateState(DateType.INSERT_TIME));
        Assert.assertTrue(ioTimesService.getIODateState(DateType.LAST_UPDATE_TIME));
        Assert.assertTrue(ioTimesService.getIODateState(DateType.LAST_READ_TIME));
    }

    @Test
    public void shouldDisableInsertTimeTest() {
        ioTimesService.toggle(DateType.INSERT_TIME);

        Assert.assertFalse(ioTimesService.getIODateState(DateType.INSERT_TIME));
    }

    @Test
    public void shouldDisableLastUpdateTimeTest() {
        ioTimesService.toggle(DateType.LAST_UPDATE_TIME);

        Assert.assertFalse(ioTimesService.getIODateState(DateType.LAST_UPDATE_TIME));
    }

    @Test
    public void shouldDisableLastReadTimeTest() {
        ioTimesService.toggle(DateType.LAST_READ_TIME);

        Assert.assertFalse(ioTimesService.getIODateState(DateType.LAST_READ_TIME));
    }

    @Test
    public void shouldAddInsertTimeWhenItIsEnabledTest() {
        ioTimesService.setInsertTime(session);

        Assert.assertNotNull(session.getInsertTime());
    }

    @Test
    public void shouldNotAddInsertTimeWhenItIsDisabledTest() {
        ioTimesService.toggle(DateType.INSERT_TIME);
        ioTimesService.setInsertTime(session);

        Assert.assertNull(session.getInsertTime());
    }
}