package foi.uzdiz.sbicak20.validatori;

public class KompozicijaFactory extends ValidatorFactory {
    @Override
    public IValidator napraviValidator() {
        return new KompozicijaValidator();
    }
}
