import java.util.Scanner;


public class Expenses {

    public static void main(String[] args) {
        System.out.println("Tjedni troškovi");

        Scanner input = new Scanner(System.in);

        String name;
        do {
            System.out.print("Kako se zoveš? ");
            name = input.nextLine();
        } while (name.length() == 0);

        System.out.println("Pozdrav, "+ name.toUpperCase() + "!");

        System.out.print("Tjedni budžet u eurima: ");
        int budget= input.nextInt();

        double total = 0;
        int count= 0;
        double min = 0;
        double max = 0;
        double bigExpense = 10.0;
        int bigCount = 0;

        System.out.println("Unesi troškove u eurima, 0 za kraj.");

        while (true) {
            System.out.print("Trošak: ");
            double amount = input.nextDouble();

            if (amount == 0) {
                break;
            }
            if (amount < 0){
                System.out.println("Iznos ne može biti negativan.");
                continue;
            }
            if (count == 0 || amount < min) {
                min = amount;
            }
            if (amount > max) {
                max = amount;
            }
            if (amount > bigExpense) {
                bigCount++;
            }
            total += amount;
            count++;
        }

        if(count == 0){
            System.out.println("Nije unesen ni jedan trošak.");
        } else {
            System.out.println("");
            System.out.print("");
            System.out.printf("%-12s %8d%n", "Troškovi:", count);
            printRow("Najmanji:", min);
            printRow("Najveći:", max);
            printRow("Ukupno:", total );
            printRow("Prosjek:", total / count);
            System.out.printf("Većih od %.2f eura: %d%n", bigExpense, bigCount);

            if (total > budget){
                System.out.printf("Budžet je premašen za %.2f eura.%n", total - budget);
            } else if (total > budget * 0.8) {
                System.out.println("Blizu si granice budžeta.");
            } else {
                System.out.println("Unutar budžeta si.");
            }

            System.out.print("Potrošnja (# = 5 eur): ");
            for (int i = 5; i < total; i +=5) {
                System.out.print("#");
            }
            System.out.println("");

            switch(bigCount) {
                case 0: System.out.println("Ocjena tjedna: štedljivo."); break;
                case 1:
                case 2: System.out.println("Ocjena tjedna: umjereno."); break;
                default: System.out.println("Ocjena tjedna: rastrošno."); break;
            }
        }

        input.nextLine();

        System.out.print("Želiš li savjete za štednju? (da/ne) ");
        String answer = input.nextLine();

        boolean wantsTips = answer.equals("da");

        if (wantsTips) {
            String[] tips = {
                "Nosi uzinu od kuće.",
                "Prije kupnje usporedi cijene.",
                "Dio džeparca odmah stavi sa strane."
            };

            System.out.println("Savjeti (" + tips.length + "):");

            for (String tip : tips) {
                System.out.println("- " + tip);
            }
        }

        input.close();
    }

    static void printRow(String label, double value) {
        System.out.printf("%-12s %8.2f%n", label, value);
    }

}