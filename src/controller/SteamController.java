package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;

public class SteamController {
	
	public SteamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void filterGames(int year, String month, double avg ) throws IOException {
		
		String dir = "C:\\TEMP";
		String filename = "SteamCharts.csv";
		
		File file = new File(dir, filename);
		if(file.exists() && file.isFile()) {
			FileInputStream flood = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(flood);
			BufferedReader buffer =  new BufferedReader(reader);
			String line = "";
			line = buffer.readLine();
			String [] auxLine = line.split(",");
			System.out.println("\t"+auxLine[0]+"|"+auxLine[3]);
			line = buffer.readLine();
			auxLine = line.split(",");
			
			while(line != null) {
				
				if(Integer.parseInt(auxLine[1]) == year &&auxLine[2].equalsIgnoreCase(month) && Double.parseDouble(auxLine[3]) >= avg) {
					auxLine = line.split(",");
					System.out.println("\t"+auxLine[0]+"|"+auxLine[3]);
				}
				line = buffer.readLine();
				if(line != null) {
					auxLine = line.split(",");
				}
			}
			buffer.close();
			reader.close();
			flood.close();
		}
		else {
			throw new IOException("Invalid operation");
		}			
	}
	
	public void createFile(String path, String filename, int year, String month) throws IOException {
		
		File file = new File(path, filename);
		File dir = new File(path);
		if(!dir.exists() && !dir.isDirectory()) {
			dir.mkdir();
		}
		FileInputStream flood = new FileInputStream("C:\\TEMP\\SteamCharts.csv");
		InputStreamReader reader = new InputStreamReader(flood);
		BufferedReader buffer =  new BufferedReader(reader);
		String line = "";
		line = buffer.readLine();
		String [] auxLine = line.split(",");
		//System.out.println("\t"+auxLine[0]+"|"+auxLine[3]);
		line = buffer.readLine();
		auxLine = line.split(",");
		while(line!=null) {
			if(Integer.parseInt(auxLine[1]) == year &&auxLine[2].equalsIgnoreCase(month)) {
				auxLine = line.split(",");
				//System.out.println(auxLine[0]+";"+auxLine[3]+"\r");
				writeFile(auxLine[0]+";"+auxLine[3]+"\r", file);
			}
			line = buffer.readLine();
			if(line!=null) {
				auxLine = line.split(",");
			}
		}
	}
	
	private void writeFile(String line, File file) throws IOException {
		
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(line);
		print.flush();
		print.close();
		fileWriter.close();
		
	}

	public void copyFile(String path, String name) {
		
		File dir = new File("C:\\TEMP");
		if(!dir.exists()) {
			boolean test = dir.mkdir();
			if(test) {
				System.out.println("Created");
			}
		}
		else {
			System.out.println("Already exists");
		}
		
		File dirToCopy = new File(path,name);
		File destination = new File(dir, name);
		
		if(!destination.exists()) {
			try {
				Files.copy(dirToCopy.toPath(), destination.toPath());
				System.out.println("Copiado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
