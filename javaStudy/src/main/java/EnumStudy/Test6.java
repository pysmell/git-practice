package EnumStudy;

/*
* 单例模式
* */
public enum Test6 {

    DATASOURCE;

    private DataSourceConnection dataSourceConnection = null;

    Test6() {
        dataSourceConnection = new DataSourceConnection();
    }

    public DataSourceConnection getDataSourceConnection() {
        return dataSourceConnection;
    }
}
