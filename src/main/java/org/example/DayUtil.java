package org.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DayUtil {
    public static boolean isHoliday(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate must be supplied");
        }
        return Arrays.stream(Day.Holiday.values()).anyMatch(holiday -> holiday.getDayPredicate().matches(localDate));
    }

    public static boolean shouldNotCharge(LocalDate localDate, Tool tool) {
        if ((localDate == null) || (tool == null)) {
            throw new IllegalArgumentException("localDate and tool must be supplied");
        }
        return Arrays.stream(Day.values()).anyMatch(day ->
                noChargeToolTypes(day).contains(tool.getToolType()) && day.getDayPredicate().matches(localDate)
        );
    }

    private static List<Tool.ToolType> noChargeToolTypes(Day day) {
        if (day == null) {
            throw new IllegalArgumentException("day must be supplied");
        }
        return Optional.ofNullable(day.getNoChargeToolTypes())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
