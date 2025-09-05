package main.java.com.project.repository;

import main.java.com.project.dto.Flight;

import java.sql.SQLException;
import java.util.List;

public interface FlightDao {
    /**
     * insert flights
     */
    long saveOrUpdatePrice(Flight flight) throws SQLException;

    /**
     * update flights seats count
     */
    int updateSeatCount(int flight_id) throws SQLException;

    /**
     * select flights
     * @param origin
     * @param destination
     * @param departDate
     * @return
     */
    List<Flight> findByOriginAndDestinationAndDepartDateAndCheckTime(String origin, String destination, String departDate) throws SQLException;

    /**
     *  방금 추가된 flight_id를 가져오는 쿼리
     * @return flight_id
     */
    public long findLastInsertId() throws SQLException;
}
