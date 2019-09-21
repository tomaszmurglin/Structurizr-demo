package com.murglin.consulting.diagram;

import com.structurizr.Workspace;
import com.structurizr.model.Model;

public class Factory {

    public static void buildDiagram(final Workspace workspace) {
        Model model = workspace.getModel();

        final var agent = model.addPerson("Agent", "Agent of the online casino platform.");
        final var admin = model.addPerson("Admin", "Administrator of the online casino platform.");
        final var player = model.addPerson("Player", "Player in the casino");

        final var softwareSystem = model.addSoftwareSystem("Casino System", "Online casino system.");
        agent.uses(softwareSystem, "operate");
        admin.uses(softwareSystem, "administrate");
        player.uses(softwareSystem, "play");

        final var playerBrowserContainer = softwareSystem.addContainer("Player browser container", "Browser application", "Chrome browser");
        final var adminBrowserContainer = softwareSystem.addContainer("Admin browser container", "Browser application", "Chrome browser");
        final var agentBrowserContainer = softwareSystem.addContainer("Agent browser container", "Browser application", "Chrome browser");
        final var casinoAPIContainer = softwareSystem.addContainer("Casino API container", "Casino monolithic application with multiple different services", "Spring boot application");
        final var casinoDBContainer = softwareSystem.addContainer("Casino DB container", "Transactional db", "Postgres");
        final var casinoReplicaDBContainer = softwareSystem.addContainer("Casino replica DB container", "View replicated model", "Postgres replication");
        final var backofficeAPIContainer = softwareSystem.addContainer("Backoffice API container", "Casino management applications", "Spring boot application");
        final var backofficeDBContainer = softwareSystem.addContainer("Backoffice DB container", "Transactional db", "Postgres");
        casinoAPIContainer.uses(casinoDBContainer, "uses");
        casinoDBContainer.uses(casinoReplicaDBContainer, "replicates");
        backofficeAPIContainer.uses(backofficeDBContainer, "uses");
        backofficeAPIContainer.uses(casinoReplicaDBContainer, "uses");
        backofficeAPIContainer.uses(casinoAPIContainer, "uses", "REST [sync]");
        playerBrowserContainer.uses(casinoAPIContainer, "uses", "REST [sync]");
        adminBrowserContainer.uses(backofficeAPIContainer, "uses", "REST [sync]");
        agentBrowserContainer.uses(backofficeAPIContainer, "uses", "REST [sync]");

        //TODO add componenets level

        final var views = workspace.getViews();
        final var contextView = views.createSystemContextView(softwareSystem, "SystemContext", "An example of a Casino System Context diagram.");
        final var systemContainers = views.createContainerView(softwareSystem, "SystemContainers", "An example of a Casino System Containers diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();
        systemContainers.addAllContainers();
    }
}
