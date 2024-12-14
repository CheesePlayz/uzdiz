package foi.uzdiz.sbicak20.validatori;

public class PrijevoznoSredstvoFactory extends ValidatorFactory{
    @Override
    public IValidator napraviValidator() {
        return new PrijevoznoSredstvoValidator();
    }
}
