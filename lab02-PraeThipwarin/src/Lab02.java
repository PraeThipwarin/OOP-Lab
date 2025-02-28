public class Lab02 {
    public static class AirPurifier {
        private static String defaultMode = "Auto"; //"Sleep","Normal","Turbo"
        private static int filterLifeLimit = 100;

        private String brand;
        private boolean power_status;
        private String filter_status;
        private int filter_life;
        private String purification_mode;

        public AirPurifier(String brand) {
            this.brand = brand;
            this.power_status = false;
            this.filter_status = "Normal"; //"Should be changed"
            this.filter_life = 0;
            this.purification_mode = defaultMode;
            System.out.println("New AirPurifier has been created");
            System.out.println("Filter life limit = " + filterLifeLimit);
        }

        public void TurnOn(){
            if(power_status){
                System.out.println("Air purifier is already on");
            }else{
                power_status = true;
                System.out.println("Air purifier is turned on");
                this.filter_life += 10;
                if(this.filter_life >= filterLifeLimit) {
                    this.filter_status = "Filter should be change now";
                    System.out.println("Filter life limit exceeded");
                }
            }
        }

        public void TurnOff(){
            if(power_status){
                power_status = false;
                System.out.println("Air purifier is turned off");
            }else{
                System.out.println("Air purifier is already off");
            }
        }

        public void ChangeMode(String mode){
            if(mode.equals("Auto") || mode.equals("Normal") || mode.equals("Sleep") || mode.equals("Turbo")) {
                this.purification_mode = mode;
            }else{
                System.out.println("Don't have this mode");
            }
        }

        public void replaceFilter() {
            this.filter_status = "Normal";
            this.filter_life = 0;
        }

        public void GetStatus(){
            System.out.println("------------------------------------------");
            System.out.println("Brand: " + this.brand);
            if(power_status)
                System.out.println("Status: on");
            else
                System.out.println("Status: off");
            System.out.println("Current filter life:" + this.filter_life);
            System.out.println("Current filter status: " + this.filter_status);
            System.out.println("Current purification mode: " + this.purification_mode);
            System.out.println("------------------------------------------");
        }

        public static void setFilterLifeLimit(int limit) {
            filterLifeLimit = limit;
        }

        public static void setDefaultMode(String mode) {
            if(mode.equals("Auto") || mode.equals("Normal") || mode.equals("Sleep") || mode.equals("Turbo")) {
                defaultMode = mode;
            }else{
                System.out.println("Don't have this mode");
            }
        }
    }

    public static void main(String[] args) {
        AirPurifier airPurifier1 = new AirPurifier("Capybara");
        airPurifier1.GetStatus();

        airPurifier1.TurnOn();
        airPurifier1.ChangeMode("Crazy");
        airPurifier1.GetStatus();

        System.out.println("------------------------------------------------------------------------------------");

        AirPurifier.setFilterLifeLimit(20);
        AirPurifier.setDefaultMode("Sleep");
        AirPurifier airPurifier2 = new AirPurifier("Seals");
        airPurifier2.GetStatus();

        airPurifier2.TurnOff();
        airPurifier2.TurnOn();
        airPurifier2.TurnOff();
        airPurifier2.TurnOn();
        airPurifier2.GetStatus();

        airPurifier2.replaceFilter();
        airPurifier2.GetStatus();}
}