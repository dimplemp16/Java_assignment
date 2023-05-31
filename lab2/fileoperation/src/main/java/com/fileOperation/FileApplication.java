package com.fileOperation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileApplication {

	private static final String DIRECTORY_PATH = "C:/Dimple/";

	public void show() {
		Scanner myObj = new Scanner(System.in);
		while (true) {
			System.out.println("MAIN MENU");
			System.out.println("1 Retrieving the file names in an ascending order");
			System.out.println("2 Business-level operations");
			System.out.println("3 Close the application");

			System.out.println("Enter your option:");

			int option = myObj.nextInt();

			switch (option) {
			case 1:
				List<String> names = gtAllFiles();
				System.out.println("File names:");
				names.forEach(System.out::println);
				break;
			case 2:
				fileOpration();
				break;
			case 3:
				System.out.println("Closed");
				
				return;
			default:
				System.out.println("No Other Option");
			}

		}
	}
	
	

	
	
	private List<String> gtAllFiles(){
		
		File[] files = new File(DIRECTORY_PATH).listFiles();
		if (files.length > 0) {
			return Arrays.stream(files).map(File::getName).collect(Collectors.toList());
					
		} else {
			return Collections.emptyList();
		}
		
//		File[] files = new File(DIRECTORY_PATH).listFiles();
//		if (files.length > 0) {
//			Arrays.stream(files).map(File::getName).forEach(filename -> System.out.println(filename));
//		} else {
//			System.out.println("File is empty");
//		}
		
		
	}

	private void fileOpration() {
	
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Add a file to the existing directory list");
			System.out.println("Delete a file from the existing directory list");
			System.out.println("Search a specified file from the main directory");
			System.out.println("Close the fileOpration ");
			System.out.println("Enter your Choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addFile();
				break;
			case 2:
					delete();
				break;
			case 3:
				searchFile();
				break;
			case 4:
				System.out.println("Closed current");
				show();
				return;
			default:
				System.out.println("No Other Option");
			}
		}

	}

	private void searchFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Write File Name to search");
		String searchFile = DIRECTORY_PATH + sc.nextLine();
		File file = new File(searchFile);
		if (file.exists()) {
			System.out.println("File is found: " + file.getName());
		} else {
			System.out.println("File not found");
		}

	}

	private void delete() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Write File Name to delete");
		String deleteFile = DIRECTORY_PATH + sc.nextLine();
		File file = new File(deleteFile);
		if (file.exists()) {
			if(file.delete()) {
			System.out.println("File is deleted: " + file.getName());
			}else {
				System.out.println("Failed to delete");
			}
			
		} else {
			System.out.println("File not found");
		}
	}

	private void addFile() {
		try {
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Write new File Name");
			String NewFile = DIRECTORY_PATH + sc1.nextLine();

			File file = new File(NewFile);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("File is created "+file.getName());
			} else {
				System.out.println("file alreday exist");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("File is not created");
		}

		
	}

}
