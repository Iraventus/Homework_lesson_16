package tests;

import io.qameta.allure.restassured.AllureRestAssured;

public class Templates {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private static class InitLogFilter{
        private static final Templates logFilter = new Templates();
    }

    private Templates() {}

    public static Templates filters() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured customTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}
