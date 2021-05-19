package com.booking.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.booking.facility.Facility.BookingParams;
import com.booking.facility.FacilityOperations;

class FacilityBookingTest {

	@Test
	void testCalculateFeeWhenOneHourInBothSlotsForClubHouse() {
		BookingParams bp = new BookingParams("club house","2021-05-19","15:00", "17:00");
		FacilityOperations facilityOperations = new FacilityOperations();
		assertEquals(600, facilityOperations.calculateFee(bp));
	}
	
	@Test
	void testCalculateFeeWhenTwoHourOnlyInSlot1ForClubHouse() {
		BookingParams bp = new BookingParams("club house","2021-05-19","14:00", "16:00");
		FacilityOperations facilityOperations = new FacilityOperations();
		assertEquals(200, facilityOperations.calculateFee(bp));
	}
	
	@Test
	void testCalculateFeeWhenTwoHourOnlyInSlot2ForClubHouse() {
		BookingParams bp = new BookingParams("club house","2021-05-19","16:00", "18:00");
		FacilityOperations facilityOperations = new FacilityOperations();
		assertEquals(1000, facilityOperations.calculateFee(bp));
	}
	
	@Test
	void testCalculateFeeWhenTwoHourForTennisCourt() {
		BookingParams bp = new BookingParams("tennis court","2021-05-19","16:00", "18:00");
		FacilityOperations facilityOperations = new FacilityOperations();
		assertEquals(100, facilityOperations.calculateFee(bp));
	}

	
	
}
