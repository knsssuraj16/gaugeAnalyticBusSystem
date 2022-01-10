package com.knsssuraj.gaugeanalyticsbussystem.pojo;

public class Session {
    String cardId;
    String currTime;
    String currDate;
    int numOfDay;
    int startStation;
    int endStation;
    double fare =0.0;
    double remainigbalnce =0.0;

    public Session(String cardId, String currTime, String currDate, int numOfDay, int startStation, int endStation) {
        this.cardId = cardId;
        this.currTime = currTime;
        this.currDate = currDate;
        this.numOfDay = numOfDay;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getRemainigbalnce() {
        return remainigbalnce;
    }

    public void setRemainigbalnce(double remainigbalnce) {
        this.remainigbalnce = remainigbalnce;
    }

    @Override
    public String toString() {
        return "Session{" +
                "cardId='" + cardId + '\'' +
                ", currTime='" + currTime + '\'' +
                ", currDate='" + currDate + '\'' +
                ", numOfDay=" + numOfDay +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", fare=" + fare +
                ", remainigbalnce=" + remainigbalnce +
                '}';
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public int getNumOfDay() {
        return numOfDay;
    }

    public void setNumOfDay(int numOfDay) {
        this.numOfDay = numOfDay;
    }

    public int getStartStation() {
        return startStation;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public int getEndStation() {
        return endStation;
    }

    public void setEndStation(int endStation) {
        this.endStation = endStation;
    }
}
