package raspruner.CLI;


import com.sun.management.OperatingSystemMXBean;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Leon on 2017/4/13.
 */
public class CLIT {

    public static Double getMemery() {

        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        Double compare =  (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;

        // String str = compare.intValue() + "%";
        return compare;

    }

    static Socket server;

    private static void getMemoryInfoBy (int freq, PrintWriter pw)throws InterruptedException{
        int count = 0;
        Long stime = System.currentTimeMillis();
        Double ncompare = 0.0;
        while(count<=freq){
            ncompare = CLIT.getMemery();
            pw.println(ncompare);
            pw.flush();
//            System.out.println(ncompare);
            count++ ;
            Thread.sleep(20);
            if (count == freq){
                while(System.currentTimeMillis()-stime<1000){
//                    System.out.println("too fast");
//                    System.out.println(System.currentTimeMillis());
//                    System.out.println(stime);
                }
                stime = System.currentTimeMillis();
                count = 0;
            }

        }
    }


    public static void main(String[] args) throws Exception{
        server = new Socket(InetAddress.getLocalHost(), 13221);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

        CLIT.getMemoryInfoBy(50, out);

        while (true) {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end")) {
                break;
            }
            System.out.println(in.readLine());
        }
        server.close();
    }
}
