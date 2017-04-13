package raspruner.rasp;

import cn.edu.thu.tsfile.common.utils.RandomAccessOutputStream;
import cn.edu.thu.tsfile.common.utils.TSRandomAccessFileWriter;
import cn.edu.thu.tsfile.timeseries.FileFormat.TsFile;
import cn.edu.thu.tsfile.timeseries.write.record.DataPoint;
import cn.edu.thu.tsfile.timeseries.write.record.TSRecord;
import cn.edu.thu.tsfile.timeseries.write.record.datapoint.DoubleDataPoint;
import cn.edu.thu.tsfile.timeseries.write.record.datapoint.FloatDataPoint;
import cn.edu.thu.tsfile.timeseries.write.record.datapoint.IntDataPoint;
import com.sun.management.OperatingSystemMXBean;
import org.json.JSONObject;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 * Created by BD-Loen on 2017/4/14.
 */
public class sensorSavor {
    static long totalPhysicMemSize = 0L;
    static private OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    static {
        totalPhysicMemSize = osmxb.getTotalPhysicalMemorySize();
    }

    private static double memWatchDog(){
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        //System.out.println(freePhysicalMemorySize);
        Double compare =  (1 - freePhysicalMemorySize * 1.0 / totalPhysicMemSize) * 100;

        // String str = compare.intValue() + "%";
        // System.out.println(compare);
        return compare;
    }


    private void fileFiller(TsFile tsf, int maxFreq, int maxCounts) throws Exception{
        int sleepTime = 1000/maxFreq;
        int i = 0;
        while(i<=maxCounts){
            Thread.sleep((long)sleepTime);
            TSRecord record = new TSRecord(i, "rasp");
            record.dataPointList = new ArrayList<DataPoint>() {
                {
                    add(new DoubleDataPoint("mem_per", memWatchDog()));
                }
            };
            tsf.writeLine(record);
            i++;
            System.out.println(i);
        }
        return;

    }

    public static void main(String args[]) throws Exception{
        try {
            String path = "rasp_data.ts";
            String s = "{\n" +
                    "    \"schema\": [\n" +
                    "        {\n" +
                    "            \"measurement_id\": \"mem_per\",\n" +
                    "            \"data_type\": \"DOUBLE\",\n" +
                    "            \"encoding\": \"RLE\"\n" +
                    "        },\n" +
                    "    ],\n" +
                    "    \"row_group_size\": 400,\n" +
                    "    \"page_size\": 30,\n" +
                    "}";
            JSONObject schemaObject = new JSONObject(s);

            TSRandomAccessFileWriter output = new RandomAccessOutputStream(new File(path));
            TsFile tsFile = new TsFile(output, schemaObject);

            sensorSavor ssvor = new sensorSavor();
            ssvor.fileFiller(tsFile, 4, 300);

            tsFile.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
