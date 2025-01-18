package foi.uzdiz.sbicak20.pomocnici.komande;

public class IzvrsiKomanduVisitor implements KomandaVisitor {


    @Override
    public void visit(IP komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(ISP komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(ISI2S komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(IK komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(IV komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(IEV komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(IEVD komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(IVRV komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(DK komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(PK komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(DPK komanda) {
        komanda.izvrsi();
    }

    @Override
    public void visit(CVP komanda) {komanda.izvrsi();}

    @Override
    public void visit(PSP2S komanda) {komanda.izvrsi();}

    @Override
    public void visit(IRPS komanda) {komanda.izvrsi();}
    @Override
    public void visit(Q komanda) {
        komanda.izvrsi();
    }
}
