package org.example;

import java.time.DayOfWeek;
import java.time.Month;

public enum Day {
    HOLIDAY(localDate -> (localDate != null) && (
            DayUtil.isHoliday(localDate)
    ), Tool.ToolType.LADDER, Tool.ToolType.JACKHAMMER),
    WEEKDAY(localDate -> (localDate != null) && (
            !localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) && !localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
    )),
    WEEKEND(localDate -> (localDate != null) && (
            localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) || localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
    ), Tool.ToolType.CHAINSAW, Tool.ToolType.JACKHAMMER);

    private final DayPredicate dayPredicate;
    private final Tool.ToolType[] noChargeToolTypes;

    Day(DayPredicate dayPredicate, Tool.ToolType... toolTypes) {
        this.dayPredicate = dayPredicate;
        this.noChargeToolTypes = toolTypes;
    }

    Tool.ToolType[] getNoChargeToolTypes() {
        return noChargeToolTypes;
    }

    DayPredicate getDayPredicate() {
        return dayPredicate;
    }

    enum Holiday {
        INDEPENDENCE_DAY(localDate -> localDate.getMonth().equals(Month.JULY) && (
                (localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) && (localDate.getDayOfMonth() == 3)) ||
                        (localDate.getDayOfWeek().equals(DayOfWeek.MONDAY) && (localDate.getDayOfMonth() == 5)) ||
                        (!localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) && (localDate.getDayOfMonth() == 4))
        )),
        LABOR_DAY(localDate -> localDate.getMonth().equals(Month.SEPTEMBER) &&
                localDate.getDayOfWeek().equals(DayOfWeek.MONDAY) &&
                localDate.minusWeeks(1).getMonth().equals(Month.AUGUST)
        );

        private final DayPredicate dayPredicate;

        Holiday(DayPredicate dayPredicate) {
            this.dayPredicate = dayPredicate;
        }

        public DayPredicate getDayPredicate() {
            return dayPredicate;
        }
    }
}
