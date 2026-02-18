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

class DET_YourDetailsSpec extends BaseSpec {

  private val loginPage           = AuthLoginStubPage
  private val dashboardPage       = ACC_1_DashboardPage
  private val contactDetailsPage  = DET_1_ContactDetailsPage
  private val addNewEmailPage     = DET_2_AddNewEmailPage
  private val checkNewEmailPage   = DET_3_CheckNewEmailPage
  private val confirmNewEmailPage = DET_4_ConfirmNewEmailPage
  // private val removeEmailPage         = DET_5_RemoveEmailPage
  // private val confirmRemovalEmailPage = DET_6_ConfirmEmailRemovedPage

  private val strNewEmail = "additionalEmail@email.com"

  Feature("[F1] The user can view their account details and add an additional email") {
    Scenario("ACC-1: The user is authenticated.") {
      When(s"the user logs in with EORI $userTraderEori.")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(userTraderLogin)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario("[F1] Step-1: The user starts the 'Your Details' journey.") {
      Given("the user clicks the link on the dashboard")
      contactDetailsPage.clickLinkToPage()

      Then("the user is taken to the 'contact details' page")
      contactDetailsPage.assertUrl()
      contactDetailsPage.assertPageTitle()
    }

    Scenario("[F1] Step-2: The user clicks to add another email address.") {
      Given("the user clicks the link to add another email address")
      addNewEmailPage.clickLinkToPage()

      Then("the user is taken to the 'add new additional email' page")
      addNewEmailPage.assertUrl()
      addNewEmailPage.assertPageTitle()
    }

    Scenario("[F1] Step-3: The user enters an email address and continues to the check page.") {
      Given(s"the user enters a new email '$strNewEmail' into the field")
      addNewEmailPage.clearAndInputKeys(strNewEmail)

      And("the user clicks to continue")
      addNewEmailPage.continue()

      Then("the user is taken to the 'confirm new email' page")
      checkNewEmailPage.assertUrl()
      checkNewEmailPage.assertPageTitle()
    }

    Scenario("[F1] Step-4: The user checks and selects 'no' to return to the entry page") {
      Given("the user checks 'no' to the email being correct.")
      checkNewEmailPage.selectYesNo(false)

      And("the user clicks to continue")
      checkNewEmailPage.continue()

      Then("the user is taken back to the 'add new additional email' page again")
      addNewEmailPage.assertUrl()
      addNewEmailPage.assertPageTitle()
    }

    Scenario("[F1] Step-5: The user enters the same email and continues back to the check page.") {
      Given(s"the user clicks to continue, because '$strNewEmail' should still be present in the text field")
      addNewEmailPage.continue()

      Then("the user is taken back to the 'confirm new email' page")
      checkNewEmailPage.assertUrl()
      checkNewEmailPage.assertPageTitle()
    }

    Scenario("[F1] Step-6: The user checks the email and continues to the confirmation page.") {
      Given("the user checks 'yes' to the email being correct.")
      checkNewEmailPage.selectYesNo(true)

      And("the user clicks to continue")
      checkNewEmailPage.continue()

      Then("the user is taken to the 'confirmation' page")
      confirmNewEmailPage.assertUrl(strNewEmail)
      confirmNewEmailPage.assertPageTitle(strNewEmail)
    }

    Scenario("[F1] Step-7: The user returns to the 'Your Contact Details' page via the link.") {
      Given("the user clicks to return to the contact details page via the link")
      contactDetailsPage.clickLinkToPage()

      Then("the user is taken to the 'contact details' page")
      contactDetailsPage.assertUrl()
      contactDetailsPage.assertPageTitle()
    }
  }

  // Feature("[F2] The user can view their account details and remove an additional email") {
  //   Scenario("[F2] Step-1: x") {}
  // }
}
