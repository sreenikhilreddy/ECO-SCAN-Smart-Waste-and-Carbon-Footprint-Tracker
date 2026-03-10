import java.util.*;

// User class
class EcoUser implements Comparable<EcoUser>{
    
    String name;
    int ecoPoints;

    EcoUser(String name,int ecoPoints){
        this.name=name;
        this.ecoPoints=ecoPoints;
    }

    // Priority Queue (Max Heap)
    public int compareTo(EcoUser u){
        return u.ecoPoints - this.ecoPoints;
    }

}

// Waste info class
class Waste{
    
    String type;
    int points; 
    String tip;

    Waste(String type,int points,String tip){
        this.type=type;
        this.points=points;
        this.tip=tip;
    }

}

public class EcoScanSystem{

    static HashMap<String,Waste> wasteDB = new HashMap<>();
    static PriorityQueue<EcoUser> leaderboard = new PriorityQueue<>();
    static HashMap<String,Integer> userScores = new HashMap<>();

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        // Waste database
        wasteDB.put("plastic", new Waste("Recyclable",10,"Send to plastic recycling"));
        wasteDB.put("paper", new Waste("Recyclable",8,"Use paper recycling bin"));
        wasteDB.put("glass", new Waste("Recyclable",12,"Reuse glass containers"));
        wasteDB.put("metal", new Waste("Recyclable",15,"Send to scrap metal center"));
        wasteDB.put("organic", new Waste("Biodegradable",20,"Compost organic waste"));

        System.out.print("Enter your name: ");
        String user = sc.nextLine();

        userScores.put(user,0);

        while(true){

            System.out.println("\n====== EcoScan Smart System ======");
            System.out.println("1. Scan Waste");
            System.out.println("2. Check Eco Score");
            System.out.println("3. Carbon Footprint Calculator");
            System.out.println("4. View Eco Leaderboard");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:

                    System.out.print("Enter waste item: ");
                    String item = sc.nextLine().toLowerCase();

                    if(wasteDB.containsKey(item)){

                        Waste w = wasteDB.get(item);

                        int newScore = userScores.get(user) + w.points;
                        userScores.put(user,newScore);

                        leaderboard.add(new EcoUser(user,newScore));

                        System.out.println("\nMaterial: "+item);
                        System.out.println("Category: "+w.type);
                        System.out.println("Eco Points Earned: "+w.points);
                        System.out.println("Tip: "+w.tip);
                        System.out.println("Total Eco Score: "+newScore);

                    }
                    else{
                        System.out.println("Unknown waste item.");
                    }

                    break;

                case 2:

                    System.out.println("Your Eco Score: "+userScores.get(user));
                    break;

                case 3:

                    System.out.print("Enter KM travelled: ");
                    double km = sc.nextDouble();

                    double carbon = km * 0.21;

                    System.out.println("Carbon Output: "+carbon+" kg CO2");

                    break;

                case 4:

                    System.out.println("\n🏆 Eco Leaderboard");

                    PriorityQueue<EcoUser> temp = new PriorityQueue<>(leaderboard);

                    int rank=1;

                    while(!temp.isEmpty() && rank<=5){

                        EcoUser u = temp.poll();

                        System.out.println(rank+". "+u.name+" → "+u.ecoPoints+" points");

                        rank++;
                    }

                    break;

                case 5:

                    System.out.println("Thank you for using EcoScan 🌱");
                    return;

                default:
                    System.out.println("Invalid option");

            }

        }

    }

}