package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

public abstract class ValidatorFactory {
    public abstract IValidator napraviValidator();
}
