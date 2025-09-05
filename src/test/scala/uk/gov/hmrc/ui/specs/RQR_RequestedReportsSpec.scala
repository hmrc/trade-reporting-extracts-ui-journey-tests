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

class RQR_RequestedReportsSpec extends Base {

  Feature("[F1] The user can view their requested reports.") {
    Scenario("[F1] ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(aSinglePartyUser)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()
    }

    Scenario("[F1] RQR-1: The user starts the 'View Requested Reports' journey.") {
      Given("the user clicks the link on the dashboard")
      ACC_1_DashboardPage.clickLinkByURL(RQR_1_RequestedReportsPage.pageLink)

      Then("the user is taken to the 'requested reports' page")
      RQR_1_RequestedReportsPage.assertUrl()
      // RQR_1_RequestedReportsPage.assertPageTitle()

      /*
        QA Note:
          Page will change title depending on whether REQ_RequestReport is ran before or after.
          TO-DO: Need to figure out how to control this, or just combine the two specs.
       */

    }
  }
}
