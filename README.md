# Preset Dimensions
### For 1.20.1 (Neo)Forge and Quilt

[Modrinth link](https://modrinth.com/mod/preset-dims)

[Curseforge link](https://legacy.curseforge.com/minecraft/mc-mods/preset-dimensions)

#### Usage instructions:
- Place dimension presets (for instance, a pre-built dungeon in its own superflat dim) in `.minecraft/dimension_presets`
- When a world is first loaded, those dimension presets will be copied into the world's `dimensions` subfolder.
- Delete the lock file in a given world folder (located at `YOUR_WORLD_PATH/presetDimsLock.txt`) 
and the dimensions with presets to re-copy/regenerate the presets if needed. Note that deleting the lock file *without*
deleting the dimensions with presets may cause unexpected behavior, as the mod does not handle that case for you.

No Fabric port is planned for the foreseeable future.

A Neoforge 1.21 port will probably happen, same with a Quilt 1.21 port if interest exists.

If you want to port this to Fabric for some reason (why?) or to a previous version of the game, this mod is licensed under the MPL 2.0 (see directory's `LICENSE` file for more info)! 
