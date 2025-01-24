package foi.uzdiz.sbicak20.pomocnici.kupovanje;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Karta;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class VlakKupovanje implements NacinKupovanjaStrategy{

    @Override
    public Karta kupiKartu(String oznakaVlaka, String polaznaStanica, String odredisnaStanica, Date datum) {
        try {
            assert ZeljeznickiSustavSingleton.getInstanca() != null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            String datumVrijemeKupovine = LocalDateTime.now().format(formatter);

            double izvornaCijena = 50.0; // Ovo treba dinamički izračunati na temelju udaljenosti relacije
            double popust = ZeljeznickiSustavSingleton.getInstanca().getCJenikKarti().getUvecanjeVlak();
            double konacnaCijena = izvornaCijena + (izvornaCijena * popust / 100);

            return new Karta.KartaBuilder()
                    .oznakaVlaka(oznakaVlaka)
                    .polaznaStanica(polaznaStanica)
                    .odredisnaStanica(odredisnaStanica)
                    .datum(new SimpleDateFormat("dd.MM.yyyy.").format(datum))
                    .vrijemeKretanja("14:30:00") // Ovo treba dohvatiti iz voznog reda
                    .vrijemeDolaska("15:15:00") // Ovo treba dohvatiti iz voznog reda
                    .izvornaCijena(izvornaCijena)
                    .popust(popust)
                    .konacnaCijena(konacnaCijena)
                    .nacinKupovanja("Vlak")
                    .datumVrijemeKupovine(datumVrijemeKupovine)
                    .build();
        } catch (Exception e) {
            System.out.println("Greška prilikom kupovine karte: " + e.getMessage());
        }

        return null;
    }

}
