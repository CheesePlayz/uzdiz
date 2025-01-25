package foi.uzdiz.sbicak20.pomocnici.komande;

public class IzvrsiKomanduVisitor implements KomandaVisitor {


    @Override
    public void visit(Komanda komanda) {
        komanda.izvrsi();
    }

}
