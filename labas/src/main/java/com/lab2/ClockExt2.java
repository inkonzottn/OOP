package com.lab2;

public class ClockExt2 extends ClockExt{
    private int milliseconds;

    public int getMilliseconds(){
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds){
        this.milliseconds = milliseconds;
    }

    public ClockExt2(){}

    public ClockExt2(int milliseconds){
        this.milliseconds = milliseconds;
    }

    public ClockExt2(int seconds, int milliseconds){
        super(seconds);
        this.milliseconds = milliseconds;
    }

    public ClockExt2 (int hour, int minutes, int seconds, int milliseconds){
        super(hour, minutes, seconds);
        this.milliseconds = milliseconds;
    }

    public void nextMillisecond(){
        milliseconds+=100;

        if (milliseconds > 1000) {
            nextSecond();
            milliseconds = 0;
        }
    }

    @Override
    public String toString() {
        return "ClockExt2{" + "hour=" + getHour() +
                ", minute=" + getMinute() +
                ", seconds=" + getSeconds() +
                ", milliseconds=" + milliseconds +'}';
    }

    public static void main(String[] args) {
        ClockExt2 clock = new ClockExt2(11, 54, 34, 00);

        System.out.println(clock);
    }
}
