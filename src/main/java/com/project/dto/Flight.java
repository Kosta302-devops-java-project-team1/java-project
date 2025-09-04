package main.java.com.project.dto;

/**
 * Flight entity
 */
public class Flight {
    private int flight_id;
    private String airline_name;
    private String departure_airport;
    private int departure_terminal;
    private String departure_time;
    private String arrival_airport;
    private int arrival_terminal;
    private String arrival_time;
    private String duration;
    private double price;
    private int remaining_seat;
    private String last_update;

    @Override
    public String toString() {  // todo 뷰에 맞게 바꾸기 / airport 한국어화하기 -> ICN ->서울(인천공항) / duration 시간화하기 HT2H30M -> 2시간30분
        return "Flight{" +
                "airline_name='" + airline_name + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", departure_terminal=" + departure_terminal +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_airport='" + arrival_airport + '\'' +
                ", arrival_terminal=" + arrival_terminal +
                ", arrival_time='" + arrival_time + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", remaining_seat=" + remaining_seat +
                '}';
    }

    public Flight(String airline_name, String departure_airport, int departure_terminal, String departure_time, String arrival_airport, int arrival_terminal, String arrival_time, String duration, double price, int remaining_seat) {
        this.airline_name = airline_name;
        this.departure_airport = departure_airport;
        this.departure_terminal = departure_terminal;
        this.departure_time = departure_time;
        this.arrival_airport = arrival_airport;
        this.arrival_terminal = arrival_terminal;
        this.arrival_time = arrival_time;
        this.duration = duration;
        this.price = price;
        this.remaining_seat = remaining_seat;
    }

    // @AllArgsCons
    public Flight(int flight_id, String airline_name, String departure_airport, int departure_terminal, String departure_time, String arrival_airport, int arrival_terminal, String arrival_time, String duration, double price, int remaining_seat, String last_update) {
        this.flight_id = flight_id;
        this.airline_name = airline_name;
        this.departure_airport = departure_airport;
        this.departure_terminal = departure_terminal;
        this.departure_time = departure_time;
        this.arrival_airport = arrival_airport;
        this.arrival_terminal = arrival_terminal;
        this.arrival_time = arrival_time;
        this.duration = duration;
        this.price = price;
        this.remaining_seat = remaining_seat;
        this.last_update = last_update;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public int getDeparture_terminal() {
        return departure_terminal;
    }

    public void setDeparture_terminal(int departure_terminal) {
        this.departure_terminal = departure_terminal;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public int getArrival_terminal() {
        return arrival_terminal;
    }

    public void setArrival_terminal(int arrival_terminal) {
        this.arrival_terminal = arrival_terminal;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemaining_seat() {
        return remaining_seat;
    }

    public void setRemaining_seat(int remaining_seat) {
        this.remaining_seat = remaining_seat;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
