import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.logging.*;

import org.apache.storm.spout.SchemeAsMultiScheme;

import org.apache.storm.kafka.Broker;
import org.apache.storm.kafka.ZkHosts;
// import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.StaticHosts;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.KafkaConfig;
import org.apache.storm.kafka.StringScheme;

import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.trident.GlobalPartitionInformation;

public class KafkaStormSample {
    private final static Logger LOGGER = Logger.getLogger(KafkaStormSample.class.getName());

    public static void main(String[] args) throws Exception{

        /*
        Config config = new Config();
        config.setDebug(true);
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        String zkConnString = "192.168.10.51:2181";
        String topic = "my-first-topic";
        BrokerHosts hosts = new ZkHosts(zkConnString);
        
        SpoutConfig kafkaSpoutConfig = new SpoutConfig(hosts, topic, "/" + topic, UUID.randomUUID().toString());
        // System.out.println("~~~~~~~~~~~~~~~~~~~");
        
  

        kafkaSpoutConfig.bufferSizeBytes = 1024 * 1024 * 4;
        kafkaSpoutConfig.fetchSizeBytes = 1024 * 1024 * 4;
        // kafkaSpoutConfig.forceFromStart = true;
        // kafkaSpoutConfig.startOffsetTime = kafka.api.OffsetRequest.EarliestTime();
        kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka-spout", new KafkaSpout(kafkaSpoutConfig));
        builder.setBolt("word-spitter", new SplitBolt()).shuffleGrouping("kafka-spout");

        builder.setBolt("word-counter", new CountBolt()).shuffleGrouping("word-spitter");
            
        // LocalCluster cluster = new LocalCluster();
        // cluster.submitTopology("KafkaStormSample", config, builder.createTopology());
        System.setProperty("storm.jar", "/home/ubuntu/storm/target/storm-1.0-SNAPSHOT-jar-with-dependencies.jar");
        config.put(Config.NIMBUS_HOST, "localhost");
        StormSubmitter.submitTopologyWithProgressBar("KafkaStormSample", config, builder.createTopology());
        // Thread.sleep(10000);
        
        // cluster.shutdown();
        */

        Config conf = new Config();
        conf.setDebug(true);
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        conf.put(Config.NIMBUS_HOST, "localhost");

        String topic = "my-first-topic";
        KafkaSpoutConfig<String, String> config = KafkaSpoutConfig
            .builder("192.168.10.51:9092,192.168.10.54:9092,192.168.10.55:9092",topic)
			.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_EARLIEST)
            .setGroupId("test-2001").build();

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("kafka-spout", new KafkaSpout<>(config), 1);
        // builder.setSpout("kafka-reader", kafkaSpout, 1).setNumTasks(1);
        builder.setBolt("word-spitter", new SplitBolt(), 1).shuffleGrouping("kafka-spout");
        builder.setBolt("word-counter", new CountBolt(), 1).shuffleGrouping("word-spitter");

        StormSubmitter.submitTopologyWithProgressBar("KafkaStormSample", conf, builder.createTopology());


    }
}
