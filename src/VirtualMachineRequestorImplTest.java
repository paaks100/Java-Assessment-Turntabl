import org.junit.*;
import org.mockito.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VirtualMachineRequestorImplTest {
    @Mock
    private AuthorisingService authorisingService;

    @Mock
    private SystemBuildService systemBuildService;

    private VirtualMachineRequestorImpl virtualMachineRequestor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        virtualMachineRequestor = new VirtualMachineRequestorImpl(authorisingService, systemBuildService);
    }

    @Test(expected = UserNotEntitledException.class)
    public void testCreateNewRequestThrowsUserNotEntitledException() throws Exception {
        when(authorisingService.isAuthorised("user1")).thenReturn(false);

        Machine machine = new DesktopMachine("host20230328001", "user1", 1, 16, 160, "11", "21H2");

        virtualMachineRequestor.createNewRequest(machine);
    }

    @Test(expected = MachineNotCreatedException.class)
    public void testCreateNewRequestThrowsMachineNotCreatedException() throws Exception {
        when(authorisingService.isAuthorised("user1")).thenReturn(true);

        when(systemBuildService.createNewMachine(any(Machine.class))).thenReturn("");

        Machine machine = new DesktopMachine("host20230328001", "user1", 1, 16, 160, "11", "21H2");

        virtualMachineRequestor.createNewRequest(machine);
    }

    @Test
    public void testCreateNewRequest() throws Exception {
        when(authorisingService.isAuthorised("user1")).thenReturn(true);

        when(systemBuildService.createNewMachine(any(Machine.class))).thenReturn("host20230328001");

        Machine machine = new DesktopMachine("host20230328001", "user1", 1, 16, 160, "11", "21H2");

        virtualMachineRequestor.createNewRequest(machine);

        Map<String, Map<String, Integer>> buildsByUser = virtualMachineRequestor.totalBuildsByUserForDay();

        assertEquals(1, buildsByUser.get("user1").get("Desktop").intValue());
        assertEquals(0, virtualMachineRequestor.totalFailedBuildsForDay());
    }

    @Test
    public void testtotalFailedBuildsForDay() throws Exception {
        when(authorisingService.isAuthorised("user1")).thenReturn(true);

        when(systemBuildService.createNewMachine(any(Machine.class))).thenReturn("");

        Machine machine = new DesktopMachine("host20230328001", "user1", 1, 16, 160, "11", "21H2");

        try {
            virtualMachineRequestor.createNewRequest(machine);
        } catch (MachineNotCreatedException e){

        }

        assertEquals(1, virtualMachineRequestor.totalFailedBuildsForDay());
    }
}
