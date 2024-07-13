public class DesktopMachine extends Machine {
    private final String windowsVersion;
    private final String buildNumber;

    public DesktopMachine(String hostname, String requestor, int cpus, int ram, int diskSize, String windowsVersion, String buildNumber) {
        super(hostname, requestor, cpus, ram, diskSize);
        this.windowsVersion = windowsVersion;
        this.buildNumber = buildNumber;
    }

    public String getWindowsVersion() {
        return windowsVersion;
    }

    public String getBuildNumber() {
        return buildNumber;
    }
}
