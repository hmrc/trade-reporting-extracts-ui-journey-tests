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
  private val dashboardPage               = DashboardPage
  private val requestReportPage           = RequestReportPage
  private val reportTypePage              = ReportTypePage
  private val whichEORIPage               = WhichEORIPage
  private val reportOwnerTypePage         = ReportOwnerTypePage
  private val reportSubTypePage           = ReportSubtypeSelectionPage
  private val reportDateRangeDecisionPage = ReportDateRangeDecisionPage

  Feature("The user can request a new report of 'import' type data.") {

    Scenario("The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("The user starts the 'Request New Report' journey.") {
      Given("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkRequestNewReport)

      Then("the user is taken to the information page")
      requestReportPage.assertUrl()
      requestReportPage.assertPageTitle()
    }

    Scenario("The user selects the type of data.") {
      Given("the user clicks to continue from the previous page")
      requestReportPage.continue()

      Then("the user is taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()

      And("the user can select 'import' as a type")
      reportTypePage.selectOption(0)
    }

    Scenario("The user selects to use their own EORI number.") {
      Given("the user clicks to continue from the previous page")
      reportTypePage.continue()

      Then("the user is taken to the 'Which EORI' page")
      whichEORIPage.assertUrl()
      whichEORIPage.assertPageTitle()

      And("the user can select to use their own EORI number.")
      whichEORIPage.selectOption(0)
    }

    Scenario("The user selects the EORI role.") {
      Given("the user clicks to continue from the previous page")
      whichEORIPage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()

      And("the user can select both the 'Declarant' and 'Exporter' roles.")
      reportOwnerTypePage.selectOption(0)
      reportOwnerTypePage.selectOption(1)
    }

    Scenario("The user selects the sub-type of their 'import' report.") {
      Given("the user clicks to continue from the previous page")
      reportOwnerTypePage.continue()

      Then("the user is taken to the '(sub)type of report' page")
      reportSubTypePage.assertUrl()
      reportSubTypePage.assertPageTitle()

      And("the user can select the 'import header' type.")
      reportSubTypePage.selectOption(0)
    }

    Scenario("The user selects the date range of their report.")
    Given("the user select the last 31 days option")
    reportDateRangeDecisionPage.selectOption(0)

    Then("the user is taken to the report name input page")

    Scenario("The user gives a custom date range for their report.")(pending)

    Scenario("The user enters a name for their report.")(pending)

    Scenario("The user selects to add another email for notifications.")(pending)

    Scenario("The user selects what emails are to receive notifications.")(pending)

    Scenario("The user adds a new email.")(pending)

    Scenario("The user reaches the confirmation screen.")(pending)
  }

  Feature("The user can request a new report of 'export' type data.") {
    Scenario("The user is authenticated.") {
      Given("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithKnownEnrolment)
      loginPage.continue()

      Then("the user should be taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("The user starts the 'Request New Report' journey.") {
      Given("the user clicks the link on the dashboard")
      dashboardPage.selectLink(dashboardPage.linkRequestNewReport)

      Then("the user should be taken to the information page")
      requestReportPage.assertUrl()
      requestReportPage.assertPageTitle()
    }

    Scenario("The user selects the type of data.") {
      Given("the user clicks to continue from the previous page")
      requestReportPage.continue()

      Then("the user should be taken to the 'Data Download Type' page")
      reportTypePage.assertUrl()
      reportTypePage.assertPageTitle()

      And("the user should be able to select 'export' as a type")
      reportTypePage.selectOption(0)
    }

    Scenario("The user selects the sub-type of their 'export' report.")(pending)

    Scenario("The user continues on to the rest of the journey.")(pending)
    // Check it navigates to date-range screen.

    // Rest of journey from this point covered by the first Feature spec.
  }
}
