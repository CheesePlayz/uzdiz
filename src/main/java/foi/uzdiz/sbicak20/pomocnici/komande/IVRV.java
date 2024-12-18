package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.Vlak;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;

import java.time.LocalTime;
import java.util.*;

public class IVRV implements Komanda{

    private VozniRed vozniRed;
    private List<ZeljeznickaStanica> stanice;

    private String oznakaVlaka;

    public IVRV(VozniRed vozniRed, List<ZeljeznickaStanica> stanice, String oznakaVlaka){
        this.vozniRed = vozniRed;
        this.stanice = stanice;
        this.oznakaVlaka = oznakaVlaka;
    }
    private double ukupnaKilometraza = 0;
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        int[] maxDuljine = {12, 14, 23, 15, 18};
        StringBuilder formatBuilder = new StringBuilder("|");

        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka Vlaka", "Oznaka Pruge", "Polazna Stanica", "Vrijeme Polaska", "Ukupna Kilometraža");


        for(VozniRedKomponenta vlak : vozniRed.getDjeca()){
            Vlak vl = (Vlak) vlak.dohvatiObjekt();
            if (Objects.equals(vl.getOznakaVlaka(), this.oznakaVlaka)){
                for (VozniRedKomponenta etapa : vlak.getDjeca()){
                    racunica(stanice, etapa, format);
                }
            }
        }
    }

    private void racunica(List<ZeljeznickaStanica> stanice, VozniRedKomponenta etapa, String format) {
        Etapa et = (Etapa) etapa.dohvatiObjekt();
        String polazna = etapa.getDjeca().getFirst().toString();
        String odredisna = etapa.getDjeca().getLast().toString();
        String oznakaPrugeParam = et.getOznakaPruge();
        LocalTime trenutnoVrijeme = et.getVrijemePolaska();
        Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
        for (ZeljeznickaStanica stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
        }

        List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
        if (staniceNaPrugi == null || staniceNaPrugi.isEmpty()) {
            return;
        }

        int indeksPolazne = -1, indeksOdredisne = -1;
        for (int i = 0; i < staniceNaPrugi.size(); i++) {
            if (staniceNaPrugi.get(i).getStanica().equals(polazna)) {
                indeksPolazne = i;
            }
            if (staniceNaPrugi.get(i).getStanica().equals(odredisna)) {
                indeksOdredisne = i;
            }
        }

        if (indeksPolazne == -1 || indeksOdredisne == -1) {
            return;
        }

        boolean obrnutRedoslijed = indeksPolazne > indeksOdredisne;

        if (obrnutRedoslijed) {
            if (staniceNaPrugi.get(indeksPolazne).getDuzina() != 0){
                ukupnaKilometraza -= staniceNaPrugi.get(indeksPolazne).getDuzina();
            }
            for (int i = indeksPolazne; i >= indeksOdredisne; i--) {
                if (i == indeksOdredisne) ukupnaKilometraza += staniceNaPrugi.get(indeksPolazne).getDuzina();
                ukupnaKilometraza += staniceNaPrugi.get(i).getDuzina();
                System.out.printf(format, et.getOznakaVlaka(),et.getOznakaPruge(), staniceNaPrugi.get(i).getStanica(), trenutnoVrijeme, ukupnaKilometraza);
                trenutnoVrijeme = zbrojiVrijeme(trenutnoVrijeme, String.valueOf(staniceNaPrugi.get(i).getVrijemeNormalniVlak()));
            }
        } else {
            if (staniceNaPrugi.get(indeksPolazne).getDuzina() != 0){
                ukupnaKilometraza -= staniceNaPrugi.get(indeksPolazne).getDuzina();
            }
            for (int i = indeksPolazne; i <= indeksOdredisne; i++) {
                ukupnaKilometraza += staniceNaPrugi.get(i).getDuzina();
                System.out.printf(format, et.getOznakaVlaka(),et.getOznakaPruge(), staniceNaPrugi.get(i).getStanica(), trenutnoVrijeme, ukupnaKilometraza);
                trenutnoVrijeme = zbrojiVrijeme(trenutnoVrijeme, String.valueOf(staniceNaPrugi.get(i).getVrijemeNormalniVlak()));
            }

        }
    }

    private LocalTime zbrojiVrijeme(LocalTime pocetnoVrijeme, String vrijemeZaDodati) {
        int minuteZaDodati = 0;

        try {
            minuteZaDodati = Integer.parseInt(vrijemeZaDodati);
        } catch (NumberFormatException e) {
        }

        LocalTime novoVrijeme = pocetnoVrijeme.plusMinutes(minuteZaDodati);

        return novoVrijeme;
    }

    private LocalTime oduzmiVrijeme(LocalTime pocetnoVrijeme, String vrijemeZaDodati) {
        int minuteZaDodati = 0;

        try {
            minuteZaDodati = Integer.parseInt(vrijemeZaDodati);
        } catch (NumberFormatException e) {
        }

        LocalTime novoVrijeme = pocetnoVrijeme.minusMinutes(minuteZaDodati);

        return novoVrijeme;
    }

}
