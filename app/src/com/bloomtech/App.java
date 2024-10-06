/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.bloomtech;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {

    private static final float KNOT_TO_MPH_CONVERSION_FACTOR = 1.15f;

    public String getRawTextFromFile(String filename) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
            String result = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Unable to read file", e);
        }
    }

    public List<String> splitLines(String rawHeadings) {
        // TODO: implement
        String[] lines = rawHeadings.split("\\n");
        return Arrays.asList(lines);
    }

    public List<String> filterHeadingsFromLines(List<String> lines) {
        String directionRegex = "[NESW]{1,2}";
        String timeRegex = "\\d+\\.\\d+min";
        String speedRefex = "\\d+\\.\\d+knots";
        Pattern directionPattern = Pattern.compile(directionRegex);
        Pattern timePattern = Pattern.compile(timeRegex);
        Pattern speedPatern = Pattern.compile(speedRefex);
        List<String> output = new ArrayList<>();
        for (String line : lines) {
            Matcher directionMatcher = directionPattern.matcher(line);
            Matcher timeMatcher = timePattern.matcher(line);
            Matcher speedMatcher = speedPatern.matcher(line);
            if (directionMatcher.find() && timeMatcher.find() && speedMatcher.find()) {
                output.add(line);
            }
        }
        return output;
    }

    public Heading buildHeadingFromHeadingString(String headingString) {
        // TODO: implement
        String headingRedexWithGroups = "([NESW]{1,2})\\s(\\d+\\.\\d+)min\\s(\\d+\\.\\d+)knots";
        Pattern p = Pattern.compile(headingRedexWithGroups);
        Matcher m = p.matcher(headingString);
        if (m.matches()) {
            String dir = m.group(1);
            Float time = Float.parseFloat(m.group(2));
            Float speed = Float.parseFloat(m.group(3));
            return new Heading(dir, time, speed);
        } else {
            throw new RuntimeException("Unable to extract data from heading string - " + headingString);
        }
    }

    public List<Heading> getHeadingsFromHeadingStrings(List<String> headingStrings) {

        List<Heading> listOfHeadings = new ArrayList<>();
        for (String h : headingStrings) {
            listOfHeadings.add(buildHeadingFromHeadingString(h));
        }

        return listOfHeadings;
    }

    public float computeTotalDistanceTraveledInMiles(List<Heading> headings) {
        float totalDistanceTravelled = 0.0f;
        for (Heading h : headings) {
            totalDistanceTravelled += h.getTime() * h.getSpeedInKnots();
        }
        return totalDistanceTravelled * KNOT_TO_MPH_CONVERSION_FACTOR;
    }

    public static void main(String[] args) {
        App app = new App();
        String rawText = app.getRawTextFromFile("headings.txt");
        List<String> lines = app.splitLines(rawText);
        List<String> headingStrings = app.filterHeadingsFromLines(lines);
        List<Heading> headings = app.getHeadingsFromHeadingStrings(headingStrings);
        System.out.println("Total distance travelled = "+app.computeTotalDistanceTraveledInMiles(headings));


    }
}
