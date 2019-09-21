package com.murglin.consulting;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClientException;

import static com.murglin.consulting.diagram.Factory.buildDiagram;
import static com.murglin.consulting.diagram.Publisher.publishToDiagramStructurizr;
import static com.murglin.consulting.diagram.Styler.styleDiagram;

public class Main {

    public static void main(String[] args) throws StructurizrClientException {

        Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");

        buildDiagram(workspace);

        styleDiagram(workspace);

        publishToDiagramStructurizr(workspace);
    }
}
