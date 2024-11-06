package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.dao.*;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class PunchListFindTest2 {
    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");
    }
    
    
    @Test
    public void testFindPunchList1() throws SQLException {

        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Create StringBuilders for Test Output */
        
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        /* Create Timestamp and Badge Objects for Punch List */
        
        LocalDate ts1 = LocalDate.of(2018, Month.SEPTEMBER, 24);
        LocalDate ts2 = LocalDate.of(2018, Month.SEPTEMBER, 29);

        Badge b = badgeDAO.find("C4F37EFF");

        /* Retrieve Punch List #1 (created by DAO) */
        
        ArrayList<Punch> p1 = punchDAO.list(b, ts1, ts2);

        /* Export Punch List #1 Contents to StringBuilder */
        
        for (Punch p : p1) {
            s1.append(p.printOriginal());
            s1.append("\n");
        }

        /* Create Punch List #2 (created manually) */
        
        ArrayList<Punch> p2 = new ArrayList<>();

        /* Add Punches */
        
        p2.add(punchDAO.find(5643));
        p2.add(punchDAO.find(5700));
        p2.add(punchDAO.find(5798));
        p2.add(punchDAO.find(5849));
        p2.add(punchDAO.find(5948));
        p2.add(punchDAO.find(5993));
        p2.add(punchDAO.find(6098));
        p2.add(punchDAO.find(6143));
        p2.add(punchDAO.find(6254));
        p2.add(punchDAO.find(6314));

        /* Export Punch List #2 Contents to StringBuilder */
        
        for (Punch p : p2) {
            s2.append(p.printOriginal());
            s2.append("\n");
        }

        /* Compare Output Strings */
        
        assertEquals(s2.toString(), s1.toString());

    }
    
    @Test
    public void testFindPunchList2() throws SQLException {

        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Create StringBuilders for Test Output */
        
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        /* Create Timestamp and Badge Objects for Punch List */
        
        LocalDate ts1 = LocalDate.of(2018, Month.SEPTEMBER, 19);
        LocalDate ts2 = LocalDate.of(2018, Month.SEPTEMBER, 22);

        Badge b = badgeDAO.find("67637925");

        /* Retrieve Punch List #1 (created by DAO) */
        
        ArrayList<Punch> p1 = punchDAO.list(b, ts1, ts2);

        /* Export Punch List #1 Contents to StringBuilder */
        
        for (Punch p : p1) {
            s1.append(p.printOriginal());
            s1.append("\n");
        }

        /* Create Punch List #2 (created manually) */
        
        ArrayList<Punch> p2 = new ArrayList<>();

        /* Add Punches */
        
        p2.add(punchDAO.find(5018));
        p2.add(punchDAO.find(5132));
        p2.add(punchDAO.find(5177));
        p2.add(punchDAO.find(5267));
        p2.add(punchDAO.find(5324));
        p2.add(punchDAO.find(5392));
        p2.add(punchDAO.find(5461));
        p2.add(punchDAO.find(5526));
        

        /* Export Punch List #2 Contents to StringBuilder */
        
        for (Punch p : p2) {
            s2.append(p.printOriginal());
            s2.append("\n");
        }

        /* Compare Output Strings */
        
        assertEquals(s2.toString(), s1.toString());

    }
    
    @Test
    public void testFindPunchList3() throws SQLException {

        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Create StringBuilders for Test Output */
        
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        /* Create Timestamp and Badge Objects for Punch List */
        
        LocalDate ts1 = LocalDate.of(2018, Month.SEPTEMBER, 10);
        LocalDate ts2 = LocalDate.of(2018, Month.SEPTEMBER, 12);

        Badge b = badgeDAO.find("87176FD7");

        /* Retrieve Punch List #1 (created by DAO) */
        
        ArrayList<Punch> p1 = punchDAO.list(b, ts1, ts2);

        /* Export Punch List #1 Contents to StringBuilder */
        
        for (Punch p : p1) {
            s1.append(p.printOriginal());
            s1.append("\n");
        }

        /* Create Punch List #2 (created manually) */
        
        ArrayList<Punch> p2 = new ArrayList<>();

        /* Add Punches */
        
        p2.add(punchDAO.find(3894));
        p2.add(punchDAO.find(3958));
        p2.add(punchDAO.find(4044));
        p2.add(punchDAO.find(4094));
        p2.add(punchDAO.find(4100));
        p2.add(punchDAO.find(4123));
        p2.add(punchDAO.find(4225));
        p2.add(punchDAO.find(4260));
        p2.add(punchDAO.find(4265));
        p2.add(punchDAO.find(4272));
        

        /* Export Punch List #2 Contents to StringBuilder */
        
        for (Punch p : p2) {
            s2.append(p.printOriginal());
            s2.append("\n");
        }

        /* Compare Output Strings */
        
        assertEquals(s2.toString(), s1.toString());

    }
    
    @Test
    public void testFindPunchList4() throws SQLException {

        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Create StringBuilders for Test Output */
        
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        /* Create Timestamp and Badge Objects for Punch List */
        
        LocalDate ts1 = LocalDate.of(2018, Month.AUGUST, 1);
        LocalDate ts2 = LocalDate.of(2018, Month.AUGUST, 7);

        Badge b = badgeDAO.find("95497F63");

        /* Retrieve Punch List #1 (created by DAO) */
        
        ArrayList<Punch> p1 = punchDAO.list(b, ts1, ts2);

        /* Export Punch List #1 Contents to StringBuilder */
        
        for (Punch p : p1) {
            s1.append(p.printOriginal());
            s1.append("\n");
        }

        /* Create Punch List #2 (created manually) */
        
        ArrayList<Punch> p2 = new ArrayList<>();

        /* Add Punches */
        
        p2.add(punchDAO.find(191));
        p2.add(punchDAO.find(209));
        p2.add(punchDAO.find(297));
        p2.add(punchDAO.find(328));
        p2.add(punchDAO.find(429));
        p2.add(punchDAO.find(457));
        p2.add(punchDAO.find(529));
        p2.add(punchDAO.find(561));
        p2.add(punchDAO.find(642));
        p2.add(punchDAO.find(685));
        

        /* Export Punch List #2 Contents to StringBuilder */
        
        for (Punch p : p2) {
            s2.append(p.printOriginal());
            s2.append("\n");
        }

        /* Compare Output Strings */
        
        assertEquals(s2.toString(), s1.toString());

    }
    
    @Test
    public void testFindPunchList5() throws SQLException {

        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /* Create StringBuilders for Test Output */
        
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        /* Create Timestamp and Badge Objects for Punch List */
        
        LocalDate ts1 = LocalDate.of(2018, Month.AUGUST, 14);
        LocalDate ts2 = LocalDate.of(2018, Month.AUGUST, 14);

        Badge b = badgeDAO.find("BEAFDB2F");

        /* Retrieve Punch List #1 (created by DAO) */
        
        ArrayList<Punch> p1 = punchDAO.list(b, ts1, ts2);

        /* Export Punch List #1 Contents to StringBuilder */
        
        for (Punch p : p1) {
            s1.append(p.printOriginal());
            s1.append("\n");
        }

        /* Create Punch List #2 (created manually) */
        
        ArrayList<Punch> p2 = new ArrayList<>();

        /* Add Punches */
        
        p2.add(punchDAO.find(1295));
        p2.add(punchDAO.find(1375));
        

        /* Export Punch List #2 Contents to StringBuilder */
        
        for (Punch p : p2) {
            s2.append(p.printOriginal());
            s2.append("\n");
        }

        /* Compare Output Strings */
        
        assertEquals(s2.toString(), s1.toString());

    }
}
