# Simple Spring API example 2

Providing a second spring api application that can be connected via REST or RabbitMQ.

# Usage

```
# Build package
gradle build

# Start server on port 8081
PORT=8081 gradle runBoot
```

# Database Scripts

```
# Reset the database completely (DROP + CREATE)
db/reset_database.sh
```

# Environment configuration

You can configure the server's behavior with OS environment variables as follows:

```
export RESULTS_PER_PAGE=10
gradle bootRun
```

## Curl & jq

curl is a tool to send http requests from the console.
jq is a tool to display json API outputs in a prettyfied format on the console.
curl can be combined with a piped jq call to display request responses on the console.

## Endpoints

```
# List messages
curl -v 'localhost:8081/v1/messages' |jq '.'

# POST a new message
curl -v -X POST -d '{ "messageHeader": "My message header", "messageBody" : "My Super Message", "recipient": "test@example.com" }' 'localhost:8081/v1/messages' -H 'Content-Type: application/json' |jq '.'

```
