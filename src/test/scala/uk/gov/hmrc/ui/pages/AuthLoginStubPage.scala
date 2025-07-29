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

object AuthLoginStubPage extends BasePage("", "Authority Wizard") {

  override val pageUrl = TestEnvironment.url("auth-login-stub") + "/gg-sign-in"

  private val redirectionUrl          = ACC_2_DashboardPage.pageUrl
  private val redirectUrlSelector     = By.cssSelector("#redirectionUrl")
  private val enrolmentKeySelector    = By.cssSelector("#enrolment\\[0\\]\\.name")
  private val identifierNameSelector  = By.cssSelector("#input-0-0-name")
  private val identifierValueSelector = By.cssSelector("#input-0-0-value")

  override def continue(): Unit =
    click(By.cssSelector("input.govuk-button"))

  def enterRedirectionUrl(continueUrl: String = redirectionUrl): Unit =
    sendKeys(redirectUrlSelector, redirectionUrl)

  def enterEnrollment(userCredentials: UserCredentials): Unit =
    userCredentials.enrolmentsData.foreach { data =>
      sendKeys(enrolmentKeySelector, data.enrolmentKey)
      sendKeys(identifierNameSelector, data.identifierName)
      sendKeys(identifierValueSelector, data.identifierValue)
    }
}
