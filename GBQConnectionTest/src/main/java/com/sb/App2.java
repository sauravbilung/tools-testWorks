package com.sb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

/*
 * 
 * Using BigQuery API Client Libraries
 * 
 * */

public class App2 {

	static String keyFilePath = "key.json";
	static String projectId = "projectId";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String query = "SELECT corpus FROM `bigquery-public-data.samples.shakespeare` GROUP BY corpus;";
		simpleQuery(query);
	}

	public static void simpleQuery(String query) throws FileNotFoundException, IOException {
		try {
			// Initialize client that will be used to send requests. This client only needs
			// to be created
			// once, and can be reused for multiple requests.
			BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId(projectId).setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(keyFilePath))).build().getService();

			// Create the query job.
			QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
           
			// To run batch Query.
			// Run at batch priority, which won't count toward concurrent rate limit.
			//QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).setPriority(QueryJobConfiguration.Priority.BATCH).build();

			// Execute the query.
			TableResult result = bigquery.query(queryConfig);

			// Print the results.
			result.iterateAll().forEach(rows -> rows.forEach(row -> System.out.println(row.getValue())));

			System.out.println("Query ran successfully");
		} catch (BigQueryException | InterruptedException e) {
			System.out.println("Query did not run \n" + e.toString());
		}
	}

}
