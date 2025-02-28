import java.util.Objects;

public class Lab06 {
    public static class Accessories{
        protected int level;
        protected final int maxLevel = 10;
        protected String type;
        protected double status;
        protected String status_name;
        /**
        * @param : none
        * @return : none
        * effects : initialize status of Accessories
        */
        public void UpdateStatus(){
            this.status = 0;
        }
        /**
         * @param : none
         * @return : none
         * effects : initialize type and status_name of Accessories
         */
        public void AssignName(){
            this.type = "type";
            this.status_name = "status_name";
        }
        /**
         * Constructor of Accessories, use to create accessory at required level
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created Accessories and print message to the console
         */
        public Accessories(int level){
            AssignName();
            if(level <= this.maxLevel && level > 0){
                this.level = level;
                UpdateStatus();
                System.out.println("----------Creating Accessory----------");
                System.out.println(this.type + " level: " + this.level + " has been created.");
                System.out.println(this.status_name + " Increase = " + this.status);
                System.out.println("--------------------------------------");
            }else{
                System.out.println("Level must be positive and no more than 10.");
            }
        }
        /**
         * level up Accessories, up to tree levels each time
         * @param : level that must be positive and less than or equal to 3
         * @return : none
         * effects : increase level of Accessories and print message to the console
         */
        public void AccessoriesLevelUP(int level) {
            if(this.level < this.maxLevel && level > 0 && level <= 3){
                this.level += level;
                UpdateStatus();
                System.out.println("----------Leveling Up Accessory----------");
                System.out.println(this.type + " has been level up!.");
                System.out.println(this.type + " level " + this.level);
                System.out.println(this.status_name + " Increase = " + this.status);
                System.out.println("------------------------------------------");
            }else if(level <= 0 || level > 3){
                System.out.println("Level must be positive and can only increase by up to 3 levels at a time.");
            }else {
                System.out.println("Can't be upgrade anymore.");
            }
        }
    }

    public static class Bracelet extends Accessories {
        double maxHPtobeAdd;
        /**
         * update status value when the level have changed
         * @param : none
         * @return : none
         * effects : update state of Bracelet, increase follow its level
         */
        @Override
        public void UpdateStatus() {
            this.status = ((double) (this.level * 10) /100)*50;
            this.maxHPtobeAdd = this.status;
        }
        /**
         * assign the name of status and type of Accessories
         * @param : none
         * @return : none
         * effects : assign state of Accessories (type and status_name)
         */
        @Override
        public void AssignName(){
            this.type = "Bracelet";
            this.status_name = "MaxHP";
        }
        /**
         * Constructor of bracelet, same method as accessories constructor
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created bracelet and print message to the console
         */
        public Bracelet(int level){
            super(level);
        }
    }

    public static class Necklace extends Accessories{
        double maxManatobeAdd;
        /**
         * update status value when the level have changed
         * @param : none
         * @return : none
         * effects : update state of Necklace, increase follow its level
         */
        @Override
        public void UpdateStatus() {
            this.status = ((double) (this.level * 10) /100)*30;
            this.maxManatobeAdd = this.status;
        }
        /**
         * assign the name of status and type of Necklace
         * @param : none
         * @return : none
         * effects : assign type and status_name of Accessories
         */
        @Override
        public void AssignName(){
            this.type = "Necklace";
            this.status_name = "MaxMana";
        }
        /**
         * Constructor of necklace, same method as accessories constructor
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created necklace and print message to the console
         */
        public Necklace(int level) {
            super(level);
        }
    }

    public static class Weapons{
        protected int level = 0;
        protected final int maxLevel = 10;
        protected String type = null;
        protected double status = 0;
        protected String status_name = null;
        protected double mana_usage = 0;
        protected double runSpeed_usage = 0;
        /**
         * @param : none
         * @return : none
         * effects : initialize status of Weapon
         */
        public void UpgradeStatus(){
            this.status = 0;
            this.mana_usage = 0;
            this.runSpeed_usage = 0;
        }
        /**
         * @param : none
         * @return : none
         * effects : initialize type and status_name of Weapon
         */
        public void AssignStatus(){
            this.type = "type";
            this.status_name = "status_name";
        }
        /**
         * Constructor of Weapons, use to create weapon at required level
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created Weapon and print message to the console
         */
        public Weapons(int level){
            AssignStatus();
            if(level <= this.maxLevel && level > 0){
                this.level = level;
                UpgradeStatus();
                System.out.println("----------Creating Weapon----------");
                System.out.println(this.type + " level " + this.level + " has been created.");
                System.out.println(this.status_name + " Increase = " + this.status);
                System.out.println("Mana decrease = " + mana_usage);
                System.out.println("Run speed decrease = " + runSpeed_usage);
                System.out.println("-----------------------------------");
            }else{
                System.out.println("Level must be positive and no more than 10.");
            }
        }
        /**
         * level up Weapons, up to tree levels each time
         * @param : level that must be positive and less than or equal to 3
         * @return : none
         * effects : increase level of Weapons and print message to the console
         */
        public void WeaponsLevelUP(int level) {
            if(this.level < this.maxLevel && level > 0 && level <= 3){
                this.level += level;
                UpgradeStatus();
                System.out.println("----------Leveling Up Weapon-----------");
                System.out.println(this.type + " has been level up!.");
                System.out.println(this.type + " level: " + this.level);
                System.out.println(this.status_name + " Increase = " + this.status);
                System.out.println("Mana decrease = " + mana_usage);
                System.out.println("Run speed decrease = " + runSpeed_usage);
                System.out.println("---------------------------------------");
            }else if(level <= 0 || level > 3){
                System.out.println("Level must be positive and can only increase by up to 3 levels at a time.");
            }else {
                System.out.println("Can't be upgrade anymore.");
            }
        }
    }

    public static class Sword extends Weapons {
        double damagetobeAdd;
        /**
         * update status value of sword when the level have changed
         * @param : none
         * @return : none
         * effects : update state of sword, increase follow its level
         */
        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*70;
            this.damagetobeAdd = this.status;
            this.mana_usage = ((double) (this.level * 10) /100)*50;
            this.runSpeed_usage = ((double) (this.level * 10) /100)*20;
        }
        /**
         * assign the name of status and type of Sword
         * @param : none
         * @return : none
         * effects : assign type and status_name of Sword
         */
        @Override
        public void AssignStatus() {
            this.type = "Sword";
            this.status_name = "Damage";
        }
        /**
         * Constructor of sword, same method as weapons constructor
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created sword and print message to the console
         */
        public Sword(int level){
            super(level);
        }
    }

    public static class Shield extends Weapons{
        double defensetobeAdd;
        /**
         * update status value of shield when the level have changed
         * @param : none
         * @return : none
         * effects : update state of shield, increase follow its level
         */
        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*80;
            this.defensetobeAdd = this.status;
            this.runSpeed_usage = ((double) (this.level * 10) /100)*30;
        }
        /**
         * assign the name of status and type of Shield
         * @param : none
         * @return : none
         * effects : assign type and status_name of Shield
         */
        @Override
        public void AssignStatus() {
            this.type = "Shield";
            this.status_name = "Defense";
        }
        /**
         * Constructor of shield, same method as weapons constructor
         * @param : level that must be positive and less than or equal to 10
         * @return : none
         * effects : initialize level of new created shield and print message to the console
         */
        public Shield(int level){
            super(level);
        }
    }

    public static class Character {
        String name = "";
        String type = "";
        int level = 0;
        int max_level = 60;
        double max_hp = 0;
        double current_hp = 0;
        double max_mana = 0;
        double current_mana = 0;
        double damage = 0;
        double defense = 0;
        double max_speed = 0;
        double current_speed = 0;
        Weapons weapons = null;
        Accessories accessories = null;
        /**
         * initialize state that depend on character's level
         * @param : none
         * @return : none
         * effects : some state change value follow character's level
         */
        public void AssignStatus(){
            this.type = "type";
            this.max_hp = ((double) this.level / this.max_level)*1000;
            this.max_mana = ((double) this.level / this.max_level)*700;
            this.max_speed = ((double) this.level / this.max_level)*200;
            this.damage = ((double) this.level / this.max_level)*100;
            this.defense = ((double) this.level / this.max_level)*50;
        }
        /**
         * initialize state of character
         * @param : none
         * @return : none
         * effects : set some state equal to its max
         */
        public void UpdateStatus(){
            this.current_hp = this.max_hp;
            this.current_mana = this.max_mana;
            this.current_speed = this.max_speed;
        }
        /**
         * use to print current status of character
         * @param : none
         * @return : none
         * effects : print status of character to console
         */
        public void GetStatus() {
            if(this.weapons != null) UpdateWeaponStatus();
            if(this.accessories != null) UpgradeAccessoryStatus();
            System.out.println("----------Current Status----------");
            System.out.println("Name: " + this.name);
            System.out.println("Job: " + this.type);
            System.out.println("Level: " + this.level);
            System.out.println("HP: " + this.current_hp);
            System.out.println("Mana: " + this.current_mana);
            System.out.println("Damage: " + this.damage);
            System.out.println("Defense: " + this.defense);
            System.out.println("MaxRunSpeed: " + this.current_speed);
            if(this.weapons != null) System.out.println(weapons.type + " Level: " + this.weapons.level);
            if(this.accessories != null) System.out.println(accessories.type + " Level: " + this.accessories.level);
            System.out.println("----------------------------------");
        }
        /**
         * Constructor of character, create character whit name
         * @param : name for new create character
         * @return : none
         * effects : assign name to new created character and assign level and every state than print to console
         */
        public Character(String name) {
            this.name = name;
            this.level = 1;
            AssignStatus();
            UpdateStatus();
            System.out.println("----------Character "+this.name+" has been created.----------");
            GetStatus();
        }
        /**
         * level up character, up to tree level each time
         * @param : level that must be positive and less than or equal to 5
         * @return : none
         * effects : increase level and change state that depends on character's level then print to console
         */
        public void CharacterLevelUp(int level) {
            if(this.level < this.max_level && level > 0 && level <= 5){
                this.level += level;
                AssignStatus();
                UpdateStatus();
                System.out.println("----------Leveling Up Character-----------");
                System.out.println(this.name + " has been level up!.");
                GetStatus();
                System.out.println("---------------------------------------");
            }else if(level <= 0 || level > 5){
                System.out.println("Level must be positive and can only increase by up to 5 levels at a time.");
            }else {
                System.out.println("Can't be upgrade anymore.");
            }
        }
        /**
         * use to recheck that weapon state affect character state or not
         * @param : none
         * @return : none
         * effects : character state change follow weapon state
         */
        public void UpdateWeaponStatus(){
            this.current_mana -= this.weapons.mana_usage;
            this.current_speed -= this.weapons.runSpeed_usage;
            if (Objects.equals(this.weapons.type, "Sword")) {
                this.damage += this.weapons.status;
            } else if (Objects.equals(this.weapons.type, "Shield")) {
                this.defense += this.weapons.status;
            }
        }
        /**
         * assign character's weapon
         * @param : weapon that have to use mana and run speed less than current mana and run speed of character
         * @return : none
         * effects : character's weapon have been assigned and character state change follow weapon state them print to console
         */
        public void EquipWeapon(Weapons weapons){
            this.weapons = weapons;
            if(this.weapons != null && this.current_speed - weapons.runSpeed_usage >= 0 && this.current_mana - weapons.mana_usage >= 0){
                UpdateWeaponStatus();
                System.out.println("----------" + weapons.type + " Equipped----------");
                GetStatus();
            }else{
                System.out.println("Can't equip this weapon now.");
            }
        }
        /**
         * use to recheck that accessory state affect character state or not
         * @param : none
         * @return : none
         * effects : character state change follow accessory state
         */
        public void UpgradeAccessoryStatus(){
            if (Objects.equals(this.accessories.type, "Bracelet")) {
                this.max_hp += this.accessories.status;
                this.current_hp += this.accessories.status;
            } else if (Objects.equals(this.accessories.type, "Necklace")) {
                this.max_mana += this.accessories.status;
                this.current_mana += this.accessories.status;
            }
        }
        /**
         * assign character's accessory
         * @param : accessory to be equipped
         * @return : none
         * effects : character's accessory have been assigned and character state change follow accessory state them print to console
         */
        public void EquipAccessory(Accessories accessories){
            this.accessories = accessories;
            if(this.accessories != null){
                UpgradeAccessoryStatus();
                System.out.println("----------" + accessories.type + " Equipped----------");
                GetStatus();
            }else{
                System.out.println("Can't equip this weapon now.");
            }
        }
        /**
         * calculate total damage(minus defense state) that character will taken
         * @param : damage that plus damage from weapon
         * @return : none
         * effects : character's hp decrease due to damage taken
         */
        public void TakenDamage(double damage) {
            double total_damage = damage - this.defense;
            if(this.weapons != null && Objects.equals(this.weapons.type, "Shield")) {
                total_damage -= this.weapons.status;
            }
            this.current_hp -= total_damage;
            if(this.current_hp < 0){
                this.current_hp = 0;
            }
        }
        /**
         * select target to be attacked
         * @param : target character to be attacked
         * @return : none
         * effects : target character attacked by damage that plus weapon's damage and print to console
         */
        public void Attack(Character target) {
            double TotalDamage = this.damage;
            if(this.weapons != null && Objects.equals(this.weapons.type, "Sword")) {
                TotalDamage += this.weapons.status;
            }
            target.TakenDamage(TotalDamage);
            System.out.println("-------" + this.name + " attacks " + target.name + "-------");
        }
        /**
         * restore mana equal to percent required
         * @param : percent to heal must be positive and up to 100, @return : none
         * effects : mana state increase and hp decrease equal to percent required
         */
        public void ManaRestore(double percent) {
            if(this.current_mana < this.max_mana && percent >= 0 && percent <= 100){
                double totalMana = (this.max_mana - this.current_mana)*(percent/100);
                if(totalMana+current_mana > this.max_mana){
                    totalMana = this.max_mana-this.current_mana;
                }
                this.current_mana += totalMana;
                this.current_hp -= totalMana;
            }else{
                System.out.println("Can't be restore.");
            }
        }
    }

    public static class Fighter extends Character{
        /**
         * assign type and special state of character type fighter
         * @param : none
         * @return : none
         * effects : type and state that relate to this type of character changed
         */
        @Override
        public void AssignStatus() {
            super.AssignStatus();
            this.type = "Fighter";
            this.damage = ((double) this.level / this.max_level) * 150;
        }
        /**
         * Constructor of fighter, same method as character constructor
         * @param : name for new create fighter
         * @return : none
         * effects : assign name to new created fighter
         */
        public Fighter(String name) {
            super(name);
        }
    }

    public static class Healer extends Character{
        /**
         * assign type and special state of character type healer
         * @param : none
         * @return : none
         * effects : type and state that relate to this type of character changed
         */
        @Override
        public void AssignStatus() {
            super.AssignStatus();
            this.type = "Healer";
            this.max_hp = ((double) this.level / this.max_level) * 150;
        }
        /**
         * Constructor of healer, same method as character constructor
         * @param : name for new create healer
         * @return : none
         * effects : assign name to new created healer
         */
        public Healer(String name) {
            super(name);
        }
        /**
         * use to restore hp of selected character
         * @param : character that hp less than max hp
         * @param : percent to heal must be positive and up to 100
         * @return : none
         * effects : target character hp increase and the healer hp decrease equal to restored hp/2
         */
        public void Heal(Character target , double percent) {
            if(target.current_hp < target.max_hp && percent >= 0 && percent <= 100) {
                double totalHeal = (target.max_hp - target.current_hp) * (percent / 100);
                target.current_hp += totalHeal;
                this.current_hp -= totalHeal/2;
                if(this.current_hp < 0){
                    this.current_hp = 0;
                }
                System.out.println("-------" + this.name + " heals " + target.name + "-------");
                this.GetStatus();
                target.GetStatus();
            }
            else{
                System.out.println("Can't be heal.");
            }
        }
    }

    public static void main(String[] args) {
    }
}