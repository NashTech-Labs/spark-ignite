# spark-ignite  
A sample application to demonstrate sharing state of RDDs across multiple spark applications using Apache Spark and Apache Ignite.  
###How to Package and submit Applications
There are two application in the source namely: RDDProducer and RDDConsumer. Run the following command in the project root direcotory to package the application:  
```{r, engine='bash', count_lines}
$ sbt assmebly
```
When jar is produced. Submit both the applications by changing only the --class argument as: 

```{r, engine='bash', count_lines}
$ spark-submit --class "com.knoldus.RDDProducer"  --master master_url path_to_jar
$ spark-submit --class "com.knoldus.RDDConsumer"  --master master_url path_to_jar
```
Checkout the detailed set up instructions at this blog: https://blog.knoldus.com/2016/07/26/sharing-rdds-states-across-spark-applications-with-apache-ignite/
