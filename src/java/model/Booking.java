/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class Booking {

    private Connection con;
    private Statement st;

//    private String sql;
//    ArrayList<BookingData> array_bookingdata  = new ArrayList<BookingData>;
    ArrayList<BookingData> array_bookingdata = new ArrayList<BookingData>();
    BookingData booking_data = new BookingData();

    public Booking(Connection con) {
        this.con = con;

        try {
            st = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Booking() {

    }

    public String insertBooking(String booking_time,
            String status_accept_booking,
            String name_of_customer,
            String time_checkin,
            String status_payment,
            String time_checkout,
            Double cost,
            String booking_description,
            String username,
            String place_name,
            int people) {

        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO booking (`booking_time`, `status_accept_booking`, `name_of_customer`, `time_checkin`, `status_payment`, `time_checkout`, `cost`, `booking_description`, `username`, `place_name`, `people`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, booking_time);
            pstmt.setString(2, status_accept_booking);
            pstmt.setString(3, name_of_customer);
            pstmt.setString(4, time_checkin);
            pstmt.setString(5, status_payment);
            pstmt.setString(6, time_checkout);
            pstmt.setDouble(7, cost);
            pstmt.setString(8, booking_description);
            pstmt.setString(9, username);
            pstmt.setString(10, place_name);
            pstmt.setInt(11, people);

            pstmt.execute();
            pstmt.close();
            return "success";

        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "none";
    }

    public ArrayList<BookingData> queryBookingAll_User(String username) {
        String sql = "select * from booking where username ='" + username + "' order by booking_id desc";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int booking_id = rs.getInt("booking_id");
                String booking_time = rs.getString("booking_time");
                String status_accept_booking = rs.getString("status_accept_booking");
                String name_of_customer = rs.getString("name_of_customer");
                String time_checkin = rs.getString("time_checkin");
                String status_payment = rs.getString("status_payment");
                String time_checkout = rs.getString("time_checkout");
                Double cost = rs.getDouble("cost");
                String booking_description = rs.getString("booking_description");
//                String username_query = username;
                String place_name = rs.getString("place_name");
                int people = rs.getInt("people");

                BookingData booking_data = new BookingData();
                booking_data.setBookingDataAll(booking_id, booking_time, status_accept_booking,
                         name_of_customer, time_checkin, status_payment, time_checkout, cost,
                         booking_description, username, place_name, people);
                array_bookingdata.add(booking_data);

            }
            st.close();
            return array_bookingdata;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array_bookingdata;
    }

    public ArrayList<BookingData> queryBookingAll_Place(String place_name) {
        String sql = "select * from booking where place_name ='" + place_name + "' order by booking_id desc";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int booking_id = rs.getInt("booking_id");
                String booking_time = rs.getString("booking_time");
                String status_accept_booking = rs.getString("status_accept_booking");
                String name_of_customer = rs.getString("name_of_customer");
                String time_checkin = rs.getString("time_checkin");
                String status_payment = rs.getString("status_payment");
                String time_checkout = rs.getString("time_checkout");
                Double cost = rs.getDouble("cost");
                String booking_description = rs.getString("booking_description");
//                String username_query = username;
                String username = rs.getString("username");
                int people = rs.getInt("people");

                BookingData booking_data = new BookingData();
                booking_data.setBookingDataAll(booking_id, booking_time, status_accept_booking,
                         name_of_customer, time_checkin, status_payment, time_checkout, cost,
                         booking_description, username, place_name, people);
                array_bookingdata.add(booking_data);

            }
            st.close();
            return array_bookingdata;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array_bookingdata;
    }

    public boolean cancelBooking(int booking_id) {
        String sql = "DELETE FROM booking WHERE booking_id=" + booking_id;
        try {
            st.execute(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public BookingData queryByBooking_id(int booking_id) {
        String sql = "select * from booking where booking_id = " + booking_id;
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                String booking_time = rs.getString("booking_time");
                String status_accept_booking = rs.getString("status_accept_booking");
                String name_of_customer = rs.getString("name_of_customer");
                String time_checkin = rs.getString("time_checkin");
                String status_payment = rs.getString("status_payment");
                String time_checkout = rs.getString("time_checkout");
                Double cost = rs.getDouble("cost");
                String booking_description = rs.getString("booking_description");
                String username = rs.getString("username");
                String place_name = rs.getString("place_name");
                int people = rs.getInt("people");

//                booking_data = new BookingData();
                booking_data.setBookingDataAll(booking_id, booking_time, status_accept_booking,
                         name_of_customer, time_checkin, status_payment, time_checkout, cost,
                         booking_description, username, place_name, people);
                st.close();
                return booking_data;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booking_data;

    }

    public String updateStatusBooking(int booking_id) {
        String sql = "update booking set status_accept_booking = 'accept'";
        try {
            st.execute(sql);
            st.close();
            return "success";
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "none";
    }

}
