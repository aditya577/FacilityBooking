package com.booking.facility;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.booking.facility.Facility.BookingParams;
import com.booking.utils.DateTimeUtils;

public class FacilityOperations {

	private Scanner sc = new Scanner(System.in);
	List<String> prevBookings;
	Facility facility;

	public FacilityOperations() {
		facility = new Facility();
		prevBookings = facility.getBookings();
	}

	public List<String> showRecords() {
		return prevBookings;
	}

	public Integer initiateBooking(String tempInput) {
		String input = sc.nextLine();
		if (tempInput != null) {
			input = tempInput;
		}
		BookingParams newBookingObj = new BookingParams();
		newBookingObj = newBookingObj.getBookingParams(input);
		Boolean bookingAllowed = checkPreviousBookings(newBookingObj);
		if (bookingAllowed) {
			Integer price = makeNewBookingEntry(newBookingObj);
			return price;
		}
		return null;
	}

	public Integer makeNewBookingEntry(BookingParams newBookingObj) {
		Integer price = calculateFee(newBookingObj);
		newBookingObj.setPrice(price);
		String newBookingStr = newBookingObj.getBookingString(newBookingObj);
		prevBookings.add(newBookingStr);
		return price;
	}

	public int calculateFee(BookingParams newBookingObj) {
		Date newBookingStartTime = DateTimeUtils.convertDateTimeToDate(newBookingObj.getBookingDate(),
				newBookingObj.getStartTime());
		Date newBookingEndTime = DateTimeUtils.convertDateTimeToDate(newBookingObj.getBookingDate(),
				newBookingObj.getEndTime());
		Integer hours = DateTimeUtils.getHoursDifference(newBookingEndTime, newBookingStartTime);

		if (Facility.CLUB_HOUSE.equalsIgnoreCase(newBookingObj.getFacilityName())) {
			Date slotBoundaryDateTime = DateTimeUtils.convertDateTimeToDate(newBookingObj.getBookingDate(),
					Facility.CLUB_HOUSE_SLOT_BOUNDARY_TIME);

			if (slotBoundaryDateTime.compareTo(newBookingStartTime) >= 0
					&& slotBoundaryDateTime.compareTo(newBookingEndTime) >= 0) {
				// in slot 1
				return (hours * Facility.CLUB_HOUSE_SLOT1_PER_HOUR_PRICE);
			} else if (slotBoundaryDateTime.compareTo(newBookingStartTime) < 0
					&& slotBoundaryDateTime.compareTo(newBookingEndTime) < 0) {
				// in slot 2
				return (hours * Facility.CLUB_HOUSE_SLOT2_PER_HOUR_PRICE);
			} else {
				// booking continue in both slots 1 & 2
				Integer hoursInSlot1 = DateTimeUtils.getHoursDifference(slotBoundaryDateTime, newBookingStartTime);
				Integer hoursInSlot2 = hours - hoursInSlot1;

				return ((hoursInSlot1 * Facility.CLUB_HOUSE_SLOT1_PER_HOUR_PRICE)
						+ (hoursInSlot2 * Facility.CLUB_HOUSE_SLOT2_PER_HOUR_PRICE));
			}

		} else if (Facility.TENNIS_COURT.equalsIgnoreCase(newBookingObj.getFacilityName())) {
			return (hours * Facility.TENNIS_COURT_PER_HOUR_PRICE);
		}
		return 0;
	}

	public boolean checkPreviousBookings(BookingParams newBookingObj) {
		boolean bookingAllowed = true;

		if (prevBookings.size() == 0) {
			return bookingAllowed;
		}

		for (String prevBookingStr : prevBookings) {
			BookingParams prevBookingObj = new BookingParams();
			prevBookingObj = prevBookingObj.getBookingParams(prevBookingStr);
			if (prevBookingObj.getFacilityName().equalsIgnoreCase(newBookingObj.getFacilityName())) {

				Date newBookingStartTime = DateTimeUtils.convertDateTimeToDate(newBookingObj.getBookingDate(),
						newBookingObj.getStartTime());
				Date newBookingEndTime = DateTimeUtils.convertDateTimeToDate(newBookingObj.getBookingDate(),
						newBookingObj.getEndTime());
				Date bookedStartTime = DateTimeUtils.convertDateTimeToDate(prevBookingObj.getBookingDate(),
						prevBookingObj.getStartTime());
				Date bookedEndTime = DateTimeUtils.convertDateTimeToDate(prevBookingObj.getBookingDate(),
						prevBookingObj.getEndTime());

				boolean alreadyBooked = DateTimeUtils.isSlotBooked(bookedStartTime, bookedEndTime, newBookingStartTime,
						newBookingEndTime);
				if (alreadyBooked) {
					bookingAllowed = false;
					break;
				}
			}
		}
		return bookingAllowed;
	}
}
