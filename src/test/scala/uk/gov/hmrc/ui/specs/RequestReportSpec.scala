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

class RequestReportSpec extends BaseSpec {

  private val loginPage                   = AuthLoginStubPage
  private val dashboardPage               = ACC_2_DashboardPage
  private val requestReportPage           = REQ_0_RequestReportPage
  private val reportTypePage              = REQ_1_ReportTypePage
  private val whichEORIPage               = REQ_2_WhichEORIPage
  private val reportOwnerTypePage         = REQ_4_ReportOwnerTypePage
  private val reportImportTypePage        = REQ_5_ImportTypeSelectionPage
  private val reportDateRangeDecisionPage = REQ_7_ReportDateRangeDecisionPage
  private val ReportCustomStartPage       = REQ_8_ReportCustomDateRangeStartPage
  private val ReportCustomEndPage         = REQ_9_ReportCustomDateRangeEndPage
  private val reportNamePage              = REQ_10_ReportNamePage
  private val chooseEmailPage             = REQ_11_ChooseToAddEmailPage
  private val selectEmailsPage            = REQ_12_SelectEmailsPage
  private val enterNewEmailPage           = REQ_13_EnterNewEmail
  private val checkYourAnswersPage        = REQ_14_CheckYourAnswersPage
  private val requestSubmittedPage        = REQ_15_ReportRequestSubmittedPage

  Feature(
    "[F1] The user can request a new report of 'import'-type data and use their own EORI number to complete the journey."
  ) {

    Scenario("[F1] ACC-1: The user is authenticated.") {
      When("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] REQ-0: The user starts the 'Request New Report' journey.") {
      When("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkRequestNewReport)

      Then("the user is taken to the information page")
      requestReportPage.assertUrl()
      requestReportPage.assertPageTitle()
    }

    Scenario("[F1] REQ-1: The user selects 'import' as the type of data.") {
      When("the user clicks to continue from the previous page")
      requestReportPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()

      And("the user can select 'import' as a type")
      reportTypePage.selectOptionByIndex(0)
    }

    Scenario("[F1] REQ-2: The user selects to use their own EORI number.") {
      When("the user clicks to continue from the previous page")
      reportTypePage.continue()

      Then("the user is taken to the 'Which EORI' page")
      whichEORIPage.assertUrl()
      whichEORIPage.assertPageTitle()

      And("the user can select to use their own EORI number.")
      whichEORIPage.selectOptionByIndex(0)
    }

    Scenario("[F1] REQ-4: The user selects the EORI role.") {
      When("the user clicks to continue from the previous page")
      whichEORIPage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()

      And("the user can select the 'Declarant' role.")
      reportOwnerTypePage.assertOptionText(0, "Declarant")
      reportOwnerTypePage.selectOptionByIndex(0)

      And("the user can select the 'Importer' role.")
      reportOwnerTypePage.assertOptionText(1, "Importer")
      reportOwnerTypePage.selectOptionByIndex(1)
    }

    Scenario("[F1] REQ-5: The user selects the sub-type of their 'import' report.") {
      When("the user clicks to continue from the previous page")
      reportOwnerTypePage.continue()

      Then("the user is taken to the '(sub)type of report' page")
      reportImportTypePage.assertUrl()
      reportImportTypePage.assertPageTitle()

      And("the user can select the 'Import header' type.")
      reportImportTypePage.selectOptionByIndex(0)

      And("the user can select the 'Import item' type.")
      reportImportTypePage.selectOptionByIndex(1)

      And("the user can select the 'Import tax line' type.")
      reportImportTypePage.selectOptionByIndex(2)
    }

    Scenario("[F1] REQ-7: The user selects the date range of their report.") {
      When("the user clicks to continue from the previous page")
      reportImportTypePage.continue()

      Then("the user is taken to the 'report date range' page")
      reportDateRangeDecisionPage.assertUrl()
      reportDateRangeDecisionPage.assertPageTitle()

      And("the user can select the 'Custom date range' option")
      reportDateRangeDecisionPage.selectOptionByIndex(1)
    }

    Scenario("[F1] REQ-8: The user gives a custom 'start' date range for their report.") {
      When("the user clicks to continue from the previous page")
      reportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'custom report range start' page")
      ReportCustomStartPage.assertUrl()
      ReportCustomStartPage.assertPageTitle()

      And("the user can enter the value '1st April 2025'.")
      // NOTE: The limit is currently set to 4 years ago: So in three years, this may need a custom value in future to dynamically go off whatever the date is.
      ReportCustomStartPage.clearAndInputKeys(ReportCustomStartPage.inputCustomDay, "1")
      ReportCustomStartPage.clearAndInputKeys(ReportCustomStartPage.inputCustomMonth, "4")
      ReportCustomStartPage.clearAndInputKeys(ReportCustomStartPage.inputCustomYear, "2025")
    }

    Scenario("[F1] REQ-9: The user gives a custom 'end' date range for their report.") {
      When("the user clicks to continue from the previous page")
      ReportCustomStartPage.continue()

      Then("the user is taken to the 'custom report range end' page")
      ReportCustomEndPage.assertUrl()
      ReportCustomEndPage.assertPageTitle()

      And("the user can enter the value '2nd May 2025'.")
      // NOTE: The limit is currently set to 4 years ago: So in three years, this may need a custom value in future to dynamically go off whatever the date is.
      ReportCustomEndPage.clearAndInputKeys(ReportCustomEndPage.inputCustomDay, "2")
      ReportCustomEndPage.clearAndInputKeys(ReportCustomEndPage.inputCustomMonth, "5")
      ReportCustomEndPage.clearAndInputKeys(ReportCustomEndPage.inputCustomYear, "2025")
    }

    Scenario("[F1] REQ-10: The user enters a name for their report.") {
      When("the user clicks to continue from the previous page")
      reportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'report name' page")
      reportNamePage.assertUrl()
      // reportNamePage.assertPageTitle() -- 24 April 2024: Title is incorrect. Correction is pending tech cleanup sweep (TRE-358).

      And("the user can enter text with spaces into the text box of up to 70 characters.")
      reportNamePage.clearAndInputKeys(
        reportNamePage.inputReportName,
        "My Reportinator 2000 Has 70 Characters Including My Spaces And Numbers"
      )
    }

    Scenario("[F1] REQ 11: The user selects whether to add another email for notifications.") {
      When("the user clicks to continue from the previous page")
      reportNamePage.continue()

      Then("the user is taken to the 'choose to add email' page")
      chooseEmailPage.assertPageTitle()
      chooseEmailPage.assertUrl()

      And("the user can select the 'Yes' option")
      chooseEmailPage.selectYesNo(0)
    }

    Scenario("[F1] REQ 12: The user selects what emails are to receive notifications.") {
      When("the user clicks to continue from the previous page")
      chooseEmailPage.continue()

      Then("the user is taken to the 'choose to add email' page")
      selectEmailsPage.assertPageTitle()
      selectEmailsPage.assertUrl()

      And("the user can select the 'Add new email address' option")
      selectEmailsPage.selectOptionByValue(selectEmailsPage.inputAddNewEmail)
    }

    Scenario("[F1] REQ 13: The user adds a new email.") {
      When("the user clicks to continue from the previous page")
      selectEmailsPage.continue()

      Then("the user is taken to the 'Enter new email address' page")
      enterNewEmailPage.assertPageTitle()
      enterNewEmailPage.assertUrl()

      And("the user can enter the new email address in the text box")
      enterNewEmailPage.clearAndInputKeys(enterNewEmailPage.inputNewEmailAddress, "abc@gmail.com")
    }

    Scenario("[F1] REQ-14: The user reaches the confirmation screen.") {
      When("the user clicks to continue from the previous page")
      enterNewEmailPage.continue()

      Then("the user is taken to the 'Confirm Details' page")
      checkYourAnswersPage.assertPageTitle()
      checkYourAnswersPage.assertUrl()
    }

    Scenario("[F1] REQ-15: The user successfully submits") {
      When("the user clicks to continue from the previous page")
      checkYourAnswersPage.continue()

      Then("the user is taken to the 'Successful Submission' page")
      requestSubmittedPage.assertPageTitle()
      requestSubmittedPage.assertUrl()
    }

    Scenario("[F1] (END) The user successfully returns to the Dashboard") {
      When("the user clicks to return to the homepage")
      requestSubmittedPage.ClickLinkHomepage()

      Then("the user should be taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }
  }

  Feature(
    "[F2] The user can request a new report of 'export'-type data and use their own EORI number."
  ) {
    Scenario("[F2] REQ-1: The user selects 'export' as the type of data.") {
      Given("the user is already on the 'Data Download Type' page")
      reportTypePage.navigateTo()
      reportTypePage.assertUrl()

      Then("the user can select 'export' as a type")
      reportTypePage.selectOptionByIndex(1)
    }

    Scenario("[F2] REQ-2: The user selects to use their own EORI number.") {
      When("the user clicks to continue from the previous page")
      reportTypePage.continue()

      Then("the user is taken to the 'Which EORI' page")
      whichEORIPage.assertUrl()
      whichEORIPage.assertPageTitle()

      And("the user can select to use their own EORI number.")
      whichEORIPage.selectOptionByIndex(0)
    }

    Scenario("[F2] REQ-4: The user selects the EORI role.") {
      When("the user clicks to continue from the previous page")
      whichEORIPage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()

      And("the user can select the 'Exporter' role.")
      reportOwnerTypePage.assertOptionText(1, "Exporter")
      reportOwnerTypePage.selectOptionByIndex(1)
    }

    Scenario("[F2] (END) REQ-7: The user is skipped to the 'report date range' page.") {
      When("the user clicks to continue from the previous page")
      reportOwnerTypePage.continue()

      Then("the user is taken to the 'report date range' page")
      reportDateRangeDecisionPage.assertUrl()
      reportDateRangeDecisionPage.assertPageTitle()
    }
  }

  Feature(
    "[F3] The user can request a new report of 'import'-type data but use a third-party EORI number."
  ) {
    Scenario("[F3] REQ-1: The user selects 'import' as the type of data.")(pending)

    Scenario("[F3] REQ-2: The user selects to use an EORI number they have authority over.")(pending)

    Scenario("[F3] REQ-3: The user selects the desired third party EORI.")(pending)
    // 29 April 2025 -- The content for this page is changing from dropdown to radio buttons. So we might as well wait for this change (dropdowns not used anywhere else).

    Scenario("[F3] (END) REQ-5: The user is skipped to selecting the sub-type of their 'import' report.")(pending)
  }

  Feature(
    "[F4] The user can request a new report of 'export'-type data but use a third-party EORI number."
  ) {
    Scenario("[F4] REQ-1: The user selects 'export' as the type of data.")(pending)

    Scenario("[F4] REQ-2: The user selects to use an EORI number they have authority over.")(pending)

    Scenario("[F4] REQ-3: The user selects the desired third party EORI.")(pending)
    // 29 April 2025 -- The content for this page is changing from dropdown to radio buttons. So we might as well wait for this change (dropdowns not used anywhere else).

    Scenario("[F4] (END) REQ-7: The user is skipped to the 'report date range' page.")(pending)
  }
}
