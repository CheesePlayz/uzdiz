package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.greske.NullVrijednostGreska;
import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.validatori.IValidator;
import foi.uzdiz.sbicak20.validatori.PrijevoznoSredstvoFactory;
import foi.uzdiz.sbicak20.validatori.StanicaFactory;
import foi.uzdiz.sbicak20.validatori.ValidatorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class CitacCSV {

    static IValidator validator;

    public static List<ZeljeznickaStanica> ucitajStaniceIzCSV(String putanjaDatoteke, ValidatorFactory factory) throws Exception {
        if (putanjaDatoteke == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za stanice je null"));
            exit(1);
        }
        List<ZeljeznickaStanica> stanice = new ArrayList<>();
        int redakCSV = 1;
        validator = factory.napraviValidator();
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");
                if (redovi.length != 14) {
                    continue;
                }
                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                ZeljeznickaStanica stanica = new ZeljeznickaStanica.StanicaBuilder()
                        .setStanica(redovi[0].trim())
                        .setOznakaPruge(redovi[1].trim())
                        .setVrstaStanice(redovi[2].trim())
                        .setStatusStanice(redovi[3].trim())
                        .setPutniciUlIz(redovi[4].trim())
                        .setRobaUtIst(redovi[5].trim())
                        .setKategorijaPruge(redovi[6].trim())
                        .setBrojPerona(Integer.parseInt(redovi[7].trim()))
                        .setVrstaPruge(redovi[8].trim())
                        .setBrojKolosjeka(Integer.parseInt(redovi[9].trim()))
                        .setDoPoOsovini(Double.parseDouble(redovi[10].trim().replace(",", ".")))
                        .setDoPoDuznomM(Double.parseDouble(redovi[11].trim().replace(",", ".")))
                        .setStatusPruge(redovi[12].trim())
                        .setDuzina(Integer.parseInt(redovi[13].trim()))
                        .build();

                stanice.add(stanica);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            exit(1);
        }

        return stanice;
    }

    public static List<ZeljeznickoPrijevoznoSredstvo> ucitajVozilaIzCSV(String putanjaDatoteke, ValidatorFactory factory) throws Exception {
        if (putanjaDatoteke == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za vozila je null"));
            exit(1);
        }
        List<ZeljeznickoPrijevoznoSredstvo> vozila = new ArrayList<ZeljeznickoPrijevoznoSredstvo>();
        int redakCSV = 1;
        validator = factory.napraviValidator();
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");

                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                ZeljeznickoPrijevoznoSredstvo vozilo = new ZeljeznickoPrijevoznoSredstvo.VoziloBuilder()
                        .setOznaka(redovi[0].trim())
                        .setOpis(redovi[1].trim())
                        .setProizvodac(redovi[2].trim())
                        .setGodina(Integer.parseInt(redovi[3].trim()))
                        .setNamjena(redovi[4].trim())
                        .setVrstaPrijevoza(redovi[5].trim())
                        .setVrstaPogona(redovi[6].trim())
                        .setMaxBrzina(Integer.parseInt(redovi[7].trim()))
                        .setMaxSnaga(Double.parseDouble(redovi[8].trim().replace(",", ".")))
                        .setBrojSjedecihMjesta(Integer.parseInt(redovi[9].trim()))
                        .setBrojStajucihMjesta(Integer.parseInt(redovi[10].trim()))
                        .setBrojBicikala(Integer.parseInt(redovi[11].trim()))
                        .setBrojKreveta(Integer.parseInt(redovi[12].trim()))
                        .setBrojAutomobila(Integer.parseInt(redovi[13].trim()))
                        .setNosivost(Double.parseDouble(redovi[14].trim().replace(",", ".")))
                        .setPovrsina(Double.parseDouble(redovi[15].trim().replace(",", ".")))
                        .setZapremina(Double.parseDouble(redovi[16].trim().replace(",", ".")))
                        .setStatus(redovi[17].trim())
                        .build();

                vozila.add(vozilo);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            exit(1);
        }

        return vozila;
    }


    public static List<List<Kompozicija>> ucitajKompozicijeIzCSV(String putanjaCsvKompozicije, ValidatorFactory factory) throws Exception {
        if (putanjaCsvKompozicije == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za kompozicije je null"));
            System.exit(1);
        }
        validator = factory.napraviValidator();
        List<List<Kompozicija>> listaKompozicija = new ArrayList<>();
        List<Kompozicija> trenutnaKompozicija = new ArrayList<>();
        String trenutnaOznaka = null;

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaCsvKompozicije))) {
            String line;
            br.readLine();
            int redakCSV = 1;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                redakCSV++;

                if (line.equals(";;")) {
                    if (!trenutnaKompozicija.isEmpty()) {
                        if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                            listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                        } else {
                            SustavGresaka.getInstance().prijaviGresku(
                                    new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge."),
                                    SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                                    new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                            );
                        }
                        trenutnaKompozicija.clear();
                        trenutnaOznaka = null;
                    }
                    continue;
                }

                String[] redovi = line.split(";");
                if (redovi.length != 3) {
                    continue;
                }

                String oznaka = redovi[0].trim();
                String oznakaPrijevoznogSredstva = redovi[1].trim();
                String uloga = redovi[2].trim();

                if (oznaka.isEmpty() || oznakaPrijevoznogSredstva.isEmpty() || uloga.isEmpty()) {
                    continue;
                }

                if (trenutnaOznaka == null) {
                    trenutnaOznaka = oznaka;
                }

                if (!oznaka.equals(trenutnaOznaka)) {
                    if (!trenutnaKompozicija.isEmpty()) {
                        if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                            listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                        } else {
                            SustavGresaka.getInstance().prijaviGresku(
                                    new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge"),
                                    SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                                    new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                            );
                        }
                    }
                    trenutnaKompozicija.clear();
                    trenutnaOznaka = null;
                    listaKompozicija.removeLast();
                    continue;

                }

                try {
                    Kompozicija dioKompozicije = new Kompozicija(oznaka, oznakaPrijevoznogSredstva, uloga);
                    trenutnaKompozicija.add(dioKompozicije);
                } catch (IllegalArgumentException e) {
                    SustavGresaka.getInstance().prijaviGresku(e, "Pogrešan zapis kompozicije", new String[]{"CSV Redak: " + redakCSV, line});
                    trenutnaKompozicija.clear();
                    trenutnaOznaka = null;
                }
            }

            if (!trenutnaKompozicija.isEmpty()) {
                if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                    listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                } else {
                    SustavGresaka.getInstance().prijaviGresku(
                            new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge"),
                            SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                            new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                    );
                }
            }

        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            System.exit(1);
        }

        return listaKompozicija;
    }

}

