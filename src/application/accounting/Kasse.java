package application.accounting;
import java.io.*;
import java.util.ArrayList;
import java.lang.StringBuffer;

import java.util.logging.*;

public class Kasse {

	private static final Logger logger = Logger.getLogger(Kasse.class.getName());
	
	
	private File m_source;
	public Kasse(File f) {
		m_source = f;
	}
	
	public boolean Einzahlen(String mitgliedsnummer, int betrag, int tag){
		BufferedReader sc = null;
		try {
			sc = new BufferedReader(new FileReader(m_source));
			logger.info("lese von Datei: " + m_source);
			
		} catch (FileNotFoundException e) {
			System.out.println("Es konnte nicht Eingezahlt werden");
			e.printStackTrace();
			return false;
		}
		String linebuffer = " ";
		StringBuffer sb = new StringBuffer("");
		while(true) {
			try{
				linebuffer = sc.readLine();
				logger.info("gelesene Zeile: "+ linebuffer);
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
		
		if(argv.length == 0) {
			ArgParser ag = new ArgParser(argv);
				try {
				boolean append = true;
				FileHandler fh = new FileHandler(ag.getLogFilename(), append);
				fh.setFormatter(new Formatter() {
					public String format(LogRecord rec) {
						StringBuffer buf = new StringBuffer(1000);
						buf.append(new java.util.Date()).append('_');
						buf.append(rec.getLevel()).append('_');
						buf.append(formatMessage(rec)).append('\n');
						return buf.toString();
					}
				});
				logger.addHandler(fh);
			} catch (IOException e) {
				logger.severe("Datei kann nicht geschrieben werden");
				e.printStackTrace();
			}
		}
		logger.info("started logging");
		Kasse a = new Kasse(new File("data/mitglieder.xml"));
		a.Einzahlen("013579", 20, 40); 
	}
}





