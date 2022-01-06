import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import com.amazonaws.services.s3.AmazonS3ClientBuilder

class ModcheckFunction {
  def handler(notUsed: String, context: Context): Int = {
    val bucket = System.getenv("BUCKET_NAME")
    val region = System.getenv("AWS_REGION")

    val logger = if(Option(context).isEmpty) new LambdaLogger {override def log(string: String): Unit = println(string)}
    else context.getLogger

    val retriever = new ModcheckRetriever(new ExtendedHtmlUnitDriver(), "https://www.vocalink.com/tools/modulus-checking/", logger)

    val writer = new AWSModcheckWriter(AmazonS3ClientBuilder.standard().withRegion(region).build(), bucket)

    writer.write(retriever.retrieve())

    0
  }
}
