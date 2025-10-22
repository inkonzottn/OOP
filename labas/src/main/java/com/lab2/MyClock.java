package com.lab2;

import java.util.Calendar;
import java.util.TimerTask;

public class MyClock extends TimerTask {
    @Override
    public void run() {
        Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        System.out.println(hour+":"+minute+":"+second);
    }
}
