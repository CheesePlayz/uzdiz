package foi.uzdiz.sbicak20.pomocnici.komande;

import static java.lang.System.exit;

public class Q implements Komanda{
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        System.out.println("Izlaz iz programa.");
        exit(1);
    }
}
