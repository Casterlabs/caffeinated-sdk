package co.casterlabs.caffeinatedsdk.example;

import co.casterlabs.caffeinated.pluginsdk.CaffeinatedPlugin;
import co.casterlabs.caffeinated.pluginsdk.CaffeinatedPluginImplementation;
import co.casterlabs.caffeinated.pluginsdk.widgets.WidgetDetails;
import co.casterlabs.caffeinated.pluginsdk.widgets.WidgetDetails.WidgetDetailsCategory;

@CaffeinatedPluginImplementation
public class MyFirstPlugin extends CaffeinatedPlugin {

    @Override
    public void onInit() {
        this.getLogger().info("Hello World!");

        // Get the plugins context and register our widget.
        this.getPlugins().registerWidget(
            this,
            new WidgetDetails()
                // Must be unique.
                .withNamespace("com.example.custom_widget")

                // https://feathericons.com/?query=anchor
                .withIcon("anchor")

                // Categorize as other.
                .withCategory(WidgetDetailsCategory.OTHER)

                // This is the name shown in the creation menu.
                .withFriendlyName("My Custom Widget"),
            CustomWidget.class
        );
    }

    @Override
    public void onClose() {
        this.getLogger().info("Goodbye World!");
    }

    @Override
    public String getName() {
        return "My First Plugin";
    }

    @Override
    public String getId() {
        return "com.example.firstplugin";
    }

}
