package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;

import java.util.List;

public class IEVD implements Komanda{

    private VozniRed vozniRed;
    private String dani;

    public IEVD(VozniRed vozniRed, String dani) {
        this.vozniRed = vozniRed;
        this.dani = dani;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        int[] maxDuljine = {12, 12, 20, 20, 15, 15, 15};
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka Vlaka", "Oznaka Pruge", "Polazna Stanica",
                "Odredišna Stanica", "Vrijeme Polaska", "Vrijeme Dolaska", "Dani u Tjednu");


        for (VozniRedKomponenta vlak : vozniRed.getDjeca()) {
            List<VozniRedKomponenta> etapeVoznogReda = vlak.getDjeca();

            boolean voziNaDane = false;  // Postavljamo na false, dok ne nađemo odgovarajuće etape

            // Provjeravamo svaku etapu vlakova
            for (VozniRedKomponenta etapaKomponenta : etapeVoznogReda) {
                Etapa etapa = (Etapa) etapaKomponenta.dohvatiObjekt();

                System.out.println("Provjera etape: " + etapa.getOznakaVlaka() + ", Dani: " + etapa.getDaniUTjednu());

                boolean poklapanjeDana = false;

                // Provjeravamo svaki dan u traženim danima
                for (char dan : dani.toCharArray()) {
                    if (etapa.getDaniUTjednu().contains(String.valueOf(dan))) {
                        poklapanjeDana = true;  // Ako postoji barem jedno poklapanje, označimo kao true
                        break; // Ako postoji poklapanje, prestajemo tražiti
                    }
                }

                // Ako postoji poklapanje dana u etapi, postavljamo voziNaDane na true
                if (poklapanjeDana) {
                    voziNaDane = true;
                }
            }

            // Ako vlak vozi na tražene dane, ispisujemo njegove etape
            if (voziNaDane) {
                System.out.println("Ispisujemo vlak koji vozi na tražene dane:");
                for (VozniRedKomponenta etapaKomponenta : etapeVoznogReda) {
                    Etapa etapa = (Etapa) etapaKomponenta.dohvatiObjekt();

                    String polaznaStanica = etapa.getDjeca().get(0).toString();
                    String odredisnaStanica = etapa.getDjeca().get(etapa.getDjeca().size() - 1).toString();
                    String vrijemePolaska = etapa.getVrijemePolaska().toString();
                    String vrijemeDolaska = etapa.getVrijemeDolaska().toString();
                    String daniUTjednu = etapa.getDaniUTjednu();

                    // Ispisujemo podatke za etapu
                    System.out.printf(format, etapa.getOznakaVlaka(), etapa.getOznakaPruge(), polaznaStanica,
                            odredisnaStanica, vrijemePolaska, vrijemeDolaska, daniUTjednu);
                }
            }
        }
    }
}
