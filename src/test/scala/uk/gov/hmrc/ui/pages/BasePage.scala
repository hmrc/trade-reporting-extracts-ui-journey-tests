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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.{FluentWait, Wait}
import org.openqa.selenium.support.ui.ExpectedConditions

import java.time.Duration

import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver

abstract class BasePage(relativeUrl: String, relativeTitle: String) extends PageObject {

  protected val baseUrl: String = TestEnvironment.url("trade-reporting-extracts-frontend")
  val pageUrl: String           = baseUrl + relativeUrl
  val pageTitle: String         = s"$relativeTitle - Get customs declaration data for imports and exports - GOV.UK"

  def fluentWait: Wait[WebDriver] = new FluentWait[WebDriver](Driver.instance)
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofSeconds(2))

  def navigateTo(urlToGet: String = pageUrl): Unit =
    get(urlToGet)

  def continue(): Unit =
    click(By.cssSelector("button.govuk-button"))

  def clearAndInputKeys(input: By, value: String): Unit =
    sendKeys(input, value)

  // NOTE: ONLY for a list of radio buttons and checkboxes, NOT FOR YES/NO PAGES.
  def selectOptionByIndex(index: Int): Unit =
    click(By.cssSelector(s"#value_$index"))

  def selectOptionByValue(value: String): Unit =
    click(By.cssSelector(s"input[value='$value']"))

  // NOTE: ONLY for yes/no radio buttons.
  def selectYesNo(index: Int): Unit = {
    if (index == 0) click(By.cssSelector(s"#value"))
    if (index == 1) click(By.cssSelector(s"#value-no"))
  }

  def assertPageTitle(titleToCheck: String = pageTitle): Unit =
    assert(
      getTitle.contains(titleToCheck),
      s"Page title was [$getTitle], but it was expected to contain '[$titleToCheck]'."
    )

  def assertUrl(urlToCheck: String = pageUrl): Unit =
    fluentWait.until(ExpectedConditions.urlContains(urlToCheck))
    assert(getCurrentUrl == urlToCheck, s"Url was: [$getCurrentUrl], but [$urlToCheck] was expected.")
}
