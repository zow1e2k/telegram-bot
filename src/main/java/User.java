import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class User
{
    private Document document;

    public User(String url) { connect(url); }

    private void connect(String url)
    {
        try
        {
            document = Jsoup.connect(url).get();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getImg()
    {
        Elements elements = document.getElementsByClass("cover-book");
        String url = elements.attr("style");
        url = url.replace("background-image: url('", "");
        url = url.replace("')", "");
        return url;
    }

    public String getName()
    {
        Elements elements = document.getElementsByClass("page_name");
        return elements.text();
    }
    public String getDate()
    {
        Elements elements = document.getElementsByClass("labeled");
        return elements.text();
    }
    public String getFriends()
    {
        Elements elements = document.getElementsByClass("count");
        return elements.text();
    }

    public String getInfoUser()
    {
        String info = "Пользователь vk.com:" +
                "\nИмя Фамилия: " + getName() +
                "\nКраткая информация: " + getDate() +
                "\nПодписчиков | Фото: " + getFriends();
        return info;
    }
}
