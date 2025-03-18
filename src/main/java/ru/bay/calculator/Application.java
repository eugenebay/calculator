package ru.bay.calculator;


import ru.bay.calculator.config.ApplicationConfigImpl;

import java.util.logging.Logger;

public class Application {
    private static final Logger LOG = Logger.getLogger(Application.class.getSimpleName());

    public void run() {
        ApplicationFactory.FACTORY.getObject(ApplicationConfigImpl.class);
        LOG.info("OK");
    }
}
