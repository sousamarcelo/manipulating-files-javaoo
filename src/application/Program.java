package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Item;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Item> list = new ArrayList<Item>();
		Item item;
		
		File path = new File("C:\\temp\\test\\in.txt");
		
		boolean sucess = new File(path.getParent() + "\\out").mkdir();
		
		File pathOut = new File(path.getParent() + "\\out\\summary.txt"); 
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				Integer quantity = Integer.parseInt(vect[2]);				
				list.add(new Item(name, price, quantity));
				
				line = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))){
				for(Item i : list) {
					bw.write(i.getName() + "," + String.format("%.2f", i.totalValue()));
					bw.newLine();
				}
				
			} catch(IOException e) {
				System.out.println("error: " + e.getMessage());
			}
			
		} catch (IOException e2) {
			System.out.println("error: " + e2.getMessage());
		}
		
	}

}
