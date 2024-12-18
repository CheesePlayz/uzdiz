package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;

import java.util.Objects;

public class IEV implements Komanda {

    private final VozniRed vozniRed;
    private final String oznakaVlaka;

    public IEV(VozniRed vozniRed, String oznakaVlaka) {
        this.vozniRed = vozniRed;
        this.oznakaVlaka = oznakaVlaka;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        int[] maxDuljine = {12, 12, 23, 23, 15, 15, 18, 15};
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        int brojac = 0;
        for (VozniRedKomponenta vlak : vozniRed.getDjeca()) {

            for (VozniRedKomponenta etapa : vlak.getDjeca()) {
                Etapa et = (Etapa) etapa.dohvatiObjekt();
                if (Objects.equals(oznakaVlaka, et.getOznakaVlaka())) {
                    if (brojac == 0) {
                        System.out.printf(format, "Oznaka Vlaka", "Oznaka Pruge", "Polazna Stanica",
                                "Odredišna Stanica", "Vrijeme Polaska", "Vrijeme Dolaska",
                                "Ukupna Kilometraža", "Dani u Tjednu");
                    }
                    brojac++;
                    et.prikaziDetalje();
                }
            }
        }
        if (brojac == 0) {
            System.out.println("Ne postoje etape za vlak " + oznakaVlaka + ".");
        }
    }
}
