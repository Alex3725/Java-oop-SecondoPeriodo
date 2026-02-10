public class Main {
    public static void main(String[] args) {
        SmartPhone t1 = new SmartPhone("Samsung",500.45f,6.4f,"rosso",32);
        Laptop l1 = new Laptop("apple",300,5.3,"subaru");


        t1.connettitiAInternet();
        l1.connettitiAInternet();
        if (l1 instanceof Connettivita){
            System.out.println("Sono uno SmarthPhone");
        }


    }
}
