package foi.uzdiz.sbicak20.validatori;

public class StanicaFactory extends ValidatorFactory {
    @Override
    public IValidator napraviValidator() {
        return new StanicaValidator();
    }
}
