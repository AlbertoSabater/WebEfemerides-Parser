
public class Efemeride {

	private String month;
	private String day;
	private String year;
	private String text;

	public Efemeride(String month, String day, String year, String text) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.text = text;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		int m = Integer.parseInt(month);
		String str = "";
		
		if (m==1) { str = "Enero"; }
		if (m==2) { str = "Febrero"; }
		if (m==3) { str = "Marzo"; }
		if (m==4) { str = "Abril"; }
		if (m==5) { str = "Mayo"; }
		if (m==6) { str = "Junio"; }
		if (m==7) { str = "Julio"; }
		if (m==8) { str = "Agosto"; }
		if (m==9) { str = "Septiembre"; }
		if (m==10) { str = "Octubre"; }
		if (m==11) { str = "Noviembre"; }
		if (m==12) { str = "Diciembre"; }
		
		str += "-" + day + "-" + month + "   " + text;
		
		return str;
	}
	
	
	public String defaultToString() {
		return year + ". " + text;
	}

}
