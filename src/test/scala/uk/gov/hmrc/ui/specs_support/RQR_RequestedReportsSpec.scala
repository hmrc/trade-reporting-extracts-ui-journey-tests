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

package uk.gov.hmrc.ui.specs_support

import uk.gov.hmrc.ui.pages._
import support.builders.UserCredentialsBuilder.aSinglePartyUser

class RQR_RequestedReportsSpec(reportRequested: Boolean) extends BaseSpec {

  private val loginPage            = AuthLoginStubPage
  private val dashboardPage        = ACC_1_DashboardPage
  private val requestedReportsPage = RQR_1_RequestedReportsPage

  Feature("[F1] The user can view their requested reports.") {
    Scenario("[F1] ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(aSinglePartyUser)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] RQR-1: The user starts the 'View Requested Reports' journey.") {
      Given("the user clicks the link on the dashboard")
      requestedReportsPage.clickLinkToPage()

      Then("the user is taken to the 'requested reports' page")
      requestedReportsPage.assertUrl()

      if (reportRequested)
        requestedReportsPage.assertPageTitle()
      else
        requestedReportsPage.assertPageTitle(requestedReportsPage.titleNoReportsRequested)
    }
  }
}
