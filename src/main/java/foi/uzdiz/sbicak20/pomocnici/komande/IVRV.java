package foi.uzdiz.sbicak20.pomocnici.komande;

public class IVRV implements Komanda{
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {

    }
}
