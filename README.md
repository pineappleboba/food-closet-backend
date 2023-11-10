# Description
This is a simple online order taker and fulfillment for an operational food pantry in Beaverton, Oregon. It replaces a manual system using paper and a lot of running around.
# Definitions
In our limited application, the word "order" has two meanings: it is a request, and it is the fulfillment of that request.
Server: "Can I take your order?"
also Server: "Here is your order."
To mitigate this confusion, the terms used here are
**food order**: a collection of requested food items made by a food pantry client
**food pickup**: the collection of food items put together to be picked up by a food pantry client
# Endpoints:
curl http://localhost:8080/foodpickups
curl http://localhost:8080/foodchoices?numberInFamily=4
curl -XPOST http://localhost:8080/submitOrder --data ''