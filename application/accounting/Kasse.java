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
	
	public int init(File f){
		BufferedReader sc = null;
		try {
			sc = new BufferedReader(new FileReader(m_source));
		} catch (FileNotFoundException e) {
			System.out.println("Es konnte nicht Eingezahlt werden");
			e.printStackTrace();
			return 1;
		}
		int numerrors = 0;
		try{
			String linebuffer = sc.readLine();
			while(linebuffer != null){
				if(!linebuffer.equals("") && linebuffer.charAt(0) != '#'){
					String data[] = linebuffer.split(";");
					if(data.length >= 4){
						System.out.println("neuen Member angelegt");
						Mitglied m = new Mitglied(data[1], data[2], data[0]);
						for(int i = 3; i < data.length; i++){
							String subdata[] = data[i].split(",");
							if(subdata.length <= 1) {
								numerrors++;
								continue;
							}
							m.add(Integer.parseInt(subdata[0]), Double.parseDouble(subdata[1]));
						}
						l_member.add(m);
					}
					else {
						numerrors++;
					}
				}
				linebuffer = sc.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
			numerrors++;
		}
		return numerrors;
	}
	
	public void putMoney(String MN, int day, double amount) {
		for(int i = 0; i < l_member.size(); i++){
			if(l_member.get(i).getMN().equals(MN)) {
				l_member.get(i).add(day, amount);
				return;
			}
		}
	}
	
	public boolean save(){
		try {
			FileWriter fw = new FileWriter(m_source, false);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < l_member.size(); i++) {
				Mitglied m = l_member.get(i);
				System.out.println("writing user nr."+i);
				bw.write(m.getMN()+";"+m.getNN()+";"+m.getVN());
				for(int j = 0; j < m.bNum(); j++) {
					bw.write(";"+m.getDay(j)+","+m.getAmount(j));
				}
				bw.newLine();
			}
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String argv[]){
		Kasse a = new Kasse(new File("data/mitglieder.xml"));
		a.putMoney("013579", 20, 40); 
		
		a.save();
	}
}





