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
import support.builders.UserCredentialsBuilder.anOrganisationUserWithKnownEnrolment

class YourDetailsSpec extends BaseSpec {

  private val loginPage          = AuthLoginStubPage
  private val dashboardPage      = ACC_2_DashboardPage
  private val contactDetailsPage = DET_0_ContactDetailsPage

  Feature("The user can view their account details.") {
    Scenario("ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("DET-0: The user starts the 'Your Details' journey.") {
      Given("the user clicks the link on the dashboard")
      dashboardPage.selectLink(ACC_2_DashboardPage.linkYourDetails)

      Then("the user is taken to the 'contact details' page")
      contactDetailsPage.assertUrl()
      contactDetailsPage.assertPageTitle()
    }
  }
}
