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
import support.builders.EnrolmentsDataBuilder.enrolmentRandomEORI
import support.builders.UserCredentialsBuilder.aThirdPartyUser

class ADD_AddThirdPartySpec extends BaseSpec {

  private val randEORI = enrolmentRandomEORI.identifierValue

  private val loginStub               = AuthLoginStubPage
  private val dashboardPage           = ACC_1_DashboardPage
  private val addThirdPartyPage       = ADD_1_AddThirdPartyPage
  private val importerOrExporterPage  = ADD_2_ImporterOrExporterPage
  private val cannotAddThirdPartyPage = ADD_2_KO_CannotAddThirdPartyPage
  private val eoriNumberPage          = ADD_3_EORINumberPage
  private val eoriAlreadyAddedPage    = ADD_3_KO_EORIAlreadyAddedPage
  private val confirmEORIPage         = ADD_4_ConfirmEORIPage
  private val accessStartPage         = ADD_6_AccessStartPage
  private val accessEndPage           = ADD_7_AccessEndPage
  private val dataTypesPage           = ADD_8_DataTypesPage
  private val giveAccessToDataPage    = ADD_9_GiveAccessToDataPage
  private val dataStartPage           = ADD_10_DataStartPage
  private val dataEndPage             = ADD_11_DataEndPage
  private val checkAnswersPage        = ADD_12_CheckAnswersPage
  private val confirmationPage        = ADD_13_ConfirmationPage

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

    Scenario("[F1] Step-1: The user selects 'Continue' to begin the journey.") {
      When("the user clicks to continue")
      addThirdPartyPage.continue()

      Then("the user is taken to the 'Importer or Exporter' page")
      importerOrExporterPage.assertUrl()
      importerOrExporterPage.assertPageTitle()
    }

    Scenario("[F1] Step-2: The user selects 'No' to reach a kickout page.") {
      Given("the user selects the 'No' option")
      importerOrExporterPage.selectOptionByValue("false")

      When("the user clicks to continue")
      importerOrExporterPage.continue()

      Then("the user is taken to the 'Cannot Add Third Party' kickout page")
      cannotAddThirdPartyPage.assertUrl()
      cannotAddThirdPartyPage.assertPageTitle()
    }

    Scenario("[F1] Step-3: The user selects the link to return to Dashboard.") {
      When("the user clicks 'Go to Homepage'")
      dashboardPage.clickLinkToPage()

      Then("the user is taken back to the Dashboard")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Step-4: The user goes back to select 'Yes' to continue instead.") {
      Given("the user returns to the 'Importer or Exporter' page")
      importerOrExporterPage.navigateTo()

      And("the user selects 'yes' instead")
      importerOrExporterPage.selectOptionByValue("true")

      When("the user clicks to continue")
      importerOrExporterPage.continue()

      Then("the user is taken to the 'what EORI number' page")
      eoriNumberPage.assertUrl()
      eoriNumberPage.assertPageTitle()
    }

    Scenario("[F1] Step-5: The user enters the EORI of the third party.") {
      Given(s"the user enters '$randEORI' as the third party EORI")
      eoriNumberPage.clearAndInputKeys(randEORI)

      And("the user clicks to continue")
      eoriNumberPage.continue()

      Then("the user is taken to the 'confirm EORI' page")
      confirmEORIPage.assertUrl()
      confirmEORIPage.assertPageTitle()
    }

    Scenario("[F1] Step-6: The user selects 'No' and is taken back to the previous page.") {
      Given("the user selects 'No' to enter a different EORI number")
      confirmEORIPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      confirmEORIPage.continue()

      Then("the user is taken back to the 'what EORI number' page")
      eoriNumberPage.assertUrl()
      eoriNumberPage.assertPageTitle()
    }

    Scenario("[F1] Step-7: The user goes back to select 'Yes' to continue instead.") {
      Given("the user returns to the 'confirm EORI' page")
      confirmEORIPage.navigateTo()

      And("the user selects 'yes' to continue")
      confirmEORIPage.selectOptionByIndex(0)

      When("the user clicks to continue")
      confirmEORIPage.continue()

      Then("the user is taken to the 'access start' page")
      accessStartPage.assertUrl()
      accessStartPage.assertPageTitle()
    }

    Scenario("[F1] Step-8: The user enters when they want access to start.") {
      Given("the user enters a date four years before the current day")
      accessStartPage.clearAndInputKeys(
        accessStartPage.getDateMinusYears("dd", 0),
        accessStartPage.inputCustomDay
      )
      accessStartPage.clearAndInputKeys(
        accessStartPage.getDateMinusYears("MM", 0),
        accessStartPage.inputCustomMonth
      )
      accessStartPage.clearAndInputKeys(
        accessStartPage.getDateMinusYears("yyyy", 0),
        accessStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      accessStartPage.continue()

      Then("the user is taken to the 'access end' page")
      accessEndPage.assertUrl()
      accessEndPage.assertPageTitle()
    }

    Scenario("[F1] Step-9: The user enters when they want access to end.") {
      Given("the user just clicks continue.")
      accessEndPage.continue()

      Then("the user is taken to the 'select data types' page")
      dataTypesPage.assertUrl()
      dataTypesPage.assertPageTitle()
    }

    Scenario("[F1] Step-10: The user selects what data types the third party can access.") {
      Given("the user selects both 'Import' and 'Export' data types")
      dataTypesPage.selectOptionByIndex(0)
      dataTypesPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      dataTypesPage.continue()

      Then("the user is taken to the 'give data access' page")
      giveAccessToDataPage.assertUrl()
      giveAccessToDataPage.assertPageTitle()
    }

    Scenario("[F1] Step-11: The user selects whether they want to give access to all available data") {
      Given("the user selects 'no' to set a custom date")
      giveAccessToDataPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      giveAccessToDataPage.continue()

      Then("the user is taken to the 'data access start' page")
      dataStartPage.assertUrl()
      dataStartPage.assertPageTitle()
    }

    Scenario("[F1] Step-12: The user enters the access start date for their data") {
      Given("the user enters a date up to four years from the current date")
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("dd", 4),
        dataStartPage.inputCustomDay
      )
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("MM", 4),
        dataStartPage.inputCustomMonth
      )
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("yyyy", 4),
        dataStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      dataStartPage.continue()

      Then("the user is taken to the 'data access end' page")
      dataEndPage.assertUrl()
      dataEndPage.assertPageTitle()
    }

    Scenario("[F1] Step-13: The user enters the access end date for their data") {
      Given("the user just clicks continue.")
      dataEndPage.continue()

      Then("the user is taken to the 'check answers' page")
      checkAnswersPage.assertUrl()
      checkAnswersPage.assertPageTitle()
    }

    // Confirmation Page - Scenarios

    Scenario("[F1] Step-14: The user can change if they are importer or exporter.") {
      Given("the user clicks the link to change if they are importer or exporter")
      importerOrExporterPage.clickLinkToPage()

      Then("the user is taken to the 'Importer or Exporter' page")
      importerOrExporterPage.assertPageTitle()
    }

    Scenario("[F1] Step-15: The user can change the EORI number to add.") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks the link to change the EORI number")
      eoriNumberPage.clickLinkToPage()

      Then("the user is taken to the 'enter EORI number' page")
      eoriNumberPage.assertPageTitle()
    }

    Scenario("[F1] Step-17: The user can change the third-party access period") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks the link to change the access start date")
      accessStartPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      accessStartPage.assertPageTitle()
    }

    Scenario("[F1] Step-18: The user can change the type of data the third party can access") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks the link to change the type of data")
      dataTypesPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      dataTypesPage.assertPageTitle()
    }

    Scenario("[F1] Step-19: The user can change whether they want to give access to all available data") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks the link to change if they want to give access to all data")
      giveAccessToDataPage.clickLinkToPage()

      Then("the user is taken to the 'give access to data' page")
      giveAccessToDataPage.assertPageTitle()
    }

    Scenario("[F1] Step-20: The user can change the period of data the third party can access") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks the link to change the data access date")
      dataStartPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      dataStartPage.assertPageTitle()
    }

    Scenario("[F1] Step-21: The user can click to continue to add third party.") {
      Given("the user returns to the check answers page")
      checkAnswersPage.navigateTo()

      And("the user clicks to continue")
      checkAnswersPage.continue()

      Then("the user is taken to the 'confirmation' page")
      confirmationPage.assertUrl()
      confirmationPage.assertPageTitle()
    }

    // Confirmation Page - Scenarios

    Scenario("[F1] Step-22: The user can continue from the confirmation page") {
      Given("the user clicks the link to return to the dashboard")
      dashboardPage.clickLinkToPage()

      Then("the user is taken to the 'dashboard' page")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Step-23: The user cannot add the same EORI again.") {
      Given("the user jumps to the EORI number page.")
      eoriNumberPage.navigateTo()

      And(s"the user enters again '$randEORI' as the third party EORI")
      eoriNumberPage.clearAndInputKeys(randEORI)

      When("the user clicks to continue")
      eoriNumberPage.continue()

      Then("the user is taken to the 'eori already added' page")
      eoriAlreadyAddedPage.assertUrl()
      eoriAlreadyAddedPage.assertPageTitle()
    }

    Scenario("[F1] Step-24: The user can return to the dashboard from the kickout.") {
      Given("the user clicks the link to return to the dashboard")
      dashboardPage.clickLinkToPage()

      Then("the user is taken to the 'dashboard' page")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }
  }
}
