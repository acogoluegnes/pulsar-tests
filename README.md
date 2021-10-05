```shell
cd /tmp
wget https://archive.apache.org/dist/pulsar/pulsar-2.8.1/apache-pulsar-2.8.1-bin.tar.gz
tar xf apache-pulsar-2.8.1-bin.tar.gz
cd apache-pulsar-2.8.1
bin/pulsar standalone
```

```shell
bin/pulsar-admin topics create-partitioned-topic --partitions 3 my-topic
```