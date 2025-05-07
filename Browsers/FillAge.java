package browsers;

import java.util.regex.Pattern;

public class FillAge {
    static Pattern pattern;
    private static final String AGE_PATTERN = "(\\w{2,120})";
    public FillAge() {
        pattern = Pattern.compile(AGE_PATTERN);
    }

    public boolean TestAge(final String age) {
        return pattern.matcher(age).matches();
    }
}
