package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.time.LocalDate;

public class AbsenteeismDAO {
    
        private final DAOFactory daoFactory;

    
    AbsenteeismDAO(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public Absenteeism find(Employee employee, LocalDate date)
    {
        
    }
}
