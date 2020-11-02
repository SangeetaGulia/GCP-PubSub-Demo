import com.google.api.core.ApiFuture
import com.google.cloud.pubsub.v1.Publisher
import com.google.protobuf.ByteString
import com.google.pubsub.v1.PubsubMessage
import com.google.pubsub.v1.TopicName

import scala.collection.mutable
import java.io.IOException
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

import scala.util.{Failure, Success, Try}


object PublisherExample extends App {
    val projectId = "dh-ce-dev-media-59188"
    val topicId = "pubsubdemo"
    publisherExample(projectId, topicId)

  @throws[IOException]
  @throws[ExecutionException]
  @throws[InterruptedException]
  def publisherExample(projectId: String, topicId: String): Unit = {
    val topicName = TopicName.of(projectId, topicId)
    val messageQueue = mutable.Queue("Initiate Data Modeller", "Initiate Data Transfer", "Initiate Nomination Scoring",
    "Initiate Targeting", "Initiate Post Targeting Transfer", "Initiate Allocation", "Initiate QA Stats")
    Try { // Create a publisher instance with default settings bound to the topic
      val publisher = Publisher.newBuilder(topicName).build
      messageQueue.map { message =>
        val data = ByteString.copyFromUtf8(message)
        val pubsubMessage = PubsubMessage.newBuilder.setData(data).build
        val messageIdFuture = publisher.publish(pubsubMessage)
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

}