import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class eventTrackerMain2{
	
	public static Integer numberOfEventsTotal = 0;
	public static LocalDate myObj = LocalDate.now();
	public static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	
	//This method retrieves the latest number of total events added so far in the event tracker. These events are stored in a folder.
	public static void numberOfEventsTotalRetriever() throws FileNotFoundException, IOException, ClassNotFoundException   {
		FileInputStream importIterator = new FileInputStream("C://Users//J O R J A//eclipse-workspace//EventTracker//adderIterator/adderIterator.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(importIterator);
		numberOfEventsTotal = (Integer) objectInputStream.readObject();
	}
	
	//This method iterates the total number of events. This method runs automatically when a new event is added.
	public static void numberOfEventsTotalIterator() throws FileNotFoundException, IOException, ClassNotFoundException  {
		numberOfEventsTotal ++;
		FileOutputStream exportIterator = new FileOutputStream("C://Users//J O R J A//eclipse-workspace//EventTracker//adderIterator/adderIterator.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(exportIterator);
		objectOutputStream.writeObject(numberOfEventsTotal);
		
	}
	//This method is the main menu page. It holds the 'add event', 'view events', and 'exit' methods.
	public static void mainMenu() throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Please Choose From The Menu:");
		System.out.println();
		System.out.println("1. Add a new event");
		System.out.println("2. View past events");
		System.out.println("3. Exit");
		Scanner input4 = new Scanner(System.in);
		int menuSelection = input4.nextInt();
			switch (menuSelection) {
			case 1:
				addEvent();
				break;
			case 2:
				viewEvents();
				break;
			case 3:
				exit();
				break;
			}
		
	}
	//This event allows the user to add an event they want to save to the event tracker.
	public static void addEvent() throws FileNotFoundException, IOException, ClassNotFoundException {
		numberOfEventsTotalIterator();
		//adder++;
		System.out.println("Type event below");
		Scanner input6 = new Scanner(System.in);
		String newEvent = input6.nextLine();
		
		LocalDate currentDate = LocalDate.now();
		String currentDateFormatted = currentDate.format(myFormatObj);
		
		FileOutputStream exportData = new FileOutputStream("C://Users//J O R J A//eclipse-workspace//EventTracker//eventTrackerTextFiles/eventTrackerObjects.txt" + numberOfEventsTotal);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(exportData);
		objectOutputStream.writeObject(currentDateFormatted);
		objectOutputStream.writeObject(newEvent);
		
		System.out.println("Event Added!");
		mainMenu();
		
	}
	
	//This method retrieves the number of events total, iterates through all of them, and displays all of the events in the console.
	public static void viewEvents() throws FileNotFoundException, IOException, ClassNotFoundException  {
		numberOfEventsTotalRetriever();
		etoIterator();
		System.out.println("Press x to return to menu");
		Scanner input5 = new Scanner(System.in);
		String returnToMenu = input5.nextLine();
		if (returnToMenu.equals("x")) {
			mainMenu();
		}
		
		
	}
	
	//This method iterates through all of the event files stored on the computer, and stops at the latest one
	public static void etoIterator()  throws FileNotFoundException, IOException, ClassNotFoundException   {
		for (int i = 1; i <= numberOfEventsTotal; i++) {
			FileInputStream importData = new FileInputStream("C://Users//J O R J A//eclipse-workspace//EventTracker//eventTrackerTextFiles/eventTrackerObjects.txt" + i);
			ObjectInputStream objectInputStream = new ObjectInputStream(importData);
			System.out.println("------------------");
			System.out.println(objectInputStream.readObject());
			System.out.println(objectInputStream.readObject());
			System.out.println("------------------");
		}
	}
	
	

	//This method exits the program
	public static void exit() {
		System.out.println("Thank you for using this program!");
		
	}
	

	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException  {
		numberOfEventsTotalRetriever();
		mainMenu();
		
	}

}
