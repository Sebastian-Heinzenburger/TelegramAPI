package com.sebastian;

/**
 * @author      Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class Parser {
    private static String JSONMessage;

    /**
     * Parses a JSON String to a Telegram.Message object
     * <p>
     * Calls trim method to further reduce the JSON string
     *
     * @param  JSONMessage   a slice of the String returned by the server, when requesting the past message(s)
     * @return a Message object
     */
    public static Telegram.Message parseJSONMessage(String JSONMessage) {
        Telegram.Message Message = new Telegram.Message();
        Parser.JSONMessage = JSONMessage;

        Message.id = Integer.parseInt(Parser.JSONMessage.substring(Parser.JSONMessage.lastIndexOf("message_id\":") + 12, Parser.JSONMessage.indexOf(",")));
        Message.senderID = Integer.parseInt(trim("\"id\":"));
        Message.sender = trim("\"username\":");
        Message.date = Integer.parseInt(trim("\"date\":"));
        Message.text = Parser.JSONMessage.substring(Parser.JSONMessage.indexOf("\"text\":") + 8, Parser.JSONMessage.indexOf("}")).replace("\"", "");
        return Message;
    }

    /**
     * crops the JSON String and returns the text untill the next comma
     * @param split     the part of the JSON String to which the String is deleted
     * @return text from the split-part to the next comma
     */
    static String trim(String split) {
        JSONMessage = JSONMessage.substring(JSONMessage.indexOf(split));
        return JSONMessage.substring(JSONMessage.indexOf(split) + split.length(), JSONMessage.indexOf(",")).replace("\"", "");
    }
}
