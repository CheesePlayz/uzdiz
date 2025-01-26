package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.greske.SustavGresakaSingleton;
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
        String putanjaVozniRed = null;
        String putanjaOznakeDana = null;

        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 >= args.length) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nedostaje vrijednost za argument: " + args[i]));
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
                case "--zvr":
                    putanjaVozniRed = args[i + 1];
                    break;
                case "--zod":
                    putanjaOznakeDana = args[i + 1];
                    break;
                default:
                    SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nepoznat argument: " + args[i]));
                    exit(0);
            }
        }

        if (putanjaStanice == null || putanjaVozila == null || putanjaKompozicije == null || putanjaVozniRed == null || putanjaOznakeDana == null) {
            if (putanjaStanice == null) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke stanica (--zs)"));
            }
            if (putanjaVozila == null) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke vozila (--zps)"));
            }
            if (putanjaKompozicije == null) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke kompozicija (--zk)"));
            }
            if (putanjaVozniRed == null) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke voznog reda (--zvr)"));
            }
            if (putanjaOznakeDana == null) {
                SustavGresakaSingleton.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke oznaka dana (--zod)"));
            }
            exit(0);
        }

        InitSustava initSustava = new InitSustava(putanjaStanice, putanjaVozila, putanjaKompozicije, putanjaVozniRed, putanjaOznakeDana);
        ZeljeznickiSustavSingleton sustavSingleton = ZeljeznickiSustavSingleton.getInstanca(initSustava.getStanice(), initSustava.getVozila(), initSustava.getKompozicije(), initSustava.getVozniRed(), initSustava.getOznakeDana());

        SustavGresakaSingleton.getInstance().ispisiSveGreske();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Komanda >> ");
                String komanda = reader.readLine();
                if (komanda == null || komanda.trim().equalsIgnoreCase("exit")) {
                    System.out.println("Izlaz iz sustava.");
                    break;
                }
                sustavSingleton.IzvrsiKomanduVisitor(komanda.trim());
            }
        } catch (IOException e) {
            SustavGresakaSingleton.getInstance().prijaviGresku(e, "Greška u čitanju komandi");
        }
    }
}
