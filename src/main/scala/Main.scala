import sttp.client4.quick.*
import sttp.client4.Response
import sttp.model.StatusCode

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties


def fetchRandomMeal(): String = 
  val response: Response[String] = quickRequest
    .get(uri"https://www.themealdb.com/api/json/v1/1/random.php")
    .send()

  if response.code == StatusCode.Ok then
    val json = ujson.read(response.body)
    val meal: String = json("meals")(0).toString()
    meal
  else
    println(s"Failed to get response")
    ""


def sendMessage(topic: String, key: String, value: String): Unit =
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:29092")
  props.put("key.serializer", classOf[StringSerializer].getName)
  props.put("value.serializer", classOf[StringSerializer].getName)

  val producer = new KafkaProducer[String, String](props)
  try
    producer.send(new ProducerRecord[String, String](topic, key, value))
    println(s"Message [$key -> $value] sent to topic [$topic]")
  finally
    producer.close()


@main def main(): Unit =
  val meal = fetchRandomMeal()
  println(meal)
  // sendMessage(topic="test", key="breakfast", value="Hello World!")