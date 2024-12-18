package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.enumeracije.KompozicijeEnum.KUlogeEnum;
import foi.uzdiz.sbicak20.greske.NevaljaniFormatGreska;
import foi.uzdiz.sbicak20.greske.PrazanStringGreska;
import foi.uzdiz.sbicak20.greske.SustavGresakaSingleton;
import foi.uzdiz.sbicak20.modeli.Kompozicija;

import java.util.List;
import java.util.regex.Pattern;

public class KompozicijaValidator implements IValidator {

    int redak = 0;
    private final Pattern ULOGA_PATTERN = Pattern.compile("^[PV]$");

    @Override
    public boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompozicija) {
        if (redovi != null) {
            try {
                if (!ULOGA_PATTERN.matcher(redovi[2].trim()).matches()) {
                    redak = 2;
                    if (redovi[redak].trim().isEmpty()) {
                        throw new PrazanStringGreska("Prazan format za ULOGA_PATTERN: očekuje se 'P' ili 'V'.");
                    }
                    throw new NevaljaniFormatGreska("Neispravan format za ULOGA_PATTERN: očekuje se 'P' ili 'V'.");
                }
            } catch (Exception e) {
                SustavGresakaSingleton.getInstance().prijaviGresku(e, SustavGresakaSingleton.getInstance().getPodrucjaGresaka().get(2), new String[]{"CSV redak: " + redakCSV, "Trenutni zapis: " + redovi[redak].trim()});
                return false;
            }
        }


        boolean minDva = kompozicija.size() >= 2;

        boolean sadrziPogon = false;
        boolean nakonVagonaPogon = false;
        boolean vagoni = false;

        for (Kompozicija dio : kompozicija) {
            if (dio.getUloga().equals(KUlogeEnum.P)) {
                if (vagoni) {
                    nakonVagonaPogon = true;
                    break;
                }
                sadrziPogon = true;
            } else if (dio.getUloga().equals(KUlogeEnum.V)) {
                vagoni = true;
            }
        }
        if (minDva) {
            if (sadrziPogon) {
                return !nakonVagonaPogon;
            }
        }
        return false;
    }
}
