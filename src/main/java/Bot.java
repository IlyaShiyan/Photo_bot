import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "absolutely_photo_bot";
    }

    @Override
    public String getBotToken() {
        return "5765794961:AAHTkcJtJ5s4gtLl4MNH0Sxp721ttIpBWlk";
    }
    // Sea AgACAgIAAxkBAAMZY6yc4zOtCP1kSJP23HGbYGaJjlIAAuPBMRtG1mFJvR3eUhN45FIBAAMCAANtAAMsBA
    // Desert AgACAgIAAxkBAAMbY6ydU2sVhc78fxJnPDfK30pRX9gAAuXBMRtG1mFJhhFAtqyoZ38BAAMCAANtAAMsBA
    // Forest AgACAgIAAxkBAAMdY6ydgCT4skXoWR9m50UbQ7iGFpEAAufBMRtG1mFJzO01dVL0wuABAAMCAANzAAMsBA
    // Mountains AgACAgIAAxkBAAMfY6ydpsdxNorsa-7pynydPQPrfFIAAujBMRtG1mFJT2yqH0c8D-sBAAMCAANzAAMsBA

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Sea"));
        keyboardRow1.add(new KeyboardButton("Mountains"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Forest"));
        keyboardRow2.add(new KeyboardButton("Desert"));

        List<KeyboardRow> list = new ArrayList<>();
        list.add(keyboardRow1);
        list.add(keyboardRow2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setText(update.getMessage().getText());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        InputFile inputFile = new InputFile();

        switch (update.getMessage().getText()) {
            case "Sea":
                inputFile.setMedia("AgACAgIAAxkBAAMZY6yc4zOtCP1kSJP23HGbYGaJjlIAAuPBMRtG1mFJvR3eUhN45FIBAAMCAANtAAMsBA");
                break;
            case "Mountains":
                inputFile.setMedia("AgACAgIAAxkBAAMfY6ydpsdxNorsa-7pynydPQPrfFIAAujBMRtG1mFJT2yqH0c8D-sBAAMCAANzAAMsBA");
                break;
            case "Forest":
                inputFile.setMedia("AgACAgIAAxkBAAMdY6ydgCT4skXoWR9m50UbQ7iGFpEAAufBMRtG1mFJzO01dVL0wuABAAMCAANzAAMsBA");
                break;
            case "Desert":
                inputFile.setMedia("AgACAgIAAxkBAAMbY6ydU2sVhc78fxJnPDfK30pRX9gAAuXBMRtG1mFJhhFAtqyoZ38BAAMCAANtAAMsBA");
                break;
        }

        sendPhoto.setPhoto(inputFile);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
