package application.accounting;

public class Mitglied {
	private String m_Nachname;
	private String m_Vorname;
	private String m_Mitgliedsnummer;
	private int m_Beitrag;
	
	public Mitglied(String Nachname, String Vorname, String Mitgliedsnummer, int Beitrag) {
		m_Nachname = Nachname;
		m_Vorname = Vorname;
		m_Mitgliedsnummer = Mitgliedsnummer;
	}
	
	public void changeVN(String Vorname) {
		m_Vorname = Vorname;
	}
	
	public void changeNN(String Nachname) {
		m_Nachname = Nachname;
	}
	
	public void setBeitrag(int Beitrag) {
		m_Beitrag = Beitrag;
	}
	
	
}
