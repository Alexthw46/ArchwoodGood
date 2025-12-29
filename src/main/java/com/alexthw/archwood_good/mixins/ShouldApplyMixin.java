package com.alexthw.archwood_good.mixins;

import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("UnstableApiUsage")
public class ShouldApplyMixin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinToApply) {
        Predicate<String> isModLoaded = modId -> LoadingModList.get().getModFileById(modId) != null; // This is too early for ModList.get().isLoaded()

        if (targetClassName.equals("com.simibubi.create.foundation.data.RuntimeDataGenerator")
                && mixinToApply.equals("com.alexthw.archwood_good.mixins.RuntimeDataGeneratorMixin")) {
            return isModLoaded.test("create");
        }
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return List.of("RuntimeDataGeneratorMixin");
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
