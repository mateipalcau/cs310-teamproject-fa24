package edu.jsu.mcis.cs310.tas_fa24;

public class Department {
    final private int id;
    final private String description;
    final private int terminalId;
    
    public Department(int id, String description, int terminalId) {
        this.id = id;
        this.description = description;
        this.terminalId = terminalId;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getTerminalId() {
        return terminalId;
    }

    @Override
    public String toString() {
        return String.format("#%d (%s), Terminal ID: %d", id, description, terminalId);
    }
}
