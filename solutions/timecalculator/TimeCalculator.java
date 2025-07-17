package solutions.timecalculator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class TimeCalculator {
    public static long calculateMinutesBetween(String timeStr) {
        String[] times = timeStr.split("-");
        String startTimeStr = times[0].toUpperCase(Locale.ENGLISH);
        String endTimeStr = times[1].toUpperCase(Locale.ENGLISH);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma", Locale.US);

        LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
        LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

        long minutes = ChronoUnit.MINUTES.between(startTime, endTime);

        if (minutes < 0) {
            minutes += 24 * 60;
        }

        return minutes;
    }

    public static void main(String[] args) {
        System.out.println("9:00am-10:00am -> " + calculateMinutesBetween("9:00am-10:00am"));
        System.out.println("1:00pm-11:00am -> " + calculateMinutesBetween("1:00pm-11:00am"));
        System.out.println("12:30pm-12:00am -> " + calculateMinutesBetween("12:30pm-12:00am"));
    }
}