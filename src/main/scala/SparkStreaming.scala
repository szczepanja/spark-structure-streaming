import org.apache.spark.sql.SparkSession

object SparkStreaming extends App {

  val spark = SparkSession
    .builder
    .master("local[*]")
    .getOrCreate()

  import org.apache.spark.sql.types._

  val schema =
    StructType(
      StructField("id", IntegerType, nullable = false) ::
        StructField("name", StringType, nullable = false) :: Nil)


  val inputs = spark.readStream
    .format("csv")
    .option("header", value = true)
    .schema(schema)
    .load("C:/Users/a.szczepanik/Projekty/SparkStreaming/docs")

  val sq = inputs.writeStream.format("console").start
  sq.awaitTermination(10000)
}
