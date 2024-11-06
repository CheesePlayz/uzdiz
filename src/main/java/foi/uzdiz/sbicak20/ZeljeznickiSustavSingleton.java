package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;
import foi.uzdiz.sbicak20.pomocnici.InitSustava;

import java.util.*;

import static java.lang.System.exit;

public class ZeljeznickiSustavSingleton {
    private static ZeljeznickiSustavSingleton instanca;

    private List<ZeljeznickeStanice> stanice;
    private List<ZeljeznickaPrijevoznaSredstva> vozila;
    private List<Kompozicija> kompozicije;

    private ZeljeznickiSustavSingleton(List<ZeljeznickeStanice> stanice, List<ZeljeznickaPrijevoznaSredstva> vozila, List<Kompozicija> kompozicije) {
        this.stanice = stanice;
        this.vozila = vozila;
        this.kompozicije = kompozicije;
    }

    public static ZeljeznickiSustavSingleton getInstanca(List<ZeljeznickeStanice> stanice, List<ZeljeznickaPrijevoznaSredstva> vozila, List<Kompozicija> kompozicije) {
        if (instanca == null) {
            instanca = new ZeljeznickiSustavSingleton(stanice, vozila, kompozicije);
        }
        return instanca;
    }

    public void IzvrsiKomandu(String komanda){

        if (komanda.startsWith("IK")){
            String[] dioKomande = komanda.split(" ");
            if (dioKomande.length != 2){
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            String oznakaKompozicije = dioKomande[1];
            IspisVozilaIzKompozicije(oznakaKompozicije);
        }
        else if (komanda.startsWith("ISP")) {
            String[] dioKomande = komanda.split(" ");
            if (dioKomande.length != 3) {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            String oznakaPruge = dioKomande[1];
            String redoslijed = dioKomande[2];
            boolean obrnutRedoslijed;
            if (Objects.equals(dioKomande[2], "N")){
                obrnutRedoslijed = false;
            }
            else if (Objects.equals(dioKomande[2], "O")){
                obrnutRedoslijed = true;
            }
            else {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            IspisPregledStanicaNaOdredenojPruzi(stanice, oznakaPruge, obrnutRedoslijed);
        } else {
            switch (komanda) {
                case "Q": {
                    exit(0);
                    break;
                }
                case "IP": {
                    IspisPruge(stanice);
                    break;
                }
                default:
                    System.out.println("Nepoznata komanda !!!");
            }
        }
    }

    private void IspisPruge(List<ZeljeznickeStanice> stanice){
        Map<String, List<ZeljeznickeStanice>> prugeMap = new HashMap<>();

        int maxDuljinaStanice = 0;
        for (ZeljeznickeStanice stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            prugeMap.putIfAbsent(oznakaPruge, new ArrayList<>());
            prugeMap.get(oznakaPruge).add(stanica);
            if (maxDuljinaStanice < stanica.getStanica().length()){
                maxDuljinaStanice = stanica.getStanica().length();
            }
        }
        String format = "| %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s |%n";
        System.out.printf(format, "Oznaka", "Pocetna stanica", "Zavrsna stanica", "Ukupna kilometraza");

        for (Map.Entry<String, List<ZeljeznickeStanice>> entry : prugeMap.entrySet()) {
            String oznaka = entry.getKey();
            List<ZeljeznickeStanice> stanicePruge = entry.getValue();

            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();

            double ukupnaKilometraza = 0;

            for (ZeljeznickeStanice stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }



            System.out.printf(format, oznaka, pocetnaStanica, zavrsnaStanica, ukupnaKilometraza);
        }
    }

        private void IspisPregledStanicaNaOdredenojPruzi(List<ZeljeznickeStanice> stanice, String oznakaPruge, boolean obrnutRedoslijed) {
            List<ZeljeznickeStanice> stanicePruge = new ArrayList<>();

            int maxDuljinaStanice = 0;
            for (ZeljeznickeStanice stanica : stanice) {
                if (stanica.getOznakaPruge().equals(oznakaPruge)) {
                    stanicePruge.add(stanica);
                    if (maxDuljinaStanice < stanica.getStanica().length()){
                        maxDuljinaStanice = stanica.getStanica().length();
                    }
                }
            }

            if (stanicePruge.isEmpty()) {
                System.out.println("Nema stanica za prugu: " + oznakaPruge);
                return;
            }


            double ukupnaKilometraza = 0;

            String format1 = "| %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s%n";
            System.out.printf(format1, "Naziv stanice", "Vrsta stanice", "Ukupna kilometraza");
            String format = "| %-"+maxDuljinaStanice+"s | %-"+maxDuljinaStanice+"s | %.2f km%n";

            if (obrnutRedoslijed) {
                int brojElemenataUnutarPruge = stanicePruge.size() - 1;
                for (int i = brojElemenataUnutarPruge; i >= 0; i--) {
                    ZeljeznickeStanice stanica = stanicePruge.get(i);
                    if (i == brojElemenataUnutarPruge) ukupnaKilometraza = 0;
                    else ukupnaKilometraza += stanicePruge.get(i+1).getDuzina();

                    System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), ukupnaKilometraza);
                }
            } else {
                for (ZeljeznickeStanice stanica : stanicePruge) {
                    ukupnaKilometraza += stanica.getDuzina();
                    System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), ukupnaKilometraza);
                }
            }
        }

        private void IspisVozilaIzKompozicije(String oznakaKompozicije){
            for (Kompozicija k : kompozicije) {
                if (k.getOznaka().equals(oznakaKompozicije)) {
                    String oznakaPrijevoznogSredstva = k.getOznakaPrijevoznogSredstva();

                    for (ZeljeznickaPrijevoznaSredstva sredstvo : vozila) {
                        if (sredstvo.getOznaka().equals(oznakaPrijevoznogSredstva)) {
                            System.out.printf("%s %s %s %d %s %s %d%n",
                                    sredstvo.getOznaka(), k.getUloga(), sredstvo.getOpis(), sredstvo.getGodina(),
                                    sredstvo.getNamjena(), sredstvo.getVrstaPogona(), sredstvo.getMaxBrzina());
                        }
                    }
                }
            }
        }
    }
