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

class AVR_AvailableReportsSpec extends Base {

  Feature("[F1] The user can view their available reports.") {
    Scenario("ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(aSinglePartyUser)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()
    }

    Scenario("[F1] AVR-1: The user starts the 'Available for download' journey.") {
      Given("the user clicks the link on the dashboard")
      ACC_1_DashboardPage.clickLinkByURL(AVR_1_AvailableReportsPage.pageLink)

      Then("the user is taken to the 'available reports' page")
      AVR_1_AvailableReportsPage.assertUrl()
      // AVR_1_AvailableReportsPage.assertPageTitle()

      /*
        QA Note:
          Page will only change when values are passed to mongo to make something show here.
          TO-DO: Need to figure out how to do that.
       */

    }
  }
}
