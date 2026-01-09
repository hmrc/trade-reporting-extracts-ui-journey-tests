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
import support.BaseSpec

class TPA_ManageBusinessesSpec extends BaseSpec {

  private val loginPage              = AuthLoginStubPage
  private val dashboardPage          = ACC_1_DashboardPage
  private val businessesAccessTo     = TPA_1_BusinessesAccessToPage
  private val businessDetails        = TPA_2_BusinessDetailsPage
  private val businessRemove         = TPA_3_BusinessRemovePage
  private val businessRemovedConfirm = TPA_4_BusinessRemoveConfirmPage

  Feature("[F1] The user can manage the businesses they have access to.") {
    Scenario(s"[F1] SETUP: Prepare MongoDB with $userThirdPartyEORI already added to $userTraderEori.") {
      Given("the mongoDB is prepped then a success should be returned.")
      assert(PrepMongoInsertRecord() == true)
    }

    Scenario("[F1] ACC-1: The user is authenticated.") {
      When(s"the user logs in with the third party EORI $userThirdPartyEORI.")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(userThirdPartyLogin)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Step-1: The user can see the businesses they have access to page, which is populated.") {
      When("the user clicks the link on the dashboard")
      businessesAccessTo.clickLinkToPage()

      Then("the user is taken to the 'businesses they have access to' page.")
      businessesAccessTo.assertUrl()
      businessesAccessTo.assertPageTitle()
    }

    Scenario(s"[F1] Step-2: The user can click to view business details for $userTraderEori") {
      When(s"the user clicks the link to view $userTraderEori details.")
      businessDetails.clickLinkToPage(businessDetails.pageRelativeAddress + userTraderEori)

      Then("the user is taken to the 'business details' page.")
      businessDetails.assertUrl(businessDetails.pageFullAddress + userTraderEori)
      businessDetails.assertPageTitle()
    }

    Scenario(s"[F1] Step-3: The user can click to remove business details for $userTraderEori") {
      Given(s"the user returns to the 'businesses they have access to' page.")
      businessesAccessTo.navigateTo()

      When(s"the user clicks the link to remove $userTraderEori.")
      businessRemove.clickLinkToPage(businessRemove.pageRelativeAddress + userTraderEori)

      Then("the user is taken to the 'are you sure?' page.")
      businessRemove.assertUrl(businessRemove.pageFullAddress + userTraderEori)
      businessRemove.assertPageTitle()
    }

    Scenario(s"[F1] Step-4: The user can click 'no' to removing $userTraderEori") {
      Given(s"the user selects the 'no' radio button.")
      businessRemove.selectYesNo(false)

      When(s"the user clicks to continue.")
      businessRemove.continue()

      Then("the user is taken back to the 'business details' page.")
      businessesAccessTo.assertUrl()
      businessesAccessTo.assertPageTitle()
    }

    Scenario(s"[F1] Step-5: The user can click 'yes' to removing $userTraderEori") {
      Given(s"the user returns to the 'are you sure?' page.")
      businessRemove.navigateTo(businessRemove.pageFullAddress + userTraderEori)

      And("the user selects the 'yes' radio button.")
      businessRemove.selectYesNo(true)

      When(s"the user clicks to continue.")
      businessRemove.continue()

      Then("the user is taken to the 'Access Removed Confirmation' page.")
      businessRemovedConfirm.assertUrl()
      businessRemovedConfirm.assertPageTitle()
    }

    Scenario("[F1] Step-6: The businesses they have access to page is now empty.") {
      When("the user clicks the link to return.")
      businessesAccessTo.clickLinkToPage()

      Then("the user is taken to the 'businesses they have access to' page.")
      businessesAccessTo.assertUrl()
      businessesAccessTo.assertPageTitle(businessesAccessTo.titleNoAccess)
    }
  }
}
