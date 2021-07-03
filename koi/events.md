---
layout: default
title: Koi Events
parent: Koi
---

# Koi Event Formats
  
This page outlines some examples of the koi event format and any modifications made by Caffeinated.  
  

## TODO, Document these:  
 - [ ] CHAT  
 - [ ] DONATION  
 - [ ] META  
 - [ ] CHANNEL_POINTS  
 - [ ] FOLLOW  
 - [ ] SUBSCRIPTION  
 - [ ] RAID  
 - [ ] USER_UPDATE  
 - [ ] STREAM_STATUS  
 - [ ] VIEWER_JOIN  
 - [ ] VIEWER_LEAVE  
 - [ ] VIEWER_LIST  
 - [ ] CATCHUP  
 - [ ] CLEARCHAT  
  
  
## `EventType`
All event types.  
<details>
  <summary>Click to expand!</summary>
  
  ```js
  enum KoiEventType {
    CHAT,
    DONATION,
    META,
    CHANNEL_POINTS,
    FOLLOW,
    SUBSCRIPTION,
    RAID,
    USER_UPDATE,
    STREAM_STATUS,
    VIEWER_JOIN,
    VIEWER_LEAVE,
    VIEWER_LIST,
    CATCHUP,
    CLEARCHAT
  }
  ```
</details>

## `User`
Represents a user/channel account.  
<details>
  <summary>Click to expand!</summary>
  
  ```js
  interface KoiUser {
    id: string, // The user id.
    UPID: string, // Unique platform id, this allows for unique identification of users incase any platform use the same ID system.
    username: string, // The user's username, on Twitch this is the login name. Always lowercase.
    displayname: string, // The user displayname, this is the preferred casing of the username. On Twitch this can also be a localized version instead: https://blog.twitch.tv/en/2016/08/22/localized-display-names-e00ee8d3250a/
    platform: UserPlatform,
    image_link: string, // The user/channel profile image.
    color: string, // The user's preferred chat color. This is a hex color (#ffffff)

    link: string, // A link that will take you to the channel OR profile page of a user.

    roles: KoiUserRoles[],
    badges: string[], // This is an array of image links.

    channel_id: string, // The channel id, not present for viewers. This is sometimes the same as the user id, platform dependent.
    bio: string, // The channel bio, not present for viewers.
    followers_count: number, // Not present for viewers.
    subscriber_count: number // Not present for viewers.
  }

  enum KoiUserPlatform {
    CAFFEINE,
    TWITCH,
    TROVO,
    GLIMESH,
    BRIME,
    CASTERLABS_SYSTEM // Used by test events.
  }

  enum KoiUserRoles {
    BROADCASTER,
    SUBSCRIBER,
    FOLLOWER,
    MODERATOR,
    STAFF
  }
  ```
</details>

## `Event`
The base interface for all events.  
<details>
  <summary>Click to expand!</summary>
  
  ```js
  interface KoiEvent {
    isTest: boolean, // Added by Caffeinated.

    streamer: KoiUser, // Tied to the signed in account, always present.

    event_type: KoiEventType,
    event_abilities: KoiEventAbilities
  }
  
  interface KoiEventAbilities {
    upvotable: boolean, // Let's you know if an event can be updated, refer to koi.upvote for more information.
    deletable: boolean  // Let's you know if an event can be deleted, refer to koi.deleteMessage for more information.
  }
  ```
</details>
