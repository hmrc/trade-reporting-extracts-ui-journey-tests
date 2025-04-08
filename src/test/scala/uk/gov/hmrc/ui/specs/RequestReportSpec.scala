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

import support.builders.UserCredentialsBuilder.anOrganisationUserWithKnownEnrolment
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, ReportTypePage, RequestReportPage, WhichEORIPage}

class RequestReportSpec extends BaseSpec {

  private val loginPage         = AuthLoginStubPage
  private val requestReportPage = RequestReportPage
  private val reportTypePage    = ReportTypePage
  private val whichEORIPage     = WhichEORIPage

  Feature("User requests a new report") {
    Scenario("The user requests a new report with 'import' type of data.") {
      Given("the user is authenticated")
      loginPage.show()
      loginPage.loginAs(anOrganisationUserWithKnownEnrolment)

      When("the user clicks to request a new report")
      requestReportPage.show()
      requestReportPage.continue()

      When("the user can select 'import' as the type of data")
      reportTypePage.selectOption(0)
      reportTypePage.continue()

      When("the user can select to use their own EORI number.")
      whichEORIPage.assertPageTitle()
      whichEORIPage.selectOption(0)
      whichEORIPage.continue()

      When("the user can selects both the 'Declarant' and 'Exporter' roles.")
    }
  }
}
