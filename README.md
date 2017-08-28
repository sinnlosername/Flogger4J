Example:

```

        //Global logger
        FLogger.initializeGlobal("Global");
        FLogger glogger = FLogger.global();

        //Add publishers

        /* Write log output into the System.out */
        glogger.addPublisher(new FlogConsolePublisher());

        /* Write log output to a writer */
        glogger.addPublisher(new FlogWriterPublisher(new FileWriter("/some/file")));

        //Sending messages
        glogger.trace("Showing some example messages"); // Trace message
        glogger.info("Started!"); // Info messaage
        glogger.warning("Something doesn't work, like it should do."); // Warning
        glogger.error("Something went wrong"); // Error message

        //Set your LogLevel
        glogger.setLevel(FlogLevel.ERROR); // Only errors & higher will be shown

        //Set your own logging format
        glogger.setFormatter(r -> "[" + r.getTimestamp() + "/"+r.getLevel().name()+"] " +
                "("+r.getFlogger().getName()+"): " + r.getMessage());

        //This would look like: [1503944413949/INFO] (MyLogger): My message!
        glogger.info("My message!");


```