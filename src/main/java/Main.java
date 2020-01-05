import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main
{
    public static void main(String[] params)
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        Bot bot = new Bot();
        try
        {
            telegram.registerBot(bot);
        }
        catch (TelegramApiRequestException e)
        {
            e.printStackTrace();
        }
    }
}
