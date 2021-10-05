package org.example;

import java.util.stream.IntStream;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarSend {

  public static void main(String[] args) throws Exception {
    PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();

    Producer<byte[]> producer = client.newProducer().topic("my-topic").create();

    IntStream.range(0, 10000)
        .forEach(
            i -> {
              try {
                producer.send(("content-" + i).getBytes());
              } catch (PulsarClientException e) {
                e.printStackTrace();
              }
            });
    System.out.println("done");
  }
}
