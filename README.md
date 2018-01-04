Checkout 

`git clone https://github.com/charlesmarvin/async-timeout-demo.git`

Run Client:

`> cd client`

`> gradle bootRun`

Run Server:

`> cd server`

`> gradle bootRun` 

Call Client API:

`curl -d '{"delay": <DELAY_IN_MILLIS>}' -H "Content-Type: application/json" -X POST http://localhost:8000`

`DELAY_IN_MILLIS` should be the latency you want to simulate. 
Requests will time out in `5000 ms` by default. 
If you set a delay greater than `5000` the request will return "Processing" and continue processing in the background.
If you set the delay less than `5000` then the request will return "Done" 
