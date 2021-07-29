import sys
from awsglue.transforms import *
from awsglue.utils import getResolvedOptions
from pyspark.context import SparkContext
from awsglue.context import GlueContext
from awsglue.job import Job
import boto3

## @params: [JOB_NAME]
args = getResolvedOptions(sys.argv, ['JOB_NAME'])
sc = SparkContext()
glueContext = GlueContext(sc)
spark = glueContext.spark_session
job = Job(glueContext)
job.init(args['JOB_NAME'], args)


client = boto3.client('glue')

response = client.get_connection(
    Name='maxmind_geoip2_adm-rp-sl-qa',
    HidePassword=False
)

connection_url=response['Connection']['ConnectionProperties']['JDBC_CONNECTION_URL']
username=response['Connection']['ConnectionProperties']['USERNAME']
password=response['Connection']['ConnectionProperties']['PASSWORD']


datasource0 = glueContext.create_dynamic_frame.from_catalog(database = "maxmind geoip2 db", table_name = "archive", transformation_ctx = "datasource0")

applymapping1 = ApplyMapping.apply(frame = datasource0, mappings = [("geoname_id", "long", "geoname_id", "long"), ("city_name", "string", "name", "string"), ("country_name", "string", "country", "string"), ("country_iso_code", "string", "country_iso_code", "string"), ("subdivision_1_iso_code", "string", "region", "string"), ("partition_0", "string", "partition_0", "string")], transformation_ctx = "applymapping1")

resolvechoice2 = ResolveChoice.apply(frame = applymapping1, choice = "make_cols", transformation_ctx = "resolvechoice2")

dropnullfields3 = DropNullFields.apply(frame = resolvechoice2, transformation_ctx = "dropnullfields3").toDF()

dropnullfields3.write.format('jdbc').options( url=connection_url,
    driver='com.mysql.jdbc.Driver',
    dbtable='cities_staging_new',
    user=username,
    password=password).mode("overwrite").save()
    
job.commit()