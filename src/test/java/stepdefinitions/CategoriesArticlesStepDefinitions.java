package stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;
import pages.CategoriesArticlesElements;
import pages.ArticlePageElements;
import utils.AccessContext;
import utils.DriverManager;
import utils.CsvDataLoader;
import io.cucumber.java.Before;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class CategoriesArticlesStepDefinitions {
    private WebDriver driver;
    private AccessContext accessContext;
    private CategoriesArticlesElements categoriesIndexElements;
    private ArticlePageElements articlePageElements;
    private List<String> categoriesGroup;

    @Before
    public void setup() {
        this.driver = DriverManager.getBrowserDriver();
        this.categoriesIndexElements = new CategoriesArticlesElements(driver);
        this.articlePageElements = new ArticlePageElements(driver);
        this.accessContext = AccessContext.getInstance();
    }

    @Cuando("el usuario se encuentra en la pagina principal")
    public void elUsuarioSeEncuentraEnLaPaginaPrincipal() {
        this.categoriesGroup = categoriesIndexElements.getCategories();
    }

    @Y("el usuario elije la categoriay el articulo parametrizado en {string}")
    public void elUsuarioElijeLaCategoriayElArticuloParametrizadoEn(String archivo) throws IOException {
        CsvDataLoader getCsvData = new CsvDataLoader(archivo);
        String categoryUser = getCsvData.getInputDataFromCsv().get(2);
        String articleUser = getCsvData.getInputDataFromCsv().get(3);

        categoriesIndexElements.clickCategory(categoryUser, this.categoriesGroup);
        categoriesIndexElements.clickArticle(articleUser);

        accessContext.setArticleName(categoriesIndexElements.articleFullName);
        accessContext.setArticlePrice(categoriesIndexElements.articlePrice);
        accessContext.setArticleDescription(categoriesIndexElements.articleDescription);

    }

}
