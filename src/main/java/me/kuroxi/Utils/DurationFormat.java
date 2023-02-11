package me.kuroxi.Utils;

import java.time.Duration;

public class DurationFormat {
    public String format(long milli) {
        Duration duration = Duration.ofMillis(milli);

        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);

        long formatSecond = absSeconds % 60;
        long formatMinute = (absSeconds % 3600) / 60;

        long formatHour = absSeconds / 3600;
        String time = ((formatHour > 0) ? String.format("%02d", formatMinute) : formatMinute) + ":" + String.format("%02d", formatSecond);

        if (formatHour > 0) time = formatHour + ":" + time;

        return time;
    }
}
