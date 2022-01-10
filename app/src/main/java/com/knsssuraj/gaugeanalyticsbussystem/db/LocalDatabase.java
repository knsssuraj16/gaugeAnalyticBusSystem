package com.knsssuraj.gaugeanalyticsbussystem.db;

import android.os.Build;
import android.util.Log;

import com.knsssuraj.gaugeanalyticsbussystem.pojo.CardHolder;
import com.knsssuraj.gaugeanalyticsbussystem.pojo.Session;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public  class LocalDatabase {
    static ArrayList<CardHolder> arrayList;

    static HashMap<String, Session> sessionMap;
    public LocalDatabase() {
        arrayList = new ArrayList<>();
        sessionMap = new HashMap<String, Session>();
    }

    public static String saveCardHolder(String na, int amoun) {
        int a = arrayList.size();
        CardHolder c= new CardHolder(a+1,amoun,na);
        arrayList.add(c);
        Log.d("card_purchase", "saveCardHolder: "+c);
        return String.valueOf(a+1);
    }

    public static boolean checkSessionStartOrNot(String id) {

        return sessionMap.containsKey(id);
    }

    public static boolean checkCardDetailAndBalance(String id) {
        if(Integer.parseInt(id) == 0 || arrayList.size() < Integer.parseInt(id)){
            return  false;
        }
       double d=  arrayList.get(Integer.parseInt(id)-1).getAmount();
        Log.d("checkCardDetail", "checkCardDetailAndBalance: "+ d);
        return (d>=10);
    }

    public static void startSession(String id, int seekbarcount) {
        Session s = new Session(id,currTime(),currDate(),currDay(),seekbarcount,-1);
        sessionMap.put(id,s);
        Log.d("Local_database", "startSession: "+s);

    }

    private static int currDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    private static String currDate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            System.out.println(dateTime.format(formatter));
            return dateTime.format(formatter);
        }
        return "";
    }

    private static String currTime() {
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
            System.out.println(time.format(formatter));
            return time.format(formatter);
        }
       return "";

    }

    public static Session StopSession(String id, int seekbarcount) {
        Session session = sessionMap.get(id);
        session.setEndStation(seekbarcount);
        int numberOfTravelStation = Math.abs(session.getEndStation()-session.getStartStation());
        double currFarePerStation = checkDayFareOrNightFare(session.getCurrTime());
        int day =session.getNumOfDay();
        double fareTill = calculateFare(numberOfTravelStation,currFarePerStation);
        if(day == 7 || day ==1){
            double dis10per = fareTill / 10;
            fareTill = fareTill -dis10per;
        }
        session.setFare(fareTill);

        double wallet = arrayList.get(Integer.parseInt(id)-1).getAmount();
        session.setRemainigbalnce(Math.min(10,wallet - fareTill));
        arrayList.get(Integer.parseInt(id)-1).setAmount(Math.min(10,wallet - fareTill));
        sessionMap.remove(id);
        return session;
    }

    private static double calculateFare(int numberOfTravelStation, double currFarePerStation) {
        if(numberOfTravelStation <= 5){
            return numberOfTravelStation * currFarePerStation;
        }
        double tot = currFarePerStation*5;
        int remainingStation  = numberOfTravelStation - 5;
        double disTot = (currFarePerStation * remainingStation);
         disTot = disTot - (disTot / 5);
        return tot+disTot;
    }

    private static double checkDayFareOrNightFare(String currTime) {
        if(Integer.parseInt(currTime) >=23 || Integer.parseInt(currTime)<6){
            return 0.60;
        }
        return 0.80;
    }
}
