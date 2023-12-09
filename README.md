# Export Layered Frames
While in a world (both multiplayer and singleplayer) this mod will export layered videos of your gameplay.
Multiple **lossless** video files with transparency will be exported as seperate layers.

As of the current version of the mod, only 2 videos will be exported:
- World (Equivalent to pressing F1)
- GUI (Will be on a transparent BG)

Audio will also be exported, as well as the in-game camera path.
*(Sorry Replay mod, but your camera path exporting was all I used you for!)*

These videos can then be loaded into an external program for a variety of uses, usually adding elements in post using external software.

You can also take layered screenshots using the default key binding of F7.


## Planned Additions

More features are planned to be added, this is a list of features that future versions may contain. ***These are not currently in the mod!***


### More layers

Exporting more layers will bring even more use cases to users with more specific needs.

If you don't need so many layers, they can be combined into groups using ingame menus and exported as a single video (Useful for UI, which generally doesnt need to be in seperate layers) or disabled entirely.
The mod will also export the camera path of the player, this includes the offset created by going into F5

An incomplete list:
- Terrain
- Entities
- Particles
- Hotbar (Image will be 184x23 pixels multiplied by your ingame GUI scale setting to save space, can be disabled)
- Hand
- Chat
- Inventory
- Tooltips


### Easy to manage GUI

A full GUI for customising, hiding, & grouping layers that will be rendered in the video as well as other settings for exporting.

### Relative camera paths

If your ingame coodinates are thousands of blocks away during the recording, the camera path can be automatically offset to be near the origin so you don't have to move them manually in your 3D software.
