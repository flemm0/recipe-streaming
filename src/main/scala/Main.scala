import sttp.client4.quick.*
import sttp.client4.Response
import sttp.model.StatusCode


def fetchRandomMeal(): Unit = 
  val response: Response[String] = quickRequest
    .get(uri"https://www.themealdb.com/api/json/v1/1/random.php")
    .send()

  if response.code == StatusCode.Ok then
    val json = ujson.read(response.body)
    val meals = json("meals")
    println(meals)
  else
    println(s"Failed to get response")


@main def main(): Unit =
  fetchRandomMeal()
