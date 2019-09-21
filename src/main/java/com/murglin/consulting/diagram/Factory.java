package com.murglin.consulting.diagram;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

public class Factory {

    public static void buildDiagram(final Workspace workspace) {
        Model model = workspace.getModel();

        Person agent = model.addPerson("Agent", "Agent of the online casino platform.");
        Person admin = model.addPerson("Admin", "Administrator of the online casino platform.");
        Person player = model.addPerson("Player", "Player in the casino");

        SoftwareSystem softwareSystem = model.addSoftwareSystem("Casino System", "Online casino system.");
        agent.uses(softwareSystem, "operate");
        admin.uses(softwareSystem, "administrate");
        player.uses(softwareSystem, "play");

        Container playerBrowserContainer = softwareSystem.addContainer("Player browser container", "Browser application", "Chrome browser");
        Container adminBrowserContainer = softwareSystem.addContainer("Admin browser container", "Browser application", "Chrome browser");
        Container agentBrowserContainer = softwareSystem.addContainer("Agent browser container", "Browser application", "Chrome browser");
        Container casinoAPIContainer = softwareSystem.addContainer("Casino API container", "Casino monolithic application with multiple different services", "Spring boot application");
        Container casinoDBContainer = softwareSystem.addContainer("Casino DB container", "Transactional db", "Postgres");
        Container casinoReplicaDBContainer = softwareSystem.addContainer("Casino replica DB container", "View replicated model", "Postgres replication");
        Container backofficeAPIContainer = softwareSystem.addContainer("Backoffice API container", "Casino management applications", "Spring boot application");
        Container backofficeDBContainer = softwareSystem.addContainer("Backoffice DB container", "Transactional db", "Postgres");
        casinoAPIContainer.uses(casinoDBContainer, "uses");
        casinoDBContainer.uses(casinoReplicaDBContainer, "replicates");
        backofficeAPIContainer.uses(backofficeDBContainer, "uses");
        backofficeAPIContainer.uses(casinoReplicaDBContainer, "uses");
        backofficeAPIContainer.uses(casinoAPIContainer, "uses", "REST [sync]");
        playerBrowserContainer.uses(casinoAPIContainer, "uses", "REST [sync]");
        adminBrowserContainer.uses(backofficeAPIContainer, "uses", "REST [sync]");
        agentBrowserContainer.uses(backofficeAPIContainer, "uses", "REST [sync]");

        //TODO add componenets level

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "An example of a Casino System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();
    }
}
