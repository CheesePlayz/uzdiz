package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IP implements Komanda{
    List<ZeljeznickaStanica> stanice;

    public IP(List<ZeljeznickaStanica> stanice){
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

        Map<String, List<ZeljeznickaStanica>> prugeMap = new HashMap<>();
        int[] maxDuljine = {6, 15, 15, 18};


        for (ZeljeznickaStanica stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            prugeMap.putIfAbsent(oznakaPruge, new ArrayList<>());
            prugeMap.get(oznakaPruge).add(stanica);

            maxDuljine[0] = Math.max(maxDuljine[0], oznakaPruge.length());
        }

        for (Map.Entry<String, List<ZeljeznickaStanica>> entry : prugeMap.entrySet()) {
            List<ZeljeznickaStanica> stanicePruge = entry.getValue();
            if (stanicePruge.size() < 2) continue;


            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();

            maxDuljine[1] = Math.max(maxDuljine[1], pocetnaStanica.length());
            maxDuljine[2] = Math.max(maxDuljine[2], zavrsnaStanica.length());
        }


        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka", "Pocetna stanica", "Zavrsna stanica", "Ukupna kilometraza");

        for (Map.Entry<String, List<ZeljeznickaStanica>> entry : prugeMap.entrySet()) {
            String oznaka = entry.getKey();
            List<ZeljeznickaStanica> stanicePruge = entry.getValue();

            if (stanicePruge.size() < 2) {
                continue;
            }

            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();
            double ukupnaKilometraza = 0;

            for (ZeljeznickaStanica stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }

            maxDuljine[2] = Math.max(maxDuljine[2], pocetnaStanica.length());
            maxDuljine[3] = Math.max(maxDuljine[3], zavrsnaStanica.length());

            System.out.printf(format, oznaka, pocetnaStanica, zavrsnaStanica, ukupnaKilometraza);
        }
    }
}
