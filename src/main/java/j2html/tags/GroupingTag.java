package j2html.tags;

import java.util.List;

class GroupingTag extends DomContent {
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