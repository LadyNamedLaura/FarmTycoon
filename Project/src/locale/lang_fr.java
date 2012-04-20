package locale;

import java.util.ListResourceBundle;

public class lang_fr extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = {
			{ "SAVE", "sauvegarder" },
			{ "FARM", "ferme" },
			{ "FARMER", "agriculteur" },
			{ "WELCOMEMSG", "Bienvenue à Farm Tycoon" },
			{ "newGame", "nouveau jeu"},
			{ "loadGame", "chargement du jeu "},
			{ "LoadFailNoSave", "il n'y a pas de jeu disponible"},
			{ "moneystring", "€ %d"},
			{ "SAVEFOUND", "Un jeu précédemment sauvegardé a été trouvé." },
			{ "ASKLOADGAME", "Voulez vous charger ce jeu? (yes/no)" },
			{ "yes", new String[] { "o", "oui" } },
			{ "no", new String[] { "n", "non" } },
			{ "PATATO" , "pomme de terre"},
			{ "CARROT", "carotte"},
			{ "WHEAT", "le blé"},		
			{ "CORN","le blé"},		
			{ "GRAPE","raisin"},		
			{ "LETTUCE","laitue"},		
			{ "TOMATO","tomate"},		
			{ "STRAWBERRY","fraise"},	
			{ "RASPBERRY","framboise"},	
			{ "COCOA","les fèves de cacao"},		
			{ "SOY","le soja"},
			{ "PLOW","labourer"},
			{ "BUILDBARN","bâtir une grange"},
			{ "COWS","des vaches"},
			{ "CHICKENS","des poules"},
			{ "NONE","case vide"},
			{ "PLOWING","en train de labourer"},
			{ "PLOWED","plowed"},
			{ "CROP","gewas"},
			{ "BUILDING","bezig met bouwen"},
			{ "MARKET","markt"}};
}
