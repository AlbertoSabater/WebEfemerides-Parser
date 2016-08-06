import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Statistics {
	
	private static ArrayList<Efemeride> events;
	
	
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		String path = "data";
		events = new ArrayList<Efemeride>();
		
		for (int m=1; m<=12; m++) {
			
			File f = new File(path + "/" + m);
			File[] days = f.listFiles();
			
			for (File d:days) {
				Scanner s = new Scanner(d);
				
				System.out.println(m + "  " + d.getName());
				String day = d.getName().split("\\.")[0];

				while (s.hasNextLine()) {
//					int year = s.nextInt();
//					String text = s.nextLine().substring(3);
					String line = s.nextLine();
					int year = Integer.parseInt(line.split(". ")[0]);
					String text = line.substring(String.valueOf(year).length()+2);
					System.out.println(text);
					
					events.add(new Efemeride(String.valueOf(m), day, String.valueOf(year), text));
				}
				
			}
			
		}
		
		System.out.println("Num events: " + events.size());
		longestText();
		textWithMaxCharacters(140);
		contains(", ");
		matchs("[^A-Z]");
		printLong(140);
		
	}
	
	
	private static void contains(String txt) {
		int count = 0;
		for (Efemeride e:events) {
			if (e.getText().contains(txt)) {
				count ++;
//				System.out.println(e.getText());
			}
		}
		System.out.println("Num matchings with '" + txt + "': " + count);
	}
	
	private static void matchs(String pattern) {
		int count = 0;
		for (Efemeride e:events) {
			if (e.getText().matches(pattern)) {
				count ++;
//				System.out.println(e.getMonth() + "-" + e.getDay() + "   " + e.getText());
			}
		}
		System.out.println("Num matchings with pattern '" + pattern + "': " + count);
	}
	
	private static void longestText() {
		int maxLength = 0;
		String text = "";
		
		for (Efemeride e:events) {
			if (e.getText().length() > maxLength) {
				maxLength = e.getText().length();
				text = e.getText();
			}
		}
		
		System.out.println("Langitud máxima = " + maxLength);
//		System.out.println(text);
	}
	
	private static void textWithMaxCharacters(int length) {
		int count = 0;
		
		for (Efemeride e:events) {
			if (e.defaultToString().length() > length) {
				count ++;
				System.out.println(e.getMonth() + "-" + e.getDay() + "   " + e.defaultToString().length() + ": " + e.defaultToString());
			}
		}
		
		System.out.println("Textos mayores de " + length + ": " + count);
	}
	
	
	private static void printLong(int length) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter("largos.txt", "utf-8");
		for (Efemeride e:events) {
			if (e.defaultToString().length() > length) {
				pw.println(e.defaultToString());
			}
		}
		pw.close();
		
		System.out.println("Textos guardados");
	}

}
