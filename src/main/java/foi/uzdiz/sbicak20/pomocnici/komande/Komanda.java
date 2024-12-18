package foi.uzdiz.sbicak20.pomocnici.komande;

public interface Komanda {
    void prihvati(KomandaVisitor visitor);

    void izvrsi();
}
