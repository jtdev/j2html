package j2html.tags;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static j2html.TagCreator.*;
import static org.junit.Assert.assertEquals;

public class DomContentCollectorTest {
    @Test
    public void testDomContentCollector() {
        List<Integer> numbers = IntStream.rangeClosed(5, 7).boxed().collect(Collectors.toList());

        ContainerTag tag = ul().with(
            li("First"),
            numbers.stream().map(number ->
                li(number.toString())
            ).collect(domContentCollect()),
            li("Last")
        );

        String expectedResult = "<ul><li>First</li><li>5</li><li>6</li><li>7</li><li>Last</li></ul>";
        assertEquals(tag.render(), expectedResult);
    }
}
