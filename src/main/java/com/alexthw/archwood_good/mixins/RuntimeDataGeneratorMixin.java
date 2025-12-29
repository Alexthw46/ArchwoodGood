package com.alexthw.archwood_good.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.foundation.data.RuntimeDataGenerator;
import com.simibubi.create.foundation.data.recipe.Mods;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@SuppressWarnings("UnstableApiUsage")
@Mixin(RuntimeDataGenerator.class)
public class RuntimeDataGeneratorMixin {

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/foundation/data/recipe/Mods;asResource(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;",
                    ordinal = 0),
            require = 0, // In case Create changes how this is done
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=archwood"))
    )
    private static ResourceLocation archwood_good$dontSpecialCaseBlue(ResourceLocation original){
        return Mods.ARS_N.asResource("blue_archwood");
    }
}
