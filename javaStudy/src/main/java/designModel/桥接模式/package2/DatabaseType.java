package designModel.桥接模式.package2;

public abstract class DatabaseType {

    protected ConversionType conversionType;

    public DatabaseType(ConversionType conversionType) {
        this.conversionType = conversionType;
    }

    public ConversionType getConversionType() {
        return conversionType;
    }

    public void setConversionType(ConversionType conversionType) {
        this.conversionType = conversionType;
    }

    public abstract void conversion();
}
