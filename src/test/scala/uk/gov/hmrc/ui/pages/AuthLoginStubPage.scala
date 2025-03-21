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

object AuthLoginStubPage extends BasePage("") {

  val title: String = "Authority Wizard"

  val redirectionUrl = s"$baseUrl/request-customs-declaration-data"

  override val url: String = TestEnvironment.url("auth-login-stub") + "/gg-sign-in"

  private val submitSelector      = By.cssSelector("#submit-top")
  private val redirectUrlSelector = By.cssSelector("#redirectionUrl")

  def show(): Unit = {
    get(url)
    assert(getTitle == title, s"Title was: $getTitle, but expected is $title")

  }

  def loginAs(continueUrl: String = redirectionUrl): Unit = {
    sendKeys(redirectUrlSelector, continueUrl)
    click(submitSelector)
  }
}
