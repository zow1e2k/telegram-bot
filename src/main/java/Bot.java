import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot
{
    private long chat_id;
    private String lastMsg = "";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update)
    {
        chat_id = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        sendMsg(getMsg(message));
    }

    public synchronized void sendMsg(String text)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chat_id);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try
        {
            try
            {
                execute(sendMessage);
            }
            catch (TelegramApiRequestException e)
            {
                sendMessage.setText("Вы слишком часто отправляете сообщения");
                execute(sendMessage);
            }
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    public String getMsg(String msg)
    {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if (msg.equals("/start") || msg.equals("Меню") )
        {
            keyboard.clear();
            keyboardFirstRow.clear();

            keyboardFirstRow.add("Пользователи");
            keyboardFirstRow.add("...");
            keyboardSecondRow.add("...");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выбрать..";
        }

        if (msg.equals("Информация") || msg.equals("информация") || msg.equals("Info") || msg.equals("info"))
        {
            keyboard.clear();
            keyboardFirstRow.clear();

            keyboardFirstRow.add("...");
            keyboardFirstRow.add("...");
            keyboardSecondRow.add("...");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выбрать..";
        }

        if (msg.equals("Пользователи"))
        {
            keyboard.clear();
            keyboardFirstRow.clear();

            keyboardFirstRow.add("vk.com");
            keyboardFirstRow.add("...");
            keyboardSecondRow.add("Меню");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выбрать..";
        }

        if (msg.equals("vk.com"))
        {
            lastMsg = msg;
            keyboard.clear();
            keyboardFirstRow.clear();

            keyboardFirstRow.add("Кристина");
            keyboardFirstRow.add("Артём");
            keyboardFirstRow.add("Антон");
            keyboardSecondRow.add("Меню");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выбрать..";
        }

        if (msg.equals("Кристина"))
        {
            keyboard.clear();
            keyboardFirstRow.clear();
            User book1 = new User("https://vk.com/kristina.saratov");
            sendImageFromUrl("https://sun9-47.userapi.com/c850720/v850720014/1c742b/bNXGxZlKcUc.jpg");
            replyKeyboardMarkup.setKeyboard(keyboard);
            return book1.getInfoUser();
        }

        if (msg.equals("Артём"))
        {
            keyboard.clear();
            keyboardFirstRow.clear();
            User book2 = new User("https://vk.com/donchambers");
            sendImageFromUrl("https://vk.com/images/camera_200.png?ava=1");
            replyKeyboardMarkup.setKeyboard(keyboard);
            return book2.getInfoUser();
        }

        if (msg.equals("Антон"))
        {
            keyboard.clear();
            keyboardFirstRow.clear();
            User book3 = new User("https://vk.com/fakeheart");
            sendImageFromUrl("https://sun9-58.userapi.com/c850724/v850724835/1b616f/LNXVrjZwMmw.jpg");
            replyKeyboardMarkup.setKeyboard(keyboard);
            return book3.getInfoUser();
        }
        return "...";
    }

    public void sendImageFromUrl(String url)
    {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chat_id);
        sendPhotoRequest.setPhoto(url);
        try
        {
            sendPhoto(sendPhotoRequest);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() { return "@narcotestantohapriest_bot"; }

    @Override
    public String getBotToken() { return "960648169:AAFX5cl169SPOx85Bin2hEzFx4Gkg-LrlfU"; }

}
