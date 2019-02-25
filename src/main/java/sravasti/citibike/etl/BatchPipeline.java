package sravasti.citibike.etl;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

public class BatchPipeline {
	public static void main(String[] args) {
		CitibikeETLBatchOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(CitibikeETLBatchOptions.class);
		Pipeline pipeline = Pipeline.create(options);

		PCollection<String> csvlines = pipeline
				.apply(TextIO.read().from(options.getInputFile()));

		PCollection<CitiBike> citiBikeStructData = csvlines.apply(ParDo.of(new CitiBikeFn()));
		PCollection<KV<String, Long>> tripDurationByUserType = citiBikeStructData
				.apply(ParDo.of(new TripDurationByUserType()));

		PCollection<KV<String, Long>> totalTripDurationByUserType = tripDurationByUserType
				.apply(Sum.<String>longsPerKey());

		totalTripDurationByUserType.apply(BigQueryIO.<KV<String, Long>>write().to(SchemaUtil.TBL_NAME)
				   .withSchema(SchemaUtil.SUM_BY_CONTENT_CAT_TYPE_SCHEMA)
			       .withFormatFunction(tuple -> TableRowUtil.getTableRow(tuple))
			       .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND));
		pipeline.run().waitUntilFinish();
	}
}
