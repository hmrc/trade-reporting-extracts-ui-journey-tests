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

// QA Note: The proper URL is "/remove-third-party/<EORI Number>" <-- Account for this when writing tests.
object MTP_3_RemoveThirdPartyPage
    extends BasePage("/remove-third-party/", "Are you sure you want to remove this third party?") {

  override def clickLinkToPage(eoriNum: String): Unit =
    click(By.cssSelector(s"a.govuk-link[href*='$pageRelativeAddress + $eoriNum']"))

  override def assertUrl(eoriNum: String): Unit = {
    val urlToCheck: String = pageFullAddress + eoriNum
    fluentWait.until(ExpectedConditions.urlContains(urlToCheck))
    assert(getCurrentUrl == urlToCheck, s"Url was: [$getCurrentUrl], but [$urlToCheck] was expected.")
  }
}
