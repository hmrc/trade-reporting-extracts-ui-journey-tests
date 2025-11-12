/*
 * Copyright 2024 HM Revenue & Customs
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

package support.builders

import support.models.EnrolmentsData
import scala.util.Random

object EnrolmentsDataBuilder {

  def randomEORI(startCode: String = "GB99", amount: Int = 10): String = {
    var randEORI =
      startCode // In stubs "GB99" is a business which has not shared data, prompting the 'add reference name' screen in AddThirdParty journey.
    1 to amount foreach { _ => randEORI += Random.between(1, 9).toString() }
    return randEORI
  }

  def BuildEnrolment(eori: String = randomEORI()): EnrolmentsData = EnrolmentsData(
    identifierValue = eori
  )

}
