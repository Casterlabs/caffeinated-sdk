---
title: Creating a Widget
parent: Development
---

# Creating a Widget

Widgets are the visible and interactable component to streams, these include alerts, text labels, and more. Widgets are created by the user and are not controlled by the plugin directly, but can be interact and implement their own logic to do so.

## Registration

In your `onInit()` method you register a widget by calling `CaffeinatedPlugins#registerWidget()`.
```java
@Override
public void onInit() {
	// Get the plugins context and register our widget.
    this.getPlugins().registerWidget(
		this, 
		new WidgetDetails()
		 	// Must be unique.
			.withNamespace("com.example.custom_widget")

			 // https://feathericons.com/?query=anchor
			.withIcon("anchor")

			 // Categorize as a label.
			.withCategory(WidgetDetailsCategory.LABELS)

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
		FastLogger.logStatic("Hello World! I am: \"%s\"", this.getName());
    }

}
```
When the user creates a widget, it will spawn with the name `My Custom Widget (New)` and the following text should appear in the console:  
`[INFO  ] [CustomWidget] Hello World! I am "My Custom Widget (New)"`  


## Widget Settings

Obviously, you want to make your widget configurable so that the user can customize it. To do this, you need to register a settings layout for your widget. This is done by calling `Widget#setSettingsLayout()`.  

The settings layout is divided into sections, and each section contains "items" which are the inputs and such.  

In your widget `onInit()` add the following code:  
```java
this.setSettingsLayout(new WidgetSettingsLayout()
	// You can actually add however many sections you want, but for now we'll just use one.
	.addSection(
		// These are rendered in the order they are added...
		new WidgetSettingsSection("text_style", "Style")
			// ... so are these
			.addItem(WidgetSettingsItem.asFont("font", "Font", "Poppins"))
			.addItem(WidgetSettingsItem.asNumber("font_size", "Font Size", 16, 1, 0, 128))
			.addItem(WidgetSettingsItem.asColor("text_color", "Text Color", "#ffffff"))
	)
);
```
  
This'll create a settings layout with a section called "Style" and three items:
 - Font
 - Font Size
 - Text Color
  
All of which can be configured by the user, and will call `Widget#onSettingsUpdate()` (which you can implement) when they are changed. You can also access these by calling `Widget#getSettings()` in your code. The default values are automatically populated for you.
  
(More advanced plugins may wish to dynamically update the settings based on the configured settings, this may be done by implementing `Widget#onSettingsUpdate()` and recreating your layout.)