package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.modeli.composite.VozniRed;

public class VozniRedFactory extends ValidatorFactory{
    @Override
    public IValidator napraviValidator() {
        return new VozniRedValidator();
    }
}
