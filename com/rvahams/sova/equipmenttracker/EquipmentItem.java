package com.rvahams.sova.equipmenttracker;

import java.time.LocalDateTime;

public class EquipmentItem {

	private static String UNCLAIMED_BORROWER_VALUE = "UNCLAIMED";

	private String id;
	private String borrower = UNCLAIMED_BORROWER_VALUE;
	private LocalDateTime checkedOutTime;
	private LocalDateTime returnedTime;
	private boolean available = true;

	public EquipmentItem(String id) {
		this.id = id;
	}

	public void checkOut(String name) {
		if (!available) {
			throw new UnsupportedOperationException(id + " is already checked out.");
		}
		borrower = name;
		checkedOutTime = LocalDateTime.now();
		available = false;
	}

	public void checkIn() {
		if (available) {
			throw new UnsupportedOperationException(id + " is already checked in.");
		}
		borrower = UNCLAIMED_BORROWER_VALUE;
		returnedTime = LocalDateTime.now();
		available = true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(" - ").append(borrower);
		if (available) {
			if (returnedTime == null) {
				sb.append(" - Never checked out");
			} else {
				sb.append(" - Returned at: ").append(returnedTime);
			}
		} else {
			sb.append(" - Checked Out at: ").append(checkedOutTime);
		}
		return sb.toString();
	}
}