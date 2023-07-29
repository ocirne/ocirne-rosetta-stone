package io.github.ocirne.rosetta.csvtabellieren;

import io.github.ocirne.rosetta.csvtabellieren.shot0.Shot0Casual;
import io.github.ocirne.rosetta.csvtabellieren.shot0.Shot0Hard;
import io.github.ocirne.rosetta.csvtabellieren.shot1.Shot1;
import io.github.ocirne.rosetta.csvtabellieren.shot2.Shot2;
import io.github.ocirne.rosetta.csvtabellieren.shot4.Shot4;
import io.github.ocirne.rosetta.csvtabellieren.shot5.Shot5;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ShotsTest {

    public static Stream<Arguments> provideCsvTabulatorClasses() {
        return Stream.of(
                Arguments.of(new Shot0Casual()),
                Arguments.of(new Shot0Hard()),
                Arguments.of(new Shot1()),
                Arguments.of(new Shot2()),
                Arguments.of(new Shot4()),
                Arguments.of(new Shot5())
        );
    }

    private static final List<String> REFERENCE_INPUT = Arrays.asList(
            "Name;Strasse;Ort;Alter",
            "Peter Pan;Am Hang 5;12345 Einsam;42",
            "Maria Schmitz;Kölner Straße 45;50123 Köln;43",
            "Paul Meier;Münchener Weg 1;87654 München;65"
    );

    private static final List<String> EXPECTED_OUTPUT = Arrays.asList(
            "Name         |Strasse         |Ort          |Alter|",
            "-------------+----------------+-------------+-----+",
            "Peter Pan    |Am Hang 5       |12345 Einsam |42   |",
            "Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |",
            "Paul Meier   |Münchener Weg 1 |87654 München|65   |"
    );

    @ParameterizedTest
    @MethodSource("provideCsvTabulatorClasses")
    public void testTabelliere(final CsvTabulator csvTabulator) {
        List<String> actualOutput = csvTabulator.tabelliere(REFERENCE_INPUT);
        assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }
}