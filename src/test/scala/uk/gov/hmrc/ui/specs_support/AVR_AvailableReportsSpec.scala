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
import support.builders.UserCredentialsBuilder.userMain

class AVR_AvailableReportsSpec extends BaseSpec {

  private val loginStub            = AuthLoginStubPage
  private val dashboardPage        = ACC_1_DashboardPage
  private val availableReportsPage = AVR_1_AvailableReportsPage

  Feature("[F1] The user can view their available reports.") {
    Scenario("ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginStub.navigateTo()
      loginStub.enterRedirectionUrl()
      loginStub.enterEnrollment(userMain)
      loginStub.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] AVR-1: The user starts the 'Available for download' journey.") {
      Given("the user clicks the link on the dashboard")
      availableReportsPage.clickLinkToPage()

      Then("the user is taken to the 'available reports' page")
      availableReportsPage.assertUrl()
      // availableReportsPage.assertPageTitle()

      // QA Note: Reports will currently not appear available to download without stubs / MongoDB shenanigans.
    }
  }
}
