public abstract class Machine {
    private String hostname;
    private String requestor;
    private int cpus;
    private int ram;
    private int diskSize;

    public Machine(String hostname, String requestor, int cpus, int ram, int diskSize) {
        this.hostname = hostname;
        this.requestor = requestor;
        this.cpus = cpus;
        this.ram = ram;
        this.diskSize = diskSize;
    }

    // Getters and setters

    public String getHostname() {
        return hostname;
    }

    public String getRequestor() {
        return requestor;
    }

    public int getCpus() {
        return cpus;
    }

    public int getRam() {
        return ram;
    }

    public int getDiskSize() {
        return diskSize;
    }
}