/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.bloomtech;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void shouldReadRawTextFromFile() {
        String rawTextFromFile = app.getRawTextFromFile("testHeadings.txt");
        assertEquals(172, rawTextFromFile.length());
    }

    @Test
    void shouldSplitLinesFromRawTextCorrectly() {
        List<String> lines = app.splitLines(
            app.getRawTextFromFile("testHeadings.txt")
        );
        assertEquals(9, lines.size());
    }

    @Test
    void shouldExtractOnlyHeadingLinesFromLines() {
        List<String> headingLines = app.filterHeadingsFromLines(
            app.splitLines(
                app.getRawTextFromFile("testHeadings.txt")
            )
        );
        assertEquals(4, headingLines.size());
    }

    @Test
    void shouldConvertHeadingStringsToHeadings() {
        List<Heading> headings = app.getHeadingsFromHeadingStrings(
            app.filterHeadingsFromLines(
                app.splitLines(
                    app.getRawTextFromFile("testHeadings.txt")
                )
            )
        );
        assertEquals(4, headings.size());
        assertEquals(new Heading("N", 3.2f, 14f), headings.get(0));
    }

    @Test
    void shouldComputeDistanceInMiles() {
        float distanceInMiles = app.computeTotalDistanceTraveledInMiles(
            app.getHeadingsFromHeadingStrings(
                app.filterHeadingsFromLines(
                    app.splitLines(
                        app.getRawTextFromFile("testHeadings.txt")
                    )
                )
            )
        );
        assertEquals(939.08765f, distanceInMiles);
    }
}
