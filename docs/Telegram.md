# Documentation

## `public class Telegram`

 * **Author:** Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * **Version:** 1.0
 * **Since:** 1.0

## `public String sendMessage(String chatID, String message)`

Sends a message to a specific chat

Sends out a GET request with botID, token, chatID and message as arguments to the telegram API server.

 * **Parameters:**
   * `chatID` — the 9 digit number that identifies telegram users.
   * `message` — the message to be send.
 * **Returns:** the JSON response of the telegram server.

## `public String getJSONMessages()`

Returns all messages recieved in the past.

 * **Returns:** all lines of the servers JSON response.

## `public String getLastJSONMessage()`

Returns the last message recieved.

 * **Returns:** the last linee of the servers JSON response.

## `public ArrayList<Message> getMessages()`

Returns all messages recieved in the past as Telegram.Message objects.

 * **Returns:** an ArrayList of Telegram.Message objects.

## `public Message getLastMessage()`

Returns the last messages recieved as a Telegram.Message object.

 * **Returns:** a Telegram.Message objects.

## `public static class Message`

 * **Author:** Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * **Version:** 1.0
 * **Since:** 1.0
