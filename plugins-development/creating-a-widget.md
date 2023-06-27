---
title: Creating a Widget
parent: Development
nav_order: 2
---

# Creating a Widget

{: .no_toc }

Widgets are the visible and interactable component displayed on streams, that includes alerts, text labels, and more. Widgets are created by the user and are not created by the plugin directly.

## Registration

In your `onInit()` method you register a widget by calling `CaffeinatedPlugins#registerWidget()`.

```java
@Override
public void onInit() {
	this.getLogger().info("Hello World!");

	// Get the plugins context and register our widget.
    this.getPlugins().registerWidget(
		this,
		new WidgetDetails()
		 	// Must be unique.
			.withNamespace("com.example.custom_widget")

			 // Pick one from https://heroicons.com
			.withIcon("rocket-launch")

			 // Categorize as other.
			.withCategory(WidgetDetailsCategory.OTHER)

			// This is the name shown in the creation menu.
			.withFriendlyName("My Custom Widget"),
		CustomWidget.class
	);
}
```

```java
public class CustomWidget extends Widget {

    @Override
    public void onInit() {

    }

    @Override
    public void onNameUpdate() { // This is always called when the widget is created.
        FastLogger.logStatic("Hello World! I am: \"%s\"", this.getName());
    }

}
```

When the user creates this widget, it will spawn with the name `My Custom Widget (New)` and the following text should appear in the console:  
`[INFO  ] [CustomWidget] Hello World! I am "My Custom Widget (New)"`

## Widget Settings

Obviously, you want to make your widget configurable so that the user can customize it. To do this, you need to register a settings layout for your widget. This is done by calling `Widget#setSettingsLayout()`.

The settings layout is divided into sections, and each section contains "items" which are the inputs and such.

In your widget's `onInit()`, add the following code:

```java
this.setSettingsLayout(new WidgetSettingsLayout()
	// You can actually add however many sections you want, but for now we'll just use one.
	.addSection(
		// These are rendered in the order they are added...
		new WidgetSettingsSection("text_style", "Style")
			// ... so are these
			.addItem(WidgetSettingsItem.asFont("font", "Font", "Poppins"))
			.addItem(WidgetSettingsItem.asNumber("font_size", "Font Size (px)", 16, 1, 0, 128))
			.addItem(WidgetSettingsItem.asColor("text_color", "Text Color", "#ffffff"))
	)
);
```

This'll create a settings layout with a section called "Style" and three items:

- Font
- Font Size (px)
- Text Color

These are configured by the user, and will call `Widget#onSettingsUpdate()` (which you can implement) when they are changed.  
You may access the settings by calling `Widget#getSettings()` in your code. The default values are automatically populated for you.

(More advanced plugins may wish to dynamically update the settings based on the configured settings, this may be done by implementing `Widget#onSettingsUpdate()` and recreating your layout.)

<br>
<br>
<br>
<span class="fs-3">
	[Next, Creating the OBS source for the widget.](/caffeinated-sdk/development/creating-the-widget-source){: .btn .float-right }
</span>
