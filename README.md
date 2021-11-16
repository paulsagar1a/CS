# Log Event Monitoring System
Summary of task
Our custom-build server logs different events to a file named logfile.txt. Every event has 2 entries in the file
- one entry when the event was started and another when the event was finished. The entries in the file
have no specific order (a finish event could occur before a start event for a given id)
Every line in the file is a JSON object containing the following event data:

{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495213}
{"id":"scsmbstgrb", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495210}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495200}
{"id":"scsmbstgrd", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495216}
{"id":"scsmbstgre", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495216}
{"id":"scsmbstgrb", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495218}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495200}
{"id":"scsmbstgrd", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495216}
{"id":"scsmbstgre", "state":"FINISHED", "timestamp":1491377495218}


 id - the unique event identifier
 state - whether the event was started or finished (can have values "STARTED" or "FINISHED"
 timestamp - the timestamp of the event in milliseconds
Application Server logs also have the following additional attributes:
 type - type of log
 host - hostname

The program should:
 Take the path to logfile.txt as an input argument
 Parse the contents of logfile.txt
 Flag any long events that take longer than 4ms
 Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
 The application should create a new table if necessary and store the following values:
 Event id
 Event duration
 Type and Host if applicable
 Alert (true if the event took longer than 4ms, otherwise false)
Additional points will be granted for:
 Proper use of info and debug logging
 Proper use of Object Oriented programming
 Unit test coverage
 Multi-threaded solution
 Program that can handle very large files (gigabytes)
