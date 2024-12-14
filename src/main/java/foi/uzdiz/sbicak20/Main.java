package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.pomocnici.InitSustava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {
        String putanjaStanice = null;
        String putanjaVozila = null;
        String putanjaKompozicije = null;

        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 >= args.length) {
                SustavGresaka.getInstance().prijaviGresku(new Exception("Nedostaje vrijednost za argument: " + args[i]));
                exit(0);
            }

            switch (args[i]) {
                case "--zs":
                    putanjaStanice = args[i + 1];
                    break;
                case "--zps":
                    putanjaVozila = args[i + 1];
                    break;
                case "--zk":
                    putanjaKompozicije = args[i + 1];
                    break;
                default:
                    SustavGresaka.getInstance().prijaviGresku(new Exception("Nepoznat argument: " + args[i]));
                    exit(0);
            }
        }

        if (putanjaStanice == null || putanjaVozila == null || putanjaKompozicije == null) {
            if (putanjaStanice == null) {
                SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke stanica (--zs)"));
            }
            if (putanjaVozila == null) {
                SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke vozila (--zps)"));
            }
            if (putanjaKompozicije == null) {
                SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke kompozicija (--zk)"));
            }
            exit(0);
        }

        InitSustava initSustava = new InitSustava(putanjaStanice, putanjaVozila, putanjaKompozicije);
        ZeljeznickiSustavSingleton sustavSingleton = ZeljeznickiSustavSingleton.getInstanca(
                initSustava.getStanice(),
                initSustava.getVozila(),
                initSustava.getKompozicije()
        );

        SustavGresaka.getInstance().ispisiSveGreske();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Komanda >> ");
                String komanda = reader.readLine();
                if (komanda == null || komanda.trim().equalsIgnoreCase("exit")) {
                    System.out.println("Izlaz iz sustava.");
                    break;
                }
                sustavSingleton.IzvrsiKomandu(komanda.trim());
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška u čitanju komandi");
        }
    }
}
