package com.rvahams.sova.equipmenttracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MainApplication {

	private static final Logger logger = LogManager.getLogger("MainApplication");

	public static void main(String[] args) {
		logger.entry();

		Scanner in = new Scanner(System.in);
		logger.info("Opened Scanner for System.in");

		EquipmentInventory inventory = new EquipmentInventory();
		logger.info("Constructed inventory");

		printWelcome();

		getRadioList(in, inventory);

		inventory.setInventoryName(getSnapshotFileName(in));

		boolean wantsToQuit = false;
		logger.info("Set wantsToQuit to false");
		String rid;
		do {
			printUserMenu();

			switch (getUserResponse(in)) {
			case '1':
				System.out.print("Enter your name: ");
				String name = in.next();
				System.out.print("Enter the radio's identifier: ");
				rid = in.next();
				try {
					inventory.checkOutItem(rid, name);
					logger.info(name + " checked out " + rid);
				} catch (IllegalStateException e) {
					logger.catching(e);
					System.out.println("Something Went Wrong. Try Again.");
					continue;
				} catch (NullPointerException e) {
					logger.catching(e);
					System.out.println("Something Went Wrong. Try Again.");
					continue;
				}
				break;
			case '2':
				System.out.print("Enter the radio's identifier: ");
				rid = in.next();
				try {
					inventory.returnItem(rid);
					logger.info(rid + " was checked back in");
				} catch (IllegalStateException e) {
					logger.catching(e);
					System.out.println("Something Went Wrong. Try Again.");
					continue;
				} catch (NullPointerException e) {
					logger.catching(e);
					System.out.println("Something Went Wrong. Try Again.");
					continue;
				}
				break;
			case '3':
				try {
					inventory.generateSnapshot();
				} catch (IOException e) {
					logger.catching(e);
					System.out.println("The snapshot file was not generated. \n"
							+ "Please try again.");
				}
				break;
			case '4':
				wantsToQuit = true;
				logger.info("User desires to quit application");
				break;
			default:
				System.out.println("How did you get this far?");
				System.out.println("Congratulations, you win a cookie.");
				logger.error("The user got to the default in the menu switch ... how?");
			}
		} while (!wantsToQuit);

		logger.info("Creating automatic Snapshot file ... ");
		try {
			inventory.generateSnapshot();
		} catch (IOException e) {
			logger.catching(e);
			System.out.println("The snapshot file was not generated.");
		}
		logger.info("Created automatic Snapshot file");

		System.out.println("\nThank you for using the RVAHams Equipment Tracker!");

		logger.info("General cleanup of application before exiting");
		in.close();
		logger.exit();
	}

	private static void printWelcome() {
		logger.entry();
		System.out.println("Welcome to the RVAHams Equipment Tracker");
		System.out.println("----------------------------------------\n");
		System.out.println("This program will keep track of any equipment loans during the event.");
		System.out.println("A note about file names:");
		System.out.println("\t1) The Radio List file MUST have an extension and MUST be a plain text file.");
		System.out.println("\t\tGood: radioList.txt -OR- RadioIdentifiers.dat");
		System.out.println("\t\tBad: Radios.doc -OR- radiolist.xls");
		System.out.println(
				"\t2) The Snapshot file should NOT have an extension. The application automatically adds a random identifier and saves it as a .txt file.");

		System.out.println();
		logger.exit();
	}

	private static void getRadioList(Scanner in, EquipmentInventory inventory) {
		logger.entry();

		System.out.print("Enter a Radio List file name: \n\t");
		logger.info("Attempting to read System.in for file name...");
		String fileName = in.next();
		File radioListFile = new File(fileName);
		while (!radioListFile.exists()) {
			System.out.print("That file - " + fileName + " - does not exist. \n"
					+ "Please enter a new name: \n\t");
			radioListFile = new File(in.next());
			fileName = radioListFile.toString();
		}
		logger.info("Successfully found file - " + fileName);

		logger.info("Starting Stream to populate inventory...");

		try {
			inventory.populate(Files.lines(radioListFile.toPath()));
		} catch (IOException e) {
			logger.catching(e);
			System.out.println("Something went wrong while parsing. \n"
					+ "Please restart the program and try again.");
			System.exit(1);
		}

		logger.info("Finished parsing radio list file");
		logger.exit();
	}

	private static String getSnapshotFileName(Scanner in) {
		logger.entry();
		System.out.println("Enter the Snapshot file name:");
		return logger.exit(in.next());
	}

	private static void printUserMenu() {
		logger.entry();
		System.out.println("\nUser Menu:");
		System.out.println("Hit the enter/return key after you type selection");
		System.out.println("\t1 - Check Out Radio");
		System.out.println("\t2 - Return Radio");
		System.out.println("\t3 - Generate Snapshot File");
		System.out.println("\t4 - Quit Application (Automatically Creates Snapshot)");
		logger.exit();
	}

	private static char getUserResponse(Scanner in) {
		logger.entry();
		while (!in.hasNext("[1234]")) {
			System.out.println();
			System.out.print("That's not one of the options. Please try again: ");
			logger.warn("User Incorrectly Entered: " + in.next());
		}
		return logger.exit(in.next().charAt(0));
	}
}