package com.murglin.consulting.diagram;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;

public class Publisher {

    private static final String API_KEY = "26bae340-a30d-42c4-9d62-973b262b32e9";
    private static final String API_SECRET = "e5a81a4f-fd84-4d9f-80b2-8241999d1c4f";
    private static final long WORKSPACE_ID = 46905;

    public static void publishToDiagramStructurizr(final Workspace workspace) throws StructurizrClientException {
        StructurizrClient structurizrClient = new StructurizrClient((API_KEY), API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
