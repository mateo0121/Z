import java.util.Locale;
import java.util.Scanner;

public class Temperature {

    public static void main(String[] args) {
        // Postavljanje Scanner-a za učitavanje brojeva s tipkovnice
        Scanner input = new Scanner(System.in);
        // Postavljanje lokalizacije kako bi se osiguralo prihvaćanje točke ili zareza (ovisno o sustavu)
        input.useLocale(Locale.getDefault());

        int count = 0;
        double min = 0.0;
        double max = 0.0;
        double total = 0.0;
        int countElevated = 0;
        final double THRESHOLD = 37.0;

        System.out.println("Unosi izmjerene temperature, 0 za kraj.");

        while (true) {
            System.out.print("Temperatura: ");
            
            // Provjera ima li sljedećeg broja za unos
            if (!input.hasNextDouble()) {
                input.next(); // preskoči neispravan unos
                continue;
            }

            double temp = input.nextDouble();

            // Unos završava kad korisnik upiše 0
            if (temp == 0) {
                break;
            }

            // Ažuriranje najniže i najviše temperature
            if (count == 0 || temp < min) {
                min = temp;
            }
            if (count == 0 || temp > max) {
                max = temp;
            }

            // Provjera povišene temperature (veće od 37.0)
            if (temp > THRESHOLD) {
                countElevated++;
            }

            total += temp;
            count++;
        }

        System.out.println(); // Prazna linija radi preglednosti kao u primjeru

        // Provjera slučaja u kojem nije uneseno nijedno mjerenje
        if (count == 0) {
            System.out.println("Nije uneseno nijedno mjerenje.");
        } else {
            double average = total / count;

            System.out.printf("Broj mjerenja: %d%n", count);
            System.out.printf("Najniža: %.2f%n", min);
            System.out.printf("Najviša: %.2f%n", max);
            System.out.printf("Prosjek: %.2f%n", average);
            System.out.printf("Povišenih mjerenja: %d%n", countElevated);

            // Zaključna poruka
            if (countElevated > 0) {
                System.out.println("Povišena temperatura zabilježena.");
            } else {
                System.out.println("Sva mjerenja u granicama normale.");
            }
        }

        // Obavezno zatvaranje objekta Scanner
        input.close();
    }
}
