package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.Domain.IOTimes;
import pl.edu.pjatk.tau.Enums.DateType;

import java.time.LocalDateTime;

public class IOTimesService {
    private boolean isInsertTimeEnabled = true;
    private boolean isLastUpdateTimeEnabled = true;
    private boolean isLastReadTimeEnabled = true;

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public void setInsertTime(IOTimes ioTimes) {
        if(isInsertTimeEnabled) {
            ioTimes.setInsertTime(getCurrentTime());
        }
    }

    public void setlastUpdateTime(IOTimes ioTimes) {
        if(isLastUpdateTimeEnabled) {
            ioTimes.setLastUpdateTime(getCurrentTime());
        }

    }

    public void setlastReadTime(IOTimes ioTimes) {
        if(isLastReadTimeEnabled) {
            ioTimes.setLastReadTime(getCurrentTime());
        }
    }

    public IOTimes getIOTimesForRecord(IOTimes ioTimesGiven) {

        IOTimes times = new IOTimes();

        times.setInsertTime(ioTimesGiven.getInsertTime());
        times.setLastReadTime(ioTimesGiven.getLastReadTime());
        times.setLastUpdateTime(ioTimesGiven.getLastReadTime());

        return times;
    }

    boolean getIODateState(DateType type) {
        switch (type) {
            case INSERT_TIME:
                return isInsertTimeEnabled;

            case LAST_READ_TIME:
                return isLastReadTimeEnabled;

            case LAST_UPDATE_TIME:
                return isLastUpdateTimeEnabled;

            default:
                return false;
        }
    }

    void toggle(DateType type) {
        switch (type) {
            case INSERT_TIME:
                isInsertTimeEnabled = !isInsertTimeEnabled;
                break;
            case LAST_READ_TIME:
                isLastReadTimeEnabled = !isLastReadTimeEnabled;
                break;
            case LAST_UPDATE_TIME:
                isLastUpdateTimeEnabled = !isLastUpdateTimeEnabled;
                break;
            default:
        }
    }
}

