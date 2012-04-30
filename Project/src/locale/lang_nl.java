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
			{ "POTATO" , "aardappel"},
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
			{ "None","Leeg veld"},
			{ "Plowing","bezig met ploegen"},
			{ "Plowed","klaar met ploegen"},
			{ "Crop","gewas"},
			{ "Building","bezig met bouwen"},
			{ "Market","markt"},
			{ "Crop_POTATO_GROWING","aardappelen aan het groeien"},
			{ "Crop_CARROT_GROWING","wortelen aan het groeien"},
			{ "Crop_WHEAT_GROWING","tarwe aan het groeien"},
			{ "Crop_CORN_GROWING","mais aan het groeien"},
			{ "Crop_GRAPE_GROWING","druiven aan het groeien"},
			{ "Crop_LETTUCE_GROWING","sla aan het groeien"},
			{ "Crop_TOMATO_GROWING","tomaten aan het groeien"},
			{ "Crop_STRAWBERRY_GROWING","aardbeien aan het groeien"},
			{ "Crop_RASPBERRY_GROWING","frambozen aan het groeien"},
			{ "Crop_COCAO_GROWING","cacaobonen aan het groeien"},
			{ "Crop_SOY_GROWING","sojabonen aan het groeien"},	
			{ "Crop_POTATO_READY","aardappelen zijn klaar"},
			{ "Crop_CARROT_READY","wortelen zijn klaar"},
			{ "Crop_WHEAT_READY","tarwe is klaar"},
			{ "Crop_CORN_READY","mais is klaar"},
			{ "Crop_GRAPE_READY","druiven zijn klaar"},
			{ "Crop_LETTUCE_READY","sla is klaar"},
			{ "Crop_TOMATO_READY","tomaten zijn klaar"},
			{ "Crop_STRAWBERRY_READY","aardbeien zijn klaar"},
			{ "Crop_RASPBERRY_READY","frambozen zijn klaar"},
			{ "Crop_COCAO_READY","cacaobonen zijn klaar"},
			{ "Crop_SOY_READY","sojabonen zijn klaar"},
			{ "HARVEST", "oogsten"},
			{ "CLEAR", "vrijmaken"}};
}