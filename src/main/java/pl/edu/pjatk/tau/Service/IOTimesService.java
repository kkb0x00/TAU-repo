package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.Domain.IOTimes;
import java.time.LocalDateTime;

public class IOTimesService {

    boolean isInsertTimeEnabled = true;
    boolean isLastUpdateTimeEnabled = true;
    boolean isLastReadTimeEnabled = true;

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



}
