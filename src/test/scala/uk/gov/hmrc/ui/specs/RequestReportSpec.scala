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
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, GuidancePage, RequestReportPage}

class RequestReportSpec extends BaseSpec {

  private val loginPage         = AuthLoginStubPage
  private val guidancePage      = GuidancePage
  private val requestReportPage = RequestReportPage

  Feature("User can request a report") {
    Scenario("User is Authenticated and request a report") {
      Given("I navigated to request report page")
      guidancePage.continue()
      loginPage.show()
      loginPage.loginAs(anOrganisationUserWithKnownEnrolment)
      requestReportPage.show()
      requestReportPage.continue()

    }
  }
}
