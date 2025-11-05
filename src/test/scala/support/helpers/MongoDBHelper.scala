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

package support.helpers

import support.models.MongoDocument

import java.util.Date
import java.time.ZoneId
import java.time.LocalDateTime

import org.mongodb.scala._
import org.mongodb.scala.bson.Document
import org.mongodb.scala.result._

def MongoInsertRecord(doc: MongoDocument): Boolean = {

  var success: Boolean                      = false

  val mongoClient                           = MongoClient(doc.client)
  val database: MongoDatabase               = mongoClient.getDatabase(doc.database)
  val collection: MongoCollection[Document] = database.getCollection(doc.collection)

  var thirdParty: List[Document] = List()
  if (doc.addThirdParty) {
    thirdParty = List(Document(
      "eori" -> doc.thirdPartyEORI,
      "accessStart" -> Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
      "accessType" -> doc.accessType
    ))
  }

  val observable: Observable[InsertOneResult] = collection.insertOne(
    Document(
      "eori"             -> doc.traderEori,
      "additionalEmails" -> List(doc.additionalEmail),
      "authorisedUsers"  -> thirdParty
    )
  )

  observable.subscribe(new Observer[InsertOneResult] {
    override def onNext(result: InsertOneResult): Unit = {}
    override def onError(e: Throwable): Unit           =
      println("\n\nFAILURE: MONGO [InsertManyResult]: " + e.getMessage + "\n\n")
    override def onComplete(): Unit                    = { 
        println("\n\nSUCCESS: MONGO [InsertOneResult].\n\n") 
        success = true
      }
  })

  Thread.sleep(1000)
  mongoClient.close()

  return success

}


