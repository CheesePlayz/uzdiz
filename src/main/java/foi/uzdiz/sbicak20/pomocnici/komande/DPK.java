package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.RegistarKorisnika;

public class DPK implements Komanda{

    private String ime;
    private String prezime;
    private String oznakaVlaka;
    private String stanica;

    public DPK(String ime, String prezime, String oznakaVlaka, String stanica) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaVlaka = oznakaVlaka;
        this.stanica = stanica;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        ZeljeznickiSustavSingleton.getInstanca().getRegistarKorisnika().dodajPracenjeVlaka(ime, prezime, oznakaVlaka, stanica);
    }
}
