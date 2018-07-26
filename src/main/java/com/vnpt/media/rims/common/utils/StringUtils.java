package com.vnpt.media.rims.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StringUtils {

    private static Logger logger = LogManager.getLogger(StringUtils.class);

    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = '.';
    /**
     * space reg.
     */
    private static final String SPACE_REG = " ";
    /**
     * multi space reg.
     */
    private static final String MULTI_SPACE_REG = "\\s\\s+";

    public static String convertUnicode2Nosign(String org) {
        //convert to VNese no sign. @haidh 2008
        char arrChar[] = org.toCharArray();
        char result[] = new char[arrChar.length];
        for (int i = 0; i < arrChar.length; i++) {
            switch (arrChar[i]) {
                case '\u00E1':
                case '\u00E0':
                case '\u1EA3':
                case '\u00E3':
                case '\u1EA1':
                case '\u0103':
                case '\u1EAF':
                case '\u1EB1':
                case '\u1EB3':
                case '\u1EB5':
                case '\u1EB7':
                case '\u00E2':
                case '\u1EA5':
                case '\u1EA7':
                case '\u1EA9':
                case '\u1EAB':
                case '\u1EAD':
                case '\u0203':
                case '\u01CE': {
                    result[i] = 'a';
                    break;
                }
                case '\u00E9':
                case '\u00E8':
                case '\u1EBB':
                case '\u1EBD':
                case '\u1EB9':
                case '\u00EA':
                case '\u1EBF':
                case '\u1EC1':
                case '\u1EC3':
                case '\u1EC5':
                case '\u1EC7':
                case '\u0207': {
                    result[i] = 'e';
                    break;
                }
                case '\u00ED':
                case '\u00EC':
                case '\u1EC9':
                case '\u0129':
                case '\u1ECB': {
                    result[i] = 'i';
                    break;
                }
                case '\u00F3':
                case '\u00F2':
                case '\u1ECF':
                case '\u00F5':
                case '\u1ECD':
                case '\u00F4':
                case '\u1ED1':
                case '\u1ED3':
                case '\u1ED5':
                case '\u1ED7':
                case '\u1ED9':
                case '\u01A1':
                case '\u1EDB':
                case '\u1EDD':
                case '\u1EDF':
                case '\u1EE1':
                case '\u1EE3':
                case '\u020F': {
                    result[i] = 'o';
                    break;
                }
                case '\u00FA':
                case '\u00F9':
                case '\u1EE7':
                case '\u0169':
                case '\u1EE5':
                case '\u01B0':
                case '\u1EE9':
                case '\u1EEB':
                case '\u1EED':
                case '\u1EEF':
                case '\u1EF1': {
                    result[i] = 'u';
                    break;
                }
                case '\u00FD':
                case '\u1EF3':
                case '\u1EF7':
                case '\u1EF9':
                case '\u1EF5': {
                    result[i] = 'y';
                    break;
                }
                case '\u0111': {
                    result[i] = 'd';
                    break;
                }
                case '\u00C1':
                case '\u00C0':
                case '\u1EA2':
                case '\u00C3':
                case '\u1EA0':
                case '\u0102':
                case '\u1EAE':
                case '\u1EB0':
                case '\u1EB2':
                case '\u1EB4':
                case '\u1EB6':
                case '\u00C2':
                case '\u1EA4':
                case '\u1EA6':
                case '\u1EA8':
                case '\u1EAA':
                case '\u1EAC':
                case '\u0202':
                case '\u01CD': {
                    result[i] = 'A';
                    break;
                }
                case '\u00C9':
                case '\u00C8':
                case '\u1EBA':
                case '\u1EBC':
                case '\u1EB8':
                case '\u00CA':
                case '\u1EBE':
                case '\u1EC0':
                case '\u1EC2':
                case '\u1EC4':
                case '\u1EC6':
                case '\u0206': {
                    result[i] = 'E';
                    break;
                }
                case '\u00CD':
                case '\u00CC':
                case '\u1EC8':
                case '\u0128':
                case '\u1ECA': {
                    result[i] = 'I';
                    break;
                }
                case '\u00D3':
                case '\u00D2':
                case '\u1ECE':
                case '\u00D5':
                case '\u1ECC':
                case '\u00D4':
                case '\u1ED0':
                case '\u1ED2':
                case '\u1ED4':
                case '\u1ED6':
                case '\u1ED8':
                case '\u01A0':
                case '\u1EDA':
                case '\u1EDC':
                case '\u1EDE':
                case '\u1EE0':
                case '\u1EE2':
                case '\u020E': {
                    result[i] = 'O';
                    break;
                }
                case '\u00DA':
                case '\u00D9':
                case '\u1EE6':
                case '\u0168':
                case '\u1EE4':
                case '\u01AF':
                case '\u1EE8':
                case '\u1EEA':
                case '\u1EEC':
                case '\u1EEE':
                case '\u1EF0': {
                    result[i] = 'U';
                    break;
                }

                case '\u00DD':
                case '\u1EF2':
                case '\u1EF6':
                case '\u1EF8':
                case '\u1EF4': {
                    result[i] = 'Y';
                    break;
                }
                case '\u0110':
                case '\u00D0':
                case '\u0089': {
                    result[i] = 'D';
                    break;
                }
                default:
                    result[i] = arrChar[i];
            }
        }
        return new String(result);
    }

    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }

    public static String asString(String input) {
        if (input == null || "".equals(input)) {
            return null;
        }
        StringBuffer sb = new StringBuffer(input.trim());
        int n = 0;
        sb.append(";");

        while (n <= sb.length() - 1) {

            if (sb.charAt(n) == ' ' && sb.charAt(n + 1) == ' ') {
                sb.deleteCharAt(n);
                n--;
            }
            n++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String splitString(String input) {
        if (input == null || "".equals(input)) {
            return null;
        }
        StringBuffer sb = new StringBuffer(input.trim());
        int n = 0;
        sb.append(";");

        while (n <= sb.length() - 1) {

            if (sb.charAt(n) == ' ') {
                sb.deleteCharAt(n);
                n--;
            }
            n++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String subcriber(String subcriber) {
        StringBuffer st = new StringBuffer(subcriber);
        if (st.charAt(0) == '0') {
            st.deleteCharAt(0);
        } else if (st.charAt(0) == '8') {
            if (st.charAt(1) == '4') {
                st.delete(0, 2);
            }
        }
        return st.toString();
    }

    /**
     * Check that the given CharSequence is neither <code>null</code> nor of
     * length 0. Note: Will return <code>true</code> for a CharSequence that
     * purely consists of whitespace.
     * <p>
     * <
     * pre>
     * StringUtils.hasLength(null) = false StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given String is neither <code>null</code> nor of length 0.
     * Note: Will return <code>true</code> for a String that purely consists of
     * whitespace.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * Check whether the given CharSequence has actual text. More specifically,
     * returns <code>true</code> if the string not <code>null</code>, its length
     * is greater than 0, and it contains at least one non-whitespace character.
     * <p>
     * <
     * pre>
     * StringUtils.hasText(null) = false StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not <code>null</code>,
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String has actual text. More specifically,
     * returns <code>true</code> if the string not <code>null</code>, its length
     * is greater than 0, and it contains at least one non-whitespace character.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its
     * length is greater than 0, and it does not contain whitespace only
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        if (str != null && str.equalsIgnoreCase("null")) {
            return false;
        }
        return hasText((CharSequence) str);
    }

    /**
     * Check whether the given CharSequence contains any whitespace characters.
     *
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not empty and contains
     * at least 1 whitespace character
     * @see Character#isWhitespace
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String contains any whitespace characters.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not empty and contains at
     * least 1 whitespace character
     * @see #containsWhitespace(CharSequence)
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * Trim leading and trailing whitespace from the given String.
     *
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Trim <i>all</i> whitespace from the given String: leading, trailing, and
     * inbetween characters.
     *
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        int index = 0;
        while (buf.length() > index) {
            if (Character.isWhitespace(buf.charAt(index))) {
                buf.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return buf.toString();
    }

    /**
     * Trim leading whitespace from the given String.
     *
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim trailing whitespace from the given String.
     *
     * @param str the String to check
     * @return the trimmed String
     * @see Character#isWhitespace
     */
    public static String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Trim all occurences of the supplied leading character from the given
     * String.
     *
     * @param str the String to check
     * @param leadingCharacter the leading character to be trimmed
     * @return the trimmed String
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim all occurences of the supplied trailing character from the given
     * String.
     *
     * @param str the String to check
     * @param trailingCharacter the trailing character to be trimmed
     * @return the trimmed String
     */
    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(buf.length() - 1) == trailingCharacter) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Test if the given String starts with the specified prefix, ignoring
     * upper/lower case.
     *
     * @param str the String to check
     * @param prefix the prefix to look for
     * @see String#startsWith
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equals(lcPrefix);
    }

    /**
     * Test if the given String ends with the specified suffix, ignoring
     * upper/lower case.
     *
     * @param str the String to check
     * @param suffix the suffix to look for
     * @see String#endsWith
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }

        String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
        String lcSuffix = suffix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     * Test whether the given string matches the given substring at the given
     * index.
     *
     * @param str the original string (or StringBuffer)
     * @param index the index in the original string to start matching against
     * @param substring the substring to match at the given index
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        for (int j = 0; j < substring.length(); j++) {
            int i = index + j;
            if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count the occurrences of the substring in string s.
     *
     * @param str string to search in. Return 0 if this is null.
     * @param sub string to search for. Return 0 if this is null.
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0, pos = 0, idx = 0;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    /**
     * Replace all occurences of a substring within a string with another
     * string.
     *
     * @param inString String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuffer sbuf = new StringBuffer();
        // output StringBuffer we'll build up
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sbuf.toString();
    }

    /**
     * Delete all occurrences of the given substring.
     *
     * @param inString the original String
     * @param pattern the pattern to delete all occurrences of
     * @return the resulting String
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     * Delete any character in a given String.
     *
     * @param inString the original String
     * @param charsToDelete a set of characters to delete. E.g. "az\n" will
     * delete 'a's, 'z's and new lines.
     * @return the resulting String
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    //---------------------------------------------------------------------
    // Convenience methods for working with formatted Strings
    //---------------------------------------------------------------------
    /**
     * Quote the given String with single quotes.
     *
     * @param str the input String (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or <code>null<code> if the input was
     * <code>null</code>
     */
    public static String quote(String str) {
        return (str != null ? "'" + str + "'" : null);
    }

    /**
     * Turn the given Object into a String with single quotes if it is a String;
     * keeping the Object as-is else.
     *
     * @param obj the input Object (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or the input object as-is
     * if not a String
     */
    public static Object quoteIfString(Object obj) {
        return (obj instanceof String ? quote((String) obj) : obj);
    }

    /**
     * Unqualify a string qualified by a '.' dot character. For example,
     * "this.name.is.qualified", returns "qualified".
     *
     * @param qualifiedName the qualified name
     */
    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, '.');
    }

    /**
     * Unqualify a string qualified by a separator character. For example,
     * "this:name:is:qualified" returns "qualified" if using a ':' separator.
     *
     * @param qualifiedName the qualified name
     * @param separator the separator
     */
    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    /**
     * Capitalize a <code>String</code>, changing the first letter to upper case
     * as per {@link Character#toUpperCase(char)}. No other letters are changed.
     *
     * @param str the String to capitalize, may be <code>null</code>
     * @return the capitalized String, <code>null</code> if null
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a <code>String</code>, changing the first letter to lower
     * case as per {@link Character#toLowerCase(char)}. No other letters are
     * changed.
     *
     * @param str the String to uncapitalize, may be <code>null</code>
     * @return the uncapitalized String, <code>null</code> if null
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
     * "myfile.txt".
     *
     * @param path the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }

    /**
     * Extract the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "txt".
     *
     * @param path the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     * Strip the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "mypath/myfile".
     *
     * @param path the file path (may be <code>null</code>)
     * @return the path with stripped filename extension, or <code>null</code>
     * if none
     */
    public static String stripFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
    }

    /**
     * Apply the given relative path to the given path, assuming standard Java
     * folder separation (i.e. "/" separators);
     *
     * @param path the path to start from (usually a full file path)
     * @param relativePath the relative path to apply (relative to the full file
     * path above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath += FOLDER_SEPARATOR;
            }
            return newPath + relativePath;
        } else {
            return relativePath;
        }
    }

    /**
     * Normalize the path by suppressing sequences like "path/.." and inner
     * simple dots.
     * <p>
     * The result is convenient for path comparison. For other uses, notice that
     * Windows separators ("\") are replaced by simple slashes.
     *
     * @param path the original path
     * @return the normalized path
     */
    public static String cleanPath(String path) {
        if (path == null) {
            return null;
        }
        String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

        // Strip prefix from path to analyze, to not treat it as part of the
        // first path element. This is necessary to correctly parse paths like
        // "file:core/../core/io/Resource.class", where the ".." should just
        // strip the first "core" directory while keeping the "file:" prefix.
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            pathToUse = pathToUse.substring(prefixIndex + 1);
        }
        if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
            prefix = prefix + FOLDER_SEPARATOR;
            pathToUse = pathToUse.substring(1);
        }

        String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
        List pathElements = new LinkedList();
        int tops = 0;

        for (int i = pathArray.length - 1; i >= 0; i--) {
            String element = pathArray[i];
            if (CURRENT_PATH.equals(element)) {
                // Points to current directory - drop it.
            } else if (TOP_PATH.equals(element)) {
                // Registering top path found.
                tops++;
            } else if (tops > 0) {
                // Merging path element with element corresponding to top path.
                tops--;
            } else {
                // Normal path element found.
                pathElements.add(0, element);
            }
        }

        // Remaining top paths need to be retained.
        for (int i = 0; i < tops; i++) {
            pathElements.add(0, TOP_PATH);
        }

        return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. E.g. useful for <code>toString()</code> implementations.
     *
     * @param coll the Collection to display
     * @param delim the delimiter to use (probably a ",")
     * @return the delimited String
     */
    public static String collectionToDelimitedString(Collection coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    /**
     * Convenience method to return a Collection as a CSV String. E.g. useful
     * for <code>toString()</code> implementations.
     *
     * @param coll the Collection to display
     * @return the delimited String
     */
    public static String collectionToCommaDelimitedString(Collection coll) {
        return collectionToDelimitedString(coll, ",");
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. E.g. useful for <code>toString()</code> implementations.
     *
     * @param coll the Collection to display
     * @param delim the delimiter to use (probably a ",")
     * @param prefix the String to start each element with
     * @param suffix the String to end each element with
     * @return the delimited String
     */
    public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
        if (isEmpty(coll)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * Return <code>true</code> if the supplied Collection is <code>null</code>
     * or empty. Otherwise, return <code>false</code>.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Return <code>true</code> if the supplied Map is <code>null</code> or
     * empty. Otherwise, return <code>false</code>.
     *
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Compare two paths after normalization of them.
     *
     * @param path1 first path for comparison
     * @param path2 second path for comparison
     * @return whether the two paths are equivalent after normalization
     */
    public static boolean pathEquals(String path1, String path2) {
        return cleanPath(path1).equals(cleanPath(path2));
    }

    /**
     * Parse the given <code>localeString</code> into a
     * {@link java.util.Locale}.
     * <p>
     * This is the inverse operation of
     * {@link java.util.Locale#toString Locale's toString}.
     *
     * @param localeString the locale string, following <code>Locale's</code>
     * <code>toString()</code> format ("en", "en_UK", etc); also accepts spaces
     * as separators, as an alternative to underscores
     * @return a corresponding <code>Locale</code> instance
     */
    public static Locale parseLocaleString(String localeString) {
        String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
        String language = (parts.length > 0 ? parts[0] : "");
        String country = (parts.length > 1 ? parts[1] : "");
        String variant = "";
        if (parts.length >= 2) {
            // There is definitely a variant, and it is everything after the country
            // code sans the separator between the country code and the variant.
            int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
            // Strip off any leading '_' and whitespace, what's left is the variant.
            variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
            if (variant.startsWith("_")) {
                variant = trimLeadingCharacter(variant, '_');
            }
        }
        return (language.length() > 0 ? new Locale(language, country, variant) : null);
    }

    /**
     * Determine the RFC 3066 compliant language tag, as used for the HTTP
     * "Accept-Language" header.
     *
     * @param locale the Locale to transform to a language tag
     * @return the RFC 3066 compliant language tag as String
     */
    public static String toLanguageTag(Locale locale) {
        return locale.getLanguage() + (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
    }

    //---------------------------------------------------------------------
    // Convenience methods for working with String arrays
    //---------------------------------------------------------------------
    /**
     * Append the given String to the given String array, returning a new array
     * consisting of the input array contents plus the given String.
     *
     * @param array the array to append to (can be <code>null</code>)
     * @param str the String to append
     * @return the new array (never <code>null</code>)
     */
    public static String[] addStringToArray(String[] array, String str) {
        if (isEmpty(array)) {
            return new String[]{str};
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    /**
     * Concatenate the given String arrays into one, with overlapping array
     * elements included twice.
     * <p>
     * The order of elements in the original arrays is preserved.
     *
     * @param array1 the first array (can be <code>null</code>)
     * @param array2 the second array (can be <code>null</code>)
     * @return the new array (<code>null</code> if both given arrays were
     * <code>null</code>)
     */
    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        if (isEmpty(array1)) {
            return array2;
        }
        if (isEmpty(array2)) {
            return array1;
        }
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    /**
     * Merge the given String arrays into one, with overlapping array elements
     * only included once.
     * <p>
     * The order of elements in the original arrays is preserved (with the
     * exception of overlapping elements, which are only included on their first
     * occurence).
     *
     * @param array1 the first array (can be <code>null</code>)
     * @param array2 the second array (can be <code>null</code>)
     * @return the new array (<code>null</code> if both given arrays were
     * <code>null</code>)
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (isEmpty(array1)) {
            return array2;
        }
        if (isEmpty(array2)) {
            return array1;
        }
        List result = new ArrayList();
        result.addAll(Arrays.asList(array1));
        for (int i = 0; i < array2.length; i++) {
            String str = array2[i];
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return toStringArray(result);
    }

    /**
     * Turn given source String array into sorted array.
     *
     * @param array the source array
     * @return the sorted array (never <code>null</code>)
     */
    public static String[] sortStringArray(String[] array) {
        if (isEmpty(array)) {
            return new String[0];
        }
        Arrays.sort(array);
        return array;
    }

    /**
     * Copy the given Collection into a String array. The Collection must
     * contain String elements only.
     *
     * @param collection the Collection to copy
     * @return the String array (<code>null</code> if the passed-in Collection
     * was <code>null</code>)
     */
    public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * Copy the given Enumeration into a String array. The Enumeration must
     * contain String elements only.
     *
     * @param enumeration the Enumeration to copy
     * @return the String array (<code>null</code> if the passed-in Enumeration
     * was <code>null</code>)
     */
    public static String[] toStringArray(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        }
        List list = Collections.list(enumeration);
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * Trim the elements of the given String array, calling
     * <code>String.trim()</code> on each of them.
     *
     * @param array the original String array
     * @return the resulting array (of the same size) with trimmed elements
     */
    public static String[] trimArrayElements(String[] array) {
        if (isEmpty(array)) {
            return new String[0];
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            result[i] = (element != null ? element.trim() : null);
        }
        return result;
    }

    /**
     * Remove duplicate Strings from the given array. Also sorts the array, as
     * it uses a TreeSet.
     *
     * @param array the String array
     * @return an array without duplicates, in natural sort order
     */
    public static String[] removeDuplicateStrings(String[] array) {
        if (isEmpty(array)) {
            return array;
        }
        Set set = new TreeSet();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        return toStringArray(set);
    }

    /**
     * Split a String at the first occurrence of the delimiter. Does not include
     * the delimiter in the result.
     *
     * @param toSplit the string to split
     * @param delimiter to split the string up with
     * @return a two element array with index 0 being before the delimiter, and
     * index 1 being after the delimiter (neither element includes the
     * delimiter); or <code>null</code> if the delimiter wasn't found in the
     * given input String
     */
    public static String[] split(String toSplit, String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }
        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[]{beforeDelimiter, afterDelimiter};
    }

    /**
     * Take an array Strings and split each element based on the given
     * delimiter. A <code>Properties</code> instance is then generated, with the
     * left of the delimiter providing the key, and the right of the delimiter
     * providing the value.
     * <p>
     * Will trim both the key and value before adding them to the
     * <code>Properties</code> instance.
     *
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals
     * symbol)
     * @return a <code>Properties</code> instance representing the array
     * contents, or <code>null</code> if the array to process was null or empty
     */
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return splitArrayElementsIntoProperties(array, delimiter, null);
    }

    /**
     * Take an array Strings and split each element based on the given
     * delimiter. A <code>Properties</code> instance is then generated, with the
     * left of the delimiter providing the key, and the right of the delimiter
     * providing the value.
     * <p>
     * Will trim both the key and value before adding them to the
     * <code>Properties</code> instance.
     *
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals
     * symbol)
     * @param charsToDelete one or more characters to remove from each element
     * prior to attempting the split operation (typically the quotation mark
     * symbol), or <code>null</code> if no removal should occur
     * @return a <code>Properties</code> instance representing the array
     * contents, or <code>null</code> if the array to process was
     * <code>null</code> or empty
     */
    public static Properties splitArrayElementsIntoProperties(
            String[] array, String delimiter, String charsToDelete) {

        if (isEmpty(array)) {
            return null;
        }
        Properties result = new Properties();
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            if (charsToDelete != null) {
                element = deleteAny(array[i], charsToDelete);
            }
            String[] splittedElement = split(element, delimiter);
            if (splittedElement == null) {
                continue;
            }
            result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
        }
        return result;
    }

    /**
     * Return whether the given array is empty: that is, <code>null</code> or of
     * zero length.
     *
     * @param array the array to check
     * @return whether the given array is empty
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * Trims tokens and omits empty tokens.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     *
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of
     * those characters is individually considered as delimiter).
     * @return an array of the tokens
     * @see java.util.StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     *
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of
     * those characters is individually considered as delimiter)
     * @param trimTokens trim the tokens via String's <code>trim</code>
     * @param ignoreEmptyTokens omit empty tokens from the result array (only
     * applies to tokens that are empty after trimming; StringTokenizer will not
     * consider subsequent delimiters as token in the first place).
     * @return an array of the tokens (<code>null</code> if the input String was
     * <code>null</code>)
     * @see java.util.StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(
            String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     *
     * @param str the input String
     * @param delimiter the delimiter between elements (this is a single
     * delimiter, rather than a bunch individual delimiter characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     *
     * @param str the input String
     * @param delimiter the delimiter between elements (this is a single
     * delimiter, rather than a bunch individual delimiter characters)
     * @param charsToDelete a set of characters to delete. Useful for deleting
     * unwanted line breaks: e.g. "\r\n\f" will delete all new lines and line
     * feeds in a String.
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[]{str};
        }
        List result = new ArrayList();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int pos = 0;
            int delPos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }

    /**
     * Convert a CSV list into an array of Strings.
     *
     * @param str the input String
     * @return an array of Strings, or the empty array in case of empty input
     */
    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    /**
     * Convenience method to convert a CSV string list to a set. Note that this
     * will suppress duplicates.
     *
     * @param str the input String
     * @return a Set of String entries in the list
     */
    public static Set commaDelimitedListToSet(String str) {
        Set set = new TreeSet();
        String[] tokens = commaDelimitedListToStringArray(str);
        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i]);
        }
        return set;
    }

    public static String captureStackTrace(Throwable e) {
        if (e == null) {
            throw new IllegalArgumentException("Null exception not allowed.");
        }
        ByteArrayOutputStream byteStream = null;
        PrintWriter printWriter = null;
        String stackTrace = null;

        byteStream = new ByteArrayOutputStream();
        printWriter = new PrintWriter(byteStream, true);

        e.printStackTrace(printWriter);
        printWriter.flush();
        stackTrace = byteStream.toString();
        printWriter.close();

        return stackTrace;
    }

    public static String printNumberFormat(String number) {
        try {

            if (number != null && number.trim().length() > 0) {
                if (number.trim().length() >= 16) {
                    return printFormatCurrency(number);
                } else {
                    NumberFormat nf = NumberFormat.getInstance(Locale.US);
                    return nf.format(Long.valueOf(number));
                }
            } else {
                return number;
            }
        } catch (Exception e) {
            logger.error("Exception : ", e);
            return number;
        }
    }

    public static String printFormatCurrency(String value) {
        try {
            int index = value.indexOf("-");
            if (index > 0) {
                value = value.substring(index + 1, value.length());
            }
            int len = value.length(), begin = 0, end = 0;
            String temp = "";
            for (int i = 0; i < len;) {
                end = len - i;
                if (end == 1) {
                    begin = end - 1;
                } else if (end == 2) {
                    begin = end - 2;
                } else {
                    begin = end - 3;
                }
                String string = value.substring(begin, end);
                if (begin == 0) {
                    temp = string + temp;
                } else {
                    temp = "," + string + temp;
                }
                i = i + 3;
            }
            if (index > 0) {
                temp = "-" + temp;
            }
            return temp;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    public static String insertCommas(String str) {
        try {
            if (str.length() < 4) {
                return str;
            }
            return insertCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
        } catch (Exception ex) {
            return str;
        }
    }

    public static String formatDate(String date) {
        String time = date.substring(0, 8);
        String year = time.substring(0, 4), month = time.substring(4, 6), day = time.substring(6, 8);
        return day + "/" + month + "/" + year;
    }

    //------------------------------------------------------------------------
    /**
     * Strip jsessionid off of a URL
     */
    public static String stripJsessionId(String url) {
        // Strip off jsessionid found in referer URL
        int startPos = url.indexOf(";jsessionid=");
        if (startPos != -1) {
            int endPos = url.indexOf("?", startPos);
            if (endPos == -1) {
                url = url.substring(0, startPos);
            } else {
                url = url.substring(0, startPos)
                        + url.substring(endPos, url.length());
            }
        }
        return url;
    }

    //------------------------------------------------------------------------
    /**
     * Escape, but do not replace HTML. The default behaviour is to escape
     * ampersands.
     */
    public static String escapeHTML(String s) {
        return escapeHTML(s, true);
    }

    //------------------------------------------------------------------------
    /**
     * Escape, but do not replace HTML.
     *
     * @param escapeAmpersand Optionally escape ampersands (&amp;).
     */
    public static String escapeHTML(String s, boolean escapeAmpersand) {
        // got to do amp's first so we don't double escape
        if (escapeAmpersand) {
            s = StringUtils.replace(s, "&", "&amp;");
        }
        s = StringUtils.replace(s, "&nbsp;", " ");
        s = StringUtils.replace(s, "\"", "&quot;");
        s = StringUtils.replace(s, "<", "&lt;");
        s = StringUtils.replace(s, ">", "&gt;");
        return s;
    }

    public static String unescapeHTML(String str) {
        return StringEscapeUtils.unescapeHtml4(str);
    }

    //------------------------------------------------------------------------
    /**
     * Remove occurences of html, defined as any text between the characters
     * "&lt;" and "&gt;". Replace any HTML tags with a space.
     */
    public static String removeHTML(String str) {
        return removeHTML(str, true);
    }

    /**
     * Remove occurences of html, defined as any text between the characters
     * "&lt;" and "&gt;". Optionally replace HTML tags with a space.
     *
     * @param str
     * @param addSpace
     * @return
     */
    public static String removeHTML(String str, boolean addSpace) {
        if (str == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer(str.length());
        int start = 0;
        int beginTag = str.indexOf("<");
        int endTag = 0;
        if (beginTag == -1) {
            return str;
        }

        while (beginTag >= start) {
            if (beginTag > 0) {
                ret.append(str.substring(start, beginTag));

                // replace each tag with a space (looks better)
                if (addSpace) {
                    ret.append(" ");
                }
            }
            endTag = str.indexOf(">", beginTag);

            // if endTag found move "cursor" forward
            if (endTag > -1) {
                start = endTag + 1;
                beginTag = str.indexOf("<", start);
            } // if no endTag found, get rest of str and break
            else {
                ret.append(str.substring(beginTag));
                break;
            }
        }
        // append everything after the last endTag
        if (endTag > -1 && endTag + 1 < str.length()) {
            ret.append(str.substring(endTag + 1));
        }
        return ret.toString().trim();
    }

    //------------------------------------------------------------------------
    /**
     * Run both removeHTML and escapeHTML on a string.
     *
     * @param s String to be run through removeHTML and escapeHTML.
     * @return String with HTML removed and HTML special characters escaped.
     */
    public static String removeAndEscapeHTML(String s) {
        if (s == null) {
            return "";
        } else {
            return StringUtils.escapeHTML(StringUtils.removeHTML(s));
        }
    }

    //------------------------------------------------------------------------
    /**
     * Autoformat.
     */
    public static String autoformat(String s) {
        String ret = StringUtils.replace(s, "\n", "<br />");
        return ret;
    }

    //------------------------------------------------------------------------
    /**
     * Replaces occurences of non-alphanumeric characters with an underscore.
     */
    public static String replaceNonAlphanumeric(String str) {
        return replaceNonAlphanumeric(str, '_');
    }

    //------------------------------------------------------------------------
    /**
     * Replaces occurences of non-alphanumeric characters with a supplied char.
     */
    public static String replaceNonAlphanumeric(String str, char subst) {
        StringBuffer ret = new StringBuffer(str.length());
        char[] testChars = str.toCharArray();
        for (int i = 0; i < testChars.length; i++) {
            if (Character.isLetterOrDigit(testChars[i])) {
                ret.append(testChars[i]);
            } else {
                ret.append(subst);
            }
        }
        return ret.toString();
    }

    //------------------------------------------------------------------------
    /**
     * Remove occurences of non-alphanumeric characters.
     */
    public static String removeNonAlphanumeric(String str) {
        StringBuffer ret = new StringBuffer(str.length());
        char[] testChars = str.toCharArray();
        for (int i = 0; i < testChars.length; i++) {
            // MR: Allow periods in page links
            if (Character.isLetterOrDigit(testChars[i])
                    || testChars[i] == '.') {
                ret.append(testChars[i]);
            }
        }
        return ret.toString();
    }

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string
     * is returned
     *
     * @param password Password or other credentials to use in authenticating
     * this username
     * @param algorithm Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            logger.error("Exception: " + e);
            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Strips HTML and truncates.
     */
    public static String truncate(
            String str, int lower, int upper, String appendToEnd) {
        // strip markup from the string
        String str2 = removeHTML(str, false);

        // quickly adjust the upper if it is set lower than 'lower'
        if (upper < lower) {
            upper = lower;
        }

        // now determine if the string fits within the upper limit
        // if it does, go straight to return, do not pass 'go' and collect $200
        if (str2.length() > upper) {
            // the magic location int
            int loc;

            // first we determine where the next space appears after lower
            loc = str2.lastIndexOf(' ', upper);

            // now we'll see if the location is greater than the lower limit
            if (loc >= lower) {
                // yes it was, so we'll cut it off here
                str2 = str2.substring(0, loc);
            } else {
                // no it wasnt, so we'll cut it off at the upper limit
                str2 = str2.substring(0, upper);
                loc = upper;
            }

            // the string was truncated, so we append the appendToEnd String
            str2 = str2 + appendToEnd;
        }

        return str2;
    }

    /**
     * This method based on code from the String taglib at Apache Jakarta:
     * http://cvs.apache.org/viewcvs/jakarta-taglibs/string/src/org/apache/taglibs/string/util/StringW.java?rev=1.16&content-type=text/vnd.viewcvs-markup
     * Copyright (c) 1999 The Apache Software Foundation. Author:
     * timster@mac.com
     *
     * @param str
     * @param lower
     * @param upper
     * @param appendToEnd
     * @return
     */
    public static String truncateNicely(String str, int lower, int upper, String appendToEnd) {
        // strip markup from the string
        String str2 = removeHTML(str, false);
        boolean diff = (str2.length() < str.length());

        // quickly adjust the upper if it is set lower than 'lower'
        if (upper < lower) {
            upper = lower;
        }

        // now determine if the string fits within the upper limit
        // if it does, go straight to return, do not pass 'go' and collect $200
        if (str2.length() > upper) {
            // the magic location int
            int loc;

            // first we determine where the next space appears after lower
            loc = str2.lastIndexOf(' ', upper);

            // now we'll see if the location is greater than the lower limit
            if (loc >= lower) {
                // yes it was, so we'll cut it off here
                str2 = str2.substring(0, loc);
            } else {
                // no it wasnt, so we'll cut it off at the upper limit
                str2 = str2.substring(0, upper);
                loc = upper;
            }

            // HTML was removed from original str
            if (diff) {

                // location of last space in truncated string
                loc = str2.lastIndexOf(' ', loc);

                // get last "word" in truncated string (add 1 to loc to eliminate space
                String str3 = str2.substring(loc + 1);

                // find this fragment in original str, from 'loc' position
                loc = str.indexOf(str3, loc) + str3.length();

                // get truncated string from original str, given new 'loc'
                str2 = str.substring(0, loc);

                // get all the HTML from original str after loc
                str3 = extractHTML(str.substring(loc));

                // remove any tags which generate visible HTML
                // This call is unecessary, all HTML has already been stripped
                //str3 = removeVisibleHTMLTags(str3);
                // append the appendToEnd String and
                // add extracted HTML back onto truncated string
                str = str2 + appendToEnd + str3;
            } else {
                // the string was truncated, so we append the appendToEnd String
                str = str2 + appendToEnd;
            }

        }

        return str;
    }

    public static String truncateText(String str, int lower, int upper, String appendToEnd) {
        // strip markup from the string
        String str2 = removeHTML(str, false);
        boolean diff = (str2.length() < str.length());

        // quickly adjust the upper if it is set lower than 'lower'
        if (upper < lower) {
            upper = lower;
        }

        // now determine if the string fits within the upper limit
        // if it does, go straight to return, do not pass 'go' and collect $200
        if (str2.length() > upper) {
            // the magic location int
            int loc;

            // first we determine where the next space appears after lower
            loc = str2.lastIndexOf(' ', upper);

            // now we'll see if the location is greater than the lower limit
            if (loc >= lower) {
                // yes it was, so we'll cut it off here
                str2 = str2.substring(0, loc);
            } else {
                // no it wasnt, so we'll cut it off at the upper limit
                str2 = str2.substring(0, upper);
                loc = upper;
            }
            // the string was truncated, so we append the appendToEnd String
            str = str2 + appendToEnd;
        }
        return str;
    }

    /**
     * @param str
     * @return
     */
    private static String stripLineBreaks(String str) {
        // TODO: use a string buffer, ignore case !
        str = str.replaceAll("<br>", "");
        str = str.replaceAll("<br/>", "");
        str = str.replaceAll("<br />", "");
        str = str.replaceAll("<p></p>", "");
        str = str.replaceAll("<p/>", "");
        str = str.replaceAll("<p />", "");
        return str;
    }

    /**
     * Need need to get rid of any user-visible HTML tags once all text has been
     * removed such as &lt;BR&gt;. This sounds like a better approach than
     * removing all HTML tags and taking the chance to leave some tags
     * un-closed.
     * <p/>
     * WARNING: this method has serious performance problems a
     *
     * @param str the String object to modify
     * @return the new String object without the HTML "visible" tags
     * @author Alexis Moussine-Pouchkine
     * (alexis.moussine-pouchkine@france.sun.com)
     * @author Lance Lavandowska
     */
    public static String removeVisibleHTMLTags(String str) {
        str = stripLineBreaks(str);
        StringBuffer result = new StringBuffer(str);
        StringBuffer lcresult = new StringBuffer(str.toLowerCase());

        // <img should take care of smileys
        String[] visibleTags = {"<img"}; // are there others to add?
        int stringIndex;
        for (int j = 0; j < visibleTags.length; j++) {
            while ((stringIndex = lcresult.indexOf(visibleTags[j])) != -1) {
                if (visibleTags[j].endsWith(">")) {
                    result.delete(stringIndex, stringIndex + visibleTags[j].length());
                    lcresult.delete(stringIndex, stringIndex + visibleTags[j].length());
                } else {
                    // need to delete everything up until next closing '>', for <img for instance
                    int endIndex = result.indexOf(">", stringIndex);
                    if (endIndex > -1) {
                        // only delete it if we find the end!  If we don't the HTML may be messed up, but we
                        // can't safely delete anything.
                        result.delete(stringIndex, endIndex + 1);
                        lcresult.delete(stringIndex, endIndex + 1);
                    }
                }
            }
        }

        // TODO:  This code is buggy by nature.  It doesn't deal with nesting of tags properly.
        // remove certain elements with open & close tags
        String[] openCloseTags = {"li", "a", "div", "h1", "h2", "h3", "h4"}; // more ?
        for (int j = 0; j < openCloseTags.length; j++) {
            // could this be better done with a regular expression?
            String closeTag = "</" + openCloseTags[j] + ">";
            int lastStringIndex = 0;
            while ((stringIndex = lcresult.indexOf("<" + openCloseTags[j], lastStringIndex)) > -1) {
                lastStringIndex = stringIndex;
                // Try to find the matching closing tag  (ignores possible nesting!)
                int endIndex = lcresult.indexOf(closeTag, stringIndex);
                if (endIndex > -1) {
                    // If we found it delete it.
                    result.delete(stringIndex, endIndex + closeTag.length());
                    lcresult.delete(stringIndex, endIndex + closeTag.length());
                } else {
                    // Try to see if it is a self-closed empty content tag, i.e. closed with />.
                    endIndex = lcresult.indexOf(">", stringIndex);
                    int nextStart = lcresult.indexOf("<", stringIndex + 1);
                    if (endIndex > stringIndex && lcresult.charAt(endIndex - 1) == '/' && (endIndex < nextStart || nextStart == -1)) {
                        // Looks like it, so remove it.
                        result.delete(stringIndex, endIndex + 1);
                        lcresult.delete(stringIndex, endIndex + 1);

                    }
                }
            }
        }

        return result.toString();
    }

    /**
     * Extract (keep) JUST the HTML from the String.
     *
     * @param str
     * @return
     */
    public static String extractHTML(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer(str.length());
        int start = 0;
        int beginTag = str.indexOf("<");
        int endTag = 0;
        if (beginTag == -1) {
            return str;
        }

        while (beginTag >= start) {
            endTag = str.indexOf(">", beginTag);

            // if endTag found, keep tag
            if (endTag > -1) {
                ret.append(str.substring(beginTag, endTag + 1));

                // move start forward and find another tag
                start = endTag + 1;
                beginTag = str.indexOf("<", start);
            } // if no endTag found, break
            else {
                break;
            }
        }
        return ret.toString();
    }

    public static boolean isNumb(String str) {
        String s = str;
        for (int i = 0; i < s.length(); i++) {
            //If we find a non-digit character we return false.
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validateEmail(final String hex) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    public static String getValue(String value) {
        if (hasText(value)) {
            return value;
        } else {
            return "";
        }
    }
//    trunglk_start

    public static String convertArrayToString(String[] str) {
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(",");
            builder.append(s);
        }
//        builder.substring(0,(str.length*2)-1);
        return builder.substring(1, str.length * 2).toString();
    }

//    trunglk_end
    public static void main(String[] args) {
//        String p="admin1";
//        
        String p = "123456";

    }

    public static String errorMess(String str) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
        String output = "";
        if (str == null || str == "-1") {
            return "Lỗi";
        }
        if (str.equals("0")) {
            return "OK";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                switch (item) {
                    case "1":
                        output += "Dự án không tồn tại | ";
                        break;
                    case "2":
                        output += "Mã trạm quy hoạch không tồn tại | ";
                        break;
                    case "3":
                        output += "Tên tỉnh không tồn tại | ";
                        break;
                    case "4":
                        output += "Tên quận không tồn tại | ";
                        break;
                    case "5":
                        output += "Hiện trạng trạm không tồn tại | ";
                        break;
                    case "6":
                        output += "longitude không đúng định dạng số | ";
                        break;
                    case "7":
                        output += "latitude không đúng định dạng số | ";
                        break;
                    case "8":
                        output += "Trạm dự án không tồn tại | ";
                        break;
                    case "9":
                        output += "Trạng thái cơ sở hạ tầng không tồn tại | ";
                        break;
                    case "10":
                        output += "Phải chọn OK hoạc NOK | ";
                        break;
                    case "11":
                        output += "Nguồn thiết bị không tồn tại | ";
                        break;
                    case "12":
                        output += "Loại công nghệ không tồn tại | ";
                        break;
                    case "99":
                        output += "Ngày không đúng định dang dd/mm/yyyy | ";
                        break;
                    case "13":
                        output += "VNPT Net phê duyệt phải chọn OK hoặc NOK | ";
                        break;
                    case "14":
                        output += "Nhà trạm phải chọn OK hoặc NOK | ";
                        break;
                    case "15":
                        output += "Cầu cáp ngoài phải chọn OK hoặc NOK | ";
                        break;
                    case "16":
                        output += "Tủ nguồn phải chọn OK hoặc NOK | ";
                        break;
                    case "17":
                        output += "Truyền dẫn phải chọn OK hoặc NOK | ";
                        break;
                    case "18":
                        output += "Điều hòa phải chọn OK hoặc NOK | ";
                        break;
                    case "19":
                        output += "Điện AC phải chọn OK hoặc NOK | ";
                        break;
                    case "20":
                        output += "Điện AC nội trạm phải chọn OK hoặc NOK | ";
                        break;
                    case "21":
                        output += "Điều kiện lắp đặt enodeb phải chọn OK hoặc NOK | ";
                        break;
                    case "22":
                        output += "Cấp mới tủ nguồn phải chọn OK hoặc NOK | ";
                        break;
                    case "23":
                        output += "Cấp mới accu phải chọn OK hoặc NOK | ";
                        break;
                    case "24":
                        output += "Chủng loại accu không tồn tại | ";
                        break;
                    case "25":
                        output += "Cầu cáp ngoài phải chọn OK hoặc NOK | ";
                        break;
                    case "89":
                        output += "Không có quyền cập nhật trạm này | ";
                        break;
                    case "QH0014":
                        output += resourceBundle.getString("buildling.validate.long.lat") + " | ";
                        break;

                }
            }
        }
        return output;
    }

    public static String errorDKTramDA(String str) {
        String output = "";
        if (str == null || str == "-1") {
            return "Lỗi";
        }
        if (str.equals("0")) {
            return "OK";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                switch (item) {
                    case "1":
                        output += "Mã trạm dự án đã tồn tại | ";
                        break;
                    case "2":
                        output += "Mã CSHT không tồn tại | ";
                        break;
                    case "3":
                        output += "Mã quy hoạch không tồn tại | ";
                        break;

                    case "5":
                        output += "Hiện trạng trạm không tồn tại | ";
                        break;
                }
            }
        }
        return output;
    }

    public static String errorRegTramQuyHoach(String str) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
        String output = "";
        if (str == null || str == "-1") {
            return "Lỗi hệ thống";
        }
        if (str.equals("0")) {
            return "OK";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                if (isNumeric(item)) {
                    switch (item) {
                        case "2":
                            output += "Mã tỉnh không tồn tại | ";
                            break;
                        case "1":
                            output += "Loại công nghệ không tồn tại | ";
                            break;
                        case "3":
                            output += "Băng tần không tồn tại | ";
                            break;
                        case "4":
                            output += "Chương trình PT CSHT không tồn tại | ";
                            break;
                        case "5":
                            output += "Trạng thái csht không tồn tại | ";
                            break;
                        case "6":
                            output += "Đơn vị phê duyệt không tồn tại | ";
                            break;
                        case "7":
                            output += "Ngày phê duyệt không đúng định dạng dd/mm/yyyy | ";
                            break;
                        case "8":
                            output += "Năm khởi tạo không đúng định dạng dd/mm/yyyy | ";
                            break;
                        case "9":
                            output += "Latitude không đúng kiểu số | ";
                            break;
                        case "10":
                            output += "Longitude không đúng kiểu số | ";
                            break;
                        case "11":
                            output += "Tên quy hoạch đã tồn tại | ";
                            break;
                        case "QH0014":
                            output += resourceBundle.getString("buildling.validate.long.lat") + " | ";
                            break;
                    }
                } else {
                    output += item + ", ";
                }
            }
        }
        return output;
    }

    public static String errorMessKDCell(String str) {
        String output = "";
        if (str == null || str == "-1") {
            return "Lỗi";
        }
        if (str.equals("0")) {
            return "OK";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                switch (item) {
                    case "1":
                        output += "Mã quy hoạch tỉnh không tồn tại | ";
                        break;
                    case "2":
                        output += "Loại công nghệ không tồn tại | ";
                        break;
                    case "3":
                        output += "Băng tần không tồn tại | ";
                        break;
                    case "4":
                        output += "Chương trình PT CSHT không tồn tại | ";
                        break;
                    case "5":
                        output += "Trạng thái csht không tồn tại | ";
                        break;
                    case "6":
                        output += "Đơn vị phê duyệt không tồn tại | ";
                        break;
                    case "7":
                        output += "Đơn vị điều chỉnh không tồn tại | ";
                        break;
                    case "8":
                        output += "Mã building không tồn tại | ";
                        break;
                    case "9":
                        output += "Mã quy hoạch đã tồn tại | ";
                        break;
                }
            }
        }
        return output;
    }

    public static String errorMessKDBlackList(String str) {
        String output = "";
        if (str == null || str == "-1") {
            return "Lỗi";
        }
        if (str.equals("0")) {
            return "OK";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                switch (item) {
                    case "2":
                        output += "Tên tỉnh không tồn tại | ";
                        break;

                    case "3":
                        output += "Tên quận không tồn tại | ";
                        break;
                }
            }
        }
        return output;
    }

    public static String errorMessUpdateTramQH(String str) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
        String output = "";
        if (str == null || str.isEmpty()) {
            return "Cập nhật dữ liệu thành công!";
        } else {
            str = str.substring(0, str.length() - 1);
            for (String item : str.split(",")) {
                switch (item.trim()) {

                    case "-1":
                        output += "Lỗi cập nhật | ";
                        break;
                    case "0":
                        output += "Không có quyền cập nhật | ";
                        break;
                    case "1":
                        output += "Mã trạm quy hoạch không tồn tại | ";
                        break;
                    case "2":
                        output += "Mã quy hoạch tỉnh không tồn tại | ";
                        break;
                    case "3":
                        output += "Tên trạm quy hoạch không được rỗng | ";
                        break;
                    case "4":
                        output += "Loại công nghệ không tồn tại | ";
                        break;
                    case "5":
                        output += "Băng tần không tồn tại | ";
                        break;
                    case "6":
                        output += "Chương trình PT CSHT không tồn tại | ";
                        break;
                    case "7":
                        output += "Trạng thái csht không tồn tại | ";
                        break;
                    case "8":
                        output += "Đơn vị phê duyệt không tồn tại | ";
                        break;
                    case "9":
                        output += "Số hiểu văn bản không được rỗng | ";
                        break;
                    case "10":
                        output += "Ngày phê duyệt không được rỗng | ";
                        break;
                    case "13":
                        output += "Năm khởi tạo không được rỗng | ";
                        break;
                    case "14":
                        output += "Đơn vị điều chỉnh không tồn tại | ";
                        break;
                    case "15":
                        output += "Đơn vị chịu trách nhiệm không tồn tại | ";
                        break;
                    case "16":
                        output += "Nguồn thiết bị không tồn tại | ";
                        break;
                    case "17":
                        output += "Thời điểm đáp ứng dự kiễn không được rỗng | ";
                        break;
                    case "18":
                        output += "Công nghệ đáp ứng không tồn tại | ";
                        break;
                    case "19":
                        output += "Chủng loại thiết bị không được rỗng | ";
                        break;
                    case "20":
                        output += "Đơn vị trách nhiệm cơ sở hạ tầng không tồn tại | ";
                        break;
                    case "21":
                        output += "Cách xây cơ sở hạ tầng không tồn tại | ";
                        break;
                    case "22":
                        output += "Loại đất không tồn tại | ";
                        break;
                    case "23":
                        output += "Loại cột anten không tồn tại | ";
                        break;
                    case "24":
                        output += "Phương thức truyền dẫn không tồn tại | ";
                        break;
                    case "25":
                        output += "Giao diện truyền dẫn GE không tồn tại | ";
                        break;
                    case "26":
                        output += "Giao diện truyền dẫn FE không tồn tại | ";
                        break;
                    case "27":
                        output += "Đơn vị trách nhiệm nguồn dc không tồn tại | ";
                        break;
                    case "28":
                        output += "Tủ nguồn không tồn tại | ";
                        break;
                    case "29":
                        output += "Loại tủ nguồn không tồn tại | ";
                        break;
                    case "30":
                        output += "Băng tần anten1 không tồn tại | ";
                        break;
                    case "31":
                        output += "Cấu hình port1 không tồn tại | ";
                        break;
                    case "32":
                        output += "Băng tần anten2 không tồn tại | ";
                        break;
                    case "33":
                        output += "Cấu hình port2 không tồn tại | ";
                        break;
                    case "34":
                        output += "Băng tần anten3 không tồn tại | ";
                        break;
                    case "35":
                        output += "Cấu hình port3 không tồn tại | ";
                        break;
                    case "36":
                        output += "Đơn vị chịu trách nhiệm anten không tồn tại | ";
                        break;
                    case "37":
                        output += "Loại anten1 không tồn tại | ";
                        break;
                    case "38":
                        output += "Loại anten2 không tồn tại | ";
                        break;
                    case "39":
                        output += "Loại antent3 không tồn tại | ";
                        break;
                    case "40":
                        output += "Lỗi cập nhật nhóm thông tin chung | ";
                        break;
                    case "41":
                        output += "Lỗi cập nhật nhóm cam kết thiết bị | ";
                        break;
                    case "42":
                        output += "Lỗi cập nhật nhóm cơ sở hạ tầng | ";
                        break;
                    case "43":
                        output += "Lỗi cập nhật nhóm nguồn anten | ";
                        break;
                    case "44":
                        output += "Lỗi cập nhật nhóm nguồn anten | ";
                        break;
                    case "45":
                        output += "Quận/huyện không tồn tại hoặc không thuộc Tỉnh/TP| ";
                        break;
                    case "46":
                        output += "Phường/xã không tồn tại hoặc không thuộc Quận/Huyện| ";
                        break;
                    case "47":
                        output += "Loại nhà trạm không tồn tại | ";
                        break;
                    case "48":
                        output += "Tỉnh/TP không tồn tại | ";
                        break;
                    case "QH001":
                        output += resourceBundle.getString("qh.error.qh001") + " | ";
                        break;
                    case "QH002":
                        output += resourceBundle.getString("qh.error.qh002") + " | ";
                        break;
                    case "QH003":
                        output += resourceBundle.getString("qh.error.qh003") + " | ";
                        break;
                    case "QH004":
                        output += resourceBundle.getString("qh.error.qh004") + " | ";
                        break;
                    case "QH005":
                        output += resourceBundle.getString("qh.error.qh005") + " | ";
                        break;
                    case "QH006":
                        output += resourceBundle.getString("qh.error.qh006") + " | ";
                        break;
                    case "QH007":
                        output += resourceBundle.getString("qh.error.qh007") + " | ";
                        break;
                    case "QH008":
                        output += resourceBundle.getString("qh.error.qh008") + " | ";
                        break;
                    case "QH009":
                        output += resourceBundle.getString("qh.error.qh009") + " | ";
                        break;
                    case "QH0010":
                        output += resourceBundle.getString("qh.error.qh0010") + " | ";
                        break;
                    case "QH0011":
                        output += resourceBundle.getString("qh.error.qh0011") + " | ";
                        break;
                    case "QH0012":
                        output += resourceBundle.getString("qh.error.qh0012") + " | ";
                        break;
                    case "QH0013":
                        output += resourceBundle.getString("qh.error.qh0013") + " | ";
                        break;
                    case "QH0014":
                        output += resourceBundle.getString("buildling.validate.long.lat") + " | ";
                        break;
                }
            }
        }
        return output;
    }

    public static String getRandom() {
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;
        long l = System.currentTimeMillis() % 1000;
        return l + "_" + n;

    }

    public static boolean checkLong(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        }
        try {
            Long.valueOf(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Object trimObject(Object bean) {
        if (bean == null) {
            return bean;
        }

        Field[] fields = bean.getClass().getDeclaredFields();
        if (fields == null) {
            return bean;
        }

        for (Field f : fields) {
            if (f.getType().isPrimitive()) {
                continue;
            }

            if (f.getType().equals(String.class)) {
                try {
                    f.setAccessible(true);
                    String value = (String) f.get(bean);
                    if (value != null) {
                        f.set(bean, value.trim());
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }
        return bean;
    }

    // lay ra thu muc chua file template
    public static String getFolderTemp() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
            String folderTemp = System.getProperty("catalina.base") + File.separator + resourceBundle.getString("folder_temp");
            File folder = new File(folderTemp);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folderTemp;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //kiem tra xem co phai so ko
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /*
    hàm check xem file import có giống với file template không?
    kiểm tra xem ở các row có giống nhau không
     */
    public static boolean checkImportFile(File fileImport, File fileTemplate, Integer[] checkRows) {
        FileInputStream finImport = null;
        Workbook workbookImport = null;
        FileInputStream finTemplate = null;
        Workbook workbookTemplate = null;
        try {
            finImport = new FileInputStream(fileImport);
            if (fileImport.getName().endsWith(".xls")) {
                workbookImport = new HSSFWorkbook(finImport);
            } else if (fileImport.getName().endsWith(".xlsx")) {
                workbookImport = new XSSFWorkbook(finImport);
            }
            Sheet sheetImport = workbookImport.getSheetAt(0);
            //
            finTemplate = new FileInputStream(fileTemplate);
            if (fileTemplate.getName().endsWith(".xls")) {
                workbookTemplate = new HSSFWorkbook(finTemplate);
            } else if (fileTemplate.getName().endsWith(".xlsx")) {
                workbookTemplate = new XSSFWorkbook(finTemplate);
            }

            Sheet sheetTemplate = workbookTemplate.getSheetAt(0);
            boolean flag = false;
            if (checkRows != null) {
                for (Integer r : checkRows) {
                    Iterator rowImport = sheetImport.getRow(r - 1).cellIterator();
                    Iterator rowTemplate = sheetTemplate.getRow(r - 1).cellIterator();
                    int i = 0;
                    while (rowImport.hasNext()) {

                        Cell cell = (Cell) rowImport.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {
                            i++;
                        }
                    }
                    int j = 0;
                    while (rowTemplate.hasNext()) {
                        Cell cell = (Cell) rowTemplate.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {
                            j++;
                        }
                    }
                    if (j == i) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }
            if (flag) {
                return true;
            }

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                if (finImport != null) {
                    finImport.close();
                }
                if (finTemplate != null) {
                    finTemplate.close();
                }
            } catch (IOException ex) {

            }
        }
        return false;
    }

}
