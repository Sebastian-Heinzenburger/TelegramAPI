# Documentation

## `public class Parser`

 * **Author:** Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * **Version:** 1.0
 * **Since:** 1.0

## `public static Telegram.Message parseJSONMessage(String JSONMessage)`

Parses a JSON String to a Telegram.Message object

Calls trim method to further reduce the JSON string

 * **Parameters:** `JSONMessage` — a slice of the String returned by the server, when requesting the past message(s)
 * **Returns:** a Message object

## `static String trim(String split)`

crops the JSON String and returns the text untill the next comma

 * **Parameters:** `split` — the part of the JSON String to which the String is deleted
 * **Returns:** text from the split-part to the next comma
