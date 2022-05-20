package app;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import models.battles.battles.Battle;
import models.player.Player;

public class Engine {
  private static GUI gui;
  private static Player player;
  private static Battle currentBattle;

  private static void initialize(GUI currentGUI) { gui = currentGUI; }

  public static void run(GUI currentGUI) { initialize(currentGUI); }

  // getters and setters

  public static GUI getGui() { return gui; }

  public static Player getPlayer() { return player; }

  public static void setPlayer(Player player) { Engine.player = player; }

  public static Battle getCurrentBattle() { return currentBattle; }

  public static void setCurrentBattle(Battle currentBattle) {
    Engine.currentBattle = currentBattle;
  }
  
  public static void saveWriteToFile () {
	  
	  try {
		  
		  //Creating the file to write to
		  final File saveDataFile = new File("saveData.txt");
		  
		  //Deleting old file as it is being overwritten
		  saveDataFile.delete();
		  
		  //Select parent file
		  saveDataFile.getParentFile().mkdirs();
		  
		  //Creating the new file
	      saveDataFile.createNewFile();
	      
	      //Creating the file writer
	      final PrintWriter saveDataWriter = new PrintWriter(new FileWriter(saveDataFile, true));
	      
	      //Creating a string that displays all the information on the player in following format
	      	//Name, realm number, potion number, armor plate number, level, current XP, max XP
	      String studentContent = String.format("%s;%d;%d;%d;%d;%d;%d", Engine.getPlayer().getName(), 
	    		  Engine.getPlayer().getRealm(), Engine.getPlayer().getNumPotions(), Engine.getPlayer().getNumArmorPlates(), 
	    		  Engine.getPlayer().getLevel(), Engine.getPlayer().getCurrentXP(), Engine.getPlayer().getMaxXP());
	      
	      //Writing string to file
	      saveDataWriter.println(studentContent);
	      
	      //Finding how many wanimals the player has
	      int length = Engine.getPlayer().getWanimals().size();
	      
	      //Writing the amount to the file
	      saveDataWriter.println(String.valueOf(length));
	      
	      //For loop that runs as many times are there are wanimals in player inventory
	      for (int x = 0; x < length; x++) {
	    	  
	    	  //String of all info of the wanimal in following format
	    	  	//Name, type, level, current XP, max XP, current HP, max HP, current armor, max armor, first attack name, first attack type,
	    	  	//second attack name, second attack type
	    	  String wanimalContent = String.format("%s;%d;%d;%d;%d;%d;%d;%d;%d;%s;%d;%s;%d", Engine.getPlayer().getWanimals().get(x).getName(),
	    			  Engine.getPlayer().getWanimals().get(x).getType(), Engine.getPlayer().getWanimals().get(x).getLevel(),
	    			  Engine.getPlayer().getWanimals().get(x).getCurrentXP(), Engine.getPlayer().getWanimals().get(x).getMaxXP(),
	    			  Engine.getPlayer().getWanimals().get(x).getCurrentHitpoints(), Engine.getPlayer().getWanimals().get(x).getMaxHitpoints(),
	    			  Engine.getPlayer().getWanimals().get(x).getCurrentArmor(), Engine.getPlayer().getWanimals().get(x).getMaxArmor(),
	    			  Engine.getPlayer().getWanimals().get(x).getFirstAttack().getName(), Engine.getPlayer().getWanimals().get(x).getFirstAttack().getType(),
	    			  Engine.getPlayer().getWanimals().get(x).getSecondAttack().getName(), Engine.getPlayer().getWanimals().get(x).getSecondAttack().getType());
	    	  
	    	  //Writing this wanimals info to file
	    	  saveDataWriter.println(wanimalContent);
	    	  
	      }
	      
	      //Closing the writer
	      saveDataWriter.close();
	      
	  }
	  
	  //Catch for any exceptions
	  catch ( final Exception e) { e.printStackTrace(); }
  }
}
