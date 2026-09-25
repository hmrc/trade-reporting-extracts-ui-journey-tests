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

class REQ_RequestReportSpec() extends BaseSpec {

  Feature(
    "The user can request both IMPORT and EXPORT-type reports."
  ) {
    Scenario("The user can request an IMPORT-type report.") {
      When(s"the user logs in with EORI $userTraderEori.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userTraderLogin)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      When("the user clicks the link on the dashboard")
      REQ_1_RequestReportPage.clickLinkToPage()

      Then("the user is taken to the information page")
      REQ_1_RequestReportPage.assertUrl()

      When("the user clicks 'Continue'.")
      REQ_1_RequestReportPage.continue()

      Then("the user is taken to the 'Which EORI' page")
      REQ_2_WhichEORIPage.assertUrl()
      REQ_2_WhichEORIPage.assertPageTitle()

      Given("the user selects to use their own EORI number")
      REQ_2_WhichEORIPage.selectOptionByIndex(0)

      When("the user clicks to continue")
      REQ_2_WhichEORIPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      REQ_3_ReportTypePage.assertUrl()
      REQ_3_ReportTypePage.assertPageTitle()

      Given("the user selects 'import' as a type")
      REQ_3_ReportTypePage.selectOptionByIndex(0)

      When("the user clicks to continue")
      REQ_3_ReportTypePage.continue()

      Then("the user is taken to the 'EORI Role' page")
      REQ_4_ReportOwnerTypePage.assertUrl()
      REQ_4_ReportOwnerTypePage.assertPageTitle()

      Given("the user can select both the 'Declarant' and 'Importer' roles.")
      REQ_4_ReportOwnerTypePage.selectOptionByValue("declarant")
      REQ_4_ReportOwnerTypePage.selectOptionByValue("importer")

      When("the user clicks to continue")
      REQ_4_ReportOwnerTypePage.continue()

      Then("the user is taken to the '(sub)type of report' page")
      REQ_5_ImportTypeSelectionPage.assertUrl()
      REQ_5_ImportTypeSelectionPage.assertPageTitle()

      Given("the user selects the 'Import header, item' and 'tax line' types.")
      REQ_5_ImportTypeSelectionPage.selectOptionByIndex(0)
      REQ_5_ImportTypeSelectionPage.selectOptionByIndex(1)
      REQ_5_ImportTypeSelectionPage.selectOptionByIndex(2)

      When("the user clicks to continue")
      REQ_5_ImportTypeSelectionPage.continue()

      Then("the user is taken to the 'report date range' page")
      REQ_7_ReportDateRangeDecisionPage.assertUrl()
      REQ_7_ReportDateRangeDecisionPage.assertPageTitle()

      Given("the user selects the 'Custom date range' option")
      REQ_7_ReportDateRangeDecisionPage.selectOptionByIndex(1)

      When("the user clicks to continue")
      REQ_7_ReportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'custom report range start' page")
      REQ_8_ReportCustomDateRangeStartPage.assertUrl()
      REQ_8_ReportCustomDateRangeStartPage.assertPageTitle()

      Given("the user enters a date up to four years ago for the date range to begin")
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("dd", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomDay
      )
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("MM", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomMonth
      )
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("yyyy", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomYear
      )

      When("the user clicks to continue")
      REQ_8_ReportCustomDateRangeStartPage.continue()

      Then("the user is taken to the 'custom report range end' page")
      REQ_9_ReportCustomDateRangeEndPage.assertUrl()
      REQ_9_ReportCustomDateRangeEndPage.assertPageTitle(REQ_9_ReportCustomDateRangeEndPage.titleMultipleReports)

      Given("the user enters a date up to four years ago for the date range to end")
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("dd", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomDay
      )
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("MM", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomMonth
      )
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("yyyy", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomYear
      )

      When("the user clicks to continue")
      REQ_7_ReportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'report name' page")
      REQ_10_ReportNamePage.assertUrl()
      REQ_10_ReportNamePage.assertPageTitle(REQ_10_ReportNamePage.titleMultipleReports)

      Given(s"the user enters text into the text box of up to ${REQ_10_ReportNamePage.inputLimit} characters.")
      REQ_10_ReportNamePage.clearAndInputKeys("a" * REQ_10_ReportNamePage.inputLimit)

      When("the user clicks to continue")
      REQ_10_ReportNamePage.continue()

      Then("the user is taken to the 'choose to add email' page")
      REQ_11_ChooseToAddEmailPage.assertUrl()
      REQ_11_ChooseToAddEmailPage.assertPageTitle()

      Given("the user selects the 'Yes' option")
      REQ_11_ChooseToAddEmailPage.selectYesNo(true)

      When("the user clicks to continue")
      REQ_11_ChooseToAddEmailPage.continue()

      Then("the user is taken to the 'Enter new email address' page")
      REQ_13_EnterNewEmailPage.assertUrl()
      REQ_13_EnterNewEmailPage.assertPageTitle()

      Given("the user enters the new email address in the text box")
      REQ_13_EnterNewEmailPage.clearAndInputKeys("myexample@email.com")

      When("the user clicks to continue")
      REQ_13_EnterNewEmailPage.continue()

      Then("the user is taken to the 'check new email' page")
      REQ_14_CheckEmailPage.assertUrl()
      REQ_14_CheckEmailPage.assertPageTitle()

      Given("the user clicks 'yes' to confirm the new email")
      REQ_14_CheckEmailPage.selectYesNo(true)

      When("the user clicks to continue")
      REQ_14_CheckEmailPage.continue()

      Then("the user is taken to the 'check your answers' page")
      REQ_15_CheckYourAnswersPage.assertUrl()
      REQ_15_CheckYourAnswersPage.assertPageTitle()

      When("the user clicks to continue")
      REQ_15_CheckYourAnswersPage.continue()

      Then("the user is taken to the 'Successful Submission' page")
      REQ_16_ReportRequestSubmittedPage.assertUrl()
      REQ_16_ReportRequestSubmittedPage.assertPageTitle()

      When("the user clicks to return to the homepage")
      ACC_1_DashboardPage.clickLinkToPage()

      Then("the user should be taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()
    }
  
    Scenario("The user can request an EXPORT-type report.") {
      When(s"the user logs in with EORI $userTraderEori.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userTraderLogin)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      When("the user clicks the link on the dashboard")
      REQ_1_RequestReportPage.clickLinkToPage()

      Then("the user is taken to the information page")
      REQ_1_RequestReportPage.assertUrl()

      When("the user clicks 'Continue'.")
      REQ_1_RequestReportPage.continue()

      Then("the user is taken to the 'Which EORI' page")
      REQ_2_WhichEORIPage.assertUrl()
      REQ_2_WhichEORIPage.assertPageTitle()

      Given("the user selects to use their own EORI number")
      REQ_2_WhichEORIPage.selectOptionByIndex(0)

      When("the user clicks to continue")
      REQ_2_WhichEORIPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      REQ_3_ReportTypePage.assertUrl()
      REQ_3_ReportTypePage.assertPageTitle()

      Given("the user can select 'export' as a type")
      REQ_3_ReportTypePage.selectOptionByIndex(1)

      When("the user clicks to continue")
      REQ_3_ReportTypePage.continue()

      Then("the user is taken to the 'EORI Role' page")
      REQ_4_ReportOwnerTypePage.assertUrl()
      REQ_4_ReportOwnerTypePage.assertPageTitle()

      Given("the user can select both the 'Declarant' and 'Exporter' roles.")
      REQ_4_ReportOwnerTypePage.selectOptionByValue("declarant")
      REQ_4_ReportOwnerTypePage.selectOptionByValue("exporter")

      When("the user clicks to continue")
      REQ_4_ReportOwnerTypePage.continue()

      Then("the user is taken to the 'export' subtype page")
      REQ_6_ReportSubtypeExportPage.assertUrl()
      REQ_6_ReportSubtypeExportPage.assertPageTitle()

      When("the user clicks to continue")
      REQ_6_ReportSubtypeExportPage.continue()

      Then("the user is taken to the 'report date range' page")
      REQ_7_ReportDateRangeDecisionPage.assertUrl()
      REQ_7_ReportDateRangeDecisionPage.assertPageTitle("What date range do you want the report to cover?")

            Given("the user selects the 'Custom date range' option")
      REQ_7_ReportDateRangeDecisionPage.selectOptionByIndex(1)

      When("the user clicks to continue")
      REQ_7_ReportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'custom report range start' page")
      REQ_8_ReportCustomDateRangeStartPage.assertUrl()
      REQ_8_ReportCustomDateRangeStartPage.assertPageTitle()

      Given("the user enters a date up to four years ago for the date range to begin")
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("dd", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomDay
      )
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("MM", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomMonth
      )
      REQ_8_ReportCustomDateRangeStartPage.clearAndInputKeys(
        REQ_8_ReportCustomDateRangeStartPage.getDateMinusYears("yyyy", 4),
        REQ_8_ReportCustomDateRangeStartPage.inputCustomYear
      )

      When("the user clicks to continue")
      REQ_8_ReportCustomDateRangeStartPage.continue()

      Then("the user is taken to the 'custom report range end' page")
      REQ_9_ReportCustomDateRangeEndPage.assertUrl()
      REQ_9_ReportCustomDateRangeEndPage.assertPageTitle()

      Given("the user enters a date up to four years ago for the date range to end")
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("dd", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomDay
      )
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("MM", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomMonth
      )
      REQ_9_ReportCustomDateRangeEndPage.clearAndInputKeys(
        REQ_9_ReportCustomDateRangeEndPage.getDateMinusYears("yyyy", 4),
        REQ_9_ReportCustomDateRangeEndPage.inputCustomYear
      )

      When("the user clicks to continue")
      REQ_7_ReportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'report name' page")
      REQ_10_ReportNamePage.assertUrl()
      REQ_10_ReportNamePage.assertPageTitle()

      Given(s"the user enters text into the text box of up to ${REQ_10_ReportNamePage.inputLimit} characters.")
      REQ_10_ReportNamePage.clearAndInputKeys("a" * REQ_10_ReportNamePage.inputLimit)

      When("the user clicks to continue")
      REQ_10_ReportNamePage.continue()

      Then("the user is taken to the 'choose to add email' page")
      REQ_11_ChooseToAddEmailPage.assertUrl()
      REQ_11_ChooseToAddEmailPage.assertPageTitle()

      Given("the user selects the 'Yes' option")
      REQ_11_ChooseToAddEmailPage.selectYesNo(true)

      When("the user clicks to continue")
      REQ_11_ChooseToAddEmailPage.continue()

      Then("the user is taken to the 'Enter new email address' page")
      REQ_13_EnterNewEmailPage.assertUrl()
      REQ_13_EnterNewEmailPage.assertPageTitle()

      Given("the user enters the new email address in the text box")
      REQ_13_EnterNewEmailPage.clearAndInputKeys("myexample@email.com")

      When("the user clicks to continue")
      REQ_13_EnterNewEmailPage.continue()

      Then("the user is taken to the 'check new email' page")
      REQ_14_CheckEmailPage.assertUrl()
      REQ_14_CheckEmailPage.assertPageTitle()

      Given("the user clicks 'yes' to confirm the new email")
      REQ_14_CheckEmailPage.selectYesNo(true)

      When("the user clicks to continue")
      REQ_14_CheckEmailPage.continue()

      Then("the user is taken to the 'check your answers' page")
      REQ_15_CheckYourAnswersPage.assertUrl()
      REQ_15_CheckYourAnswersPage.assertPageTitle()

      When("the user clicks to continue")
      REQ_15_CheckYourAnswersPage.continue()

      Then("the user is taken to the 'Successful Submission' page")
      REQ_16_ReportRequestSubmittedPage.assertUrl()
      REQ_16_ReportRequestSubmittedPage.assertPageTitle()

      When("the user clicks to return to the homepage")
      ACC_1_DashboardPage.clickLinkToPage()

      Then("the user should be taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()
    }
  }
}
