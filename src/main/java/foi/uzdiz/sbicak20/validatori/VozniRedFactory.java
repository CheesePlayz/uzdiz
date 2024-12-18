package foi.uzdiz.sbicak20.validatori;

public class VozniRedFactory extends ValidatorFactory {
    @Override
    public IValidator napraviValidator() {
        return new VozniRedValidator();
    }
}
