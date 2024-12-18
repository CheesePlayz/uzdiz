package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;

public class DK implements Komanda {

    private final String ime;
    private final String prezime;

    public DK(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        ZeljeznickiSustavSingleton.getInstanca().getRegistarKorisnika().dodajKorisnika(ime, prezime);
    }
}
