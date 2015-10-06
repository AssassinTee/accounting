package application.accounting;
import java.util.*;

public class Mitglied {
	private String m_Nachname;
	private String m_Vorname;
	private String m_Mitgliedsnummer;
	private List<Beitrag> l_Beitrag;
	public double zins = 0.005;
	
	public Mitglied(String Nachname, String Vorname, String Mitgliedsnummer) {
		m_Nachname = Nachname;
		m_Vorname = Vorname;
		m_Mitgliedsnummer = Mitgliedsnummer;
		l_Beitrag = new ArrayList<Beitrag>(2);
	}
	
	public boolean add(int day, double amount) {
		return l_Beitrag.add(new Beitrag(day, amount));
	}
	
	public String getNN(){
		return m_Nachname;
	}
	
	public String getVN(){
		return m_Vorname;
	}
	
	public String getMN(){
		return m_Mitgliedsnummer;
	}
	
	public int bNum(){
		return l_Beitrag.size();
	}
	
	public int getDay(int index) {
		return l_Beitrag.get(index).getDay();
	}
	
	public double getAmount(int index) {
		return l_Beitrag.get(index).getAmount();
	}
	
	public void changeVN(String Vorname) {
		m_Vorname = Vorname;
	}
	
	public void changeNN(String Nachname) {
		m_Nachname = Nachname;
	}
	
	public void rechneab(){
		for(int i = 0; i < l_Beitrag.size(); i++) {
			Beitrag l = l_Beitrag.get(i);
			l.setAmount(l.getAmount() * ( zins * (360-l.getDay()) /36000 +1 ));
			l.setDay(0);
		}
	}
}
