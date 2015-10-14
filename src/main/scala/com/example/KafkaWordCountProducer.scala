package com.example

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

/**
 * Created by anand on 10/13/15.
 */
object KafkaWordCountProducer {

  def main(args: Array[String]) {

    val (topic, msgPerSec, wordsPerMsg, brokers) = ("word-count", 10, 5, "localhost:9092")

    // Zookeeper connection properties
    val props = new HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "DATA-S5")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    // Send some messages
    while (true) {
      (1 to msgPerSec).foreach { messageNum =>
        val str = (1 to wordsPerMsg.toInt).map(x => scala.util.Random.nextInt(10).toString).mkString("xxx yyy")
        println(s"### Message : ${str}")
        val message = new ProducerRecord[String, String](topic, null, str)
        producer.send(message)
      }

      Thread.sleep(1000)
    }
  }

}
