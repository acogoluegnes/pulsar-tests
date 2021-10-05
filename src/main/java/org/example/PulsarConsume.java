package org.example;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.apache.pulsar.client.api.SubscriptionType;

public class PulsarConsume {

  public static void main(String[] args) throws Exception {
    PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();

    Consumer consumer =
        client
            .newConsumer()
            .topic("my-topic")
            .subscriptionName("1")
            .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
            .subscriptionType(SubscriptionType.Failover)
            .subscribe();

    while (true) {
      Message msg = consumer.receive();

      try {
        System.out.println(new String(msg.getData()) + " " + msg.getTopicName());

        consumer.acknowledge(msg);
        Thread.sleep(7);
      } catch (Exception e) {
        consumer.negativeAcknowledge(msg);
      }
    }
  }
}
