package com.booking.facility;

import java.util.ArrayList;
import java.util.List;

public class Facility {
	// public constants
	public static String CLUB_HOUSE = "Club House";
	public static String TENNIS_COURT = "Tennis Court";
	public static Integer TENNIS_COURT_PER_HOUR_PRICE = 50;
	public static String CLUB_HOUSE_SLOT_BOUNDARY_TIME = "16:00";
	public static Integer CLUB_HOUSE_SLOT1_PER_HOUR_PRICE = 100; // 10AM to 4PM
	public static Integer CLUB_HOUSE_SLOT2_PER_HOUR_PRICE = 500; // 4PM to 10PM

	private List<String> bookings = new ArrayList<String>();

	public List<String> getBookings() {
		return bookings;
	}

	public void setBookings(List<String> bookings) {
		this.bookings = bookings;
	}

	public static class BookingParams {
		private String facilityName;
		private String bookingDate;
		private String startTime;
		private String endTime;
		private Integer price;

		public BookingParams() {
		}

		public BookingParams(String facilityName, String bookingDate, String startTime, String endTime) {
			this.facilityName = facilityName;
			this.bookingDate = bookingDate;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public String getFacilityName() {
			return facilityName;
		}

		public void setFacilityName(String facilityName) {
			this.facilityName = facilityName;
		}

		public String getBookingDate() {
			return bookingDate;
		}

		public void setBookingDate(String bookingDate) {
			this.bookingDate = bookingDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public BookingParams getBookingParams(String jointString) {
			String[] input = jointString.split("[,]", 0);
			return new BookingParams(input[0].trim(), input[1].trim(), input[2].trim(), input[3].trim());
		}

		public String getBookingString(BookingParams bookingParamsObj) {
			return bookingParamsObj.getFacilityName() + ", " + bookingParamsObj.getBookingDate() + ", "
					+ bookingParamsObj.getStartTime() + ", " + bookingParamsObj.getEndTime() + ", Rs. "
					+ bookingParamsObj.getPrice();
		}
	}
}
