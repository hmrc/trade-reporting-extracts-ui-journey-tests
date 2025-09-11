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
import support.builders.UserCredentialsBuilder.aThirdPartyUser
import uk.gov.hmrc.ui.specs_support.BaseSpec

class ADD_AddThirdPartySpec extends BaseSpec {

  private val loginStub              = AuthLoginStubPage
  private val dashboardPage          = ACC_1_DashboardPage
  private val addThirdPartyPage      = ADD_1_AddThirdPartyPage
  private val importerOrExporterPage = ADD_2_ImporterOrExporterPage
  private val eoriNumberPage         = ADD_3_EORINumberPage

  Feature("[F1] The user can add a Third Party to their account") {
    Scenario("[F1] ACC-1: The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginStub.navigateTo()
      loginStub.enterRedirectionUrl()
      loginStub.enterEnrollment(aThirdPartyUser)
      loginStub.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Dashboard: The user starts the 'Add a Third party' journey.") {
      Given("the user clicks the link on the dashboard")
      addThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'Add a third party' starting page")
      addThirdPartyPage.assertUrl()
      addThirdPartyPage.assertPageTitle()
    }

    Scenario("[F1] ADD-1: The user selects 'Continue' to begin the journey.") {
      When("the user clicks to continue")
      addThirdPartyPage.continue()

      Then("the user is taken to the 'Importer or Exporter' page")
      importerOrExporterPage.assertUrl()
      importerOrExporterPage.assertPageTitle()
    }

    Scenario("[F1] ADD-2: The user selects 'No' to reach a kickout page.") {
      Given("the user selects the 'No' option")
      importerOrExporterPage.selectOptionByValue("false")

      When("the user clicks to continue")
      importerOrExporterPage.continue()

      Then("the user is taken to the 'Cannot Add Third Party' kickout page")
      ADD_2_KO_CannotAddThirdPartyPage.assertUrl()
      ADD_2_KO_CannotAddThirdPartyPage.assertPageTitle()
    }

    Scenario("[F1] ADD-2-KO: The user selects the link to return to Dashboard.") {
      When("the user clicks 'Go to Homepage'")
      dashboardPage.clickLinkToPage()

      Then("the user is taken back to the Dashboard")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] ADD-2: The user returns to select 'Yes' to continue.") {
      Given("the user returns to 'Importer or Exporter' page")
      importerOrExporterPage.navigateTo()

      And("the user selects 'yes' to continue")
      importerOrExporterPage.selectOptionByValue("true")

      When("the user clicks to continue")
      importerOrExporterPage.continue()

      Then("the user is taken to the 'Importer or Exporter' page")
      eoriNumberPage.assertUrl()
      eoriNumberPage.assertPageTitle()
    }

    Scenario("[F1] ADD-3: The user enters the EORI of the third party.") {
      Given("the user enters 'GB123456789123456' page")
      eoriNumberPage.clearAndInputKeys("GB123456789123456")

      And("the user clicks to continue")
      eoriNumberPage.continue()

      // Then("the user is taken to the 'x' page") -- Waiting for further AddThirdParty pages to be implemented.
      // x.assertUrl()
      // x.assertPageTitle()
    }
  }
}
