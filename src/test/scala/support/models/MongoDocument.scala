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

package support.models

private val cli = "mongodb://localhost:27017/?directConnection=true"
private val db  = "trade-reporting-extracts"

object Collections {
  val reports = "tre-report-request"
  val users   = "tre-user"
}

object AccessTypes {
  val importType = "IMPORTS"
  val exportType = "EXPORTS"
}

case class MongoDocument(
  // Mongo Details
  client: String = cli,
  database: String = db,
  collection: String = Collections.users,

  // Trader Details
  addThirdParty: Boolean = true,
  traderEori: String,

  // Third Party Details
  thirdPartyEORI: String,
  accessType: List[String] = List(AccessTypes.importType, AccessTypes.exportType)
)
