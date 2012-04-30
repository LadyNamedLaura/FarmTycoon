package locale;

import java.util.ListResourceBundle;

public class lang_en extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = {
			{ "SAVE", "save" },
			{ "FARM", "farm" },
			{ "FARMER", "farmer" },
			{ "WELCOMEMSG", "Welcome to Farm Tycoon" },
			{ "newGame", "New game"},
			{ "loadGame", "Load game"},
			{ "LoadFailNoSave", "Failed to load game, no savegame found"},
			{ "moneystring", "€ %d"},
			{ "SAVEFOUND", "a previously saved game was found." },
			{ "ASKLOADGAME", "do you want to load this game? (yes/no)" },
			{ "yes", new String[] { "y", "yes" } },
			{ "no", new String[] { "n", "no" } },
			{ "POTATO" , "patato"},
			{ "CARROT", "carrot"},
			{ "WHEAT", "wheat"},		
			{ "CORN","corn"},		
			{ "GRAPE","grape"},		
			{ "LETTUCE","lettuce"},		
			{ "TOMATO","tomato"},		
			{ "STRAWBERRY","strawberry"},	
			{ "RASPBERRY","raspberry"},	
			{ "COCOA","cocoa"},		
			{ "SOY","soy"},
			{ "PLOW","plow field"},
			{ "BUILDBARN","build barn"},
			{ "COWS","cows"},
			{ "CHICKENS","chickens"},
			{ "None","empty field"},
			{ "Plowing","plowing"},
			{ "Plowed","plowed"},
			{ "Crop","crop"},
			{ "Building","building"},
			{ "Market","market"},
			{ "Crop_POTATO_GROWING","growing potatoes"},
			{ "Crop_CARROT_GROWING","growing carrots"},
			{ "Crop_WHEAT_GROWING","growing wheat"},
			{ "Crop_CORN_GROWING","growing corn"},
			{ "Crop_GRAPE_GROWING","growing grapes"},
			{ "Crop_LETTUCE_GROWING","growing lettuce"},
			{ "Crop_TOMATO_GROWING","growing tomatoes"},
			{ "Crop_STRAWBERRY_GROWING","growing strawberries"},
			{ "Crop_RASPBERRY_GROWING","growing raspberries"},
			{ "Crop_COCAO_GROWING","growing cacao"},
			{ "Crop_SOY_GROWING","growing soy"},	
			{ "CLEAR", "clear"}};
			
			
}
