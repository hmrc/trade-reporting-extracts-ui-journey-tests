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

  private val loginPage                 = AuthLoginStubPage
  private val dashboardPage             = ACC_1_DashboardPage
  private val manageThirdPartyPage      = MTP_1_ManageThirdPartyPage
  private val editThirdPartyDetailsPage = MTP_2_EditThirdPartyDetailsPage
  private val removeThirdPartyPage      = MTP_3_RemoveThirdPartyPage
  private val removeConfirmationPage    = MTP_4_RemoveConfirmationPage

  private val accessStartPage      = ADD_6_AccessStartPage
  private val accessEndPage        = ADD_7_AccessEndPage
  private val dataTypesPage        = ADD_8_DataTypesPage
  private val giveAccessToDataPage = ADD_9_GiveAccessToDataPage
  private val dataStartPage        = ADD_10_DataStartPage
  private val dataEndPage          = ADD_11_DataEndPage

  Feature("[F1] The user can edit a third party.") {

    Scenario(s"[F1] SETUP: Prepare MongoDB with $userThirdPartyEORI already added to $userTraderEori.") {
      Given("the mongoDB is prepped then a success should be returned.")
      assert(PrepMongoInsertRecord() == true)
    }

    Scenario("[F1] ACC-1: The user is authenticated.") {
      When(s"the user logs in with EORI $userTraderEori.")
      loginPage.navigateTo()
      loginPage.enterRedirectionUrl()
      loginPage.enterEnrollment(userTraderLogin)
      loginPage.continue()

      Then("the user is taken to the dashboard.")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-1: The user can nvagiate to the manage third parties page.") {
      When("the user clicks the link on the dashboard")
      manageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page")
      manageThirdPartyPage.assertUrl()
      manageThirdPartyPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-2: The user clicks to edit '$userThirdPartyEORI' details.") {
      Given("the user clicks the 'edit' link.")
      editThirdPartyDetailsPage.clickLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit third party details' page")
      editThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      editThirdPartyDetailsPage.assertPageTitle()
    }

    // Third Party "Access Period"

    Scenario(s"[F1] Step-3: The user clicks to change the third-party access period.") {
      Given("the user clicks the 'change' link.")
      accessStartPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit access start' page")
      accessStartPage.assertEditUrl(userThirdPartyEORI)
      accessStartPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-4: The user clicks to continue onto the edit access date end page.") {
      Given("the user clicks to continue.")
      accessStartPage.continue()

      Then("the user is taken to the 'edit access end' page")
      accessEndPage.assertEditUrl(userThirdPartyEORI)
      accessEndPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-5: The user clicks to continue and is returned to '$userThirdPartyEORI' details page.") {
      Given("the user clicks to continue.")
      accessEndPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      editThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      editThirdPartyDetailsPage.assertPageTitle()
    }

    // Third Party "Types of Data"

    Scenario(s"[F1] Step-6: The user clicks to change the types of data they have access to.") {
      Given("the user clicks the 'change' link.")
      dataTypesPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'edit types of data' page")
      dataTypesPage.assertEditUrl(userThirdPartyEORI)
      dataTypesPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-7: The user clicks to continue and is returned to '$userThirdPartyEORI' details page.") {
      Given("the user clicks to continue.")
      dataTypesPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      editThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      editThirdPartyDetailsPage.assertPageTitle()
    }

    // Third Party "Data View Period"

    Scenario(s"[F1] Step-8: The user clicks to change the third-party data view period.") {
      Given("the user clicks the 'change' link.")
      accessStartPage.clickEditLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'all data access' decision page")
      giveAccessToDataPage.assertEditUrl(userThirdPartyEORI)
      giveAccessToDataPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-9: The user selects 'no' and continues onto the edit data view start date page.") {
      Given("the user ensures 'no' is ticked.")
      giveAccessToDataPage.selectOptionByIndex(1)

      And("the user clicks to continue")
      giveAccessToDataPage.continue()

      Then("the user is taken to the 'data view start date' page")
      dataStartPage.assertEditUrl(userThirdPartyEORI)
      dataStartPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-10: The user enters a start date and continues onto the edit data view end date page.") {
      Given("the user enters a date up to four years ago from the current date")
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("dd", 4),
        dataStartPage.inputCustomDay
      )
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("MM", 4),
        dataStartPage.inputCustomMonth
      )
      dataStartPage.clearAndInputKeys(
        dataStartPage.getDateMinusYears("yyyy", 4),
        dataStartPage.inputCustomYear
      )

      And("the user clicks to continue")
      dataStartPage.continue()

      Then("the user is taken to the 'data view end date' page")
      dataEndPage.assertEditUrl(userThirdPartyEORI)
      dataEndPage.assertPageTitle()
    }

    Scenario(s"[F1] Step-11: The user clicks to continue and is returned to '$userThirdPartyEORI' details page.") {
      Given("the user clicks to continue to give unending access.")
      dataEndPage.continue()

      Then("the user is returned to the 'edit third party details' page")
      editThirdPartyDetailsPage.assertUrl(userThirdPartyEORI)
      editThirdPartyDetailsPage.assertPageTitle()
    }
  }

  Feature("[F2] The user can remove a third party.") {

    Scenario(s"[F2] Step-1: The user clicks to remove '$userThirdPartyEORI'.") {
      Given("the user is on the third party manage page.")
      manageThirdPartyPage.navigateTo()

      When("the user clicks the 'remove' link.")
      removeThirdPartyPage.clickLinkToPage(userThirdPartyEORI)

      Then("the user is taken to the 'remove third party' page")
      removeThirdPartyPage.assertUrl(userThirdPartyEORI)
      removeThirdPartyPage.assertPageTitle()
    }

    Scenario(s"[F2] Step-2: The user clicks 'no' to removing '$userThirdPartyEORI'.") {
      Given("the user clicks 'no' to removing the third party")
      removeThirdPartyPage.selectYesNo(false)

      When("the user clicks to continue")
      removeThirdPartyPage.continue()

      Then("the user is taken to the 'dashboard' page")
      dashboardPage.assertUrl()
      dashboardPage.assertPageTitle()
    }

    Scenario(s"[F2] Step-3: The user clicks 'yes' to removing '$userThirdPartyEORI'.") {
      Given("the user returns to the remove page")
      removeThirdPartyPage.navigateTo(removeThirdPartyPage.pageFullAddress + userThirdPartyEORI)

      And("the user clicks 'yes' to removing the third party")
      removeThirdPartyPage.selectYesNo(true)

      When("the user clicks to continue")
      removeThirdPartyPage.continue()

      Then("the user is taken to the 'confirmation' page")
      removeConfirmationPage.assertUrl()
      removeConfirmationPage.assertPageTitle()
    }

    Scenario(s"[F2] Step-4: The user returns to the dashboard.") {
      When("the user clicks to return to the 'manage' page")
      manageThirdPartyPage.clickLinkToPage()

      Then("the user is taken to the 'manage' page which is now empty again.")
      manageThirdPartyPage.assertUrl()
      manageThirdPartyPage.assertPageTitle(manageThirdPartyPage.titleNoThirdPartiesAdded)
    }
  }
}
