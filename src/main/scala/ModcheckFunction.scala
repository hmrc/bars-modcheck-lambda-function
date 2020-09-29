import java.util

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.jessecoyle.JCredStash

class ModcheckFunction {
  def handler(input: String, context: Context): Int = {
    val bucket = System.getenv("BUCKET_NAME")
    val region = System.getenv("AWS_REGION")

    val retriever = new ModcheckRetriever(new ExtendedHtmlUnitDriver(), "https://www.vocalink.com/tools/modulus-checking/", context.getLogger)

    val writer = new ModcheckWriter(AmazonS3ClientBuilder.standard().withRegion(region).build(), bucket)

    writer.write(retriever.retrieve())

    0
  }
}

object ModcheckFunction {
  val context: java.util.HashMap[String,String] = {
    val hm = new util.HashMap[String,String]()
    hm.put("role", "bacs_modcheck")
    hm
  }
  def retrieveCredential(credName: String): String = {
    new JCredStash().getSecret(credName, context)
  }
}
