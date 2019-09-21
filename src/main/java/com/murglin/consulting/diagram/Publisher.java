package com.murglin.consulting.diagram;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;

public class Publisher {

    private static final String API_KEY = "key";
    private static final String API_SECRET = "secret";
    private static final long WORKSPACE_ID = -1;

    public static void publishToDiagramStructurizr(final Workspace workspace) throws StructurizrClientException {
        StructurizrClient structurizrClient = new StructurizrClient((API_KEY), API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
