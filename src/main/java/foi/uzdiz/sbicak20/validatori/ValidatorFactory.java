package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

public class ValidatorFactory {
    public static <T> IValidator napraviValidator(Class<T> tip) throws Exception {
        if (tip == ZeljeznickaStanica.class) {
            return new StanicaValidator();
        } else if (tip == ZeljeznickoPrijevoznoSredstvo.class) {
            return new PrijevoznoSredstvoValidator();
        } else if (tip == Kompozicija.class) {
            return new KompozicijaValidator();
        } else {
            throw new Exception("Nepoznat tip validatora!");
        }
    }
}
