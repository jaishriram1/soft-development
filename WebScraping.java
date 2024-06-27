import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProductScraper {
    public static void main(String[] args) throws Exception {
        try (WebClient client = new WebClient()) {
            HtmlPage page = client.getPage("https://www.asos.com/the-north-face/the-north-face-vault-backpack-28-litres-in-black/prd/10253008");

            // Extract data using Schema.org metadata
            String productName = page.querySelector("[itemprop=name]").getTextContent();
            String price = page.querySelector("[itemprop=price]").getAttribute("content");
            String imageUrl = page.querySelector("[itemprop=image]").getAttribute("src");

            // You can save this data to a CSV file or process it further
            System.out.println("Product Name: " + productName);
            System.out.println("Price: " + price);
            System.out.println("Image URL: " + imageUrl);
        }
    }
}
