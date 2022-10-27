# seerbit-transaction
Requirements
These are the additional requirements for the solution:

- You are free to choose any JVM language to complete the challenge in, but your application has to run in Maven.
- The API has to be thread-safe with concurrent requests.
- The POST /transactions and GET /statistics endpoints MUST execute in constant time and memory ie O(1). Scheduled cleanup is not sufficient
- The solution has to work without a database (this also applies to in-memory databases).
- Unit tests are mandatory.
- mvn clean install and mvn clean integration-test must complete successfully.
- Please ensure that no changes are made to the src/it folder.
- In addition to passing the tests, the solution must be at a quality level that you would be comfortable enough to put in production.


Problem challenge
We would like to have a REST API for our statistics. The main use case for the API is to calculate realtime statistics for the last 30 seconds of transactions. The API needs the following endpoints:

- POST /transactions – called every time a transaction is made.
- GET /statistics – returns the statistic based of the transactions of the last 30 seconds.
- DELETE /transactions – deletes all transactions.


You can complete the challenge offline using an IDE of your choice. To download the application skeleton, please enable Use Git in the editor and follow the instructions on screen. Please make sure you test your solution where possible before submitting.

1 POST /transactions
This endpoint is called to create a new transaction. It MUST execute in constant time and memory (O(1)).

Body:

{
"amount": "12.3343",
"timestamp": "2022-06-17T09:59:51.312Z"
}
