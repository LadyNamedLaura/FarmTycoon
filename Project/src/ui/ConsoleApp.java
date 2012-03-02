package ui;

/**
 * @author Rigès De Witte, Simon Peeters,Barny Pieters,Laurens Van Damme 
 *
 */
public class ConsoleApp {
	private domain.Controller domain;
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	public ConsoleApp() {
		domain = new domain.Controller();
		welcome();
		if(domain.saveExists()){
			if (askLoad())
				domain.loadGame();
			else
				domain.newGame();
		} else {
			domain.newGame();
		}
			
	}
	public domain.Controller getController() {
		return this.domain;
	}
	
	public void welcome() {
		System.out.println(Translator.getString("WELCOMEMSG"));
	}
	
	public boolean askLoad() {
		String input;
		System.out.println(Translator.getString("SAVEFOUND"));
		System.out.println(Translator.getString("ASKLOADGAME"));
		input = scanner.next();
		if (java.util.Arrays.asList(Translator.getStringArray("yes")).contains(input))
			return true;
		else if(java.util.Arrays.asList(Translator.getStringArray("no")).contains(input))
			return false;
		else
			return askLoad();
	}

}
