package part2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CalculateAverageCombiner extends MapReduceBase
	implements Reducer<Text, SumCountPair, Text, SumCountPair> {
		
	public void reduce(Text key, Iterator<SumCountPair> values,
		OutputCollector<Text, SumCountPair> output, Reporter reporter) throws IOException {	
		
		// TODO : Calculate (key, <SumCountPair1, SumCountPair2 ... >) 
		//		  to (key, SumCountPair1 + SumCountPair2 + ...)
		
		int sum = 0;
		int count = 0;
		while (values.hasNext()) {
			SumCountPair pair = values.next();
			sum += pair.getSum();
			count += pair.getCount();
		}
		
		SumCountPair newPair = new SumCountPair(sum, count);
		output.collect(key, newPair);
	}
}
