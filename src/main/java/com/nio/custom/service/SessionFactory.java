package com.nio.custom.service;

public abstract class SessionFactory {
    private static SessionService session = new SessionService();
    public static SessionService getSession() {
        return session;
    }
}
