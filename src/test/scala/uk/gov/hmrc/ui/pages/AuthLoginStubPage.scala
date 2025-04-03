/*
 * Copyright 2025 HM Revenue & Customs
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

class AuthLoginStubPage
import org.openqa.selenium.By
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.models.UserCredentials

object AuthLoginStubPage extends BasePage("") {

  val title: String = "Authority Wizard"

  val redirectionUrl = s"$baseUrl/request-customs-declaration-data"

  override val url: String = TestEnvironment.url("auth-login-stub") + "/gg-sign-in"

  private val submitSelector          = By.cssSelector("#submit-top")
  private val redirectUrlSelector     = By.cssSelector("#redirectionUrl")
  private val enrolmentKeySelector    = By.cssSelector("#enrolment\\[0\\]\\.name")
  private val identifierNameSelector  = By.cssSelector("#input-0-0-name")
  private val identifierValueSelector = By.cssSelector("#input-0-0-value")

  def show(): Unit = {
    get(url)
    assert(getTitle == title, s"Title was: $getTitle, but expected is $title")

  }

  override def continue(): Unit = {
    get(url)
    assertUrl(url)
    click(By.cssSelector("#submit-top"))
  }

  def loginAs(userCredentials: UserCredentials, continueUrl: String = redirectionUrl): Unit = {
    sendKeys(redirectUrlSelector, continueUrl)
    userCredentials.enrolmentsData.foreach { data =>
      sendKeys(enrolmentKeySelector, data.enrolmentKey)
      sendKeys(identifierNameSelector, data.identifierName)
      sendKeys(identifierValueSelector, data.identifierValue)
    }
    click(submitSelector)
  }
}
