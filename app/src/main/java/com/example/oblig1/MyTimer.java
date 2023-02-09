package com.example.oblig1;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;

/**
 *
 * A custom timer that extends thread.
 * When run it will count down in seconds until time left reaches 0.
 * @apiNote Will update view of TextView given.
 *
 */
public class MyTimer extends Thread{


    private int timerStartsFrom;
    private int timeLeft = timerStartsFrom;
    private TextView timerTextView;
    private int colorLowOnTime;
    private int colorBlack;
    private boolean running = false;


    /**
     * Creates a new MyTimer-object
     * @param ctx
     * @param seconds
     * @param textView
     */
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

    /**
     * Check to see if thread is actively running.
     * @return True if thread is currently running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets how many seconds the timer should count down from.
     * @param timeLeft
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * Gets the time (in seconds) of how much time is left until timer hits 0.
     * @return Time left (in seconds)
     */
    public int getTimeLeft() {
        return timeLeft;
    }
}
