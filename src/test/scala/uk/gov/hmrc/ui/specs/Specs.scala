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
    QA Note:
        Multiple tests use a random EORI generated in "EnrolmentsDataBuilder.scala"
        which is assigned to "enrolmentRandomEORI".
 */

class TradeReportingExtractsTests
    extends Sequential(
      //   Access

      ACC_KO_UnauthorisedSpec(),

      //   Reports

      REQ_RequestReportSpec(aThirdPartyUser),
      REQ_ExportRequestReportSpec(aThirdPartyUser),
      RQR_RequestedReportsSpec(),
      AVR_AvailableReportsSpec(),

      //   Third Party

      ADD_AddThirdPartySpec(),
      MTP_ManageThirdPartySpec(),

      //   Account

      DET_YourDetailsSpec()
    )
