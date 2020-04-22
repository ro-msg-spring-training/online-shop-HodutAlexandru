package ro.msg.learning.shop.models.enums;

public enum StatusCode {

    OK("200"),
    CREATED("201"),
    NO_CONTENT("204"),
    BAD_REQUEST("400"),
    NOT_FOUND("404"),
    INTERNAL_SERVER_ERROR("500");

    private final String value;

    private StatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
