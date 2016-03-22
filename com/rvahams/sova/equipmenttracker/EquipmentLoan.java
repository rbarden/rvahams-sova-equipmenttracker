package com.rvahams.sova.equipmenttracker;

import java.time.LocalDateTime;

public class EquipmentLoan {

	private String rid;
	private String who;
	private LocalDateTime checkedOut;
	private LocalDateTime returned;

	public EquipmentLoan(String rid) {
		setWho("UNCLAIMED");
		setRID(rid);
	}

	public String getRID() {
		return rid;
	}

	public String getWho() {
		return who;
	}

	public LocalDateTime getCheckedOut() {
		return checkedOut;
	}

	public LocalDateTime getReturned() {
		return returned;
	}

	public void setRID(String rid) {
		this.rid = rid;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public void setCheckedOutNow() {
		checkedOut = LocalDateTime.now();
	}

	public void setReturnedNow() {
		returned = LocalDateTime.now();
	}

	public void checkOut(String name) {
		setWho(name);
		setCheckedOutNow();
	}

	public void checkIn() {
		setReturnedNow();
		setWho("UNCLAIMED");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rid);
		sb.append(" - ");
		sb.append(who);
		if (!"UNCLAIMED".equals(who)) {
			sb.append(" - Checked Out at: ");
			sb.append(getCheckedOut());
		}
		return sb.toString();
	}
}