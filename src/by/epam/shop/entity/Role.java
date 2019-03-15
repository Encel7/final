package by.epam.shop.entity;

public enum Role {
    USER,
    ADMINISTRATOR,
    ANONYMOUS;

    public Role getRole(int i) {
        if (i == 0) {
            return Role.USER;
        }
        return Role.ADMINISTRATOR;
    }
}
