import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectRequest}
import org.apache.commons.io.output.ByteArrayOutputStream

import java.io.{ByteArrayInputStream, FileOutputStream}
import java.time.LocalDateTime

trait ModcheckWriter {
  val fileName = "valacdos.txt"
  def write(data: Array[Byte]): Unit
}

class AWSModcheckWriter(s3client: AmazonS3, bucketName: String) extends ModcheckWriter {
  override def write(data: Array[Byte]): Unit = {
    val inputstream = new ByteArrayInputStream(data)
    val metadata = new ObjectMetadata()

    metadata.addUserMetadata("upload-datetime", LocalDateTime.now().toString)
    s3client.putObject(
      new PutObjectRequest(bucketName, fileName, inputstream, metadata)
    )
  }
}

class LocalModcheckWriter(outputFolder: String) extends ModcheckWriter {
  override def write(data: Array[Byte]): Unit = {
    val output = new FileOutputStream(s"${outputFolder}${fileName}")
    output.write(data)
    output.flush()
    output.close()
  }
}
