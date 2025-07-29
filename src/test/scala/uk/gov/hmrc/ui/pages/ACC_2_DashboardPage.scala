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

object ACC_2_DashboardPage extends BasePage("/dashboard", "dashboard") {

  def selectLink(linkToSelect: String): Unit = {
    val clipLink = linkToSelect.replace(baseUrl, "")
    click(By.cssSelector(s"a.govuk-link[href*='$clipLink']"))
  }

  // "Reports"
  val linkRequestNewReport   = REQ_0_RequestReportPage.pageUrl
  // val linkRequestedReports = x
  val linkAvailableDownloads = AVR_0_AvailableReportsPage.pageUrl

  // "Your Account"linkAvialableDownloads
  val linkYourDetails = DET_0_ContactDetailsPage.pageUrl
  // val linkMessages = x

}
