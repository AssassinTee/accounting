package application.accounting;

public class Beitrag {
	private int m_Tag;
	private double m_Betrag;
	
	public Beitrag(int day, double amount) {
		m_Tag = day;
		m_Betrag = amount;
	}
	
	public int getDay() {
		return m_Tag;
	}
	
	public double getAmount() {
		return m_Betrag;
	}
}
