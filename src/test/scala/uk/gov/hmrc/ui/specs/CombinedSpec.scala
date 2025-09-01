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
import uk.gov.hmrc.ui.specs_support.REQ_RequestReport

// The navigation path changes for these journeys, dependant on whether the user has single or third party access.

// class SinglePartyRequestReportSpec extends REQ_RequestReport(aSinglePartyUser) {}
class ThirdPartyRequestReportSpec extends RequestReport(aThirdPartyUser) {}
