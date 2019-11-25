package jbc.oct21.jindanupajit.jobposting.component;

import jbc.oct21.jindanupajit.jobposting.model.UserDetail;

public abstract class Context {
    private static UserDetail AuthenticatedUser = new UserDetail();

    public Context() {
    }

    public static UserDetail getAuthenticatedUser() {
        return AuthenticatedUser;
    }

    public static void setAuthenticatedUser(UserDetail authenticatedUser) {
        AuthenticatedUser = authenticatedUser;
    }
}
