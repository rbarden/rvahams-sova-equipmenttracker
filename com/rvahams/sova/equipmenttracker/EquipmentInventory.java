package com.rvahams.sova.equipmenttracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;

public class EquipmentInventory {

	private String inventoryName = "inventory";
	private String snapshotName = "snapshot";

	private LinkedPositionalList<EquipmentItem> lpl;
	private HashMap<String, Position<EquipmentItem>> hm;

	public EquipmentInventory() {
		lpl = new LinkedPositionalList<EquipmentItem>();
		hm = new HashMap<>();
	}

	public EquipmentInventory(String inventoryName, String snapshotName) {
		this.inventoryName = inventoryName;
		this.snapshotName = snapshotName;
		lpl = new LinkedPositionalList<EquipmentItem>();
		hm = new HashMap<String, Position<EquipmentItem>>();
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public void setSnapshotName(String snapshotName) {
		this.snapshotName = snapshotName;
	}

	public void populate(List<String> identifiers) {
		identifiers.forEach(s -> hm.put(s, lpl.addLast(new EquipmentItem(s))));
	}

	public void checkOutItem(String id, String name) {
		hm.get(id).getElement().checkOut(name);
	}

	public void returnItem(String id) {
		hm.get(id).getElement().checkIn();
	}

	public void generateSnapshot() throws IOException {
		String generatedFileName = "snapshot/" + snapshotName + "-"
				+ Integer.toHexString(new Random().nextInt(0xff)).toUpperCase() + ".txt";
		try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(generatedFileName)))) {
			out.println("------ Snapshot file ------ \n Name: " + generatedFileName + "\n");
			for (EquipmentItem element : lpl) {
				out.println(element);
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}
}
