import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchPage {
    private final static String article_href = "https://appleinsider.ru/iphone/chem-iphone-13-otlichaetsya-ot-iphone-12.html";

    private final ElementsCollection articleTitles = $$x("//h2//a");

    /**
     * Возвращает href атрибут из первой статьи
     * @return
     */
    public String getHrefFromFirstArticle(){
        return articleTitles.first().getAttribute("href");

    }
}
