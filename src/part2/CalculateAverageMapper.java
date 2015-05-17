package part2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CalculateAverageMapper extends MapReduceBase
	implements Mapper<LongWritable, Text, Text, SumCountPair> {

	public void map(LongWritable key, Text value, OutputCollector<Text, SumCountPair> output, 
		Reporter reporter) throws IOException{
			String delimiter = " "; 
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
		
			// TODO : Get (word, value) from tokenizer and emit a key-value with 
			// 		  	key	  : word
			// 		  	value : a SumCountPair instance	
			while (tokenizer.hasMoreTokens()) {
				String aWord = tokenizer.nextToken();
				String sum = tokenizer.nextToken();
				SumCountPair pair = new SumCountPair(Integer.valueOf(sum) ,1);
				output.collect(new Text(aWord), pair);
			}
	}
}

