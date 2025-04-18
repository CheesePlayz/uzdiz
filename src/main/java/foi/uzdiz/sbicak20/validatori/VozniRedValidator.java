package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.greske.NevaljaniFormatGreska;
import foi.uzdiz.sbicak20.greske.PrazanStringGreska;
import foi.uzdiz.sbicak20.greske.SustavGresakaSingleton;
import foi.uzdiz.sbicak20.modeli.Kompozicija;

import java.util.List;
import java.util.regex.Pattern;

public class VozniRedValidator implements IValidator {
    private static final Pattern PRUGA_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern SMJER_PATTERN = Pattern.compile("^(N|O)$");
    private static final Pattern VRSTA_VLAKA_PATTERN = Pattern.compile("^(U|B)?$"); // Prazno ili U ili B
    private static final Pattern VRIJEME_POLASKA_PATTERN = Pattern.compile("^([01]?\\d|2[0-3]):[0-5]\\d$");
    private static final Pattern OZNAKA_DANA_PATTERN = Pattern.compile("^\\d{1,2}$|^100$");

    @Override
    public boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompozicije) {
        int redak = 0;

        try {
            boolean jePrazanRedak = true;
            for (String vrijednost : redovi) {
                if (!vrijednost.trim().isEmpty()) {
                    jePrazanRedak = false;
                    break;
                }
            }

            if (jePrazanRedak) {
                return false;
            }

            if (!PRUGA_PATTERN.matcher(redovi[0].trim()).matches()) {
                if (redovi[0].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za Oznaka pruge.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za Oznaka pruge: dozvoljeni znakovi su slova i brojevi.");
            }


            if (!SMJER_PATTERN.matcher(redovi[1].trim()).matches()) {
                if (redovi[1].trim().isEmpty()) {
                    redak = 1;
                    throw new PrazanStringGreska("Prazan format za Smjer.");
                }
                redak = 1;
                throw new NevaljaniFormatGreska("Neispravan format za Smjer: očekuje se 'N' ili 'O'.");
            }

            if (redovi[4].trim().isEmpty()) {
                redak = 4;
                throw new PrazanStringGreska("Prazan format za Oznaka vlaka.");
            }

            if (redovi.length < 6) {
                throw new NevaljaniFormatGreska("Redak ne sadrži dovoljno polja za Vrsta vlaka.");
            }

            if (!VRSTA_VLAKA_PATTERN.matcher(redovi[5].trim()).matches()) {
                redak = 5;
                throw new NevaljaniFormatGreska("Neispravan format za Vrsta vlaka: očekuje se 'U', 'B' ili prazno.");
            }

            if (!VRIJEME_POLASKA_PATTERN.matcher(redovi[6].trim()).matches()) {
                redak = 6;
                throw new NevaljaniFormatGreska("Neispravan format za Vrijeme polaska: očekuje se format HH:mm (24-satni).");
            }

            if (!redovi[8].trim().isEmpty() && !OZNAKA_DANA_PATTERN.matcher(redovi[8].trim()).matches()) {
                redak = 8;
                throw new NevaljaniFormatGreska("Neispravan format za Oznaka dana: očekuje se broj između 0 i 100.");
            }

        } catch (Exception e) {
            SustavGresakaSingleton.getInstance().prijaviGresku(e, SustavGresakaSingleton.getInstance().getPodrucjaGresaka().get(3),
                    new String[]{"CSV redak: " + redakCSV, "Trenutni zapis: " + redovi[redak]});
            return false;
        }
        return true;
    }
}
