package org.example;

import java.time.LocalDate;

public interface DayPredicate {
    boolean matches(LocalDate localDate);
}
