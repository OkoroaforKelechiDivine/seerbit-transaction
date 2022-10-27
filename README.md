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
