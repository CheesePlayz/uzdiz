package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;
import foi.uzdiz.sbicak20.pomocnici.InitSustava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

public class ZeljeznickiSustavSingleton {
    private static ZeljeznickiSustavSingleton instanca;

    private InitSustava initSustava;
    private List<ZeljeznickeStanice> stanice;
    private List<ZeljeznickaPrijevoznaSredstva> vozila;
    private List<Kompozicija> kompozicije;

    private ZeljeznickiSustavSingleton(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije) {
        this.initSustava = new InitSustava(putanjaCsvStanice, putanjaCsvPrijevoznaSredstva, putanjaCsvKompozicije);
        this.stanice = initSustava.getStanice();
        this.vozila = initSustava.getVozila();
        this.kompozicije = initSustava.getKompozicije();
    }

    public static ZeljeznickiSustavSingleton getInstanca(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije) {
        if (instanca == null) {
            instanca = new ZeljeznickiSustavSingleton(putanjaCsvStanice, putanjaCsvPrijevoznaSredstva, putanjaCsvKompozicije);
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
            boolean obrnutRedoslijed = redoslijed.equalsIgnoreCase("O");

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

        for (ZeljeznickeStanice stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            prugeMap.putIfAbsent(oznakaPruge, new ArrayList<>());
            prugeMap.get(oznakaPruge).add(stanica);
        }

        for (Map.Entry<String, List<ZeljeznickeStanice>> entry : prugeMap.entrySet()) {
            String oznaka = entry.getKey();
            List<ZeljeznickeStanice> stanicePruge = entry.getValue();

            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();

            double ukupnaKilometraza = 0;

            for (ZeljeznickeStanice stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }

            System.out.printf("%s, %s - %s, %.2f km%n", oznaka, pocetnaStanica, zavrsnaStanica, ukupnaKilometraza);
        }
    }

        private void IspisPregledStanicaNaOdredenojPruzi(List<ZeljeznickeStanice> stanice, String oznakaPruge, boolean obrnutRedoslijed) {
            List<ZeljeznickeStanice> stanicePruge = new ArrayList<>();

            for (ZeljeznickeStanice stanica : stanice) {
                if (stanica.getOznakaPruge().equals(oznakaPruge)) {
                    stanicePruge.add(stanica);
                }
            }

            if (stanicePruge.isEmpty()) {
                System.out.println("Nema stanica za prugu: " + oznakaPruge);
                return;
            }

            double ukupnaKilometraza = 0;
            for (ZeljeznickeStanice stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }

            if (obrnutRedoslijed) {
                for (int i = stanicePruge.size() - 1; i >= 0; i--) {
                    ZeljeznickeStanice stanica = stanicePruge.get(i);
                    System.out.printf("%s, %s, %.2f km%n", stanica.getStanica(), stanica.getVrstaStanice(), ukupnaKilometraza);
                }
            } else {
                for (ZeljeznickeStanice stanica : stanicePruge) {
                    System.out.printf("%s, %s, %.2f km%n", stanica.getStanica(), stanica.getVrstaStanice(), ukupnaKilometraza);
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
