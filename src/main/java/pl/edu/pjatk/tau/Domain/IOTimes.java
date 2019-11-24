package pl.edu.pjatk.tau.Domain;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class IOTimes {

    @Column(name="insert_time")
    private LocalDateTime insertTime;
    @Column(name="last_update_time")
    private LocalDateTime lastUpdateTime;
    @Column(name="last_read_time")
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
