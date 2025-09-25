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

import support.builders.UserCredentialsBuilder.{aSinglePartyUser, aThirdPartyUser}
import uk.gov.hmrc.ui.specs_support._
import org.scalatest.Sequential

/*
    QA NOTE:
        The screens for these journeys occur in a different order or have different titles,
        depending on whether a Single or Third Party user logs in or what order they're tested in.

            1.  To avoid re-writing the entire spec again to cover both single and third party users,
                we will just call it twice here with appropriate credentials.

            2.  To control the order the specs are run, thus which titles to expect,
                we will list them here in the desired order.
 */

class TradeReportingExtractsTests
    extends Sequential(
      // Dashboard
      // TO-DO: Insert checks for single vs third party user's dashboard.
      ACC_KO_UnauthorisedSpec(),

      // Dashbaord - "Reports"
      RQR_RequestedReportsSpec(false),
      // REQ_RequestReportSpec(aSinglePartyUser),
      REQ_RequestReportSpec(aThirdPartyUser),
      RQR_RequestedReportsSpec(true),
      AVR_AvailableReportsSpec(),
      REQ_ExportRequestReportSpec(aThirdPartyUser),

      // Dashbaord - "Data Access"
      ADD_AddThirdPartySpec(),

      // Dashbaord - "Account"
      DET_YourDetailsSpec()
    )
