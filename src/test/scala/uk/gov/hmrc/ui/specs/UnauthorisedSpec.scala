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
import support.builders.UserCredentialsBuilder.anOrganisationUserWithInvalidEnrolment

class UnauthorisedSpec extends BaseSpec {

  private val loginPage        = AuthLoginStubPage
  private val unauthorisedPage = ACC_KO_1_UnauthorisedPage

  Feature("The user encounters the unauthorised page.") {
    Scenario("ACC-KO-1: User is not Authenticated.") {
      Given("the user is not authenticated")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(anOrganisationUserWithInvalidEnrolment)
      loginPage.continue()

      Then("the user encounters the 'unauthorised' page")
      unauthorisedPage.assertUrl()
      unauthorisedPage.assertPageTitle()
    }
  }
}
