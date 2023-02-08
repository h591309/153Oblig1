package com.example.oblig1;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;

public class MyTimer extends Thread{


    private int timerStartsFrom;
    private int timeLeft = timerStartsFrom;
    private TextView timerTextView;
    private int colorLowOnTime;
    private int colorBlack;
    private boolean running = false;


    public MyTimer(Context ctx, int seconds, TextView textView) {
        timerStartsFrom = seconds;
        timerTextView = textView;
        this.colorLowOnTime = ctx.getResources().getColor(R.color.red, ctx.getTheme());
        this.colorBlack = ctx.getResources().getColor(R.color.black, ctx.getTheme());
    }



    @Override
    public void run() {
        running = true;
        timeLeft = timerStartsFrom;
        while (true) {
            if(timeLeft >= 0) {
                timerTextView.post(new Runnable() {
                    public void run() {
                        timerTextView.setText("Time left: " + timeLeft + "s");
                        timerTextView.setAlpha(1);
                        timerTextView.setTextColor(colorBlack);
                        if (timeLeft <= 10) {
                            timerTextView.setTextColor(colorLowOnTime);
                        }
                    }
                });
            } else {
                timerTextView.post(new Runnable() {
                    public void run() {
                        timerTextView.setText("You ran out of time!");
                        timerTextView.setAlpha(1);
                        timerTextView.setTextColor(colorLowOnTime);
                    }
                });
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                running = false;
            }
            timeLeft--;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
