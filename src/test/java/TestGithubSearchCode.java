package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestGithubSearchCode {

    @BeforeAll
    static void testsBeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void testGithubSearchSelenide () {
    open("/selenide/selenide");
        $("#wiki-tab").click();

        //чтобы найти в списке нужный пункт (а он последний), можно ввести его в поле поиска, -->
        // начиная вводить сочетание из букв "So" или любое другое, обязательно уникальное.
        $("#wiki-pages-filter").setValue("So").pressEnter();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();

        //в проверке текста нужно использовать конкатенацию строк с помощью операнда +
        $("#wiki-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                    "@Test\n" +
                    "void test() {\n" +
                    "Configuration.assertionMode = SOFT;\n" +
                    "open(\"page.html\");\n" +
                    "\n" +
                    "$(\"#first\").should(visible).click();\n" + // про экранирование символов можно почитать тут https://javarush.com/groups/posts/1921-ehkranirovanie-simvolov
                    "$(\"#second\").should(visible).click();\n" + //   -||- (то же)
                 "}\n" +
                 "}"
        ));
    }
}

