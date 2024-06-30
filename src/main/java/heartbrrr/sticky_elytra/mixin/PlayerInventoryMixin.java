package heartbrrr.sticky_elytra.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow public abstract boolean isEmpty();

    @Redirect(method = "dropAll",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private boolean
    onDropAll(ItemStack stack) {
        return isEmpty() || stack.getItem() == Items.ELYTRA;
    }
}
