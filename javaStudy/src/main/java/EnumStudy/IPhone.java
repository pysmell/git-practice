package EnumStudy;

public class IPhone extends Handphone<IPhone> {

    private int version;

    public IPhone(int version) {
        this.version = version;
    }

    @Override
    int osVersion() {
        return version;
    }


}
