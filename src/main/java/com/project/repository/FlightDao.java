package main.java.com.project.repository;

import main.java.com.project.dto.Flight;

import java.sql.SQLException;
import java.util.List;

public interface FlightDao {
    /**
     * insert flights
     */
    int save(Flight flight) throws SQLException;

    /**
     * select flights
     * @param origin
     * @param destination
     * @param departDate
     * @return
     */
    List<Flight> findByOriginAndDestinationAndDepartDateAndCheckTime(String origin, String destination, String departDate) throws SQLException;
}
