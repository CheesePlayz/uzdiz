package foi.uzdiz.sbicak20.modeli.composite;

import java.util.List;

public abstract class VozniRedKomponenta {
    public abstract void dodajKomponentu(VozniRedKomponenta komponenta);

    public abstract void ukloniKomponentu(VozniRedKomponenta komponenta);

    public abstract List<VozniRedKomponenta> getDjeca();

    public abstract void prikaziDetalje();

    public abstract Object dohvatiObjekt();

}
