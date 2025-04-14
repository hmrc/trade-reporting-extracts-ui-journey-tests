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

object DashboardPage extends BasePage("/dashboard", "dashboard") {

  def selectLink(linkToSelect: String): Unit = {
    val clipLink = linkToSelect.replace(baseUrl, "")
    click(By.cssSelector(s"a.govuk-link[href*='$clipLink']"))
  }

  // "Reports"
  val linkRequestNewReport = RequestReportPage.url
  // val linkRequestedReports = x
  // val linkAvailableDownloads = x

  // "Your Account"
  val linkYourDetails = ContactDetailsPage.url
  // val linkMessages = x

}
