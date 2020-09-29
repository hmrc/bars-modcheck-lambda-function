import java.io.ByteArrayInputStream
import java.time.LocalDateTime

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.{AmazonS3Exception, ObjectMetadata, PutObjectRequest}

class ModcheckWriter(s3client: AmazonS3, bucketName: String) {
  val fileName = "valacdos.txt"

  def write(data: Array[Byte]): Unit = {

    val inputstream = new ByteArrayInputStream(data)
    val metadata = new ObjectMetadata()
    metadata.addUserMetadata("upload-datetime", LocalDateTime.now().toString)
    s3client.putObject(
      new PutObjectRequest(bucketName, fileName, inputstream, metadata)
    )
  }
}
