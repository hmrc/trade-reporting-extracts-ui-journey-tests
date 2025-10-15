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
import support.builders.EnrolmentsDataBuilder.enrolmentRandomEORI.identifierValue as randEORI
import support.builders.UserCredentialsBuilder.aSinglePartyUser

class MTP_ManageThirdPartySpec extends BaseSpec {

  private val loginPage                 = AuthLoginStubPage
  private val dashboardPage             = ACC_1_DashboardPage
  private val manageThirdPartyPage      = MTP_1_ManageThirdPartyPage
  private val viewThirdPartyDetailsPage = MTP_2_ViewThirdPartyDetailsPage
  private val removeThirdPartyPage      = MTP_3_RemoveThirdPartyPage
  private val removeConfirmationPage    = MTP_4_RemoveConfirmationPage

  Feature("[F1] The user can manage their third parties.") {
    Scenario("[F1] ACC-1: The user is authenticated.") {
      When("the user logs in using an organisation with a known enrolment")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(aSinglePartyUser)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-1: The user has added the third party '$randEORI' to their account.") {
      When("the user clicks the link on the dashboard")
      manageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page")
      manageThirdPartyPage.assertUrl()
      manageThirdPartyPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-2: The user clicks to view '$randEORI' details.") {
      Given("the user clicks the 'view' link.")
      viewThirdPartyDetailsPage.clickLinkToPage()

      Then("the user is taken to the 'view third party details' page")
      viewThirdPartyDetailsPage.assertUrl()
      viewThirdPartyDetailsPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-3: The user clicks to remove '$randEORI'.") {
      Given("the user returns to the manage page.")
      viewThirdPartyDetailsPage.browserBack

      When("the user clicks the 'remove' link.")
      removeThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'remove third party' page")
      removeThirdPartyPage.assertUrl()
      removeThirdPartyPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-4: The user clicks 'no' to removing '$randEORI'.") {
      Given("the user clicks 'no' to removing the third party")
      removeThirdPartyPage.selectYesNo(false)

      When("the user clicks to continue")
      removeThirdPartyPage.continue()

      Then("the user is taken to the 'dashboard' page")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-5: The user clicks 'yes' to removing '$randEORI'.") {
      Given("the user returns to the remove page")
      dashboardPage.browserBack

      And("the user clicks 'yes' to removing the third party")
      removeThirdPartyPage.selectYesNo(true)

      When("the user clicks to continue")
      removeThirdPartyPage.continue()

      Then("the user is taken to the 'confirmation' page")
      removeConfirmationPage.assertUrl()
      removeConfirmationPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-6: The user returns to the dashboard.") {
      When("the user clicks to return to the 'manage' page")
      manageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page which is now empty again.")
      manageThirdPartyPage.assertUrl()
      manageThirdPartyPage.assertPageTitle(manageThirdPartyPage.titleNoThirdPartiesAdded)
    }
  }
}
