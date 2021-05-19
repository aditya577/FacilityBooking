package com.booking.driver;

import java.util.List;
import java.util.Scanner;

import com.booking.facility.FacilityOperations;

public class Driver {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		FacilityOperations facilityOperations = new FacilityOperations();

		while (true) {
			System.out.println("========START========");
			System.out.println("1 to initiate booking\n2 for showRecords\n3 for exit\n");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Enter booking info\n");
				Integer price = facilityOperations.initiateBooking(null);
				if (price == null) {
					System.out.println("Booking Failed,	Already	Booked");
				} else {
					System.out.println("Booked, Rs. " + price);
				}
				break;
			case 2:
				List<String> allBookings = facilityOperations.showRecords();
				if (allBookings.size() == 0) {
					System.out.println("No records");
				} else {
					System.out.println("Pre-Made Bookings\n");
					for (String booking : allBookings) {
						System.out.println(booking);
					}
				}
				break;
			case 3:
				System.out.println("========END========");
				System.exit(0);
			default:
				System.out.println("invalid request");
			}
		}
	}

}
