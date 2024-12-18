package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.Kompozicija;

import java.util.List;

public interface IValidator {
    boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompozicije);

}
