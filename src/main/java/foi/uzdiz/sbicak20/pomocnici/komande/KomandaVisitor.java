package foi.uzdiz.sbicak20.pomocnici.komande;

public interface KomandaVisitor {
    void visit(IP komanda);
    void visit(ISP komanda);
    void visit(ISI2S komanda);
    void visit(IK komanda);
    void visit(IV komanda);
    void visit(IEV komanda);
    void visit(IEVD komanda);
    void visit(IVRV komanda);
    void visit(DK komanda);
    void visit(PK komanda);
    void visit(DPK komanda);
    void visit(Q komanda);
}
