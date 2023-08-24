---
title: Chat Bot
nav_order: 7
has_toc: true
has_children: true
---

# Chat Bot

Documentation for chatbot scripts.

{: .no_toc }

## Types

```typescript
// A list of arguments, so if someone runs "!example 123 abc" this will be ['123', 'abc'].
const args: string[];
```

```typescript
declare global {
  // The K:V store, you can use this to store information like points or stats.
  interface ChatBotStore {
    /* Puts a value in the store. */
    public put(key: string, value: any): void;

    /* Returns a value */
    public get(key: string): any;

    /* Returns a value, using the default if it's not present */
    public getOrDefault(key: string, defaultValue: any): any;

    /* Returns whether or not the store contains a key */
    public containsKey(key: string): boolean;
  }
}

const store: ChatBotStore;
```

```typescript
declare global {
  interface ChatBotMusic {
    // Null if there is no song playing.
    activePlayback?: ActivePlayback;

    // Not really useful, feel free to poke around though.
    providers: any[];
  }

  interface ActivePlayback {
    serviceId: string;
    serviceName: string;
    accountName?: string;
    accountLink?: string;
    playbackState: PlaybackState;
    currentTrack: MusicTrack;
  }

  enum PlaybackState {
    PLAYING,
    PAUSED,
    INACTIVE,
  }

  interface MusicTrack {
    title: string;
    album?: string;
    albumArtUrl: string; // Can also be base64 data. Be wary.
    link: string;
    artists: string[];
  }
}

const Music: ChatBotMusic;
```

```typescript
declare global {
  interface ChatBotKoi {
    public sendChat(
      platform: KoiPlatform, // Must be a signed in platform for this to succeed.
      message: string,
      chatter: KoiChatter,
      replyTarget: string // The event ID, if you want to do a direct reply.
    ): void;

    public upvoteChat( // Not supported by all platforms.
      platform: KoiPlatform, // Must be a signed in platform for this to succeed.
      messageId: string // The event ID.
    ): void;

    public deleteChat( // Not supported by all platforms.
      platform: KoiPlatform, // Must be a signed in platform for this to succeed.
      messageId: string // The event ID.
    ): void;

    history: KoiEvent[];

    // This are for the currently signed in accounts.
    viewers: { [key: KoiPlatform]: KoiUser[] };
    userStates: { [key: KoiPlatform]: KoiUserUpdateEvent[] };
    streamStates: { [key: KoiPlatform]: KoiStreamUpdateEvent[] };
    roomStates: { [key: KoiPlatform]: KoiRoomstateEvent[] };
    features: { [key: KoiPlatform]: KoiIntegrationFeatures[] };
  }

  enum KoiChatter {
    CLIENT, // The streamer's account.
    PUPPET, // Unused, right now this redirects to CLIENT.
    SYSTEM, // The @Casterlabs account.
  }

  enum KoiPlatform {
    CAFFEINE,
    TWITCH,
    TROVO,
    YOUTUBE,
    DELIVE,
    THETA,
    KICK,
    TIKTOK,

    // Coming up.
    YOUNOW,

    // Dead D:
    GLIMESH,
    BRIME,

    // Other
    CASTERLABS_SYSTEM,
    CUSTOM_INTEGRATION,
  }
}

const Koi: ChatBotKoi;

const PLATFORMS: KoiPlatform[]; // All enums.

// The event that triggered this script. Always a KoiRichMessageEvent, which you can inspect the Java definition here:
// https://github.com/Casterlabs/caffeinated/blob/dev/app/Koi/src/main/java/co/casterlabs/koi/api/types/events/RichMessageEvent.java
const event: KoiEvent;
```

```typescript
declare global {
  interface ChatBotFetchAPI {
    public asText(url: string): string;

    public asJson(url: string): object;
  }
}

const fetch: ChatBotFetchAPI;
```

```typescript
declare global {
  interface CaffeinatedPlugins {
    public callServiceMethod(pluginId: string, serviceId: string, methodName: string, args: any[]): object;
  }
}

const Plugins: CaffeinatedPlugins;
```
