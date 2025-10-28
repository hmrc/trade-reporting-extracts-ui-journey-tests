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

import uk.gov.hmrc.ui.models.UserCredentials
import uk.gov.hmrc.ui.pages._

class REQ_ExportRequestReportSpec(enrollmentToUse: UserCredentials) extends BaseSpec {

  private val loginPage                   = AuthLoginStubPage
  private val dashboardPage               = ACC_1_DashboardPage
  private val requestReportPage           = REQ_1_RequestReportPage
  private val reportTypePage              = REQ_2_ReportTypePage
  private val whichEORIPage               = REQ_3_WhichEORIPage
  private val reportOwnerTypePage         = REQ_5_ReportOwnerTypePage
  private val reportSubTypeExportPage     = REQ_7_ReportSubtypeExportPage
  private val reportDateRangeDecisionPage = REQ_8_ReportDateRangeDecisionPage

  Feature(
    "The user can request a new report of 'export'-type data and use their own EORI number to complete the journey."
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

    Scenario("[F2] Step-2: The user selects 'export' as the type of data.") {
      Given("the user can select 'export' as a type")
      reportTypePage.selectOptionByIndex(1)

      When("the user clicks to continue")
      reportTypePage.continue()

      Then("the user is taken to the 'EORI Role' page")
      reportOwnerTypePage.assertUrl()
      reportOwnerTypePage.assertPageTitle()
    }

    Scenario("[F2] Step-3: The user selects the EORI roles.") {
      Given("the user can select both the 'Declarant' and 'Exporter' roles.")
      reportOwnerTypePage.selectOptionByValue("declarant")
      reportOwnerTypePage.selectOptionByValue("exporter")

      When("the user clicks to continue")
      reportOwnerTypePage.continue()

      Then("the user is taken to the 'export' subtype page")
      reportSubTypeExportPage.assertUrl()
      reportSubTypeExportPage.assertPageTitle()
    }

    Scenario("[F2] E(ND) Step-4: The user continues on to the date range page.") {
      When("the user clicks to continue")
      reportSubTypeExportPage.continue()

      Then("the user is taken to the 'report date range' page")
      reportDateRangeDecisionPage.assertUrl()
      reportDateRangeDecisionPage.assertPageTitle("What date range do you want the report to cover?")
    }
  }
}
