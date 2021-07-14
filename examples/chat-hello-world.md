---
title: "Chat Example: \"Hello world\""
parent: Examples
---

# Chat Example: "Hello World"

module.js:
```javascript

// Register our class to the module system.
MODULES.uniqueModuleClasses["chat_hello_world"] = class {

    // Init the module.
    constructor(id) {
        this.namespace = "chat_hello_world";
        this.displayname = "Chat Hello World";
        this.type = "settings";
        this.id = id;
        this.persist = true;
    }

    getDataToStore() {
        return this.settings;
    }

    init() {
        koi.addEventListener("chat", (event) => {
            if (event.message === this.settings.command) {
                this.sendMessage();
            }
        });
    }

    sendMessage() {
        // We use PUPPET to indicate that we prefer the chatbot account over the streamer's account.
        koi.sendMessage("Hello world!", null, "PUPPET");
    }

    settingsDisplay = {
        command: {
            display: "Chat Command",
            type: "text"
        },
        sendMessageBtn: {
            display: "Send Message",
            type: "button"
        }
    };

    defaultSettings = {
        command: "!hello",
        sendMessageBtn: () => {
            this.sendMessage();
        }
    };

};

```
  
modules.json:
```json
{
    "supported": [
        "1.*-*"
    ],
    "unsupported": [
        "0.4.*-*",
        "0.5.*-*"
    ],
    "version": "1.0.0",
    "name": "Caffeinated Hello World Example",
    "scripts": [
        "module.js"
    ],
    "simple": [],
    "required": [
        {
            "namespace": "chat_hello_world",
            "id": "chat_hello_world"
        }
    ]
}
```
  
## Result
![The generated module settings](https://i.imgur.com/jCUN4jP.png)
![The !hello command in action](https://i.imgur.com/zEs7hee.png)
