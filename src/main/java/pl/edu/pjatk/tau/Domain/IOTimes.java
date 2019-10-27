package pl.edu.pjatk.tau.Domain;

import java.time.LocalDateTime;

public abstract class IOTimes {
    private LocalDateTime insertTime;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime lastReadTime;

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public LocalDateTime getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(LocalDateTime lastReadTime) {
        this.lastReadTime = lastReadTime;
    }



}
