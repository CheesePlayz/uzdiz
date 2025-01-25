package foi.uzdiz.sbicak20.pomocnici.kupovanje;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.CjenikKarti;
import foi.uzdiz.sbicak20.modeli.Karta;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.pomocnici.ISI2Spomocnik;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class VlakKupovanje implements NacinKupovanjaStrategy {

    @Override
    public Karta kupiKartu(String oznakaVlaka, String polaznaStanica, String odredisnaStanica, Date datum) {
        try {
            ZeljeznickiSustavSingleton sustav = ZeljeznickiSustavSingleton.getInstanca();
            if (sustav == null || sustav.getCjenikKarti() == null) {
                System.out.println("Nije moguće kupiti kartu jer cjenik karti ne postoji!");
                return null;
            }

            CjenikKarti cjenikKarti = sustav.getCjenikKarti();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            String datumVrijemeKupovine = LocalDateTime.now().format(formatter);

            String vrstaVlaka = sustav.getVozniRedPodaci().stream()
                    .filter(podaci -> oznakaVlaka.equals(podaci.getOznakaVlaka()))
                    .map(VozniRedPodaci::getVrstaVlaka)
                    .findFirst()
                    .orElse(null);

            double ukupnoKm = ISI2Spomocnik.izracunajUdaljenost(polaznaStanica, odredisnaStanica);
            double izvornaCijena = switch (vrstaVlaka) {
                case "U" -> ukupnoKm * cjenikKarti.getCijenaUbrzani();
                case "B" -> ukupnoKm * cjenikKarti.getCijenaBrzi();
                default -> ukupnoKm * cjenikKarti.getCijenaNormalni();
            };

            double uvecanje = cjenikKarti.getUvecanjeVlak();

            LocalDate localDate = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek danUTjednu = localDate.getDayOfWeek();
            double konacnaCijena = izvornaCijena + (izvornaCijena * uvecanje / 100);
            if (danUTjednu == DayOfWeek.SATURDAY || danUTjednu == DayOfWeek.SUNDAY) {
                konacnaCijena -= izvornaCijena * cjenikKarti.getPopustSuN() / 100;
            }

            return new Karta.KartaBuilder()
                    .oznakaVlaka(oznakaVlaka)
                    .polaznaStanica(polaznaStanica)
                    .odredisnaStanica(odredisnaStanica)
                    .datum(DateTimeFormatter.ofPattern("dd.MM.yyyy.").format(localDate))
                    .vrijemeKretanja("")
                    .vrijemeDolaska("")
                    .izvornaCijena(izvornaCijena)
                    .popust(uvecanje)
                    .konacnaCijena(konacnaCijena)
                    .nacinKupovanja("Vlak")
                    .datumVrijemeKupovine(datumVrijemeKupovine)
                    .build();

        } catch (Exception e) {
            System.out.println("Greška prilikom kupovine karte: " + e.getMessage());
            return null;
        }
    }
}
