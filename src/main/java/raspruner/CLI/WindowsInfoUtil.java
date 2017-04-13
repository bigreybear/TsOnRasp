package raspruner.CLI;


import java.lang.management.ManagementFactory;


import com.sun.management.OperatingSystemMXBean;

/**
 * Created by BD-Loen on 2017/4/13.
 */
public class WindowsInfoUtil{
    public static Double getMemery() {

        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        Double compare =  (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;

        // String str = compare.intValue() + "%";
        return compare;

    }

    public static void main(String[] args) {
        System.out.println(WindowsInfoUtil.getMemery());
    }
}
