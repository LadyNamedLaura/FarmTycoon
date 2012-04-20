package locale;

import java.util.ListResourceBundle;

public class lang_nl extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = {
			{ "SAVE", "opslaan" },
			{ "FARM", "boerderij" },
			{ "FARMER", "landbouwer" },
			{ "WELCOMEMSG", "Welkom bij Farm Tycoon" },
			{ "newGame", "Nieuw spel"},
			{ "loadGame", "Laden"},
			{ "LoadFailNoSave", "Laden is niet mogelijk, geen spel gevonden."},
			{ "moneystring", "€ %d"},
			{ "SAVEFOUND", "Een vorig spel is gevonden." },
			{ "ASKLOADGAME", "wil je dit spel laden? (ja/nee)" },
			{ "yes", new String[] { "j", "ja" } },
			{ "no", new String[] { "n", "nee" } },
			{ "PATATO" , "aardappel"},
			{ "CARROT", "wortel"},
			{ "WHEAT", "tarwe"},		
			{ "CORN", "mais"},		
			{ "GRAPE","druif"},		
			{ "LETTUCE","sla"},		
			{ "TOMATO","tomaat"},		
			{ "STRAWBERRY","aardbei"},	
			{ "RASPBERRY","framboos"},	
			{ "COCOA","cacaoboon"},		
			{ "SOY","sojaboon"},
			{ "PLOW","ploeg veld"},
			{ "BUILDBARN","bouw schuur"},
			{ "COWS","koeien"},
			{ "CHICKENS","kippen"},
			{ "NONE","Leeg veld"},
			{ "PLOWING","bezig met ploegen"},
			{ "PLOWED","klaar met ploegen"},
			{ "CROP","gewas"},
			{ "BUILDING","bezig met bouwen"},
			{ "MARKET","markt"}};
}