# citibike-etl
This project is created to demonstrate how to do ETL with apache beam framework using NY Citi bike data.

## Running the project on local machine
Before running the project make sure you have google credentials set on the command line

export GOOGLE_APPLICATION_CREDENTIALS=Path to JSON key file (For example : /var/secrets/google/default-key.json)

mvn compile exec:java -Dexec.mainClass=sravasti.citibike.etl.BatchPipeline 
                      -Dexec.args="--inputFile=gs://citibike-springml/incoming/201306-citibike-tripdata1.csv 
                      --tempLocation=gs://citibike-springml/dataflow/temp" 
                      -Pdirect-runner
       
                      
