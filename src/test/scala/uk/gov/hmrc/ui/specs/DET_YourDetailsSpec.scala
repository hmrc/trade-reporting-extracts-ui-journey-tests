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


  private val strNewEmail = "additionalEmail@email.com"

  Feature("[F1] The user can view their account details and add and remove additional email") {
    Scenario(s"The user can view their account details and add an additional email.") {
      Given("the user logs in with EORI $userTraderEori.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userTraderLogin)
      AuthLoginStubPage.continue()

      When("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      And("the user clicks the link on the dashboard")
      DET_1_ContactDetailsPage.clickLinkToPage()

      Then("the user is taken to the 'contact details' page")
      DET_1_ContactDetailsPage.assertUrl()
      DET_1_ContactDetailsPage.assertPageTitle()

      When("the user clicks the link to add another email address")
      DET_2_AddNewEmailPage.clickLinkToPage()

      Then("the user is taken to the 'add new additional email' page")
      DET_2_AddNewEmailPage.assertUrl()
      DET_2_AddNewEmailPage.assertPageTitle()


      When(s"the user enters a new email '$strNewEmail' into the field")
      DET_2_AddNewEmailPage.clearAndInputKeys(strNewEmail)

      And("the user clicks to continue")
      DET_2_AddNewEmailPage.continue()

      Then("the user is taken to the 'confirm new email' page")
      DET_3_CheckNewEmailPage.assertUrl()
      DET_3_CheckNewEmailPage.assertPageTitle()

      When("the user checks 'no' to the email being correct.")
      DET_3_CheckNewEmailPage.selectYesNo(false)

      And("the user clicks to continue")
      DET_3_CheckNewEmailPage.continue()

      Then("the user is taken back to the 'add new additional email' page again")
      DET_2_AddNewEmailPage.assertUrl()
      DET_2_AddNewEmailPage.assertPageTitle()

      Given(s"the user clicks to continue, because '$strNewEmail' should still be present in the text field")
      DET_2_AddNewEmailPage.continue()

      Then("the user is taken back to the 'confirm new email' page")
      DET_3_CheckNewEmailPage.assertUrl()
      DET_3_CheckNewEmailPage.assertPageTitle()

      Given("the user checks 'yes' to the email being correct.")
      DET_3_CheckNewEmailPage.selectYesNo(true)

      And("the user clicks to continue")
      DET_3_CheckNewEmailPage.continue()

      Then("the user is taken to the 'confirmation' page")
      DET_4_ConfirmNewEmailPage.assertUrl(strNewEmail)
      DET_4_ConfirmNewEmailPage.assertPageTitle(strNewEmail)

      Given("the user clicks to return to the contact details page via the link")
      DET_1_ContactDetailsPage.clickLinkToPage()

      Then("the user is taken to the 'contact details' page")
      DET_1_ContactDetailsPage.assertUrl()
      DET_1_ContactDetailsPage.assertPageTitle()
    }


    Scenario("The user can view their account details and remove an additional email.") {
      Given("the user can click on remove button")
      DET_5_RemoveEmailPage.clickLinkToPage(strNewEmail)

      Then("User is taken to the 'remove email' page")
      DET_5_RemoveEmailPage.assertUrl(strNewEmail)
      DET_5_RemoveEmailPage.assertPageTitle()


      When("the user selects the 'no' radio button.")
      DET_5_RemoveEmailPage.selectYesNo(false)

      And("the user clicks to continue.")
      DET_5_RemoveEmailPage.continue()

      Then("the user is taken back to the 'contact details' page.")
      DET_1_ContactDetailsPage.assertUrl()
      DET_1_ContactDetailsPage.assertPageTitle()

      Given("the user returns to the 'remove email' page.")
      DET_5_RemoveEmailPage.clickLinkToPage(strNewEmail)

      And("the user selects the 'yes' radio button.")
      DET_5_RemoveEmailPage.selectYesNo(true)

      When("the user clicks to continue.")
      DET_5_RemoveEmailPage.continue()

      Then("the user is taken to the 'confirm removal email' page.")
      DET_6_ConfirmEmailRemovedPage.assertUrl(strNewEmail)
      DET_6_ConfirmEmailRemovedPage.assertPageTitle()
    }

  }
}
