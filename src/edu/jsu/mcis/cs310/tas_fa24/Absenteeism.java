package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.Employee;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Absenteeism {
    private Employee employee;
    private LocalDate startPayPeriod;
    private BigDecimal percentage;
    
    public Absenteeism(Employee e, LocalDate ts, BigDecimal percentage)
    {
        this.employee = e;
        this.startPayPeriod = ts;
        this.percentage = percentage;
    }
    
    public Employee getEmployee(){
        return this.employee;
    }
    public LocalDate getStartPayPeriod(){
        return this.startPayPeriod;
    }
    public BigDecimal getPercentage(){
        return this.percentage;
    }
    
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        
        s.append("#").append(employee.getBadge().getId()).append(" ");
        s.append("(Pay Period Starting ");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
        String date = startPayPeriod.format(df);
        s.append(date).append("): ").append(percentage).append("%");
        
        return s.toString();
    }
}
