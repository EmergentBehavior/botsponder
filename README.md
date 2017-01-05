# Botsponder

Botsponder is a very simple demonstration of a service that handles and responds to intents formatted for [api.ai](https://api.ai).

## Running

To run `botsponder` on its default port, simply call:

    $ lein run -m botsponder.server 

Otherwise, you may specify a port with `-p`:

    $ lein run -m botsponder.server -p 5555

## Tests

Botsponder comes with a few example tests. To run these tests, try:

    $ lein test

