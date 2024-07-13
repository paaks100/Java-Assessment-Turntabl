public class ServerMachine extends Machine {
    private String linuxDistribution;
    private int majorNumber;
    private String kernelVersion;
    private String adminTeam;

    public ServerMachine(String hostname, String requestor, int cpus, int ram, int diskSize, String linuxDistribution, int majorNumber, String kernelVersion, String adminTeam) {
        super(hostname, requestor, cpus, ram, diskSize);
        this.linuxDistribution = linuxDistribution;
        this.majorNumber = majorNumber;
        this.kernelVersion = kernelVersion;
        this.adminTeam = adminTeam;
    }

    public String getLinuxDistribution() {
        return linuxDistribution;
    }

    public int getMajorNumber() {
        return majorNumber;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public String getAdminTeam() {
        return adminTeam;
    }
}
