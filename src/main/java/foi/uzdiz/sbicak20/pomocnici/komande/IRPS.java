package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Pruga;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.List;

public class IRPS implements Komanda {

    private String oznaka;

    private String status;

    public IRPS(String oznaka, String status){
        this.oznaka = oznaka;
        this.status = status;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        ZeljeznickiSustavSingleton sustav = ZeljeznickiSustavSingleton.getInstanca();
        assert sustav != null : "Instanca ZeljeznickiSustavSingleton nije inicijalizirana.";
        boolean prviRedakTablice = true;
        for (Pruga pruga : sustav.getPruge()) {
            if (oznaka != null && !oznaka.equals(pruga.getOznaka())) {
                continue;
            }

            List<ZeljeznickaStanica> stanice = pruga.getStanice();
            for (int i = 0; i < stanice.size() - 1; i++) {
                ZeljeznickaStanica trenutna = stanice.get(i);
                ZeljeznickaStanica sljedeca = stanice.get(i + 1);

                boolean statusOdgovara = trenutna.getStatusPruge().equals(status) && sljedeca.getStatusPruge().equals(status);
                boolean statusObrnuto = sljedeca.getStatusPruge().equals(status) && trenutna.getStatusPruge().equals(status);

                if (statusOdgovara || statusObrnuto) {
                    if (prviRedakTablice) {
                        System.out.printf("%-10s | %-50s | %-10s%n", "Pruga", "Relacija", "Status Pruge");
                        prviRedakTablice = false;
                    }

                    System.out.printf("%-10s | %-50s | %-10s%n",
                            pruga.getOznaka(),
                            trenutna.getStanica() + " - " + sljedeca.getStanica(),
                            pruga.getTrenutniStatus());
                }
            }
        }
    }
}
