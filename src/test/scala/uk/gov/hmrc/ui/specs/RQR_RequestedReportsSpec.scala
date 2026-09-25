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
import support.BaseSpec

class RQR_RequestedReportsSpec extends BaseSpec {

  Feature("The user can view their requested reports.") {
    Scenario("The user opens the requested reports page.") {
      When(s"the user logs in with EORI $userTraderEori.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userTraderLogin)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      Given("the user clicks the link on the dashboard")
      RQR_1_RequestedReportsPage.clickLinkToPage()

      Then("the user is taken to the 'requested reports' page")
      RQR_1_RequestedReportsPage.assertUrl()
      RQR_1_RequestedReportsPage.assertPageTitle()
    }
  }
}
