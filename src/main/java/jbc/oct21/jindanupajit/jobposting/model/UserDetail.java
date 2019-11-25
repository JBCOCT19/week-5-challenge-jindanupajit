package jbc.oct21.jindanupajit.jobposting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class UserDetail {

    @Id
    @SequenceGenerator(name = "UserDetail", sequenceName = "UserDetailId", initialValue = 10001, allocationSize = 1)
    @GeneratedValue(generator = "UserDetail")
    private long id;

    private String username;

    private String password;

    private String displayName;

    public UserDetail() {
    }

    public UserDetail(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
