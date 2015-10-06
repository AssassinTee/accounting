package application.accounting;
import java.io.*;
import java.util.*;
import java.lang.StringBuffer;

public class Kasse {
	private File m_source;
	private List<Mitglied> l_member;
	
	public Kasse(File f) {
		m_source = f;
		l_member = new ArrayList<Mitglied>();
		System.out.println("Die Datei "+m_source+" wurde mit "+init(m_source)+" Fehlern eingelesen");
	}
	
	public boolean add(Mitglied m) {
		return l_member.add(m);
	}
	
	public int init(File f) throws FileNotFoundException {
		BufferedReader sc = null;
		try {
			sc = new BufferedReader(new FileReader(m_source));
		} catch (FileNotFoundException e) {
			System.out.println("Es konnte nicht Eingezahlt werden");
			e.printStackTrace();
			return false;
		}
		int numerrors = 0;
		String linebuffer = sc.readLine();
		while(linebuffer != null){
			if(linebuffer.charAt(0) != '#'){
				String data[] = linebuffer.split(";");
				if(data.length >= 4){
					Mitglied m = new Mitglied(data[1], data[2], data[0])
					for(int i = 3; i < data.length; i++){
						String subdata[] = data[i].split(",");
						if(subdata.length <= 1) {
							numerrors++;
							continue;
						}
						m.add(subdata[0], subdata[1]);
					}
				}
				else {
					numerrors++;
				}
			}
			linebuffer = sc.readLine();
		}
		return numerrors
	}
	
	public void putMoney(String MN, int day, double amount) {
		for(int i = 0; i < l_member.length; i++){
			if(l_member.get(i).getMN().equals(MN)) {
				l_member.get(i).add(day, amount);
				return;
			}
		}
	}
	
	public boolean save(File f, boolean reset) throws FileNotFoundException {
		try {
			FileWriter fw = new FileWriter(f, false);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < l_member.size(); i++) {
				Mitglied m = l_member.get(i);
				bw.write(m.getMN()+";"+m.getNN()+";"+m.getVN())
				for(int j = 0; j < m.size(); j++) {
					bw.write(";"+m.getDay(j)+","+m.getAmount(j));
				}
				bw.newLine();
			}
			bw.close();
			return true;
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}
	
	public static void main(String argv[]){
		Kasse a = new Kasse(new File("data/mitglieder.xml"));
		a.Einzahlen("013579", 20, 40); 
	}
}





