package trinitysoft.preset_dims.quilt;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelResource;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents;
import trinitysoft.preset_dims.PresetDimsMod;

import java.nio.file.Path;

public class PresetDimsQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ServerWorldLoadEvents.LOAD.register(
                new ResourceLocation(PresetDimsMod.MOD_ID, "dim_setup"),
                new ServerWorldLoadEvents.Load() {
                    @Override
                    public void loadWorld(MinecraftServer server, ServerLevel level) {
                        Path worldDimsDirectory = server.getWorldPath(LevelResource.ROOT).resolve("dimensions");
                        Path presetDimsDirectory = QuiltLoader.getGameDir().resolve(PresetDimsMod.LOCATION);
                        Path originalLockLocation = mod.getPath("assets/preset_dims/presetDimsLock.txt");
                        PresetDimsMod.copyPresetDims(worldDimsDirectory, presetDimsDirectory, originalLockLocation);
                    }
                });
    }
}
