import java.util.concurrent.TimeUnit

import com.google.cloud.pubsub.v1.{AckReplyConsumer, MessageReceiver, Subscriber}
import com.google.pubsub.v1.{ProjectSubscriptionName, PubsubMessage}


object SubscribeAsyncExample2 extends App {

// TODO(developer): Replace these variables before running the sample.
    val projectId = "dh-ce-dev-media-59188"
    val subscriptionId = "pubsub-subscription-2"
    subscribeAsyncExample(projectId, subscriptionId)

  def subscribeAsyncExample(projectId: String, subscriptionId: String): Unit = {
    val subscriptionName: ProjectSubscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId)
    // Instantiate an asynchronous message receiver.
    val receiver: MessageReceiver = new MessageReceiver {
      override def receiveMessage(message: PubsubMessage, consumer: AckReplyConsumer): Unit = {
        // Handle incoming message, then ack the received message.
        println("Id: " + message.getMessageId)
        println("Data: " + message.getData.toStringUtf8)
        consumer.ack()
      }
    }

      val subscriber = Subscriber.newBuilder(subscriptionName, receiver).build
      // Start the subscriber.
      subscriber.startAsync.awaitRunning()
      println("Listening for messages on %s:\n", subscriptionName.toString)
      // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
      subscriber.awaitTerminated(120, TimeUnit.SECONDS)
    }

}