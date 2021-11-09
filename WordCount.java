import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
    //key is the TERM value is the Count & Document it is from!!
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>{
        String filename;
        String parentName;
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            //want to get rid of punctuation and uppercase letters.
            //reference: https://www.fatalerrors.org/a/mapreduce-programming-document-inverted-index.html
            //get File Name:
            FileSplit split = (FileSplit) context.getInputSplit();
            filename = split.getPath().getName();
            Path path = split.getPath();
            path = path.getParent();
            parentName = path.getName();
            //don't want numbers and want to be lower case.

            StringTokenizer itr = new StringTokenizer(value.toString().toLowerCase().replaceAll("\\d","").replaceAll("[^a-zA-Z ]",""));
            while (itr.hasMoreTokens()) {
                //stop list 
                if(wordDoc.equals("if") ||
                            wordDoc.equals("he")||
                            wordDoc.equals("she")||
                            wordDoc.equals("and")||
                            wordDoc.equals("can")||
                            wordDoc.equals("of")||
                            wordDoc.equals("a") ||
                            wordDoc.equals("an") ||
                            wordDoc.equals("to") ||
                            wordDoc.equals("the") ||
                            wordDoc.equals("in") ||
                            wordDoc.equals("is") ||
                            wordDoc.equals("it") ||
                            wordDoc.equals("from") ||
                            wordDoc.equals("for") ||
                            wordDoc.equals("not") ||
                            wordDoc.equals("this") ||
                            wordDoc.equals("i")
                    ) {
                        continue;
                    }
                word.set(itr.nextToken()+ "->" + filename);
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReducer.class);
        job.setNumReduceTasks(1);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //adding multiple files. to this path try this !
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

//Notes on the Inverting Indexes:
//want  to sort by BOTH FOLDER and FILE NAME and by WORD!! -- need to modify this as such
