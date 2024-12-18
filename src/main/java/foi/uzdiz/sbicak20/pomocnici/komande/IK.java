package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.pomocnici.InitSustava;

import java.util.ArrayList;
import java.util.List;

public class IK implements Komanda {
    private String oznakaKompozicije;

    List<List<Kompozicija>> kompozicije;
    List<ZeljeznickoPrijevoznoSredstvo> vozila;

    public IK(List<List<Kompozicija>> kompozicije, List<ZeljeznickoPrijevoznoSredstvo> vozila, String oznakaKompozicije) {
        this.vozila = vozila;
        this.kompozicije = kompozicije;
        this.oznakaKompozicije = oznakaKompozicije;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        boolean kompozicijaNadena = false;
        List<Kompozicija> trazenaKompozicija = null;

        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        for (List<Kompozicija> kompozicija : kompozicije) {
            if (kompozicija.get(0).getOznaka().equals(oznakaKompozicije)) {
                trazenaKompozicija = kompozicija;
                kompozicijaNadena = true;
                break;
            }
        }

        if (!kompozicijaNadena || trazenaKompozicija == null) {
            System.out.println("Kompozicija s oznakom " + oznakaKompozicije + " nije pronadena.");
            return;
        }

        int[] maxInitStupacaDuljine = {7, 5, 4, 6, 7, 12, 12};
        List<String[]> podaciZaIspis = new ArrayList<>();

        for (Kompozicija kompozicija : trazenaKompozicija) {
            String oznakaPrijevoznogSredstva = kompozicija.getOznakaPrijevoznogSredstva();
            ZeljeznickoPrijevoznoSredstvo sredstvo = null;
            for (ZeljeznickoPrijevoznoSredstvo ps : vozila) {
                if (ps.getOznaka().equals(oznakaPrijevoznogSredstva)) {
                    sredstvo = ps;
                    break;
                }
            }

            if (sredstvo != null) {
                String[] red = {
                        oznakaPrijevoznogSredstva,
                        String.valueOf(kompozicija.getUloga()),
                        sredstvo.getOpis(),
                        String.valueOf(sredstvo.getGodina()),
                        String.valueOf(sredstvo.getNamjena()),
                        String.valueOf(sredstvo.getVrstaPogona()),
                        sredstvo.getMaxBrzina() + " km/h"
                };
                podaciZaIspis.add(red);

                for (int i = 0; i < red.length; i++) {
                    maxInitStupacaDuljine[i] = Math.max(maxInitStupacaDuljine[i], red[i].length());
                }
            }
        }

        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxInitStupacaDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka", "Uloga", "Opis", "Godina", "Namjena", "Vrsta pogona", "Maks. brzina");
        for (String[] red : podaciZaIspis) {
            System.out.printf(format, (Object[]) red);
        }
    }
}
