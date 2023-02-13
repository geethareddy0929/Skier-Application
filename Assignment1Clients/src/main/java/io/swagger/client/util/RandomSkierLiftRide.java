package io.swagger.client.util;

import io.swagger.client.model.LiftRide;

import java.util.concurrent.ThreadLocalRandom;

public class RandomSkierLiftRide {

    private LiftRide body;
    private Integer resortID;
    private String seasonID;
    private String dayID ;
    private Integer skierID;

    public RandomSkierLiftRide() {
        body = new LiftRide()
                .time(ThreadLocalRandom.current().nextInt(1, 361))
                .liftID(ThreadLocalRandom.current().nextInt(1, 40));

        resortID = ThreadLocalRandom.current().nextInt(1, 10);
        seasonID = "2022";
        dayID = "1";
        skierID = ThreadLocalRandom.current().nextInt(1, 100001);
    }

    public LiftRide getBody() {
        return body;
    }

    public void setBody(LiftRide body) {
        this.body = body;
    }

    public Integer getResortID() {
        return resortID;
    }

    public void setResortID(Integer resortID) {
        this.resortID = resortID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public Integer getSkierID() {
        return skierID;
    }

    public void setSkierID(Integer skierID) {
        this.skierID = skierID;
    }
}
