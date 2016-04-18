package j2html.tags;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class DomContentCollector implements Collector<DomContent, List<DomContent>, DomContent> {
    @Override
    public Supplier<List<DomContent>> supplier() {
        return () -> Collections.synchronizedList(new LinkedList<>());
    }

    @Override
    public BiConsumer<List<DomContent>, DomContent> accumulator() {
        return (list, domContent) -> list.add(domContent);
    }

    @Override
    public BinaryOperator<List<DomContent>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Function<List<DomContent>, DomContent> finisher() {
        return list -> new GroupingTag(list);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }

    private static class GroupingTag extends DomContent {
        private List<DomContent> children;

        public GroupingTag(List<DomContent> children) {
            this.children = children;
        }

        @Override
        public String render() {
            StringBuilder rendered = new StringBuilder();
            if (children != null && children.size() > 0) {
                for (DomContent child : children) {
                    rendered.append(child.render());
                }
            }
            return rendered.toString();
        }
    }
}