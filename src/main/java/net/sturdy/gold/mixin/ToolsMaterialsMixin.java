package net.sturdy.gold.mixin;

import net.minecraft.item.ToolMaterials;
import net.sturdy.gold.SturdyGold;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(ToolMaterials.class)
public abstract class ToolsMaterialsMixin {
    @ModifyArgs(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/item/ToolMaterials.<init>(Ljava/lang/String;IIIFFILjava/util/function/Supplier;)V"))
    private static void fixGold(Args args) {
        if (args.get(0).equals("GOLD")) {
            args.set(2, 2);
            args.set(3, 768);
            args.set(5, 2F);
        }
    }
}
