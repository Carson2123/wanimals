package models.wanimals.wanimals.water;

import models.wanimals.types.WaterWanimal;

public class Aqua extends WaterWanimal {
  /**
   * Constructor method: this overload of this method takes an argument for
   * every possible attribute and uses them to create a new wanimal
   *
   * @param name - the name with which to create the new wanimal
   * @param type - the type with which to create the new wanimal
   * @param level - the level with which to create the new wanimal
   * @param maxHitpoints - the max hitpoints with which to create the new
   *     wanimal
   * @param currentHitpoints - the current hitpoints with which to create the
   *     new wanimal
   * @param baseAttack - the base attack with which to create the new wanimal
   * @param maxArmor - the max armor with which to create the new wanimal
   * @param currentArmor - the current armor with which to create the new
   *     wanimal
   */
  public Aqua(String name, String type, int level, int maxHitpoints,
              int currentHitpoints, int baseAttack, int maxArmor,
              int currentArmor) {
    super(name, type, level, maxHitpoints, currentHitpoints, baseAttack,
          maxArmor,
          currentArmor); // call the superconstructor with the given values
  }

  /**
   * Constructor method: this overload of this method takes no arguments and
   * creates a new wanimal with default values with each attribute
   */
  public Aqua() {
    this.name = "Aqua";
    this.type = "water";
    this.level = 1;
    this.maxHitpoints = 160;
    this.currentHitpoints = 160;
    this.baseAttack = 9;
    this.maxArmor = 32;
    this.currentArmor = 32;
  }

  public void flashFlood() {}
}
