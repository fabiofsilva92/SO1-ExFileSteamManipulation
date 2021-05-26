package view;

import java.io.IOException;

import controller.SteamController;

public class Main {

	public static void main(String[] args) {
		
		SteamController sc = new SteamController();
		
		String pathToCopy = System.getProperty("user.dir")+"\\src\\files";
		String filename = "SteamCharts.csv";
		sc.copyFile(pathToCopy, filename); //Copy the csv file that is in the package to the TEMP folder.


		int year = 2020;
		String month = "may";
		double avg = 100000;
		
		try {
			sc.filterGames(year, month, avg); // Filter the games according the parameters
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String path = "C:\\TEMP";
		String filenameCreate = month+year+".csv";
		try {
			sc.createFile(path, filenameCreate, year, month); // Create the file with the parameters
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
