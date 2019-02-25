package sravasti.citibike.etl;

import org.apache.beam.sdk.values.KV;

import com.google.api.services.bigquery.model.TableRow;

public class TableRowUtil {
	public static TableRow getTableRow(KV<String, Long> tuple) {
		return new TableRow().set("user_type", tuple.getKey())
		.set("trip_duration", tuple.getValue());
	}
}
