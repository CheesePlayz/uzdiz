package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.CjenikKarti;

public class CVP implements Komanda{

    private double cijenaNormalni;
    private double cijenaUbrzani;
    private double cijenaBrzi;
    private double popustSuN;
    private double popustWebMob;
    private double uvecanjeVlak;

    public CVP(double cijenaNormalni, double cijenaUbrzani, double cijenaBrzi, double popustSuN, double popustWebMob, double uvecanjeVlak) {
        this.cijenaNormalni = cijenaNormalni;
        this.cijenaUbrzani = cijenaUbrzani;
        this.cijenaBrzi = cijenaBrzi;
        this.popustSuN = popustSuN;
        this.popustWebMob = popustWebMob;
        this.uvecanjeVlak = uvecanjeVlak;
    }


    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        ZeljeznickiSustavSingleton.getInstanca().setCjenikKarti(new CjenikKarti.CjenikKartiBuilder().setCijenaNormalni(cijenaNormalni).setCijenaUbrzani(cijenaUbrzani).setCijenaBrzi(cijenaBrzi).setPopustSuN(popustSuN).setPopustWebMob(popustWebMob).setUvecanjeVlak(uvecanjeVlak).build());
        CjenikKarti ck = ZeljeznickiSustavSingleton.getInstanca().getCjenikKarti();
        ck.ispisiCjenik();
    }
}
