package sravasti.citibike.etl;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.google.common.collect.ImmutableList;

public class SchemaUtil {
	public static String TBL_NAME = "springmlproject:citi_bike_data.total_trip_duration_by_user_type";
	
	public static final TableSchema SUM_BY_CONTENT_CAT_TYPE_SCHEMA = new TableSchema().setFields(
			ImmutableList.of(new TableFieldSchema().setName("user_type").setType("STRING"),
					new TableFieldSchema().setName("trip_duration").setType("NUMERIC")));
}
