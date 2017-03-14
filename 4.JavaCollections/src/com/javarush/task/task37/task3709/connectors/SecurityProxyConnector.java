package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by butkoav on 19.02.2017.
 */
public class SecurityProxyConnector implements Connector {
    SimpleConnector simpleConnector;
    SecurityChecker securityChecker;

    public SecurityProxyConnector(String resourceString) {
        simpleConnector = new SimpleConnector(resourceString);
        securityChecker = new SecurityCheckerImpl();
    }

    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck()) simpleConnector.connect();
    }
}
