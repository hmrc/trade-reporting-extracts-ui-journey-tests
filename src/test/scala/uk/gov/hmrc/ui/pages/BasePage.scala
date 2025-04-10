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
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.component.PageObject

abstract class BasePage(relativeUrl: String, relativeTitle: String = "") extends PageObject {

  protected val baseUrl: String   = TestEnvironment.url("trade-reporting-extracts-frontend")
  protected def url: String       = baseUrl + relativeUrl
  protected def pageTitle: String = s"$relativeTitle - Trade Reporting Extracts - GOV.UK"

  def continue(): Unit = {
    assertUrl(url)
    click(By.cssSelector("button.govuk-button"))
  }

  def selectOption(index: Int): Unit =
    click(By.cssSelector(s"#value_$index"))

  protected def selectRadioOption(index: Int): BasePage = {
    assertUrl(url)
    selectOption(index)
    this
  }

  protected def selectYesNoOption(value: Boolean): Unit =
    if (value) click(By.cssSelector("#value")) else click(By.cssSelector("#value-no"))

  def assertPageTitle(titleToCheck: String = pageTitle): Unit =
    assert(getTitle == titleToCheck, s"Page title was '$getTitle', but expected: '$titleToCheck'")

  protected def assertUrl(url: String): Unit =
    assert(getCurrentUrl == url, s"Url was: $getCurrentUrl, but expected is $url")
}
