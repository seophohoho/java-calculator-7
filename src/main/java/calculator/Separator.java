package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    public String[] separate(String input) {
        return separate(input, Constants.SEPARATOR);
    }

    public String[] separate(String input, String separator) {
        String regex = separator;

        if (isContainsCustomSeparatorSymbol(input)) {
            input = deleteCustomSeparatorConvention(input);
        }

        return input.split(regex);
    }

    public boolean isContainsCustomSeparatorSymbol(String input) {
        return input.startsWith(Constants.CUSTOM_SEPARATOR_PREFIX) &&
                input.contains(Constants.CUSTOM_SEPARATOR_SUFFIX);
    }

    public String deleteCustomSeparatorConvention(String input) {
        StringBuilder sb = new StringBuilder(input);
        int prefixIdx = input.indexOf(Constants.CUSTOM_SEPARATOR_PREFIX);
        int suffixIdx = input.lastIndexOf(Constants.CUSTOM_SEPARATOR_SUFFIX);

        sb.delete(prefixIdx, suffixIdx + 2);
        return sb.toString();
    }

    public String addCustomSeparator(String regex, String customSeparator) {
        return regex.concat("|" + customSeparator);
    }

    public String getSeparator(String input) {
        String regex = Constants.SEPARATOR;

        if (isContainsCustomSeparatorSymbol(input)) {
            regex = findAndAddCustomSeparator(input, regex);
        }
        return regex;
    }

    public String findAndAddCustomSeparator(String input, String regex) {
        Pattern pattern = Pattern.compile(Constants.REGEX_CUSTOM_SEPARATOR_PATTERN);
        Matcher match = pattern.matcher(input);

        while (match.find()) {
            String customSeparator = match.group(1);
            regex = addCustomSeparator(regex, customSeparator);
        }
        return regex;
    }
}
