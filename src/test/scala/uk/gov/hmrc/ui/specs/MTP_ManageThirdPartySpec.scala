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

class MTP_ManageThirdPartySpec extends BaseSpec {

  Feature("The user can EDIT and REMOVE a third party.") {

    Scenario(s"The user with trader EORI $userThirdPartyEORI can EDIT third party $userTraderEori.") {
      Given("the mongoDB is prepped then a success should be returned.")
      assert(PrepMongoInsertRecord() == true)

      When(s"the user logs in with EORI $userTraderEori.")
      AuthLoginStubPage.navigateTo()
      AuthLoginStubPage.enterRedirectionUrl()
      AuthLoginStubPage.enterEnrollment(userTraderLogin)
      AuthLoginStubPage.continue()

      Then("the user is taken to the dashboard.")
      ACC_1_DashboardPage.assertUrl()
      ACC_1_DashboardPage.assertPageTitle()

      When("the user clicks the link on the dashboard")
      MTP_1_ManageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page")
      MTP_1_ManageThirdPartyPage.assertUrl()
      MTP_1_ManageThirdPartyPage.assertPageTitle()

      Given("the user clicks the 'edit' link.")
      MTP_2_EditThirdPartyDetailsPage.clickLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit third party details' page")
      MTP_2_EditThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      MTP_2_EditThirdPartyDetailsPage.assertPageTitle()

      // Third Party "Access Period"

      Given("the user clicks the 'change' link.")
      ADD_6_AccessStartPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit access start' page")
      ADD_6_AccessStartPage.assertEditUrl(userThirdPartyEORI)
      ADD_6_AccessStartPage.assertPageTitle()

      Given("the user clicks to continue.")
      ADD_6_AccessStartPage.continue()

      Then("the user is taken to the 'edit access end' page")
      ADD_7_AccessEndPage.assertEditUrl(userThirdPartyEORI)
      ADD_7_AccessEndPage.assertPageTitle()

      Given("the user clicks to continue.")
      ADD_7_AccessEndPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      MTP_2_EditThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      MTP_2_EditThirdPartyDetailsPage.assertPageTitle()

      // Third Party "Types of Data"

      Given("the user clicks the 'change' link.")
      ADD_8_DataTypesPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit types of data' page")
      ADD_8_DataTypesPage.assertEditUrl(userThirdPartyEORI)
      ADD_8_DataTypesPage.assertPageTitle()

      Given("the user clicks to continue.")
      ADD_8_DataTypesPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      MTP_2_EditThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      MTP_2_EditThirdPartyDetailsPage.assertPageTitle()

      // Third Party "Data View Period"

      Given("the user clicks the 'change' link.")
      ADD_9_GiveAccessToDataPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'all data access' decision page")
      ADD_9_GiveAccessToDataPage.assertEditUrl(userThirdPartyEORI)
      ADD_9_GiveAccessToDataPage.assertPageTitle()

      Given("the user ensures 'no' is ticked.")
      ADD_9_GiveAccessToDataPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      ADD_9_GiveAccessToDataPage.continue()

      Then("the user is taken to the 'data view start date' page")
      ADD_10_DataStartPage.assertEditUrl(userThirdPartyEORI)
      ADD_10_DataStartPage.assertPageTitle()

      Given("the user enters a date up to four years ago from the current date")
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("dd", 4),
        ADD_10_DataStartPage.inputCustomDay
      )
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("MM", 4),
        ADD_10_DataStartPage.inputCustomMonth
      )
      ADD_10_DataStartPage.clearAndInputKeys(
        ADD_10_DataStartPage.getDateMinusYears("yyyy", 4),
        ADD_10_DataStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      ADD_10_DataStartPage.continue()

      Then("the user is taken to the 'data view end date' page")
      ADD_11_DataEndPage.assertEditUrl(userThirdPartyEORI)
      ADD_11_DataEndPage.assertPageTitle()

      Given("the user clicks to continue to give unending access.")
      ADD_11_DataEndPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      MTP_2_EditThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      MTP_2_EditThirdPartyDetailsPage.assertPageTitle()
    }

    Scenario(s"The user with trader EORI $userThirdPartyEORI can REMOVE third party $userTraderEori.") {
      Given("the user is on the third party manage page.")
      MTP_1_ManageThirdPartyPage.navigateTo()

      When("the user clicks the 'remove' link.")
      MTP_3_RemoveThirdPartyPage.clickLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'remove third party' page")
      MTP_3_RemoveThirdPartyPage.assertUrl(userThirdPartyEORI)
      MTP_3_RemoveThirdPartyPage.assertPageTitle()

      Given("the user clicks 'no' to removing the third party")
      MTP_3_RemoveThirdPartyPage.selectYesNo(false)

      When("the user clicks to continue")
      MTP_3_RemoveThirdPartyPage.continue()

      Then("the user is taken back to the 'manage third parties' page")
      MTP_1_ManageThirdPartyPage.assertUrl()
      MTP_1_ManageThirdPartyPage.assertPageTitle()

      Given("the user returns to the remove page")
      MTP_3_RemoveThirdPartyPage.navigateTo(MTP_3_RemoveThirdPartyPage.pageFullAddress + userThirdPartyEORI)

      And("the user clicks 'yes' to removing the third party")
      MTP_3_RemoveThirdPartyPage.selectYesNo(true)

      When("the user clicks to continue")
      MTP_3_RemoveThirdPartyPage.continue()

      Then("the user is taken to the 'confirmation' page")
      MTP_4_RemoveConfirmationPage.assertUrl()
      MTP_4_RemoveConfirmationPage.assertPageTitle()

      When("the user clicks to return to the 'manage' page")
      MTP_1_ManageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page which is now empty again.")
      MTP_1_ManageThirdPartyPage.assertUrl()
      MTP_1_ManageThirdPartyPage.assertPageTitle(MTP_1_ManageThirdPartyPage.titleNoThirdPartiesAdded)
    }
  }
}
