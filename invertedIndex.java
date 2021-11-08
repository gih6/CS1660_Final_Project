import java.io.IOException;
import java.util.HashMap;
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
//reference to this is from https://github.com/imehrdadmahdavi/map-reduce-inverted-index/blob/master/InvertedIndex.java
//reference for running jobs: http://www-scf.usc.edu/~shin630/Youngmin/files/HadoopInvertedIndexV5.pdf
public class invertedIndex{
    //key is the TERM value is the Count & Document it is from!!
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, Text>{
        String filename;
        String parentName;
        private Text word = new Text();
        private Text doc = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            //want to get rid of punctuation and uppercase letters.
            //get File Name:
            FileSplit split = (FileSplit) context.getInputSplit();
            filename = split.getPath().getName();
            Path path = split.getPath();
            path = path.getParent();
            parentName = path.getName();
            //here setting document name in TEXT FILE!!!
            doc.set(filename);
            //make sure only lower cased words are used and no numbers counted!!
            StringTokenizer itr = new StringTokenizer(value.toString().toLowerCase().replaceAll("\\d","").replaceAll("[^a-zA-Z ]",""));
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, doc);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text,Text,Text,Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {

           //Iterate through and COUNT ALL WORDS within document with HASH MAP!!!
            //fastest way to count values.
            HashMap<String,Integer> count = new HashMap<String,Integer>();
            for(Text val: values){
                if(count.containsKey(val.toString())){
                    count.put(val.toString(),count.get(val.toString())+1); // adding to existing on
                }else{
                    count.put(val.toString(),1); //first instance of word in file
                }
            }
            //now have created map of all files used.
            StringBuilder list = new StringBuilder();
            for(String file: count.keySet()){
                list.append(file + ":" +count.get(file) + "," );
            }
            result.set(list.toString());
            context.write(key, result);
        }
    }


    //CAN change the amount of like args to do different things???
    public static void main(String[] args) throws Exception {
        //testing if 0 then do inverted index algorithm

            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "inverted Index");
            job.setJarByClass(invertedIndex.class);
            job.setMapperClass(TokenizerMapper.class);
            job.setReducerClass(IntSumReducer.class);
            job.setNumReduceTasks(1);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            //adding multiple files. to this path try this !
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);


    }
}

//Notes on the Inverting Indexes:
//want  to sort by BOTH FOLDER and FILE NAME and by WORD!! -- need to modify this as such
/*
bye	5722018411:1
goodbye	6722018415:1
hadoop	6722018415:2
hello	5722018411:1 6722018415:1
world	5722018411:2
 */