package org.json;

/*
Copyright (c) 2008 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.Iterator;

import org.json.JSON.JSONType;


/**
 * This provides static methods to convert an XML text into a JSONArray or
 * JSONObject, and to covert a JSONArray or JSONObject into an XML text using
 * the JsonML transform.
 * @author JSON.org
 * @version 2011-10-05
 */
public class JSONML {

    /**
     * Parse XML values and store them in a JSONArray.
     * @param x       The XMLTokener containing the source string.
     * @param arrayForm true if array form, false if object form.
     * @param ja      The JSONArray that is containing the current tag or null
     *     if we are at the outermost level.
     * @return A JSONArray if the value is the outermost tag, otherwise null.
     * @throws JSONException
     */
    private static Object parse(
        XMLTokener x,
        boolean    arrayForm,
        JSON       ja
    ) throws JSONException {
        String     attribute;
        char       c;
        String     closeTag = null;
        int        i;
        JSON       newja = null;
        JSON       newjo = null;
        Object     token;
        String     tagName = null;

// Test for and skip past these forms:
//      <!-- ... -->
//      <![  ... ]]>
//      <!   ...   >
//      <?   ...  ?>

        while (true) {
            if (!x.more()) {
//                throw x.syntaxError("Bad XML");
            }
            token = x.nextContent();
            if (token == JSONXML.LT) {
                token = x.nextToken();
                if (token instanceof Character) {
                    if (token == JSONXML.SLASH) {

// Close tag </

                        token = x.nextToken();
                        if (!(token instanceof String)) {
                            throw new JSONException(
                                    "Expected a closing name instead of '" +
                                    token + "'.");
                        }
                        if (x.nextToken() != JSONXML.GT) {
//                            throw x.syntaxError("Misshaped close tag");
                        }
                        return token;
                    } else if (token == JSONXML.BANG) {

// <!

                        c = x.next();
                        if (c == '-') {
                            if (x.next() == '-') {
                                x.skipPast("-->");
                            }
                            x.back();
                        } else if (c == '[') {
                            token = x.nextToken();
                            if (token.equals("CDATA") && x.next() == '[') {
                                if (ja != null) {
                                    ja.append/*put*/(x.nextCDATA());
                                }
                            } else {
//                                throw x.syntaxError("Expected 'CDATA['");
                            }
                        } else {
                            i = 1;
                            do {
                                token = x.nextMeta();
                                if (token == null) {
//                                    throw x.syntaxError("Missing '>' after '<!'.");
                                } else if (token == JSONXML.LT) {
                                    i += 1;
                                } else if (token == JSONXML.GT) {
                                    i -= 1;
                                }
                            } while (i > 0);
                        }
                    } else if (token == JSONXML.QUEST) {

// <?

                        x.skipPast("?>");
                    } else {
//                        throw x.syntaxError("Misshaped tag");
                    }

// Open tag <

                } else {
                    if (!(token instanceof String)) {
//                        throw x.syntaxError("Bad tagName '" + token + "'.");
                    }
                    tagName = (String)token;
                    newja = new JSON();
                    newja.type = JSONType.ARRAY;
                    newjo = new JSON();
                    newjo.type = JSONType.OBJECT;
                    if (arrayForm) {
                        newja.append/*put*/(tagName);
                        if (ja != null) {
                            ja.append/*put*/(newja);
                        }
                    } else {
                        newjo.put("tagName", tagName);
                        if (ja != null) {
                            ja.append/*put*/(newjo);
                        }
                    }
                    token = null;
                    for (;;) {
                        if (token == null) {
                            token = x.nextToken();
                        }
                        if (token == null) {
//                            throw x.syntaxError("Misshaped tag");
                        }
                        if (!(token instanceof String)) {
                            break;
                        }

// attribute = value

                        attribute = (String)token;
                        if (!arrayForm && (attribute == "tagName" || attribute == "childNode")) {
//                            throw x.syntaxError("Reserved attribute.");
                        }
                        token = x.nextToken();
                        if (token == JSONXML.EQ) {
                            token = x.nextToken();
                            if (!(token instanceof String)) {
//                                throw x.syntaxError("Missing value");
                            }
                            newjo.accumulate(attribute, JSONXML.stringToValue((String)token));
                            token = null;
                        } else {
                            newjo.accumulate(attribute, "");
                        }
                    }
                    if (arrayForm && newjo.length() > 0) {
                        newja.append/*put*/(newjo);
                    }

// Empty tag <.../>

                    if (token == JSONXML.SLASH) {
                        if (x.nextToken() != JSONXML.GT) {
//                            throw x.syntaxError("Misshaped tag");
                        }
                        if (ja == null) {
                            if (arrayForm) {
                                return newja;
                            } else {
                                return newjo;
                            }
                        }

// Content, between <...> and </...>

                    } else {
                        if (token != JSONXML.GT) {
//                            throw x.syntaxError("Misshaped tag");
                        }
                        closeTag = (String)parse(x, arrayForm, newja);
                        if (closeTag != null) {
                            if (!closeTag.equals(tagName)) {
//                                throw x.syntaxError("Mismatched '" + tagName +
//                                        "' and '" + closeTag + "'");
                            }
                            tagName = null;
                            if (!arrayForm && newja.length() > 0) {
                                newjo.put("childNodes", newja);
                            }
                            if (ja == null) {
                                if (arrayForm) {
                                    return newja;
                                } else {
                                    return newjo;
                                }
                            }
                        }
                    }
                }
            } else {
                if (ja != null) {
                    ja.append/*put*/(token instanceof String ?
                            JSONXML.stringToValue((String)token) : token);
                }
            }
        }
    }


    /**
     * Convert a well-formed (but not necessarily valid) XML string into a
     * JSONArray using the JsonML transform. Each XML tag is represented as
     * a JSONArray in which the first element is the tag name. If the tag has
     * attributes, then the second element will be JSONObject containing the
     * name/value pairs. If the tag contains children, then strings and
     * JSONArrays will represent the child tags.
     * Comments, prologs, DTDs, and <code>&lt;[ [ ]]></code> are ignored.
     * @param string The source string.
     * @return A JSONArray containing the structured data from the XML string.
     * @throws JSONException
     */
    public static JSONArr toJSONArray(String string) throws JSONException {
        return toJSONArray(new XMLTokener(string));
    }


    /**
     * Convert a well-formed (but not necessarily valid) XML string into a
     * JSONArray using the JsonML transform. Each XML tag is represented as
     * a JSONArray in which the first element is the tag name. If the tag has
     * attributes, then the second element will be JSONObject containing the
     * name/value pairs. If the tag contains children, then strings and
     * JSONArrays will represent the child content and tags.
     * Comments, prologs, DTDs, and <code>&lt;[ [ ]]></code> are ignored.
     * @param x An XMLTokener.
     * @return A JSONArray containing the structured data from the XML string.
     * @throws JSONException
     */
    public static JSONArr toJSONArray(XMLTokener x) throws JSONException {
        return (JSONArr)parse(x, true, null);
    }


    /**
     * Convert a well-formed (but not necessarily valid) XML string into a
     * JSONObject using the JsonML transform. Each XML tag is represented as
     * a JSONObject with a "tagName" property. If the tag has attributes, then
     * the attributes will be in the JSONObject as properties. If the tag
     * contains children, the object will have a "childNodes" property which
     * will be an array of strings and JsonML JSONObjects.

     * Comments, prologs, DTDs, and <code>&lt;[ [ ]]></code> are ignored.
     * @param x An XMLTokener of the XML source text.
     * @return A JSONObject containing the structured data from the XML string.
     * @throws JSONException
     */
    public static JSON toJSON(XMLTokener x) throws JSONException {
        return (JSON)parse(x, false, null);
    }


    /**
     * Convert a well-formed (but not necessarily valid) XML string into a
     * JSONObject using the JsonML transform. Each XML tag is represented as
     * a JSONObject with a "tagName" property. If the tag has attributes, then
     * the attributes will be in the JSONObject as properties. If the tag
     * contains children, the object will have a "childNodes" property which
     * will be an array of strings and JsonML JSONObjects.

     * Comments, prologs, DTDs, and <code>&lt;[ [ ]]></code> are ignored.
     * @param string The XML source text.
     * @return A JSONObject containing the structured data from the XML string.
     * @throws JSONException
     */
    public static JSON toJSON(String string) throws JSONException {
        return toJSON(new XMLTokener(string));
    }


    /**
     * Reverse the JSONML transformation, making an XML text from a JSONArray.
     * @param ja A JSONArray.
     * @return An XML string.
     * @throws JSONException
     */
    public static String toString(JSONArr ja) throws JSONException {
        int          i;
        JSONObj   jo;
        String       key;
        Iterator     keys;
        int          length;
        Object       object;
        StringBuffer sb = new StringBuffer();
        String       tagName;
        String       value;

// Emit <tagName

        tagName = ja.getString(0);
        JSONXML.noSpace(tagName);
        tagName = JSONXML.escape(tagName);
        sb.append('<');
        sb.append(tagName);

        object = ja.opt(1);
        if (object instanceof JSONObj) {
            i = 2;
            jo = (JSONObj)object;

// Emit the attributes

            keys = jo.keys();
            while (keys.hasNext()) {
                key = keys.next().toString();
                JSONXML.noSpace(key);
                value = jo.optString(key);
                if (value != null) {
                    sb.append(' ');
                    sb.append(JSONXML.escape(key));
                    sb.append('=');
                    sb.append('"');
                    sb.append(JSONXML.escape(value));
                    sb.append('"');
                }
            }
        } else {
            i = 1;
        }

//Emit content in body

        length = ja.length();
        if (i >= length) {
            sb.append('/');
            sb.append('>');
        } else {
            sb.append('>');
            do {
                object = ja.get(i);
                i += 1;
                if (object != null) {
                    if (object instanceof String) {
                        sb.append(JSONXML.escape(object.toString()));
                    } else if (object instanceof JSONObj) {
                        sb.append(toString((JSONObj)object));
                    } else if (object instanceof JSONArr) {
                        sb.append(toString((JSONArr)object));
                    }
                }
            } while (i < length);
            sb.append('<');
            sb.append('/');
            sb.append(tagName);
            sb.append('>');
        }
        return sb.toString();
    }

    /**
     * Reverse the JSONML transformation, making an XML text from a JSONObject.
     * The JSONObject must contain a "tagName" property. If it has children,
     * then it must have a "childNodes" property containing an array of objects.
     * The other properties are attributes with string values.
     * @param jo A JSONObject.
     * @return An XML string.
     * @throws JSONException
     */
    public static String toString(JSONObj jo) throws JSONException {
        StringBuffer sb = new StringBuffer();
        int          i;
        JSONArr    ja;
        String       key;
        Iterator     keys;
        int          length;
        Object       object;
        String       tagName;
        String       value;

//Emit <tagName

        tagName = jo.optString("tagName");
        if (tagName == null) {
            return JSONXML.escape(jo.toString());
        }
        JSONXML.noSpace(tagName);
        tagName = JSONXML.escape(tagName);
        sb.append('<');
        sb.append(tagName);

//Emit the attributes

        keys = jo.keys();
        while (keys.hasNext()) {
            key = keys.next().toString();
            if (!key.equals("tagName") && !key.equals("childNodes")) {
                JSONXML.noSpace(key);
                value = jo.optString(key);
                if (value != null) {
                    sb.append(' ');
                    sb.append(JSONXML.escape(key));
                    sb.append('=');
                    sb.append('"');
                    sb.append(JSONXML.escape(value));
                    sb.append('"');
                }
            }
        }

//Emit content in body

        ja = jo.optJSONArray("childNodes");
        if (ja == null) {
            sb.append('/');
            sb.append('>');
        } else {
            sb.append('>');
            length = ja.length();
            for (i = 0; i < length; i += 1) {
                object = ja.get(i);
                if (object != null) {
                    if (object instanceof String) {
                        sb.append(JSONXML.escape(object.toString()));
                    } else if (object instanceof JSONObj) {
                        sb.append(toString((JSONObj)object));
                    } else if (object instanceof JSONArr) {
                        sb.append(toString((JSONArr)object));
                    } else {
                        sb.append(object.toString());
                    }
                }
            }
            sb.append('<');
            sb.append('/');
            sb.append(tagName);
            sb.append('>');
        }
        return sb.toString();
    }
}