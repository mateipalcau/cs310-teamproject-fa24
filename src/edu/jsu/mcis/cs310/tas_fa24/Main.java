package edu.jsu.mcis.cs310.tas_fa24;

//Matei-Victor Palcau(test)

import edu.jsu.mcis.cs310.tas_fa24.dao.*;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
//jack test
public class Main {
//matthew
    public static void main(String[] args) throws SQLException {
        
        // test database connectivity; get DAOs

        DAOFactory daoFactory = new DAOFactory("tas.jdbc");
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        
        // find badge

        Badge b = badgeDAO.find("C4F37EFF");
        Shift s = shiftDAO.find(1);
        Employee e =employeeDAO.find(14);
        Punch p1 = new Punch(103, badgeDAO.find("021890C0"), EventType.CLOCK_IN);
        int punchid = punchDAO.create(p1);
        Punch p = punchDAO.find(punchid);
        
        // output should be "Test Badge: #C4F37EFF (Welch, Travis C)"
        
        System.err.println("Test Badge: " + b.toString());
        System.err.println("Test Shift: " + s.toString());
        System.err.println("Test Employee: " + e.toString());
        System.err.println("Test Punch Create: " + punchid);
        System.err.println("Test Punch Create: " + p1.printOriginal());
        System.err.println("Test Punch Create: " + p.printOriginal());

    }

}
