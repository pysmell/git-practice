package EnumStudy;

public class Android extends Handphone<Android> {

    private int version;

    @Override
    int osVersion() {
        return version;
    }

    public Android(int version) {
        this.version = version;
    }
}
