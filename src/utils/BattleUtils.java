package utils;

import app.Engine;
import app.GUI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import models.battles.attacks.Attack;
import models.battles.battles.Battle;
import models.player.Player;
import models.wanimals.Wanimal;

public class BattleUtils {
  /**
   * This method starts a battle with the given enemy.
   *
   * @param enemy - the enemy with which to start the new battle
   */
  public static void createBattle(Wanimal enemy) {
    Player player = Engine.getPlayer(); // get the player of the current game

    Engine.setCurrentBattle(
        new Battle(player, player.getWanimals().get(0),
                   enemy)); // set the current battle to a battle between the
                            // player's first wanimal and the enemy given

    Timer timer = new Timer(); // create a new timer object. This will be used
    // to check any condition that must be repeatedly
    // check during the battle

    // every two seconds, execute the run method defined below. This is where
    // any condition that needs to repeatedly be checked for the duration of the
    // battle will be checked.
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Battle currentBattle = Engine.getCurrentBattle();
        GUI gui = Engine.getGui(); // get the current GUI

        if (!(currentBattle != null &&
              currentBattle
                  .isRunning())) { // if there isn't a battle or if it's not
          gui.setBattleButtonsEnabled(
              false); // immediately disable all battle buttons
          // running
          timer.cancel(); // cancel the current timer immediately
        }

        if (currentBattle.getCurrentTurn() ==
            0) { // if the battle is running and it is the enemy's turn
          currentBattle.setCurrentTurn(
              1); // revert to the player's turn as fast as possible so nothing
                  // runs more than it has to

          // wait one second and execute the enemy attack. The wait will give
          // time for adding to the battle logs
          Utils.delayRun(1000, new Runnable() {
            @Override
            public void run() {
              BattleUtils.enemyAttack(currentBattle);
              Engine.getGui().refreshBattleGUI(
                  currentBattle); // refresh the battle GUI
            }
          });
        }

        if (currentBattle.getPlayerWanimal().getCurrentHitpoints() == 0 ||
            currentBattle.getEnemy().getCurrentHitpoints() ==
                0) { // if at any point the health of the player's wanimal (or
                     // the enemy) is depleted
          Engine.getCurrentBattle().setIsRunning(
              false); // end the battle immediately

          gui.setBattleButtonsEnabled(
              false); // set all buttons on the battle screen to disabled if
                      // they are not already disabled

          // wait two seconds
          Utils.delayRun(2000, new Runnable() {
            @Override
            public void run() {
              // go back to the move select screen
              gui.getMasterLayout().show(gui.getContentPane(),
                                         "panel_moveSelect");
            }
          });
        }
      }
    }, new Date(), 1000);
  }

  /**
   * This method takes a battle and makes the enemy in that battle attack the
   * player's current active wanimal in that battle
   *
   * @param battle - the battle information to use to execute the attack
   */
  public static void enemyAttack(Battle battle) {
    Wanimal enemy = battle.getEnemy(); // get and store the enemy in a variable
    // for easier access
    Attack attackToExecute =
        enemy.getFirstAttack(); // this variable stores the attack to execute.
    // Set the attack to execute to the first attack
    // of the enemy by default (this may change
    // later)
    int[] percentageChancesForSecondAttack = {50, 65, 80};

    if (enemy.getLevel() >=
        5) { // if the enemy has a level greater than or equal to 5 (meaning
      // they have both attacks avaialable to them. Note that this part
      // part will never execute for enemy under level 5 because they do
      // not have their seocnd attack available
      double randomNum = Math.random(); // get a random number from 0.0 to 1.0

      if (randomNum <
          percentageChancesForSecondAttack[battle.getPlayer().getRealm() - 1] /
              100.0) { // if the random number is less than the second attack
        // percentage chance for this realm
        attackToExecute =
            enemy.getSecondAttack(); // set the attack to execute to the enemy's
        // second attack instead of the default
        // first attack
      }
    }

    attackToExecute.execute(
        enemy,
        Engine.getCurrentBattle()
            .getPlayerWanimal()); // execute the attack on the player's wanimal
  }

  /**
   * This method ends the battle currently in progress
   */
  public static void endCurrentBattle() {
    Engine.setCurrentBattle(null); // set the current battle to null (discarding
    // of the info about the current battle
  }
}
