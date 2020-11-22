package com.sb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.QueryJobConfiguration;

/*
 * 
 * Using BigQuery API Client Libraries
 * 
 * */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, JobException, InterruptedException 
    {
    	String keyFilePath = "key.json";
    	String projectId = "projectId";
    	BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId(projectId).setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(keyFilePath))).build().getService();
    	String query = "SELECT corpus FROM `bigquery-public-data.samples.shakespeare` GROUP BY corpus limit 10;";
    	QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
    	
    	for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
    		  for (FieldValue val : row) {
    		    System.out.printf("%s,", val.toString());
    		  }
    		  System.out.printf("\n");
    		}
    }
}
