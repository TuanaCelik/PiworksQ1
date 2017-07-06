import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;

public class PiworksQ1{

	static List<String> date;
	static List<Integer> song;
	static List<Integer> client;

	public PiworksQ1() throws IOException{

		try{
			
			File csvFile = new File("exhibitA-input.csv");
			Scanner file = new Scanner(csvFile);
			//file.useDelimiter(",");
			song		= new ArrayList<Integer>();
			client 	 	= new ArrayList<Integer>();
			date  		= new ArrayList<String>();
			
			
			int i = 0;
			while(file.hasNextLine()){
				String string = file.nextLine();
				String[] fields = string.split("	"); 
				if(i != 0){
					//Integer score = 0;
					if(fields[3].split(" ")[0].equals("10/08/2016")){
						date.add(fields[3]);
						song.add(Integer.parseInt(fields[1]));
						client.add(Integer.parseInt(fields[2]));
					}
					
				}
				i++;
			}
		}catch (FileNotFoundException e){
			System.out.println("Something went wrong!");
		}
	}

	public static void main(String args[]){

		try{
			PiworksQ1 pi = new PiworksQ1();
			ArrayList<ArrayList<Integer>> clientOut = new ArrayList<ArrayList<Integer>>(song.size());
			//System.out.println(clientOut.size());
			for(int i = 0; i<song.size(); i++){
				ArrayList<Integer> empty = new ArrayList<Integer>();
				clientOut.add(i, empty);
			}


			ArrayList<Integer> distincts = new ArrayList<Integer>();

			for(int i = 0; i < song.size(); i++){
				if(!clientOut.get(client.get(i)-1).contains(song.get(i)))clientOut.get(client.get(i)-1).add(song.get(i));
			}
			//System.out.println("done 2");
			//System.out.println(clientOut.get(0).size());

			for(int i = 0; i < clientOut.size(); i++){
				if(clientOut.get(i).size()>0)distincts.add(clientOut.get(i).size());
			}

			//System.out.println(distincts);
			pi.result(distincts);
		}
		catch(IOException e){
			System.out.println("Problem with file input.");
		}
	}

	public void result(ArrayList<Integer> distinct){
		try{
			
			FileWriter writer1 = new FileWriter("test.csv");
			writer1.write("DISTINCT_PLAY_COUNT,CLIENT_COUNT\n");
			//System.out.println("done");
			//System.out.println(distinct.size());
			while(distinct.size()>0){
				int result = 1;
				for(int i = 1; i<distinct.size(); i++){
					if(distinct.get(i).equals(distinct.get(0))){
						result++;
						distinct.remove(i);
						i--;
					}
				}

				writer1.write(distinct.get(0) + "," + result + "\n");
				distinct.remove(0);
			}

				writer1.close();
				System.out.println("DONE");
		}
		catch(IOException e){
			System.out.println("Problem with file inputssss.");
		}

	}
}