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

class ADD_AddThirdPartySpec extends BaseSpec {

  Feature("The user can add a Third Party to their account") {
    Scenario(s"The user with EORI $userTraderEori adds the third party $userThirdPartyEORI") {
      When(s"the user logs in with EORI $userTraderEori.")
      setupTest()

      Given("the user clicks the link on the dashboard")
      ADD_1_AddThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'Add a third party' starting page")
      ADD_1_AddThirdPartyPage.assertUrl()
      ADD_1_AddThirdPartyPage.assertPageTitle()

      When("the user clicks to continue")
      ADD_1_AddThirdPartyPage.continue()

      Then("the user is taken to the 'Importer or Exporter' page")
      ADD_2_ImporterOrExporterPage.assertUrl()
      ADD_2_ImporterOrExporterPage.assertPageTitle()

      Given("the user selects the 'No' option")
      ADD_2_ImporterOrExporterPage.selectOptionByValue("false")

      When("the user clicks to continue")
      ADD_2_ImporterOrExporterPage.continue()

      Then("the user is taken to the 'Cannot Add Third Party' kickout page")
      ADD_2_KO_CannotAddThirdPartyPage.assertUrl()
      ADD_2_KO_CannotAddThirdPartyPage.assertPageTitle()

      When("the user clicks 'Go to Homepage'")
      ADD_2_KO_CannotAddThirdPartyPage.clickGreyButton()

      Then("the user is taken back to the Dashboard")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      Given("the user returns to the 'Importer or Exporter' page")
      ADD_2_ImporterOrExporterPage.navigateTo()

      And("the user selects 'yes' instead")
      ADD_2_ImporterOrExporterPage.selectOptionByValue("true")

      When("the user clicks to continue")
      ADD_2_ImporterOrExporterPage.continue()

      Then("the user is taken to the 'what EORI number' page")
      ADD_3_EORINumberPage.assertUrl()
      ADD_3_EORINumberPage.assertPageTitle()

      Given(s"the user enters '$userThirdPartyEORI' as the third party EORI")
      ADD_3_EORINumberPage.clearAndInputKeys(userThirdPartyEORI)

      And("the user clicks to continue")
      ADD_3_EORINumberPage.continue()

      Then("the user is taken to the 'confirm EORI' page")
      ADD_4_ConfirmEORIPage.assertUrl()
      ADD_4_ConfirmEORIPage.assertPageTitle()

      Given("the user selects 'No' to enter a different EORI number")
      ADD_4_ConfirmEORIPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      ADD_4_ConfirmEORIPage.continue()

      Then("the user is taken back to the 'what EORI number' page")
      ADD_3_EORINumberPage.assertUrl()
      ADD_3_EORINumberPage.assertPageTitle()

      Given("the user returns to the 'confirm EORI' page")
      ADD_4_ConfirmEORIPage.navigateTo()

      And("the user selects 'yes' to continue")
      ADD_4_ConfirmEORIPage.selectOptionByIndex(0)

      When("the user clicks to continue")
      ADD_4_ConfirmEORIPage.continue()

      Then("the user is taken to the 'access start' page")
      ADD_5_ReferenceNamePage.assertUrl()
      ADD_5_ReferenceNamePage.assertPageTitle()

      Given(s"the user enters text into the text box of up to ${ADD_5_ReferenceNamePage.inputLimit} characters.")
      ADD_5_ReferenceNamePage.clearAndInputKeys("a" * ADD_5_ReferenceNamePage.inputLimit)

      When("the user clicks to continue")
      ADD_5_ReferenceNamePage.continue()

      Then("the user is taken to the 'access start' page")
      ADD_6_AccessStartPage.assertUrl()
      ADD_6_AccessStartPage.assertPageTitle()

      Given("the user can has entered any date in the past.")
      ADD_6_AccessStartPage.clearAndInputKeys(
        "1",
        ADD_6_AccessStartPage.inputCustomDay
      )
      ADD_6_AccessStartPage.clearAndInputKeys(
        "1",
        ADD_6_AccessStartPage.inputCustomMonth
      )
      ADD_6_AccessStartPage.clearAndInputKeys(
        "1",
        ADD_6_AccessStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      ADD_6_AccessStartPage.continue()

      Then("the user is taken to the 'access end' page")
      ADD_7_AccessEndPage.assertUrl()
      ADD_7_AccessEndPage.assertPageTitle()

      Given("the user just clicks continue to give unending access.")
      ADD_7_AccessEndPage.continue()

      Then("the user is taken to the 'select data types' page")
      ADD_8_DataTypesPage.assertUrl()
      ADD_8_DataTypesPage.assertPageTitle()

      Given("the user selects both 'Import' and 'Export' data types")
      ADD_8_DataTypesPage.selectOptionByIndex(0)
      ADD_8_DataTypesPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      ADD_8_DataTypesPage.continue()

      Then("the user is taken to the 'give data access' page")
      ADD_9_GiveAccessToDataPage.assertUrl()
      ADD_9_GiveAccessToDataPage.assertPageTitle()

      Given("the user selects 'no' to set a custom date")
      ADD_9_GiveAccessToDataPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      ADD_9_GiveAccessToDataPage.continue()

      Then("the user is taken to the 'data access start' page")
      ADD_10_DataStartPage.assertUrl()
      ADD_10_DataStartPage.assertPageTitle()

      Given("the user enters a date up to four years ago from the current date")
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("dd", 4),
        ADD_10_DataStartPage.inputCustomDay
      )
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("MM", 4),
        ADD_10_DataStartPage.inputCustomMonth
      )
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("yyyy", 4),
        ADD_10_DataStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      ADD_10_DataStartPage.continue()

      Then("the user is taken to the 'data access end' page")
      ADD_11_DataEndPage.assertUrl()
      ADD_11_DataEndPage.assertPageTitle()

      Given("the user just clicks continue to give unending access")
      ADD_11_DataEndPage.continue()

      Then("the user is taken to the 'check answers' page")
      ADD_12_CheckAnswersPage.assertUrl()
      ADD_12_CheckAnswersPage.assertPageTitle()

      // Confirmation Page - Change answers

      Given("the user clicks the link to change if they are importer or exporter")
      ADD_2_ImporterOrExporterPage.clickLinkToPage()

      Then("the user is taken to the 'Importer or Exporter' page")
      ADD_2_ImporterOrExporterPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks the link to change the EORI number")
      ADD_3_EORINumberPage.clickLinkToPage()

      Then("the user is taken to the 'enter EORI number' page")
      ADD_3_EORINumberPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks the link to change the access start date")
      ADD_6_AccessStartPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      ADD_6_AccessStartPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks the link to change the type of data")
      ADD_8_DataTypesPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      ADD_8_DataTypesPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks the link to change if they want to give access to all data")
      ADD_9_GiveAccessToDataPage.clickLinkToPage()

      Then("the user is taken to the 'give access to data' page")
      ADD_9_GiveAccessToDataPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks the link to change the data access date")
      ADD_10_DataStartPage.clickLinkToPage()

      Then("the user is taken to the 'access start date' page")
      ADD_10_DataStartPage.assertPageTitle()

      Given("the user returns to the check answers page")
      ADD_12_CheckAnswersPage.navigateTo()

      And("the user clicks to continue")
      ADD_12_CheckAnswersPage.continue()

      Then("the user is taken to the 'confirmation' page")
      ADD_13_ConfirmationPage.assertUrl()
      ADD_13_ConfirmationPage.assertPageTitle()

      // "Already added" check

      Given("the user clicks the link to return to the dashboard")
      ACC_1_DashboardPage.clickLinkToPage()

      Then("the user is taken to the 'dashboard' page")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      Given("the user jumps to the EORI number page.")
      ADD_3_EORINumberPage.navigateTo()

      And(s"the user enters again '$userThirdPartyEORI' as the third party EORI")
      ADD_3_EORINumberPage.clearAndInputKeys(userThirdPartyEORI)

      When("the user clicks to continue")
      ADD_3_EORINumberPage.continue()

      Then("the user is taken to the 'eori already added' page")
      ADD_3_KO_EORIAlreadyAddedPage.assertUrl()
      ADD_3_KO_EORIAlreadyAddedPage.assertPageTitle()

      Given("the user clicks the link to return to the dashboard")
      ACC_1_DashboardPage.clickLinkToPage()

      Then("the user is taken to the 'dashboard' page")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()
    }
  }
}
