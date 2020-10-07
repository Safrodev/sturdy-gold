package user11681.sturdygold.asm.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(ToolMaterials.class)
abstract class ToolMaterialsMixin {
}
