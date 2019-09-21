package com.murglin.consulting.diagram;

import com.structurizr.Workspace;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;

public class Styler {

    public static void styleDiagram(final Workspace workspace) {
        final var views = workspace.getViews();
        final var styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);
    }
}
