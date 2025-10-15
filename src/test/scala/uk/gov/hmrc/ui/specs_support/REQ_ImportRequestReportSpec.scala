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
import uk.gov.hmrc.ui.models.UserCredentials

class REQ_RequestReportSpec(enrollmentToUse: UserCredentials) extends BaseSpec {

  private val loginPage                   = AuthLoginStubPage
  private val dashboardPage               = ACC_1_DashboardPage
  private val requestReportPage           = REQ_1_RequestReportPage
  private val reportTypePage              = REQ_2_ReportTypePage
  private val whichEORIPage               = REQ_3_WhichEORIPage
  private val reportOwnerTypePage         = REQ_5_ReportOwnerTypePage
  private val reportImportTypePage        = REQ_6_ImportTypeSelectionPage
  private val reportDateRangeDecisionPage = REQ_8_ReportDateRangeDecisionPage
  private val ReportCustomStartPage       = REQ_9_ReportCustomDateRangeStartPage
  private val ReportCustomEndPage         = REQ_10_ReportCustomDateRangeEndPage
  private val reportNamePage              = REQ_11_ReportNamePage
  private val chooseEmailPage             = REQ_12_ChooseToAddEmailPage
  // private val selectEmailsPage            = REQ_13_SelectEmailsPage
  private val enterNewEmailPage           = REQ_14_EnterNewEmailPage
  private val checkNewEmailPage           = REQ_14_1_CheckEmailPage
  private val checkYourAnswersPage        = REQ_15_CheckYourAnswersPage
  private val requestSubmittedPage        = REQ_16_ReportRequestSubmittedPage

  Feature(
    "[F1] The user can request a new report of 'import'-type data and use their own EORI number to complete the journey."
  ) {

    Scenario("[F1] ACC-1: The user is authenticated.") {
      When("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(enrollmentToUse)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Dashboard: The user selects the 'Request New Report' journey.") {
      When("the user clicks the link on the dashboard")
      requestReportPage.clickLinkToPage()

      Then("the user is taken to the information page")
      requestReportPage.assertUrl()
    }

    Scenario("[F1] Step-0: The user selects 'Continue' to begin the journey.") {
      When("the user clicks 'Continue'.")
      requestReportPage.continue()

      Then("the user is taken to the 'Which EORI' page")
      whichEORIPage.assertUrl()
      whichEORIPage.assertPageTitle()
    }

    Scenario("[F1] Step-1: The user selects to use their own EORI number.") {
      Given("the user selects to use their own EORI number")
      whichEORIPage.selectOptionByIndex(0)

      When("the user clicks to continue")
      whichEORIPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()
    }

    Scenario("[F1] Step-2: The user selects 'import' as the type of data.") {
      Given("the user selects 'import' as a type")
      reportTypePage.selectOptionByIndex(0)

      When("the user clicks to continue")
      reportTypePage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()

    }

    Scenario("[F1] Step-4: The user selects the EORI role.") {
      Given("the user can select both the 'Declarant' and 'Importer' roles.")
      reportOwnerTypePage.selectOptionByValue("declarant")
      reportOwnerTypePage.selectOptionByValue("importer")

      When("the user clicks to continue")
      reportOwnerTypePage.continue()

      Then("the user is taken to the '(sub)type of report' page")
      reportImportTypePage.assertUrl()
      reportImportTypePage.assertPageTitle()
    }

    Scenario("[F1] Step-5: The user selects the sub-type of their 'import' report.") {
      Given("the user selects the 'Import header, item' and 'tax line' types.")
      reportImportTypePage.selectOptionByIndex(0)
      reportImportTypePage.selectOptionByIndex(1)
      reportImportTypePage.selectOptionByIndex(2)

      When("the user clicks to continue")
      reportImportTypePage.continue()

      Then("the user is taken to the 'report date range' page")
      reportDateRangeDecisionPage.assertUrl()
      reportDateRangeDecisionPage.assertPageTitle()
    }

    Scenario("[F1] Step-6: The user selects the date range of their report.") {
      Given("the user selects the 'Custom date range' option")
      reportDateRangeDecisionPage.selectOptionByIndex(1)

      When("the user clicks to continue")
      reportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'custom report range start' page")
      ReportCustomStartPage.assertUrl()
      ReportCustomStartPage.assertPageTitle()
    }

    Scenario("[F1] Step-7: The user gives a custom 'start' date range for their report.") {
      Given("the user enters a date up to four years ago for the date range to begin")
      ReportCustomStartPage.clearAndInputKeys(
        ReportCustomStartPage.getDateMinusYears("dd", 4),
        ReportCustomStartPage.inputCustomDay
      )
      ReportCustomStartPage.clearAndInputKeys(
        ReportCustomStartPage.getDateMinusYears("MM", 4),
        ReportCustomStartPage.inputCustomMonth
      )
      ReportCustomStartPage.clearAndInputKeys(
        ReportCustomStartPage.getDateMinusYears("yyyy", 4),
        ReportCustomStartPage.inputCustomYear
      )

      When("the user clicks to continue")
      ReportCustomStartPage.continue()

      Then("the user is taken to the 'custom report range end' page")
      ReportCustomEndPage.assertUrl()
      ReportCustomEndPage.assertPageTitle()
    }

    Scenario("[F1] Step-8: The user gives a custom 'end' date range for their report.") {
      Given("the user enters a date up to four years ago for the date range to end")
      ReportCustomEndPage.clearAndInputKeys(
        ReportCustomEndPage.getDateMinusYears("dd", 4),
        ReportCustomEndPage.inputCustomDay
      )
      ReportCustomEndPage.clearAndInputKeys(
        ReportCustomEndPage.getDateMinusYears("MM", 4),
        ReportCustomEndPage.inputCustomMonth
      )
      ReportCustomEndPage.clearAndInputKeys(
        ReportCustomEndPage.getDateMinusYears("yyyy", 4),
        ReportCustomEndPage.inputCustomYear
      )

      When("the user clicks to continue")
      reportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'report name' page")
      reportNamePage.assertUrl()
      reportNamePage.assertPageTitle()
    }

    Scenario("[F1] Step-9: The user enters a name for their report.") {
      Given(s"the user enters text with spaces into the text box of up to ${reportNamePage.inputLimit} characters.")
      reportNamePage.clearAndInputKeys("a" * reportNamePage.inputLimit)

      When("the user clicks to continue")
      reportNamePage.continue()

      if (enrollmentToUse.isThirdParty) {
        Then("the user is taken to the 'choose to add email' page")
        chooseEmailPage.assertUrl()
        chooseEmailPage.assertPageTitle()
      } else {
        Then("the user is taken to the 'Confirm Details' page")
        checkYourAnswersPage.assertUrl()
        checkYourAnswersPage.assertPageTitle()
      }
    }

    if (enrollmentToUse.isThirdParty) {
      Scenario("[F1] Step-10: The user selects whether to add another email for notifications.") {
        Given("the user selects the 'Yes' option")
        chooseEmailPage.selectYesNo(true)

        When("the user clicks to continue")
        chooseEmailPage.continue()

        // Then("the user is taken to the 'choose to add email' page")
        // selectEmailsPage.assertUrl()
        // selectEmailsPage.assertPageTitle()
        Then("the user is taken to the 'Enter new email address' page")
        enterNewEmailPage.assertUrl()
        enterNewEmailPage.assertPageTitle()
      }

      // QA Note:
      // Step-11 is skipped if the account has no additional email address associated.
      // An email will need to be added earlier on in the tests/by direct DB editing.

      // Scenario("[F1] Step-11: The user selects what emails are to receive notifications.") {
      //   Given("the user selects the 'Add new email address' option")
      //   selectEmailsPage.selectOptionByValue(selectEmailsPage.inputAddNewEmail)

      //   When("the user clicks to continue")
      //   selectEmailsPage.continue()

      //   Then("the user is taken to the 'Enter new email address' page")
      //   enterNewEmailPage.assertUrl()
      //   enterNewEmailPage.assertPageTitle()
      // }

      Scenario("[F1] Step-12: The user adds a new email.") {
        Given("the user enters the new email address in the text box")
        enterNewEmailPage.clearAndInputKeys("myexample@email.com")

        When("the user clicks to continue")
        enterNewEmailPage.continue()

        Then("the user is taken to the 'check new email' page")
        checkNewEmailPage.assertUrl()
        checkNewEmailPage.assertPageTitle()
      }

      Scenario("[F1] Step-13: The user clicks 'yes' to confirm the new email.") {
        Given("the user clicks 'yes' to confirm the new email")
        checkNewEmailPage.selectYesNo(true)

        When("the user clicks to continue")
        checkNewEmailPage.continue()

        Then("the user is taken to the 'check your answers' page")
        checkYourAnswersPage.assertUrl()
        checkYourAnswersPage.assertPageTitle()
      }
    }

    Scenario("[F1] Step-14: The user reaches the confirmation screen.") {
      When("the user clicks to continue")
      checkYourAnswersPage.continue()

      Then("the user is taken to the 'Successful Submission' page")
      requestSubmittedPage.assertUrl()
      requestSubmittedPage.assertPageTitle()
    }

    Scenario("[F1] Step-15: The user successfully submits") {
      When("the user clicks to return to the homepage")
      dashboardPage.clickLinkToPage()

      Then("the user should be taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }
  }

}
