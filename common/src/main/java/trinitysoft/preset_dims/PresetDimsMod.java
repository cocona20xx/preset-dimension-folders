package trinitysoft.preset_dims;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


public class PresetDimsMod {
    public static final String MOD_ID = "preset_dims";
    public static final String LOCATION = "dimension_presets";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void copyPresetDims(Path worldDimensionsDir, Path globalPresetsDir, Path originalLockLocation) {
        //sanity: generate preset dir if it doesn't exist yet, or is a file and not a directory for some reason
        //do nothing afterward since there won't exactly be any files to move >.<
        if (!Files.exists(globalPresetsDir)) {
            LOGGER.info("Generating the preset_dims directory for the first time in this instance!");
            boolean sanity = globalPresetsDir.toFile().mkdir();
            if (!sanity) LOGGER.error("Preset dims directory couldn't be generated despite no file existing at location!");
        } else if (!Files.isDirectory(globalPresetsDir)) {
            try {
                Files.delete(globalPresetsDir);
                LOGGER.warn("Non-directory file existed in place of preset_dims directory, generating one in its place.");
                boolean sanity = globalPresetsDir.toFile().mkdir();
                if (!sanity) LOGGER.error("Preset dims directory couldn't be generated despite removal of preexisting file!");
            } catch (IOException e) {
                LOGGER.error(e.toString());
            }
        } else {
            //actual file copying logic
            Path localDimsLock = worldDimensionsDir.getParent().resolve("presetDimsLock.txt");
            if (!Files.exists(localDimsLock)) {
                try(Stream<Path> copyStream = Files.walk(globalPresetsDir)) {
                    copyStream.forEach(subSrc -> copy(subSrc, worldDimensionsDir.resolve(globalPresetsDir.relativize(subSrc))));
                } catch (IOException e) {
                    LOGGER.error(e.toString());
                }
                copy(originalLockLocation, localDimsLock);
            } else {
                LOGGER.info("Preset dimension lock exists for this world, skipping copy process");
            }
        }
    }

    private static void copy(Path src, Path dest) {
        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }
}
