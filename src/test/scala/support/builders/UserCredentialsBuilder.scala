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

import support.builders.EnrolmentsDataBuilder.anEnrolmentsData
import uk.gov.hmrc.ui.models._

object UserCredentialsBuilder {

  val aUserCredentials: UserCredentials = UserCredentials(
    affinityGroup = Organisation,
    credentialRole = User,
    confidenceLevel = None,
    nino = None,
    enrolmentsData = None
  )

  val anIndividualUserCredentials: UserCredentials = UserCredentials(
    affinityGroup = Individual,
    credentialRole = User,
    confidenceLevel = Some(CL250),
    nino = Some("AA000000A"),
    enrolmentsData = None
  )

  val anOrganisationUserWithKnownEnrolment: UserCredentials =
    aUserCredentials.copy(affinityGroup = Organisation, enrolmentsData = Some(anEnrolmentsData))
}
