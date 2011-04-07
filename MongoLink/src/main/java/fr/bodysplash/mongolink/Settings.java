package fr.bodysplash.mongolink;

public class Settings {

    public static Settings defaultInstance() {
        Settings settings = new Settings();
        settings.port = 27017;
        settings.host = "127.0.0.1";
        settings.factoryClass = DbFactory.class;
        settings.dbName = "test";
        return settings;
    }

    private Settings() {
    }

    public Settings withHost(String host) {
        this.host = host;
        return this;
    }

    public Settings withPort(int port) {
        this.port = port;
        return this;
    }

    public String getDbName() {
        return dbName;
    }

    public Settings withDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public DbFactory createDbFactory() {
        try {
            DbFactory dbFactory = factoryClass.newInstance();
            dbFactory.setHost(host);
            dbFactory.setPort(port);
            return dbFactory;
        } catch (Exception e) {
            throw new MongoLinkError("Can't create DbFactory", e);
        }
    }

    public Settings withFactory(Class<? extends DbFactory> FactoryClass) {
        factoryClass = FactoryClass;
        return this;
    }


    private Class<? extends DbFactory> factoryClass;
    private String host;
    private int port;

    private String dbName;
}
