import com.amazonaws.services.lambda.runtime.LambdaLogger
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebElement}

import scala.util.{Failure, Success, Try}

class ModcheckRetriever(driver: ExtendedHtmlUnitDriver, baseUrl: String, logger: LambdaLogger) {

  def retrieve(): Array[Byte] = {
    driver.get(baseUrl)

    waitForElementToBeClickable(By.linkText("Modulus weight table data"), "Could not find Modulus Weight Table link.").click()

    org.apache.commons.io.IOUtils.toByteArray(driver.getPage.getWebResponse.getContentAsStream)
  }
  
  def waitForElementToBeClickable(by: By, errorMessage: String): WebElement = {
    Try{new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by))} match {
      case Success(element) ⇒ element
      case Failure(exception) ⇒ logger.log(errorMessage)
        throw exception
    }
  }
}