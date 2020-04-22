package ro.msg.learning.shop.models.enums;

public enum StrategyType {

    SINGLE_LOCATION("singleLocation"),
    MOST_ABUNDANT("mostAbundant");

    private final String value;

    private StrategyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
