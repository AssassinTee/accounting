package application.accounting;
import java.util.*;

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
	
	public void setDay(int day) {
		m_Tag = day;
	}
	
	public double getAmount() {
		return m_Betrag;
	}
	
	public void setAmount(double amount) {
		m_Betrag = amount;
	}
}
