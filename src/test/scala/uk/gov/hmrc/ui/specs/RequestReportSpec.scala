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
  private val reportOwnerTypePage         = REQ_3_ReportOwnerTypePage
  private val reportSubTypePage           = REQ_4_ReportSubtypeSelectionPage
  private val reportDateRangeDecisionPage = REQ_5_ReportDateRangeDecisionPage
  private val reportNamePage              = REQ_6_ReportNamePage
  private val chooseEmailPage             = REQ_7_ChooseToAddEmailPage
  private val selectEmailsPage            = REQ_8_SelectEmailsPage

  Feature("The user can request a new report of 'import' type data.") {

    Scenario("ACC-1: The user is authenticated.") {
      When("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("REQ-0: The user starts the 'Request New Report' journey.") {
      When("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkRequestNewReport)

      Then("the user is taken to the information page")
      requestReportPage.assertUrl()
      requestReportPage.assertPageTitle()
    }

    Scenario("REQ-1: The user selects the type of data.") {
      When("the user clicks to continue from the previous page")
      requestReportPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()

      And("the user can select 'import' as a type")
      reportTypePage.selectOption(0)
    }

    Scenario("REQ-2: The user selects to use their own EORI number.") {
      When("the user clicks to continue from the previous page")
      reportTypePage.continue()

      Then("the user is taken to the 'Which EORI' page")
      whichEORIPage.assertUrl()
      whichEORIPage.assertPageTitle()

      And("the user can select to use their own EORI number.")
      whichEORIPage.selectOption(0)
    }

    Scenario("REQ-3: The user selects the EORI role.") {
      When("the user clicks to continue from the previous page")
      whichEORIPage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()

      And("the user can select both the 'Declarant' and 'Exporter' roles.")
      reportOwnerTypePage.selectOption(0)
      reportOwnerTypePage.selectOption(1)
    }

    Scenario("REQ-4: The user selects the sub-type of their 'import' report.") {
      When("the user clicks to continue from the previous page")
      reportOwnerTypePage.continue()

      Then("the user is taken to the '(sub)type of report' page")
      reportSubTypePage.assertUrl()
      reportSubTypePage.assertPageTitle()

      And("the user can select the 'import header' type.")
      reportSubTypePage.selectOption(0)
    }

    Scenario("REQ-5: The user selects the date range of their report.") {
      When("the user clicks to continue from the previous page")
      reportSubTypePage.continue()

      Then("the user is taken to the 'report date range decision' page")
      reportDateRangeDecisionPage.assertUrl()
      reportDateRangeDecisionPage.assertPageTitle()

      And("the user can select the 'Custom date range' option")
      reportDateRangeDecisionPage.selectOption(2)
    }

    Scenario("REQ-12: The user gives a custom date range for their report.")(pending)

    Scenario("REQ-6: The user enters a name for their report.") {
      When("the user clicks to continue from the previous page")
      reportDateRangeDecisionPage.continue()

      Then("the user is taken to the 'report name' page")
      reportNamePage.assertUrl()
      // reportNamePage.assertPageTitle() -- 17 April 2024: Title is incorrect. Correction is pending tech cleanup sweep.

      And("the user can enter text into the text box")
      reportNamePage.clearAndInputKeys(reportNamePage.inputReportName, "a")
    }

    Scenario("REQ 7: The user selects whether to add another email for notifications.") {
      When("the user clicks to continue from the previous page")
      reportNamePage.continue()

      Then("the user is taken to the 'choose to add email' page")
      chooseEmailPage.assertPageTitle()
      chooseEmailPage.assertUrl()

      And("the user can select the 'Yes' option")
      chooseEmailPage.selectYesNo(0)
    }

    Scenario("REQ 8: The user selects what emails are to receive notifications.") {
      When("the user clicks to continue from the previous page")
      chooseEmailPage.continue()

      Then("the user is taken to the 'choose to add email' page")
      selectEmailsPage.assertPageTitle()
      selectEmailsPage.assertUrl()

      And("the user can select the 'Add new email address' option")
      selectEmailsPage.selectOption(2)
    }

    Scenario("The user adds a new email.")(pending)

    Scenario("The user reaches the confirmation screen.")(pending)
  }

  Feature("ACC-1: The user can request a new report of 'export' type data.") {
    Scenario("The user is authenticated.") {
      When("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user should be taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("REQ-0: The user starts the 'Request New Report' journey.") {
      When("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkRequestNewReport)

      Then("the user should be taken to the information page")
      requestReportPage.assertUrl()
      requestReportPage.assertPageTitle()
    }

    Scenario("REQ-1: The user selects the type of data.") {
      When("the user clicks to continue from the previous page")
      requestReportPage.continue()

      Then("the user should be taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()

      And("the user should be able to select 'export' as a type")
      reportTypePage.selectOption(0)
    }

    Scenario("REQ-2: The user selects to use their own EORI number.")(pending)

    Scenario("REQ-3: The user selects the EORI role.")(pending)

    Scenario("REQ-4: The user selects the sub-type of their 'export' report.")(pending)

    // End of this Feature spec -- the rest of journey from this point is covered by the first Feature spec.
  }
}
