import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class EfemeridesParser {
	
	private static ArrayList<Efemeride> efemerides;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		efemerides = new ArrayList<Efemeride>();
		
		System.out.println("------------");
		for (int m=1; m<=12; m++) {
			System.out.printf("*");
			for (int d=1; d<=31; d++) {
				
				if (m==2 && d==30) {
					break;
				}
				if (m==4 && d==31) {
					break;
				}
				if (m==6 && d==31) {
					break;
				}
				if (m==9 && d==31) {
					break;
				}
				if (m==11 && d==31) {
					break;
				}
				
				/*if (m==1) { System.out.printf("Enero"); }
				if (m==2) { System.out.printf("Febrero"); }
				if (m==3) { System.out.printf("Marzo"); }
				if (m==4) { System.out.printf("Abril"); }
				if (m==5) { System.out.printf("Mayo"); }
				if (m==6) { System.out.printf("Junio"); }
				if (m==7) { System.out.printf("Julio"); }
				if (m==8) { System.out.printf("Agosto"); }
				if (m==9) { System.out.printf("Septiembre"); }
				if (m==10) { System.out.printf("Octubre"); }
				if (m==11) { System.out.printf("Noviembre"); }
				if (m==12) { System.out.printf("Diciembre"); }*/

				ArrayList<Efemeride> current = getInfoUrl("http://www.enciclopedia-aragonesa.com/efemerides/default.asp?day=" + d + "&month=" + m, String.valueOf(m), String.valueOf(d));
				
//				for (Efemeride e:efemerides) {
//					System.out.println(e);
//				}			
				
				PrintWriter pw = new PrintWriter("data/" + m + "/" + d + ".txt", "utf-8");
				for (int i=0; i<current.size()-1; i++) {
					pw.println(current.get(i).defaultToString());
				}
//				for (int i=0; i<current.size()-1; i++) {
//					System.out.println(current.get(i).defaultToString());
//				}
				pw.printf(current.get(current.size()-1).defaultToString());
				pw.close();
			}
			
//			System.out.println("==================================================================================================================");
		}
		
		
//		for (Efemeride e:efemerides) {
//			System.out.println(e);
//		}
		
	}
	
	
	//http://www.enciclopedia-aragonesa.com/efemerides/default.asp?day=4&month=1
	private static ArrayList<Efemeride> getInfoUrl(String url, String month, String day) {
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		
		Scanner s = new Scanner(content);
		String info = "";
		for (int i=0; i<154; i++) {
			s.nextLine();
		}
		info = s.nextLine();
		s.close();
		
		return parseHtml(info, month, day);
	}
	
	private static ArrayList<Efemeride> parseHtml(String info, String month, String day) {
		
		ArrayList<Efemeride> current = new ArrayList<Efemeride>();
		
		info = info.replaceAll("\\<[^>]*>", "");
		info = info.replaceAll("\\s+\\.", ".");
		info = info.replaceAll("\\s+", " ");
		info = info.replace("En ", "\nEn ");
		info = info.replace(" , ", ", ");
		info = info.replace(" ;", ";");
		info = info.replace(" :", ":");
		info = info.replace(" )", ")");
		info = info.replace("( ", "(");
		info = info.substring(1);
		
//		System.out.println(info);

		String[] events = info.split("\n");
		for (String line:events) {
			String[] terms = line.split(", ");
			
			String year = "";
			Scanner s = new Scanner(terms[0]);
			while (!s.hasNextInt()) {
				s.next();
			}
			year = s.next();
			s.close();
//			System.out.printf(year + " - ");
			
			String text = Character.toUpperCase(terms[1].charAt(0)) + terms[1].substring(1);
			for (int i=2; i<terms.length; i++) {
				text += ", " + terms[i];
			}
			
//			System.out.println(text);
			text = text.replaceAll("\\s+$", "");

			if (!text.substring(text.length()-1).equals(".")) {
				text += ".";
			}
			
			efemerides.add(new Efemeride(month, day, year, text));
			current.add(new Efemeride(month, day, year, text));
		}
		
		return current;
	}
	
}
