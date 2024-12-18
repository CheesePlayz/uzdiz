package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.ArrayList;
import java.util.List;

public class ISP implements Komanda {
    private String oznakaPruge;
    private boolean obrnutRedoslijed;

    List<ZeljeznickaStanica> stanice;

    public ISP(List<ZeljeznickaStanica> stanice, String oznakaPruge, boolean obrnutRedoslijed) {
        this.oznakaPruge = oznakaPruge;
        this.obrnutRedoslijed = obrnutRedoslijed;
        this.stanice = stanice;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {

        if (stanice == null || stanice.isEmpty()) {
            System.out.println("Nema dostupnih podataka o stanicama.");
            return;
        }

        List<ZeljeznickaStanica> stanicePruge = new ArrayList<>();
        int[] maxDuljine = {13, 13, 18};

        for (ZeljeznickaStanica stanica : stanice) {
            if (stanica.getOznakaPruge().equals(oznakaPruge)) {
                stanicePruge.add(stanica);
                maxDuljine[0] = Math.max(maxDuljine[0], stanica.getStanica().length());
                maxDuljine[1] = Math.max(maxDuljine[1], stanica.getVrstaStanice().toString().length());
            }
        }

        if (stanicePruge.isEmpty()) {
            System.out.println("Nema stanica za prugu: " + oznakaPruge);
            return;
        }

        if (stanicePruge.size() < 2) {
            System.out.println("Pruga mora imati barem dvije stanice!");
            return;
        }

        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Naziv stanice", "Vrsta stanice", "Ukupna kilometraza");

        double ukupnaKilometraza = 0;


        if (obrnutRedoslijed) {
            int brojElemenataUnutarPruge = stanicePruge.size() - 1;
            for (int i = brojElemenataUnutarPruge; i >= 0; i--) {
                ZeljeznickaStanica stanica = stanicePruge.get(i);
                if (i == brojElemenataUnutarPruge) {
                    ukupnaKilometraza = 0;
                } else {
                    ukupnaKilometraza += stanicePruge.get(i + 1).getDuzina();
                }
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        } else {
            for (ZeljeznickaStanica stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        }
    }
}
