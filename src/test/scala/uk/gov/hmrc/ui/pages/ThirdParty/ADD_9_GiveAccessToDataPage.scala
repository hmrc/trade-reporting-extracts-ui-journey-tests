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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

object ADD_9_GiveAccessToDataPage
    extends BasePage(
      "/data-access-range",
      "Do you want to give access to all of your available import and export data?" // Title is dynamic depending on 'Import' and/or 'Export' being selected.
    ) {
  val editURL = "/edit-data-access-range/"

  def clickEditLinkToPage(eoriNum: String): Unit =
    click(By.cssSelector(s"a.govuk-link[href*='" + editURL + eoriNum + "']"))

  def assertEditUrl(eoriNum: String): Unit =
    val urlToCheck: String = baseUrl + editURL + eoriNum
    fluentWait.until(ExpectedConditions.urlContains(urlToCheck))
    assert(getCurrentUrl == urlToCheck, s"Url was: [$getCurrentUrl], but [$urlToCheck] was expected.")
}
