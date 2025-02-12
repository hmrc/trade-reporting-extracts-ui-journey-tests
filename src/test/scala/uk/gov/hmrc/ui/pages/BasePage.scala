/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedCondition, WebDriverWait}
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.driver.BrowserDriver

import java.time.Duration
import scala.util.Random

trait BasePage(relativeUrl: String) extends BrowserDriver with Matchers {

  protected def url: String = baseUrl + relativeUrl

  val baseUrl: String = TestEnvironment.url("trade-reporting-extracts-frontend")

  val continueButton = "continue-button"

  val random = new Random

  def findByID(id: String) = driver.findElement(By.id(id))

  def waitFor[T](condition: ExpectedCondition[T]): T = {
    val wait = new WebDriverWait(driver, Duration.ofSeconds(10))
    wait.until(condition)
  }

  def findByClassName(className: String) = driver.findElements(By.className(className))

  def submitPage(): Unit =
    findByID(continueButton).click()

  def onPage(pageTitle: String): Unit =
    if (driver.getTitle != pageTitle + "Authority Wizard")
      throw PageNotFoundException(
        s"Expected '$pageTitle' page, but found '${driver.getTitle}' page."
      )
}

case class PageNotFoundException(s: String) extends Exception(s)
