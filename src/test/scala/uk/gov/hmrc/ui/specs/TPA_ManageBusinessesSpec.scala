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

  Feature("The user can manage the businesses they have access to.") {
    Scenario(s"The user with third-party EORI $userThirdPartyEORI can manage the trader $userTraderEori.") {
      Given("the mongoDB is prepped then a success should be returned.")
      assert(PrepMongoInsertRecord() == true)

      When(s"the user logs in with the third party EORI $userThirdPartyEORI.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userThirdPartyLogin)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      When("the user clicks the link on the dashboard")
      TPA_1_BusinessesAccessToPage.clickLinkToPage()

      Then("the user is taken to the 'businesses they have access to' page.")
      TPA_1_BusinessesAccessToPage.assertUrl()
      TPA_1_BusinessesAccessToPage.assertPageTitle()

      When(s"the user clicks the link to view $userTraderEori details.")
      TPA_2_BusinessDetailsPage.clickLinkToPage(TPA_2_BusinessDetailsPage.pageRelativeAddress + userTraderEori)

      Then("the user is taken to the 'business details' page.")
      TPA_2_BusinessDetailsPage.assertUrl(TPA_2_BusinessDetailsPage.pageFullAddress + userTraderEori)
      TPA_2_BusinessDetailsPage.assertPageTitle()

      Given(s"the user returns to the 'businesses they have access to' page.")
      TPA_1_BusinessesAccessToPage.navigateTo()

      When(s"the user clicks the link to remove $userTraderEori.")
      TPA_3_BusinessRemovePage.clickLinkToPage(TPA_3_BusinessRemovePage.pageRelativeAddress + userTraderEori)

      Then("the user is taken to the 'are you sure?' page.")
      TPA_3_BusinessRemovePage.assertUrl(TPA_3_BusinessRemovePage.pageFullAddress + userTraderEori)
      TPA_3_BusinessRemovePage.assertPageTitle()

      Given(s"the user selects the 'no' radio button.")
      TPA_3_BusinessRemovePage.selectYesNo(false)

      When(s"the user clicks to continue.")
      TPA_3_BusinessRemovePage.continue()

      Then("the user is taken back to the 'business details' page.")
      TPA_1_BusinessesAccessToPage.assertUrl()
      TPA_1_BusinessesAccessToPage.assertPageTitle()

      Given(s"the user returns to the 'are you sure?' page.")
      TPA_3_BusinessRemovePage.navigateTo(TPA_3_BusinessRemovePage.pageFullAddress + userTraderEori)

      And("the user selects the 'yes' radio button.")
      TPA_3_BusinessRemovePage.selectYesNo(true)

      When(s"the user clicks to continue.")
      TPA_3_BusinessRemovePage.continue()

      Then("the user is taken to the 'Access Removed Confirmation' page.")
      TPA_4_BusinessRemoveConfirmPage.assertUrl()
      TPA_4_BusinessRemoveConfirmPage.assertPageTitle()

      When("the user clicks the link to return.")
      TPA_1_BusinessesAccessToPage.clickLinkToPage()

      Then("the user is taken to the 'businesses they have access to' page.")
      TPA_1_BusinessesAccessToPage.assertUrl()
      TPA_1_BusinessesAccessToPage.assertPageTitle()
    }
  }
}
