package foi.uzdiz.sbicak20.modeli;

import foi.uzdiz.sbicak20.pomocnici.stanja.PrugaState;
import foi.uzdiz.sbicak20.pomocnici.stanja.TestiranjeState;
import foi.uzdiz.sbicak20.pomocnici.stanja.ZatvorenaState;

import java.util.ArrayList;
import java.util.List;

public class Pruga {
    private String oznaka;
    private List<ZeljeznickaStanica> stanice;
    private PrugaState trenutniStatus;

    public Pruga(String oznaka, List<ZeljeznickaStanica> stanice, PrugaState pocetnoStanje) {
        this.oznaka = oznaka;
        this.stanice = stanice;
        this.trenutniStatus = pocetnoStanje;
    }

    public List<ZeljeznickaStanica> getStanice(){
        return stanice;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void postaviStatus(PrugaState noviStatus, String polazna, String odredisna) {

        if (trenutniStatus instanceof ZatvorenaState && !(noviStatus instanceof TestiranjeState)) {
            System.out.println("Nije moguće iz Zatvorenog stanja prijeći u '" + noviStatus.dohvatiNazivStatusa() + "'!");
            return;
        }

        System.out.println("Promjena trenutnog statusa pruge na: " + noviStatus.dohvatiNazivStatusa());
        this.trenutniStatus = noviStatus;
        trenutniStatus.promijeniStatus(this, polazna, odredisna);
    }

    public void azurirajStanice(String polazna, String odredisna, String noviStatus) {
        boolean unutarRelacije = false;

        for (ZeljeznickaStanica stanica : stanice) {
            if (stanica.getStanica().equals(polazna)) {
                unutarRelacije = true;
            }

            if (unutarRelacije) {
                stanica.setStatusPruge(noviStatus);
            }

            if (stanica.getStanica().equals(odredisna)) {
                unutarRelacije = false;
                break;
            }
        }

        if (!unutarRelacije) {
            unutarRelacije = false;
            for (int i = stanice.size() - 1; i >= 0; i--) {
                ZeljeznickaStanica stanica = stanice.get(i);

                if (stanica.getStanica().equals(polazna)) {
                    unutarRelacije = true;
                }

                if (unutarRelacije) {
                    stanica.setStatusPruge(noviStatus);
                }

                if (stanica.getStanica().equals(odredisna)) {
                    unutarRelacije = false;
                    break;
                }
            }
        }
    }

    public String getTrenutniStatus() {
        return trenutniStatus.dohvatiNazivStatusa();
    }
}

