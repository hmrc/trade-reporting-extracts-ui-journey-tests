/*
 * Copyright 2023 HM Revenue & Customs
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

package support

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}

import support.models.MongoDocument
import support.helpers.MongoInsertRecord
import support.builders.EnrolmentsDataBuilder.BuildEnrolment

trait BaseSpec
    extends AnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with BeforeAndAfterEach
    with BeforeAndAfterAll
    with Browser
    with ScreenshotOnFailure {

  // Generate random EORIs.
  val userTraderLogin     = BuildEnrolment()
  val userTraderEori = userTraderLogin.identifierValue
  val userThirdPartyLogin = BuildEnrolment()
  val userThirdPartyEORI = userThirdPartyLogin.identifierValue

  // Populate the MongoDB document and ready for use.
  def PrepMongoInsertRecord: Boolean = MongoInsertRecord(new MongoDocument(
    traderEori = userTraderEori,
    thirdPartyEORI = userThirdPartyEORI
  ))

  // Code to run before each spec starts.
  override def beforeAll(): Unit =
    startBrowser()

  // Code to run after each spec finishes.
  override def afterAll(): Unit =
    quitBrowser()

}
