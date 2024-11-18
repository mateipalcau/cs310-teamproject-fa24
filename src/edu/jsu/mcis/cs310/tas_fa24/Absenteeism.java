package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Represents absenteeism data for an employee during a specific pay period.
 * Tracks the employee, the start date of the pay period, and the percentage of absenteeism.
 */
public class Absenteeism {

    /**
     * The employee associated with the absenteeism record.
     */
    private Employee employee;

    /**
     * The start date of the pay period for which absenteeism is being recorded.
     */
    private LocalDate startPayPeriod;

    /**
     * The percentage of absenteeism during the pay period.
     */
    private BigDecimal percentage;

    /**
     * Constructs an Absenteeism object with the given employee, timestamp, and absenteeism percentage.
     * 
     * @param e the employee associated with the absenteeism record
     * @param ts a timestamp used to determine the start date of the pay period
     * @param percentage the percentage of absenteeism during the pay period
     */
    public Absenteeism(Employee e, LocalDate ts, BigDecimal percentage) {
        this.employee = e;
        this.startPayPeriod = ts.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        this.percentage = percentage;
    }

    /**
     * Returns the employee associated with this absenteeism record.
     * 
     * @return the employee
     */
    public Employee getEmployee() {
        return this.employee;
    }

    /**
     * Returns the start date of the pay period for this absenteeism record.
     * 
     * @return the start date of the pay period
     */
    public LocalDate getStartPayPeriod() {
        return this.startPayPeriod;
    }

    /**
     * Returns the percentage of absenteeism during the pay period.
     * 
     * @return the absenteeism percentage
     */
    public BigDecimal getPercentage() {
        return this.percentage;
    }

    /**
     * Returns a string representation of the absenteeism record.
     * The format includes the badge ID of the employee, the start date of the pay period,
     * and the absenteeism percentage.
     * 
     * @return a formatted string representing the absenteeism record
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("#").append(employee.getBadge().getId()).append(" ");
        s.append("(Pay Period Starting ");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
        String date = this.startPayPeriod.format(df);
        s.append(date).append("): ").append(String.format("%.2f", percentage)).append("%");

        return s.toString();
    }
}

