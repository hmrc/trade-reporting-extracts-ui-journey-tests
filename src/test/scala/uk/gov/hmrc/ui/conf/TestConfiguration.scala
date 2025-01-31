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

package uk.gov.hmrc.test.ui.conf

import com.typesafe.config.{Config, ConfigFactory}

object TestConfiguration {
  val config: Config    = ConfigFactory.load()
  val env: String       = Option(System.getProperty("environment")).getOrElse("local")
  private val envConfig = ConfigFactory.load("application.conf").getConfig(s"environments.$env")

  def url(url: String): String =
    if (env == "local") {
      s"$environmentHost:${servicePort(url)}${serviceRoute(url)}"
    } else {
      s"$environmentHost${serviceRoute(url)}"
    }

  def environmentHost: String = envConfig.getString("services.host")

  def servicePort(serviceName: String): String = envConfig.getString(s"services.$serviceName.port")

  def serviceRoute(serviceName: String): String = envConfig.getString(s"services.$serviceName.productionRoute")
}
