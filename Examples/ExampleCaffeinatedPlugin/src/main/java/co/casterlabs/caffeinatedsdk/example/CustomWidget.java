package co.casterlabs.caffeinatedsdk.example;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.Nullable;

import co.casterlabs.caffeinated.pluginsdk.widgets.Widget;
import co.casterlabs.caffeinated.pluginsdk.widgets.WidgetInstance;
import co.casterlabs.caffeinated.pluginsdk.widgets.settings.WidgetSettingsItem;
import co.casterlabs.caffeinated.pluginsdk.widgets.settings.WidgetSettingsLayout;
import co.casterlabs.caffeinated.pluginsdk.widgets.settings.WidgetSettingsSection;
import co.casterlabs.rakurai.io.IOUtil;
import co.casterlabs.rakurai.json.element.JsonElement;
import lombok.NonNull;
import lombok.SneakyThrows;
import xyz.e3ndr.fastloggingframework.logging.FastLogger;

public class CustomWidget extends Widget {

    @Override
    public void onInit() {
        this.setSettingsLayout(
            new WidgetSettingsLayout()
                // You can actually add however many sections you want, but for now we'll just
                // use one.
                .addSection(
                    // These are rendered in the order they are added...
                    new WidgetSettingsSection("text_style", "Style")
                    // ... so are these
//                        .addItem(WidgetSettingsItem.asFont("font", "Font", "Poppins")) // Not in the SDK yet.
                        .addItem(WidgetSettingsItem.asNumber("font_size", "Font Size (px)", 16, 1, 0, 128))
                        .addItem(WidgetSettingsItem.asColor("text_color", "Text Color", "#ffffff"))
                )
        );
    }

    @Override
    public void onNameUpdate() { // This is always called when the widget is created.
        FastLogger.logStatic("Hello World! I am: \"%s\"", this.getName());
    }

    @Override
    public void onNewInstance(@NonNull WidgetInstance instance) {
        instance.on(
            "message", (JsonElement e) -> {
                // We just print the raw json.
                FastLogger.logStatic("Received message from a widget instance: %s", e);

                // instance.emit("mytype", mymessage);
            }
        );
    }

    @SneakyThrows
    @Override
    public @Nullable String getWidgetHtml() {
        // Read the source from the resources folder.
        InputStream in = MyFirstPlugin.class.getResourceAsStream("/custom_widget.html");

        // Return our html string.
        return IOUtil.readInputStreamString(in, StandardCharsets.UTF_8);
    }

}
