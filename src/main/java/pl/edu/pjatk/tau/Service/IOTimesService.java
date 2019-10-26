package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.Domain.IOTimes;

import java.time.LocalDateTime;

public class IOTimesService {

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public void setInsertTime(IOTimes ioTimes) {
        ioTimes.setInsertTime(getCurrentTime());
    }


    public void setlastUpdateTime(IOTimes ioTimes) {
        ioTimes.setLastUpdateTime(getCurrentTime());
    }


    public void setlastReadTime(IOTimes ioTimes) {
        ioTimes.setLastReadTime(getCurrentTime());
    }
}
