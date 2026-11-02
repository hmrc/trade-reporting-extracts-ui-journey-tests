# trade-reporting-extracts-ui-journey-tests

## About
These acceptance tests are written using ScalaTest and cover the following services:
- [trade-reporting-extracts-frontend](https://github.com/hmrc/trade-reporting-extracts-frontend)
- [trade-reporting-extracts](https://github.com/hmrc/trade-reporting-extracts)

The acceptance tests can be run both locally and on Jenkins.
## Key Information
When changes have been made to the front-end or back-end services listed above, run the acceptance tests locally against your changes.
1. Stop the front-end and/or back-end services that are affected by your changes, if they are currently running through sm2:
    - `sm2 --stop TRADE_REPORTING_EXTRACTS_FRONTEND`
    - `sm2 --stop TRADE_REPORTING_EXTRACTS`
2. Run the trade-reporting-extracts-frontend and trade-reporting-extracts services locally using:
    - sbt run
3. Verify that both the front-end and back-end services are running successfully.
4. Run tests locally, against the front-end and back-end services.
5. Once the front-end and/or back-end changes have been approved and merged, ensure that the tests Jenkins job completes successfully. See the [Jenkins Builds](#jenkins-builds) section for links to verify the build status.

## How to Run the Tests

### Prerequisites

Run the services for Trade Reporting Extracts:

```bash
 sm2 --start TRE_ALL
```

## Testing Locally
Follow the steps below to run the acceptance tests locally.

### How to Run the Tests
```bash
./run_tests.sh
```

### Optional Arguments for the Test Script
Note that the order of the arguments is not relevant.

By default, the script runs the scenarios using the Chrome browser. To run the script using a different browser, specify the browser as follows:
```bash
./run_tests.sh firefox # chrome, edge or firefox
```

By default, the script runs against the local environment. To run the script against a specific environment, specify the environment as follows:
```bash
./run_tests.sh staging  # local or staging
```

## Post-Merge Testing
>**Important:** When acceptance tests are added or updated, run the relevant tests locally before raising a PR. Once the changes have been reviewed and merged, ensure that the following Jenkins jobs have completed successfully against your front-end and/or back-end changes.
> - Accessibility and ZAP tests are run as part of the Jenkins job. The latest reports can be accessed using the links in the Jenkins Build section below.
## Jenkins Builds
- [trade-reporting-extracts-ui-journey-tests](https://build.tax.service.gov.uk/job/BordersAndTradeLiveServices/job/TRE/job/trade-reporting-extracts-ui-journey-tests/)

## Manual Testing in QA and Staging
Once the above acceptance tests jobs have been completed successfully, manually verify the service in the QA and Staging environments.
> **Note:** The QA environment uses real upstream services, so the acceptance tests are **not** run against QA. QA must be verified manually.
> Before testing in QA, make sure the EORINumber being used has a valid CDS subscription.

| Environment | Service URL                                                                                      | 
|-------------|--------------------------------------------------------------------------------------------------|
| Local       | [Open the service in Local](http://localhost:9949/auth-login-stub/gg-sign-in)                    |
| QA          | [Open the service in QA](https://www.qa.tax.service.gov.uk/auth-login-stub/gg-sign-in)           |
| Staging     | [Open the service in Staging](https://www.staging.tax.service.gov.uk/auth-login-stub/gg-sign-in) |

### Enrolment Required
Enter the required details as shown below and select **Submit** to continue.

| Field            | Value                                                                                                                                                            |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Redirect URL     | Local : `http://localhost:2102/request-customs-declaration-data`<br> QA : `/request-customs-declaration-data` <br> Staging : `/request-customs-declaration-data` |
| Enrolment Key    | `HMRC-CUS-ORG`                                                                                                                                                   |
| Identifier Name  | `EORINumber`                                                                                                                                                     |
| Identifier Value | A GB EORI number, e.g. `GB744638982000 / GB744638982001'                                                                                                         |

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
