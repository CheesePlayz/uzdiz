package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.Kompozicija;

import java.util.List;

public class VozniRedValidator implements IValidator{
    @Override
    public boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompozicije) {
        if (redovi[0].trim().isEmpty()) {
            System.err.println("Redak " + redakCSV + ": Oznaka pruge nije unesena.");
            return false;
        }

        String smjer = redovi[1].trim().toUpperCase();
        if (!(smjer.equals("N") || smjer.equals("O"))) {
            System.err.println("Redak " + redakCSV + ": Smjer mora biti 'N' ili 'O'.");
            return false;
        }

        if (redovi[4].trim().isEmpty()) {
            System.err.println("Redak " + redakCSV + ": Oznaka vlaka nije unesena.");
            return false;
        }

        if (redovi[6].trim().isEmpty()) {
            System.err.println("Redak " + redakCSV + ": Vrijeme polaska nije uneseno.");
            return false;
        }
        return true;
    }
}
