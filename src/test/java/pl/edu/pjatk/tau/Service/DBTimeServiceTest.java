package pl.edu.pjatk.tau.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.Domain.IOTimes;
import pl.edu.pjatk.tau.Domain.PentestingSession;
import pl.edu.pjatk.tau.Enums.DateType;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class DBTimeServiceTest {
    public static final LocalDateTime TEST_DATE = LocalDateTime.of(2019, 10, 25, 15, 56);

    private DBTimeService DBTimeService;
    private PentestingSession session;

    @Before
    public void setUp() {
        DBTimeService = spy(DBTimeService.class);
        when(DBTimeService.getCurrentTime()).thenReturn(TEST_DATE);

        session = new PentestingSession("test", null);
    }

    @Test
    public void shouldSetInsertTimeInSessionInstanceTest() {
        DBTimeService.setInsertTime(session);

        Assert.assertEquals(session.getInsertTime(), TEST_DATE);
    }

    @Test
    public void shouldSetLastReadTimeInSessionInstanceTest() {
        DBTimeService.setlastReadTime(session);

        Assert.assertEquals(session.getLastReadTime(), TEST_DATE);
    }

    @Test
    public void shouldSetLastUpdateTimeInSessionInstanceTest() {
        DBTimeService.setlastUpdateTime(session);

        Assert.assertEquals(session.getLastUpdateTime(), TEST_DATE);
    }

    @Test
    public void shouldGetIOTimesRecordTest() {
        session.setInsertTime(TEST_DATE);
        session.setLastReadTime(TEST_DATE);
        session.setLastUpdateTime(TEST_DATE);

        IOTimes times = DBTimeService.getIOTimesForRecord(session);

        verify(DBTimeService, never()).getCurrentTime();

        Assert.assertEquals(times.getInsertTime(), TEST_DATE);
        Assert.assertEquals(times.getLastReadTime(), TEST_DATE);
        Assert.assertEquals(times.getLastUpdateTime(), TEST_DATE);
    }

    @Test
    public void shouldAllDatesBeEnabledByDefaultTest() {
        Assert.assertTrue(DBTimeService.getIODateState(DateType.INSERT_TIME));
        Assert.assertTrue(DBTimeService.getIODateState(DateType.LAST_UPDATE_TIME));
        Assert.assertTrue(DBTimeService.getIODateState(DateType.LAST_READ_TIME));
    }

    @Test
    public void shouldDisableInsertTimeTest() {
        DBTimeService.toggle(DateType.INSERT_TIME);

        Assert.assertFalse(DBTimeService.getIODateState(DateType.INSERT_TIME));
    }

    @Test
    public void shouldDisableLastUpdateTimeTest() {
        DBTimeService.toggle(DateType.LAST_UPDATE_TIME);

        Assert.assertFalse(DBTimeService.getIODateState(DateType.LAST_UPDATE_TIME));
    }

    @Test
    public void shouldDisableLastReadTimeTest() {
        DBTimeService.toggle(DateType.LAST_READ_TIME);

        Assert.assertFalse(DBTimeService.getIODateState(DateType.LAST_READ_TIME));
    }

    @Test
    public void shouldAddInsertTimeWhenItIsEnabledTest() {
        DBTimeService.setInsertTime(session);

        Assert.assertNotNull(session.getInsertTime());
    }

    @Test
    public void shouldNotAddInsertTimeWhenItIsDisabledTest() {
        DBTimeService.toggle(DateType.INSERT_TIME);
        DBTimeService.setInsertTime(session);

        Assert.assertNull(session.getInsertTime());
    }
}