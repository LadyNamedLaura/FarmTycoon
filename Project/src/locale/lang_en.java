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
			{ "PATATO" , "patato"},
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
			{ "NONE","empty field"},
			{ "PLOWING","plowing"},
			{ "PLOWED","plowed"},
			{ "CROP","crop"},
			{ "BUILDING","building"},
			{ "MARKET","market"}};
			
			
}
