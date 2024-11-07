package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;

public class ValidatorFactory {
    public static <T> IValidator napraviValidator(Class<T> tip) throws Exception {
        if (tip == ZeljeznickeStanice.class) {
            return new StanicaValidator();
        } else if (tip == ZeljeznickaPrijevoznaSredstva.class) {
            return new PrijevoznoSredstvoValidator();
        } else if (tip == Kompozicija.class) {
            return new KompozicijaValidator();
        } else {
            throw new Exception("Nepoznat tip validatora!");
        }
    }
}
