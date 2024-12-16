package foi.uzdiz.sbicak20.modeli.composite;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.List;

public class Stanica{
    private ZeljeznickaStanica stanica;

    public Stanica(ZeljeznickaStanica detaljiStanice) {
        this.stanica = detaljiStanice;
    }

    public ZeljeznickaStanica getStanica() {
        return stanica;
    }

}
