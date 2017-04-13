package raspruner.CLI;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * Created by BD-Loen on 2017/4/13.
 */
public class cltForRasp {
    private  void seeMemory(){
        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        System.out.println(totalvirtualMemory);
        System.out.println(freePhysicalMemorySize);
        Double compare =  (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;

        // String str = compare.intValue() + "%";
        System.out.println(compare);
    }

    public static void main(String[] args) {
        System.out.println("hellow word");
        cltForRasp cfr = new cltForRasp();
        cfr.seeMemory();
    }
}
