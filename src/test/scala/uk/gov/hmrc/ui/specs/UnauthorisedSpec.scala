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

import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, GuidancePage, UnauthorisedPage}

class UnauthorisedSpec extends BaseSpec {

  private val guidancePage = GuidancePage

  private val loginPage = AuthLoginStubPage

  private val unauthorisedPage = UnauthorisedPage

  Feature("User can see unauthorised page") {
    Scenario("User is not Authenticated ") {
      Given("I navigated to unauthorized age")
      guidancePage.continue()
      loginPage.show()
      loginPage.continue()
      unauthorisedPage.show()
    }
  }
}
