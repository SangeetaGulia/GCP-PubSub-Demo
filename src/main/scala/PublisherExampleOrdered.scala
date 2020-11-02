/*
import java.io.IOException
import java.util.concurrent.{ExecutionException, TimeUnit}

import com.google.cloud.pubsub.v1.Publisher
import com.google.protobuf.ByteString
import com.google.pubsub.v1.{PubsubMessage, TopicName}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}


object PublisherExampleOrdered extends App {
    val projectId = "dh-ce-dev-media-59188"
    val topicId = "pubsubdemo"
    publisherExample(projectId, topicId)

  @throws[IOException]
  @throws[ExecutionException]
  @throws[InterruptedException]
  def publisherExample(projectId: String, topicId: String): Unit = {
    val topicName = TopicName.of(projectId, topicId)
    val messageQueue = mutable.Queue(
      ("Initiate Data Modeller", 1),
      ("Initiate Data Transfer",2),
      ("Initiate Nomination Scoring",3),
      ("Initiate Targeting",4),
      ("Initiate Post Targeting Transfer",5),
      ("Initiate Allocation",6),
      ("Initiate QA Stats",7))
    Try { // Create a publisher instance with default settings bound to the topic
      val publisher = Publisher.newBuilder(topicName).build
      messageQueue.map { case (message, orderingKey) =>
        val data = ByteString.copyFromUtf8(message)
//        val pubsubMessage = PubsubMessage.newBuilder.setData(data, orderingKey).build
        val messageIdFuture = publisher.publish(topicName, data, orderingKey)
        // Once published, returns a server-assigned message id (unique within the topic)
        val messageId = messageIdFuture.get
        System.out.println("Published message ID: " + messageId)
      }
     // When finished with the publisher, shutdown to free up resources.
      publisher.shutdown
      publisher.awaitTermination(1, TimeUnit.MINUTES)
    } match {
      case Success(data) =>
        println(s"Received ${data}")
      case Failure(exception: Exception) =>
        exception.printStackTrace()
        println("\n\n Got exception : " + exception.getMessage)
    }
  }

}*/
