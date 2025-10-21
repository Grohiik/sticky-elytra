package heartbrrr.sticky_elytra.mixin;

import net.minecraft.entity.EntityEquipment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityEquipment.class)
public abstract class EntityEquipmentMixin {

    @Redirect(method = "dropAll",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"))
    private ItemEntity ondropItem(LivingEntity entity, ItemStack stack, boolean dropAtSelf, boolean retainOwnership) {
        if (stack.getItem() == Items.ELYTRA && entity instanceof PlayerEntity) {
            return null;
        }
        return entity.dropItem(stack, dropAtSelf, retainOwnership);
    }

    @Redirect(method = "dropAll",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityEquipment;clear()V"))
    private void onClear(EntityEquipment equipment, LivingEntity entity) {
        if (!(entity instanceof PlayerEntity)) {
            equipment.clear();
        }
    }

}
