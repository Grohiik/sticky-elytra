package heartbrrr.sticky_elytra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "respawnPlayer", at = @At(value = "RETURN"), cancellable = true)
    private void transferInventory(ServerPlayerEntity oldplayer, boolean alive, Entity.RemovalReason removalReason,
                                   CallbackInfoReturnable<ServerPlayerEntity> cir) {
        ServerPlayerEntity newPlayer = cir.getReturnValue();
        newPlayer.getInventory().clone(oldplayer.getInventory());

        cir.setReturnValue(newPlayer);
    }
}
