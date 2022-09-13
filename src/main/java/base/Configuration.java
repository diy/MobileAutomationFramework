package base;


public class Configuration {

    private String platformName;
    private String platformVersion;
    private String deviceName;
    private String automationName;
    private String app;
    private String appPackage;
    private String appActivity;

    @Override
    public String toString() {
        return "Configuration{" +
                "platformName='" + platformName + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", automationName='" + automationName + '\'' +
                ", app='" + app + '\'' +
                '}';
    }
}
