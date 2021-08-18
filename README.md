Example:

```JAVA
//Global logger
Flogger4J logger = Flogger4J.initializeGlobal("Global"); //You can get it with FLogger.global() later

//Add publishers

/* Write log output into the System.out */
logger.addPublisher(new LogConsolePublisher());

/* Write log output to a writer */
logger.addPublisher(new LogWriterPublisher(new FileWriter("/some/file")));

//Sending messages
logger.trace("Showing some example messages"); // Trace message
logger.info("Started!"); // Info messaage
logger.warning("Something doesn't work, like it should do."); // Warning
logger.error("Something went wrong"); // Error message

//Set your LogLevel
logger.setLevel(FlogLevel.ERROR); // Only errors & higher will be shown

//Set your own logging format
logger.setFormatter(r -> "[" + r.getTimestamp() + "/"+r.getLevel().name()+"] " +
        "("+r.getFlogger().getName()+"): " + r.getMessage());

//This would look like: [1503944413949/INFO] (MyLogger): Your message
logger.info("Your message");
```
