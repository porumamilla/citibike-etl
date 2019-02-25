package sravasti.citibike.etl;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;
public interface CitibikeETLBatchOptions extends PipelineOptions  {
	@Description("Path of the file to read from")
    @Default.String("gs://citibike-springml/incoming/201306-citibike-tripdata.csv")
	ValueProvider<String> getInputFile();
    void setInputFile(ValueProvider<String> value);

}
