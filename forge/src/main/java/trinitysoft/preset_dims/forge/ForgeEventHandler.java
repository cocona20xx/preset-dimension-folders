package trinitysoft.preset_dims.forge;

import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import trinitysoft.preset_dims.PresetDimsMod;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = PresetDimsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
        Path worldDimsDirectory = event.getServer().getWorldPath(LevelResource.ROOT).resolve("dimensions");
        Path presetDimsDirectory = event.getServer().getServerDirectory().toPath().resolve(PresetDimsMod.LOCATION);
        Path originalLockLocation = ModList.get().getModFileById(PresetDimsMod.MOD_ID).getFile().findResource("assets/preset_dims/presetDimsLock.txt");
        PresetDimsMod.copyPresetDims(worldDimsDirectory, presetDimsDirectory, originalLockLocation);
    }
}
