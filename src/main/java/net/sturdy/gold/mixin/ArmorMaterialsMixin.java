package net.sturdy.gold.mixin;

import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(ArmorMaterials.class)
abstract class ArmorMaterialsMixin {
    @ModifyArgs(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/item/ArmorMaterials.<init>(Ljava/lang/String;ILjava/lang/String;I[IILnet/minecraft/sound/SoundEvent;FFLjava/util/function/Supplier;)V"))
    private static void fixGold(Args arguments) {
        if (arguments.get(0).equals("GOLD")) {
            arguments.set(3, 24);
            arguments.set(4, new int[]{3, 5, 6, 3});
        }
    }
}
