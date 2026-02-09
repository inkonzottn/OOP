package com.lab2;

public class ClockExt extends Clock {
    private int seconds;

    public ClockExt() {}

    public ClockExt(int seconds) {
        this.seconds = seconds;
    }

    public ClockExt(int hour, int minute, int seconds) {
        super(hour, minute);
        this.seconds = seconds;
    }

    public void nextSecond() {
        seconds++;

        if (seconds > 59) {
            nextMinute();
            seconds = 0;
        }
    }

    public int getSeconds(){
        return seconds;
    }

    @Override
    public String toString() {
        return "ClockExt{" + "hour=" + getHour() +
                ", minute=" + getMinute() +
                ", seconds=" + seconds + '}';
    }

    public static void main(String[] args) {
        ClockExt clock = new ClockExt(11, 54, 34);

        System.out.println(clock);
    }
}
