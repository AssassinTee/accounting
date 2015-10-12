package application.accounting;
import java.io.*;
import java.util.*;
import java.lang.StringBuffer;

public class Kasse {
	private File m_source;
	public Kasse(File f) {
		m_source = f;
	}
	
	public boolean Einzahlen(String mitgliedsnummer, int betrag, int tag){
		BufferedReader sc = null;
		try {
			sc = new BufferedReader(new FileReader(m_source));
		} catch (FileNotFoundException e) {
			System.out.println("Es konnte nicht Eingezahlt werden");
			e.printStackTrace();
			return false;
		}
		System.out.println("Ich war hier!");
		String linebuffer = " ";
		StringBuffer sb = new StringBuffer("");
		while(true) {
			try{
				linebuffer = sc.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			if(linebuffer == null){
				break;
			}
			sb.append(linebuffer+"\n");
			System.out.println(linebuffer);
			if(linebuffer.split(";")[0].contains(mitgliedsnummer)){
				System.out.println("Ich war hier!5");
				linebuffer += ";"+tag+","+betrag;
				sb.append(linebuffer);
				while(true){
					try{
						linebuffer = sc.readLine();
					} catch(IOException e){
						e.printStackTrace();
					}
					if(linebuffer == null){
						break;
					}
					sb.append(linebuffer+"\n");
				}
				System.out.println("7:"+sb.toString());
				try {
					System.out.println(" ");
					FileWriter fw = new FileWriter(m_source);
					fw.write(sb.toString());
				} catch(IOException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
	public static void main(String argv[]){
		Kasse a = new Kasse(new File("data/mitglieder.xml"));
		a.Einzahlen("013579", 20, 40); 
	}
}





