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

import support.builders.UserCredentialsBuilder._
import uk.gov.hmrc.ui.specs_support._
import org.scalatest.Sequential

/*
    QA NOTE:
        The screens for these journeys occur in a different order or change different titles,
        depending on whether a Single or Third Party user logs in and how the data changes in the DB.

            1.  To avoid re-writing the entire spec again to cover both single and third party users,
                we will just call it twice here with appropriate credentials.

            2.  To control the order the specs are run, thus which titles to expect,
                we will list them here in the desired order with certain conditions.

            3.  Where a random EORI is needed (especially for third party journeys)
                the one generated in "EnrolmentsDataBuilder.scala" for "enrolmentRandomEORI" is used.
 */

class TradeReportingExtractsTests
    extends Sequential(
      // Access
      new ACC_KO_UnauthorisedSpec(),

      // Reports

      new RQR_RequestedReportsSpec(false), // false -- "no reports requested"
      new REQ_RequestReportSpec(aThirdPartyUser),
      new REQ_ExportRequestReportSpec(aThirdPartyUser),
      new RQR_RequestedReportsSpec(true), // true -- "reports have been requested"
      new AVR_AvailableReportsSpec(),

      // Third Party

      new MTP_ManageThirdPartySpec(false), // false, false -- "no third parties added", "no removing"
      new ADD_AddThirdPartySpec(),
      new MTP_ManageThirdPartySpec(true), // true, false -- "third parties are added", "no removing"
      new MTP_ManageThirdPartySpec(
        true,
        true
      ), // true, true -- "third parties are added", "third party to be removed"

      // Account

      new DET_YourDetailsSpec()
    )
