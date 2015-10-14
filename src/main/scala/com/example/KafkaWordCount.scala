package com.example

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._


/**
 * Created by anand on 10/13/15.
 */
object KafkaWordCount {

  def main(args: Array[String]) {

    val (zkQuorum, group, topics, numThreads) = ("localhost:2181", "test-consumer-group", "word-count", 1)
    val sparkConf = new SparkConf(false)
      .setMaster("local[*]")
      .setAppName("KafkaWordCount")
      .set("spark.logConf", "true")

    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    val topicMap = topics.split(",").map((_, numThreads)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    words.foreachRDD(rdd => println("#####################rdd###################### " + rdd.first))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)

    println("*****************************" + wordCounts.print())

    ssc.start()
    ssc.awaitTermination()
  }

}
