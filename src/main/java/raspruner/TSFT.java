package raspruner;

import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;

import cn.edu.thu.tsfile.common.utils.RandomAccessOutputStream;
import cn.edu.thu.tsfile.common.utils.TSRandomAccessFileWriter;
import cn.edu.thu.tsfile.timeseries.FileFormat.TsFile;
import cn.edu.thu.tsfile.timeseries.write.record.DataPoint;
import cn.edu.thu.tsfile.timeseries.write.record.TSRecord;
import cn.edu.thu.tsfile.timeseries.write.record.datapoint.FloatDataPoint;
import cn.edu.thu.tsfile.timeseries.write.record.datapoint.IntDataPoint;

/**
 * Created by BD-Loen on 2017/4/12.
 */
public class TSFT {
    public static void main(String args[]) {
        try {
            String path = "test.ts";
            String s = "{\n" +
                    "    \"schema\": [\n" +
                    "        {\n" +
                    "            \"measurement_id\": \"sensor_1\",\n" +
                    "            \"data_type\": \"FLOAT\",\n" +
                    "            \"encoding\": \"RLE\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"measurement_id\": \"sensor_2\",\n" +
                    "            \"data_type\": \"INT32\",\n" +
                    "            \"encoding\": \"TS_2DIFF\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"measurement_id\": \"sensor_3\",\n" +
                    "            \"data_type\": \"INT32\",\n" +
                    "            \"encoding\": \"TS_2DIFF\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"row_group_size\": 134217728\n" +
                    "}";
            JSONObject schemaObject = new JSONObject(s);

            TSRandomAccessFileWriter output = new RandomAccessOutputStream(new File(path));
            TsFile tsFile = new TsFile(output, schemaObject);

            tsFile.writeLine("device_1,1, sensor_1, 1.2, sensor_2, 20, sensor_3,");
            tsFile.writeLine("device_1,2, sensor_1, , sensor_2, 20, sensor_3, 50");
            tsFile.writeLine("device_1,3, sensor_1, 1.4, sensor_2, 21, sensor_3,");
            tsFile.writeLine("device_1,4, sensor_1, 1.2, sensor_2, 20, sensor_3, 51");

            TSRecord tsRecord1 = new TSRecord(6, "device_1");
            tsRecord1.dataPointList = new ArrayList<DataPoint>() {
                {
                    add(new FloatDataPoint("sensor_1", 7.2f));
                    add(new IntDataPoint("sensor_2", 10));
                    add(new IntDataPoint("sensor_3", 11));
                }
            };
            TSRecord tsRecord2 = new TSRecord(7, "device_1");
            tsRecord2.dataPointList = new ArrayList<DataPoint>() {
                {
                    add(new FloatDataPoint("sensor_1", 6.2f));
                    add(new IntDataPoint("sensor_2", 20));
                    add(new IntDataPoint("sensor_3", 21));
                }
            };
            TSRecord tsRecord3 = new TSRecord(8, "device_1");
            tsRecord3.dataPointList = new ArrayList<DataPoint>() {
                {
                    add(new FloatDataPoint("sensor_1", 9.2f));
                    add(new IntDataPoint("sensor_2", 30));
                    add(new IntDataPoint("sensor_3", 31));
                }
            };
            tsFile.writeLine(tsRecord1);
            tsFile.writeLine(tsRecord2);
            tsFile.writeLine(tsRecord3);

            tsFile.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
