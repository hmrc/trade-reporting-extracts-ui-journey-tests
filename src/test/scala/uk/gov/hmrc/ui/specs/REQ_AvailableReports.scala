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

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages._
import support.builders.UserCredentialsBuilder.aSinglePartyUser
import uk.gov.hmrc.ui.specs_support.Base

class REQ_AvailableReports extends Base {

  private val loginPage            = AuthLoginStubPage
  private val dashboardPage        = ACC_1_DashboardPage
  private val availableReportsPage = AVR_1_AvailableReportsPage

  Feature("The user can view their available reports.") {
    Scenario("ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(aSinglePartyUser)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("The user starts the 'Available for download' journey.") {
      Given("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkAvailableDownloads)

      Then("the user is taken to the 'available reports' page")
      availableReportsPage.assertUrl()
      // availableReportsPage.assertPageTitle() -- 02 June 2025: Page title pending whether reports are available to download. Right now its "There are no reports available to download yet".

    }
  }
}
