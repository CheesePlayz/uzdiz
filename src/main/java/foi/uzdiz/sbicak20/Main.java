package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.pomocnici.InitSustava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static InitSustava initSustava = null;

    public static void main(String[] args) throws Exception {

        String putanjaStanice = null;
        String putanjaVozila = null;
        String putanjaKompozicije = null;

        for (int i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case "--zs":
                    if (i + 1 < args.length) {
                        putanjaStanice = args[i + 1];
                    }
                    break;
                case "--zps":
                    if (i + 1 < args.length) {
                        putanjaVozila = args[i + 1];
                    }
                    break;
                case "--zk":
                    if (i + 1 < args.length) {
                        putanjaKompozicije = args[i + 1];
                    }
                    break;
                default:
                    SustavGresaka.getInstance().prijaviGresku(new Exception("Nepoznat argument: " + args[i]));
            }
        }

        if (putanjaStanice == null) {
            SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke stanica (--zs)"));
        }
        if (putanjaVozila == null) {
            SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke vozila (--zps)"));
        }
        if (putanjaKompozicije == null) {
            SustavGresaka.getInstance().prijaviGresku(new Exception("Nije naveden put do datoteke kompozicija (--zk)"));
        }

        initSustava = new InitSustava(putanjaStanice, putanjaVozila, putanjaKompozicije);
        if (putanjaStanice != null && putanjaVozila != null && putanjaKompozicije != null) {
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Komanda >> ");
                String komanda = reader.readLine();
                ZeljeznickiSustavSingleton.getInstanca(initSustava.getStanice(), initSustava.getVozila(), initSustava.getKompozicije()).IzvrsiKomandu(komanda);
            }
        }
    }
}