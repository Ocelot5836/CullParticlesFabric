package tfar.cullparticles.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.cullparticles.extension.LevelRendererExtension;

@Mixin(ParticleEngine.class)
public class ParticleEngineMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/Particle;render(Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/client/Camera;F)V"))
    private void cullParticles(Particle particle, VertexConsumer builder, Camera camera, float partialTicks) {
        if (((LevelRendererExtension) Minecraft.getInstance().levelRenderer).cullparticles_getFrustum().isVisible(particle.getBoundingBox()))
            particle.render(builder, camera, partialTicks);
    }
}
