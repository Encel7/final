package by.epam.shop.command.common;

public enum JSPPath {
    INDEX("index.jsp"),
    MAIN_USER_PAGE("usermain.jsp"),
    REGISTRATION("registration.jsp"),
    USER_LIST("userlist.jsp"),
    PROFILE("profile.jsp"),
    ORDER_LIST("orderlist.jsp");


    private String url;

    JSPPath(String s) {
        this.url = s;
    }

    public String getUrl() {
        return url;
    }
}
