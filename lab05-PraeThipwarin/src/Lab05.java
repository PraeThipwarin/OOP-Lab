import java.util.Objects;

public class Lab05 {
    public static abstract class Accessories{
        protected int level = 0;
        protected final int maxLevel = 10;
        protected String type = null;
        protected double status = 0;
        protected String status_name = null;

        public void UpgradeStatus(){
            this.status = 0;
        }
        public void AssignStatus(){
            this.type = "type";
            this.status_name = "status_name";
        }
        public Accessories(int level){
            AssignStatus();
            if(level <= this.maxLevel && level > 0){
                this.level = level;
                UpgradeStatus();
                System.out.println("----------Creating Accessory----------");
                System.out.println(this.type + " level: " + this.level + " has been created.");
                System.out.println(this.status_name + " Increase = " + this.status);
                System.out.println("--------------------------------------");
            }else{
                System.out.println("Level must be positive and no more than 10.");
            }
        }

        public void AccessoriesLevelUP(int level) {
            if(this.level < this.maxLevel && level > 0 && level <= 3){
                this.level += level;
                UpgradeStatus();
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
        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*50;
            this.maxHPtobeAdd = this.status;
        }
        @Override
        public void AssignStatus(){
            this.type = "Bracelet";
            this.status_name = "MaxHP";
        }
        public Bracelet(int level){
            super(level);
        }
    }

    public static class Necklace extends Accessories{
        double maxManatobeAdd;
        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*30;
            this.maxManatobeAdd = this.status;
        }
        @Override
        public void AssignStatus(){
            this.type = "Necklace";
            this.status_name = "MaxMana";
        }
        public Necklace(int level) {
            super(level);
        }
    }

    public static abstract class Weapons{
        protected int level = 0;
        protected final int maxLevel = 10;
        protected String type = null;
        protected double status = 0;
        protected String status_name = null;
        protected double mana_usage = 0;
        protected double runSpeed_usage = 0;

        public void UpgradeStatus(){
            this.status = 0;
            this.mana_usage = 0;
            this.runSpeed_usage = 0;
        }
        public void AssignStatus(){
            this.type = "type";
            this.status_name = "status_name";
        }
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

        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*70;
            this.damagetobeAdd = this.status;
            this.mana_usage = ((double) (this.level * 10) /100)*50;
            this.runSpeed_usage = ((double) (this.level * 10) /100)*20;
        }
        @Override
        public void AssignStatus() {
            this.type = "Sword";
            this.status_name = "Damage";
        }
        public Sword(int level){
            super(level);
        }
    }

    public static class Shield extends Weapons{
        double defensetobeAdd;

        @Override
        public void UpgradeStatus() {
            this.status = ((double) (this.level * 10) /100)*80;
            this.defensetobeAdd = this.status;
            this.runSpeed_usage = ((double) (this.level * 10) /100)*30;
        }
        @Override
        public void AssignStatus() {
            this.type = "Shield";
            this.status_name = "Defense";
        }
        public Shield(int level){
            super(level);
        }
    }

    public static class Character {
        protected String name;
        protected String type;
        protected int level;
        protected int max_level = 60;
        protected double max_hp;
        protected double current_hp;
        protected double max_mana;
        protected double current_mana;
        protected double damage;
        protected double defense;
        protected double max_speed;
        protected double current_speed;
        protected Weapons weapons = null;
        protected Accessories accessories = null;

        public void AssignStatus(){
            this.type = "type";
            this.max_hp = ((double) this.level /this.max_level)*1000;
            this.max_mana = ((double) this.level /this.max_level)*700;
            this.max_speed = ((double) this.level /this.max_level)*200;
            this.damage = ((double) this.level /this.max_level)*100;
            this.defense = ((double) this.level /this.max_level)*50;
        }
        public void UpgradeStatus(){
            this.current_hp = this.max_hp;
            this.current_mana = this.max_mana;
            this.current_speed = this.max_speed;
        }
        public void GetStatus() {
            if(this.weapons != null) UpgradeWesponStatus();
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
        public Character(String name) {
            this.name = name;
            this.level = 1;
            AssignStatus();
            UpgradeStatus();
            System.out.println("----------Character "+this.name+" has been created.----------");
            GetStatus();
        }
        public void CharacterLevelUp(int level) {
            if(this.level < this.max_level && level > 0 && level <= 5){
                this.level += level;
                AssignStatus();
                UpgradeStatus();
                System.out.println("----------Leveling Up Character-----------");
                System.out.println(this.name + " has been level up!.");
                GetStatus();
                System.out.println("---------------------------------------");
            }else if(level <= 0 || level > 3){
                System.out.println("Level must be positive and can only increase by up to 5 levels at a time.");
            }else {
                System.out.println("Can't be upgrade anymore.");
            }
        }
        public void UpgradeWesponStatus(){
            this.current_mana -= this.weapons.mana_usage;
            this.current_speed -= this.weapons.runSpeed_usage;
            if (Objects.equals(this.weapons.type, "Sword")) {
                this.damage += this.weapons.status;
            } else if (Objects.equals(this.weapons.type, "Shield")) {
                this.defense += this.weapons.status;
            }
        }
        public void EquipWeapon(Weapons weapons){
            this.weapons = weapons;
            if(this.weapons != null && this.current_speed - weapons.runSpeed_usage >= 0 && this.current_mana - weapons.mana_usage >= 0){
                UpgradeWesponStatus();
                System.out.println("----------" + weapons.type + " Equipped----------");
                GetStatus();
            }else{
                System.out.println("Can't equip this weapon now.");
            }
        }
        public void UpgradeAccessoryStatus(){
            if (Objects.equals(this.accessories.type, "Bracelet")) {
                this.max_hp += this.accessories.status;
                this.current_hp += this.accessories.status;
            } else if (Objects.equals(this.accessories.type, "Necklace")) {
                this.max_mana += this.accessories.status;
                this.current_mana += this.accessories.status;
            }
        }
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
        public void TakenDamage(double damage) {
            if(this.weapons != null && Objects.equals(this.weapons.type, "Shield")) {
                damage -= this.weapons.status;
            }
            this.current_hp -= damage;
            if(this.current_hp < 0){
                this.current_hp = 0;
            }
        }
        public void Attack(Character target) {
            double TotalDamage = this.damage;
            if(this.weapons != null && Objects.equals(this.weapons.type, "Sword")) {
                TotalDamage += this.weapons.status;
            }
            target.TakenDamage(TotalDamage);
            System.out.println("-------" + this.name + " attacks " + target.name + "-------");
        }
        public void ManaRestore(double percent) {
            if(this.current_mana < this.max_mana){
                double totalMana = (this.max_mana - this.current_mana)*(percent/100);
                this.current_mana += totalMana;
                this.current_hp -= totalMana;
            }
        }
    }

    public static class Fighter extends Character{
        @Override
        public void AssignStatus() {
            super.AssignStatus();
            this.type = "Fighter";
            this.damage = ((double) this.level / this.max_level) * 150;
        }
        public Fighter(String name) {
            super(name);
        }
    }

    public static class Healer extends Character{
        @Override
        public void AssignStatus() {
            super.AssignStatus();
            this.type = "Healer";
            this.max_hp = ((double) this.level / this.max_level) * 150;
        }
        public Healer(String name) {
            super(name);
        }
        public void Heal(Character target , double percent) {
            if(target.current_hp < target.max_hp) {
                double totalHeal = (target.max_hp - target.current_hp) * (percent / 100);
                target.current_hp += totalHeal;
                this.current_hp -= totalHeal/2;
                if(this.current_hp < 0){
                    this.current_hp = 0;
                }
            }
            System.out.println("-------" + this.name + " heals " + target.name + "-------");
            this.GetStatus();
            target.GetStatus();
        }
    }

    public static void main(String[] args) {
        Character manud1 = new Fighter("manud1");
        Character manud2 = new Character("manud2");
        Weapons dap = new Sword(3);
        Accessories bracelet = new Bracelet(3);
        manud1.CharacterLevelUp(5);
        manud1.CharacterLevelUp(5);
        manud1.EquipAccessory(bracelet);
        manud1.EquipWeapon(dap);
        manud1.Attack(manud2);
        manud2.GetStatus();
        Healer manud3 = new Healer("manud3");
        manud3.Heal(manud2,50);
        Shield shield = new Shield(3);
        manud2.EquipWeapon(shield);
    }
}