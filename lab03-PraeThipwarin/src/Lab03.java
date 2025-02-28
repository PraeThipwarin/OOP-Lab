public class Lab03 {
    public static class sword{
        int level;
        double damage;

        public sword(int level){
            this.level = level;
            this.damage = level+(0.3*level);
        }
    }

    public static class shield{
        int level;
        double reducedDamage;

        public shield(int level){
            this.level = level;
            this.reducedDamage = level+(0.8*level);
        }
    }

    public static class character {
        private String name;
        private int level;
        private double HP;
        private double Damage;
        private double Defense;
        private double RunSpeed;
        private sword Sword;
        private shield Shield;

        public character(String name) {
            this.name = name;
            this.level = 1;
            this.HP = 100;
            this.Damage = 3+level;
            this.Defense = 2+(0.5*level);
            this.RunSpeed = 10+(0.1+(0.03*this.level));
            this.Sword = null;
            this.Shield = null;
        }

        public void LevelUP(int level) {
            this.level += level;
            this.HP += 10*this.level;
            this.Damage = 3+level;
            this.Defense = 2+(0.5*level);
            this.RunSpeed += (0.1+(0.03*this.level));
            System.out.println("-------" + this.name + " has been level up-------");
        }

        public void EquipSword(sword sword) {
            this.Sword = sword;
            this.RunSpeed -= this.RunSpeed*(0.1+(0.04*this.level));
            System.out.println("-------sword levels " + sword.level + " has been equipped-------");
        }

        public void EquipShield(shield shield) {
            this.Shield = shield;
            this.RunSpeed -= this.RunSpeed*(0.1+(0.08*this.level));
            System.out.println("-------shield levels " + shield.level + " has been equipped-------");
        }

        public void TakenDamage(double damage) {
            if(this.Shield != null){
                damage -= this.Shield.reducedDamage;
            }
            this.HP -= damage;
        }

        public void Attack(character target) {
            double TotalDamage = this.Damage;
            if(this.Sword != null){
                TotalDamage = this.Damage+this.Sword.damage;
            }
            target.TakenDamage(TotalDamage);
            System.out.println("-------" + this.name + " attacks " + target.name + "-------");
        }

        public void GetStatus() {
            System.out.println("------------------------------------------");
            System.out.println("Name: " + this.name);
            System.out.println("Level: " + this.level);
            System.out.println("HP: " + this.HP);
            System.out.println("Damage: " + this.Damage);
            System.out.println("Defense: " + this.Defense);
            System.out.println("MaxRunSpeed: " + this.RunSpeed);
            if(this.Sword != null) System.out.println("Sword Level: " + this.Sword.level);
            if(this.Shield != null) System.out.println("Shield Level: " + this.Shield.level);
            System.out.println("------------------------------------------");
        }
    }
    public static void main(String[] args) {
        character manud1 = new character("manud1");
        character manud2 = new character("manud2");
        manud1.GetStatus();
        manud2.GetStatus();

        sword dap1 = new sword(3);
        shield kro1 = new shield(5);
        sword dap2 = new sword(4);
        shield kro2 = new shield(3);

        manud1.EquipSword(dap1);
        manud1.EquipShield(kro1);
        manud2.EquipSword(dap2);
        manud2.EquipShield(kro2);

        manud1.GetStatus();
        manud2.GetStatus();

        manud2.LevelUP(2);
        manud2.GetStatus();

        manud1.Attack(manud2);
        manud1.GetStatus();
        manud2.GetStatus();
    }
}