package raspruner.CLI;


import com.sun.management.OperatingSystemMXBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;

/**
 * Created by BD-Loen on 2017/4/13.
 */
public class cltForRasp {
    static Socket server;

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

    private static PrintWriter connetToServer() throws Exception{
        server = new Socket("192.168.130.120", 13221);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());

        return out;

    }

    private static void talkTester(PrintWriter pw) throws Exception{
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = wt.readLine();
            pw.println(str);
            pw.flush();
            if (str.equals("end")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println("hellow word");
        cltForRasp cfr = new cltForRasp();
        cfr.seeMemory();
        PrintWriter pw = cltForRasp.connetToServer();
        pw.println("now we are connected");
        pw.flush();
        cltForRasp.talkTester(pw);

    }
}
