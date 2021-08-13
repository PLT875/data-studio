# Data Studio

Various spark applications to ingest various datasets of https://archive.org/details/stackexchange

# Pre-requisites
* sbt (1.5.5)
* Scala (2.12.13)
* Docker

# Environment setup

Run tests
```
sbt test
```

Build JAR
```
sbt package
```

Make a data directory to extract the 7Z files from stackexhange into. 
```
mkdir data
```

In this exercise the stackoverflow.com-PostLinks.7z and stackoverflow.com-Users.7z were tested

Start up the Spark and PostgreSQL instances using Docker
```
docker-compose up
```

This will also mount volumes containing the .jars and data sets required to ingest and analyze the data.

# Examples

Ingest post links XML
```
docker exec -it data-studio-main_spark_1 bin/spark-submit \
    --class com.example.entrypoints.PostLinksIngestion \
    --jars libs/spark-xml_2.12-0.12.0.jar,libs/postgresql-42.2.23.jar \
    --master <MASTER URL> 
    --deploy-mode client \
    /opt/bitnami/spark/data-studio-jars/data-studio_2.12-0.1.jar /opt/bitnami/spark/data/<post links XML>
```

```
docker exec -it data-studio_postgresql_1 bash
bash-5.1# psql -U spark -d stackoverflow
psql (13.3)
Type "help" for help.

stackoverflow=# select count(*) from post_links;
  count
---------
 7499403
(1 row)

stackoverflow=# select * from post_links limit 5;
      _CreationDate      | _Id | _LinkTypeId | _PostId | _RelatedPostId
-------------------------+-----+-------------+---------+----------------
 2010-04-26 02:59:48.13  |  19 |           1 |     109 |          32412
 2010-04-26 02:59:48.6   |  37 |           1 |    1970 |         617600
 2010-04-26 02:59:48.647 |  42 |           1 |    2154 |        2451138
 2010-04-26 02:59:48.757 |  52 |           1 |    2572 |         209329
 2010-04-26 02:59:48.943 |  58 |           1 |    3376 |           2187
(5 rows)
```

Running [example](examples/Ingest_Users.png)