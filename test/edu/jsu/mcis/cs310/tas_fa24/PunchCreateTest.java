package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.dao.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.*;
import static org.junit.Assert.*;

public class PunchCreateTest {

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");

    }

    @Test
    public void testCreatePunch1() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PunchDAO punchDAO = daoFactory.getPunchDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Create New Punch Object */
        
        Punch p1 = new Punch(103, badgeDAO.find("021890C0"), EventType.CLOCK_IN);

        /* Create Timestamp Objects */
        
        LocalDateTime ots, rts;

        /* Get Punch Properties */
        
        String badgeid = p1.getBadge().getId();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalId();
        EventType punchtype = p1.getPunchType();

        /* Insert Punch Into Database */
        
        int punchid = punchDAO.create(p1);

        /* Retrieve New Punch */
        
        Punch p2 = punchDAO.find(punchid);

        /* Compare Punches */
        
        assertEquals(badgeid, p2.getBadge().getId());

        rts = p2.getOriginalTimestamp();

        assertEquals(terminalid, p2.getTerminalId());
        assertEquals(punchtype, p2.getPunchType());
        assertEquals(ots.format(dtf), rts.format(dtf));

    }

    @Test
    public void testCreatePunch2() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PunchDAO punchDAO = daoFactory.getPunchDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Create New Punch Object */
    
        Punch p3 = new Punch(104, badgeDAO.find("29C03912"), EventType.CLOCK_IN);

        /* Create Timestamp Objects */
    
        LocalDateTime ots, rts;

        /* Get Punch Properties */
    
        String badgeid = p3.getBadge().getId();
        ots = p3.getOriginalTimestamp();
        int terminalid = p3.getTerminalId();
        EventType punchtype = p3.getPunchType();

        /* Insert Punch Into Database */
    
        int punchid = punchDAO.create(p3);
        assertNotEquals("Punch ID should not be 0", 0, punchid);         
        /* Retrieve New Punch */
    
        Punch p4 = punchDAO.find(punchid);
        assertNotNull("Punch should be found in database", p4);
 
        /* Compare Punches */
    
        assertEquals(badgeid, p3.getBadge().getId());

        rts = p4.getOriginalTimestamp();

        assertEquals(terminalid, p4.getTerminalId());
        assertEquals(punchtype, p4.getPunchType());
        assertEquals(ots.format(dtf), rts.format(dtf));
}
    @Test
    public void testCreatePunch3() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PunchDAO punchDAO = daoFactory.getPunchDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Create New Punch Object */
    
        Punch p5 = new Punch(105, badgeDAO.find("8C0644BA"), EventType.CLOCK_IN);

        /* Create Timestamp Objects */
    
        LocalDateTime ots, rts;

        /* Get Punch Properties */
    
        String badgeid = p5.getBadge().getId();
        ots = p5.getOriginalTimestamp();
        int terminalid = p5.getTerminalId();
        EventType punchtype = p5.getPunchType();

        /* Insert Punch Into Database */
    
        int punchid = punchDAO.create(p5);
                 
        /* Retrieve New Punch */
    
        Punch p6 = punchDAO.find(punchid);
        
 
        /* Compare Punches */
    
        assertEquals(badgeid, p5.getBadge().getId());

        rts = p6.getOriginalTimestamp();

        assertEquals(terminalid, p6.getTerminalId());
        assertEquals(punchtype, p6.getPunchType());
        assertEquals(ots.format(dtf), rts.format(dtf));
} 
    @Test
    public void testCreatePunch4() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PunchDAO punchDAO = daoFactory.getPunchDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Create New Punch Object */
    
        Punch p7 = new Punch(106, badgeDAO.find("29C03912"), EventType.CLOCK_IN);

        /* Create Timestamp Objects */
    
        LocalDateTime ots, rts;

        /* Get Punch Properties */
    
        String badgeid = p7.getBadge().getId();
        ots = p7.getOriginalTimestamp();
        int terminalid = p7.getTerminalId();
        EventType punchtype = p7.getPunchType();

        /* Insert Punch Into Database */
    
        int punchid = punchDAO.create(p7);
        
        /* Retrieve New Punch */
    
        Punch p8 = punchDAO.find(punchid);
        
        
        /* Compare Punches */
    
        assertEquals(badgeid, p7.getBadge().getId());

        rts = p8.getOriginalTimestamp();

        assertEquals(terminalid, p8.getTerminalId());
        assertEquals(punchtype, p8.getPunchType());
        assertEquals(ots.format(dtf), rts.format(dtf));
} 
    @Test
    public void testCreatePunch5() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PunchDAO punchDAO = daoFactory.getPunchDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Create New Punch Object */
    
        Punch p9 = new Punch(101, badgeDAO.find("8001201A"), EventType.CLOCK_IN);

        /* Create Timestamp Objects */
    
        LocalDateTime ots, rts;

        /* Get Punch Properties */
    
        String badgeid = p9.getBadge().getId();
        ots = p9.getOriginalTimestamp();
        int terminalid = p9.getTerminalId();
        EventType punchtype = p9.getPunchType();

        /* Insert Punch Into Database */
    
        int punchid = punchDAO.create(p9);
        
        /* Retrieve New Punch */
    
        Punch p10 = punchDAO.find(punchid);
        
        
        /* Compare Punches */
    
        assertEquals(badgeid, p9.getBadge().getId());

        rts = p10.getOriginalTimestamp();

        assertEquals(terminalid, p10.getTerminalId());
        assertEquals(punchtype, p10.getPunchType());
        assertEquals(ots.format(dtf), rts.format(dtf));
}
}
