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
			{ "newGame", "Nouveau jeu"},
			{ "loadGame", "Charger"},
			{ "LoadFailNoSave", "il n'y a pas de jeu disponible"},
			{ "moneystring", "€ %d"},
			{ "SAVEFOUND", "Un jeu précédemment sauvegardé a été trouvé." },
			{ "ASKLOADGAME", "Voulez vous charger ce jeu? (yes/no)" },
			{ "yes", new String[] { "o", "oui" } },
			{ "no", new String[] { "n", "non" } },
			{ "POTATO" , "pomme de terre"},
			{ "CARROT", "carotte"},
			{ "WHEAT", "le blé"},		
			{ "CORN"," maïs"},		
			{ "GRAPE","raisin"},		
			{ "LETTUCE","laitue"},		
			{ "TOMATO","tomate"},		
			{ "STRAWBERRY","fraise"},	
			{ "RASPBERRY","framboise"},	
			{ "COCOA","les fèves de cacao"},		
			{ "SOY","les fève de soja"},
			{ "PLOW","labourer"},
			{ "BUILDBARN","bâtir une grange"},
			{ "COWS","des vaches"},
			{ "CHICKENS","des poules"},
			{ "None","case vide"},
			{ "Plowing","en train de labourer"},
			{ "Plowed","est labouré"},
			{ "Crop","culture"},
			{ "Building","en train de bâtir"},
			{ "Market","marché"},
			{ "Crop_POTATO_GROWING","cultiver des pommes de terre"},
			{ "Crop_CARROT_GROWING","cultiver des carottes"},
			{ "Crop_WHEAT_GROWING","cultiver des blés"},
			{ "Crop_CORN_GROWING","cultiver des maïs"},
			{ "Crop_GRAPE_GROWING","cultiver des raisins"},
			{ "Crop_LETTUCE_GROWING","cultiver des laitues"},
			{ "Crop_TOMATO_GROWING","cultiver des tomates"},
			{ "Crop_STRAWBERRY_GROWING","cultiver des fraises"},
			{ "Crop_RASPBERRY_GROWING","cultiver des framboises"},
			{ "Crop_COCAO_GROWING","cultiver des fèves de cacao"},
			{ "Crop_SOY_GROWING","cultiver des fèves de soja"},	
			{ "CLEAR", "dégager"}};
}
