package com.your.egyptianhelper;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimerTask extends AsyncTask<Void, String, Void> {

    private VisionActivity context;
    private TextView timer;
    private boolean stop = false;
    private long startTime;
    private String allTime;
    private long offset;
    Calendar calendar;

    public TimerTask(VisionActivity context, TextView timer) {
        this.context = context;
        this.timer = timer;
        calendar = new GregorianCalendar();
        offset = calendar.getTimeZone().getRawOffset();
    }

    public boolean getStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        startTime = System.currentTimeMillis();
        allTime = timer.getText().toString();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat(Settings.FORMAT_DATE, Locale.getDefault());
            SimpleDateFormat sdf1 = new SimpleDateFormat(Settings.FORMAT_DATE, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                String firstDate = allTime;
                String secondDate = "00:00:00";

                Date d1 = sdf1.parse(firstDate);
                Date d2 = sdf1.parse(secondDate);
                long disRes = d1.getTime() - d2.getTime();
                long dif = System.currentTimeMillis() - startTime;
                long res = disRes - dif;

                if (res <= 0) {
                    break;
                }

                SystemClock.sleep(1000);
                if (stop) {
                    break;
                }

                publishProgress(sdf.format(res));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        timer.setText(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        context.endTimer();
    }
}