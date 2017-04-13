package raspruner.rasp;

import cn.edu.thu.tsfile.timeseries.FileFormat.TsFile;
import cn.edu.thu.tsfile.timeseries.filter.definition.FilterExpression;
import cn.edu.thu.tsfile.timeseries.filter.definition.FilterFactory;
import cn.edu.thu.tsfile.timeseries.read.LocalFileInput;
import cn.edu.thu.tsfile.timeseries.read.qp.Path;
import cn.edu.thu.tsfile.timeseries.read.query.QueryDataSet;

import java.util.ArrayList;

/**
 * Created by BD-Loen on 2017/4/14.
 */
public class serverWorker {

    private void timeQuery(int st, int et)throws Exception{
        String path = "rasp_data.ts";

        // read example : no filter
        LocalFileInput input = new LocalFileInput(path);
        TsFile readTsFile = new TsFile(input);
        ArrayList<Path> paths = new ArrayList<Path>();
        paths.add(new Path("rasp.mem_per"));

        FilterExpression timeFilter = FilterFactory.and(FilterFactory.gtEq(FilterFactory.timeFilterSeries(), (long)st, true)
                , FilterFactory.ltEq(FilterFactory.timeFilterSeries(), (long)et, false));

        QueryDataSet queryDataSet = readTsFile.query(paths, timeFilter, null);
        while(queryDataSet.hasNextRecord()){
            System.out.println(queryDataSet.getNextRecord());
        }
    }

    public static void main(String[] args) throws Exception{
        serverWorker swer = new serverWorker();
        swer.timeQuery(5,112);

    }
}
