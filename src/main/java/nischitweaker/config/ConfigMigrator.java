package nischitweaker.config;

import meldexun.betterconfig.api.ConfigMigrationHelper;
import meldexun.betterconfig.api.tree.IConfigCategory;
import meldexun.betterconfig.api.tree.IConfigContext;
import nischitweaker.Tags;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public class ConfigMigrator {
	private static boolean versionBelow(ArtifactVersion fileVersion, String version) {
		return fileVersion == null || fileVersion.compareTo(new DefaultArtifactVersion(version)) < 0;
	}

	public static <T extends IConfigContext<T>> void handleMigration(IConfigCategory<T> general, T context, ArtifactVersion fileVersion) {
		if(general.getElements().isEmpty() && general.getSubCategories().isEmpty()) return;
		if(versionBelow(fileVersion, "1.1")) migrateTo1_1(general); // cfg version 1.0 was used up to mod version 1.1.2.2
	}

	private static <T extends IConfigContext<T>> void migrateTo1_1(IConfigCategory<T> general) {
		try {
			IConfigCategory<T> enchanterTools = general.getSubCategories().get("Enchanter Tools");
			if(enchanterTools != null)
				ConfigMigrationHelper.renameElement(enchanterTools, "Fix Min Enchanting Lvl (Mixin & ASM Toggle", "Fix Min Enchanting Lvl (Mixin & ASM Toggle)");

			IConfigCategory<T> locks = general.getSubCategories().get("Locks");
			if(locks != null)
				ConfigMigrationHelper.renameCategory(locks, "Additional Lock Picks(MixinToggle)", "Lock Pick Strengths (MixinToggle)");
		} catch (Exception e) {
			Tags.LOGGER.error("Config migration to 1.1 failed", e);
			throw new RuntimeException("Config migration to 1.1 failed", e);
		}
	}
}
