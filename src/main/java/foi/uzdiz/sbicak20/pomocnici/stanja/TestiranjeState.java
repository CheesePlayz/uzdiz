package foi.uzdiz.sbicak20.pomocnici.stanja;

import foi.uzdiz.sbicak20.modeli.Pruga;

public class TestiranjeState implements PrugaState{
    String oznakaStanja = "T";
    @Override
    public void promijeniStatus(Pruga pruga, String polazna, String odredisna) {
        System.out.println("Postavljam status 'U Testiranju' na relaciji od " + polazna + " do " + odredisna);
        pruga.azurirajStanice(polazna, odredisna, oznakaStanja);
    }

    @Override
    public String dohvatiNazivStatusa() {
        return "U Testiranju";
    }
}
