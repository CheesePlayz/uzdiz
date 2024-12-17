package foi.uzdiz.sbicak20.pomocnici.komande;

public class ISI2S implements Komanda{

    private String oznakaStanicePolazna;
    private String oznakaStaniceOdredisna;

    public ISI2S(String oznakaStaniceStart, String oznakaStaniceEnd) {
        this.oznakaStanicePolazna = oznakaStaniceStart;
        this.oznakaStaniceOdredisna = oznakaStaniceEnd;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {

    }
}
