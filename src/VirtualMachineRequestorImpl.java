import java.util.HashMap;
import java.util.Map;

public class VirtualMachineRequestorImpl implements VirtualMachineRequestor {

    private AuthorisingService authorisingService;
    private SystemBuildService systemBuildService;

    private Map<String, Map<String, Integer>> successfulBuilds = new HashMap<>();
    private int failedBuildsCount = 0;

    public VirtualMachineRequestorImpl(AuthorisingService authorisingService, SystemBuildService systemBuildService) {
        this.authorisingService = authorisingService;
        this.systemBuildService = systemBuildService;
    }

    @Override
    public void createNewRequest(Machine machine) throws UserNotEntitledException, MachineNotCreatedException {
        String user = machine.getRequestor();
        if (!authorisingService.isAuthorised(user)){
            throw new UserNotEntitledException("User not entitled to request a new VM.");
        }

        String hostname = systemBuildService.createNewMachine(machine);
        if (hostname.isEmpty()){
            failedBuildsCount++;
            throw new MachineNotCreatedException("Failed to create machine.");
        }

        String machineType = (machine instanceof DesktopMachine) ? "Desktop" : "Server";
        successfulBuilds.computeIfAbsent(user, k -> new HashMap<>())
                        .merge(machineType, 1, Integer::sum);
    }

    @Override
    public Map<String, Map<String, Integer>> totalBuildsByUserForDay() {
        return successfulBuilds;
    }

    @Override
    public int totalFailedBuildsForDay() {
        return failedBuildsCount;
    }
}
