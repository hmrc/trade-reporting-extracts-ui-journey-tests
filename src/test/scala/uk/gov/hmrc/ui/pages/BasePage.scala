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
import uk.gov.hmrc.selenium.webdriver.Driver
import scala.compiletime.ops.string

abstract class BasePage(relativeUrl: String, relativeTitle: String) extends PageObject {

  protected val baseUrl: String = TestEnvironment.url("trade-reporting-extracts-frontend")
  val url: String               = baseUrl + relativeUrl
  val pageTitle: String         = s"$relativeTitle - Trade Reporting Extracts - GOV.UK"

  def navigateTo(urlToGet: String = url): Unit =
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

  // NOTE: This will only work if you return to the page. Selecting a checkbox/radio option doesn't update the HTML immediately.
  def assertOptionSelected(index: Int): Unit =
    assert(
      Driver.instance.findElement(By.cssSelector(s"#value_$index[checked]")).isSelected(),
      s"Option $index is not marked as 'checked' (is not selected)."
    )

  def assertOptionText(index: Int, textToCheck: String): Unit = {
    val optionText: String = getText(By.cssSelector(s"label.govuk-label[for=value_$index]"))
    assert(
      optionText.contains(textToCheck),
      s"Option $index text was [$optionText], but expected it to contain [$textToCheck]."
    )
  }

  def assertPageTitle(titleToCheck: String = pageTitle): Unit =
    assert(getTitle == titleToCheck, s"Page title was [$getTitle], but [$titleToCheck] was expected.")

  def assertUrl(urlToCheck: String = url): Unit =
    assert(getCurrentUrl == urlToCheck, s"Url was: [$getCurrentUrl], but [$url] was expected.")
}
