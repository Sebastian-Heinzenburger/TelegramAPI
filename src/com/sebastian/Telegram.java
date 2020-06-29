package com.sebastian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author      Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class Telegram {
    String token;
    String botID;

    public Telegram(String token, String botID){
        this.token = token;
        this.botID = botID;
    }

    public Telegram(int token, String botID){
        this.token = Integer.toString(token);
        this.botID = botID;
    }

    /**
     * Sends a message to a specific chat
     * <p>
     * Sends out a GET request with botID, token, chatID and message as
     * arguments to the telegram API server.
     *
     * @param  chatID   the 9 digit number that identifies telegram users.
     * @param  message  the message to be send.
     * @return the JSON response of the telegram server.
     */
    public String sendMessage(String chatID, String message) {
        String JSONresponse = "ERROR";
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                "https://api.telegram.org/bot"
                +  this.botID + ":" + this.token
                + "/sendMessage?chat_id="+ chatID
                + "&text=" + message
            ).openConnection();

            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                JSONresponse = in.readLine();
            }

        } catch (IOException e) {
            return null;
        }
        return JSONresponse;
    }

    public String sendMessage(int chatID, String Message) {
        return sendMessage(Integer.toString(chatID), Message);
    }

    /**
     * Returns all messages recieved in the past.
     * @return all lines of the servers JSON response.
     */
    public String getJSONMessages() {
        StringBuilder JSONresponse = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.telegram.org/bot"
                            +  this.botID + ":" + this.token
                            + "/getUpdates"
            ).openConnection();

            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String line = in.readLine();
                while (line != null) {
                    if (line.startsWith("\"")) {
                        JSONresponse.append("\n");
                        JSONresponse.append(line);
                    }
                    line = in.readLine();
                }
            }

        } catch (IOException e) {
            return null;
        }
        return JSONresponse.toString();
    }

    /**
     * Returns the last message recieved.
     * @return the last linee of the servers JSON response.
     */
    public String getLastJSONMessage() {
        String JSONresponse = "ERROR";
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.telegram.org/bot"
                            +  this.botID + ":" + this.token
                            + "/getUpdates"
            ).openConnection();

            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String line = in.readLine();
                while (line != null) {
                    JSONresponse = line;
                    line = in.readLine();
                }
            }

        } catch (IOException e) {
            return null;
        }
        return JSONresponse;
    }

    /**
     * Returns all messages recieved in the past as Telegram.Message objects.
     * @return an ArrayList of Telegram.Message objects.
     */
    public ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (String message : getJSONMessages().trim().split("\n")) {
            messages.add(Parser.parseJSONMessage(message));
        }
        return messages;
    }

    /**
     * Returns the last messages recieved as a Telegram.Message object.
     * @return a Telegram.Message objects.
     */
    public Message getLastMessage() {
        return Parser.parseJSONMessage(getLastJSONMessage());
    }

    /**
     * @author      Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
     * @version     1.0
     * @since       1.0
     */
    public static class Message {
        String sender;
        String text;
        int senderID;
        int date;
        int id;

        @Override
        public String toString() {
            return "Message{" +
                    "id=" + id +
                    ", date=" + date +
                    ", sender='" + sender + '\'' +
                    ", senderID=" + senderID +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
