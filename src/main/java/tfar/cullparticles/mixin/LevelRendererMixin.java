package tfar.cullparticles.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.cullparticles.extension.LevelRendererExtension;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin implements LevelRendererExtension {

    @Unique
    private Frustum frustum;

    @Inject(method = "setupRender", at = @At("HEAD"))
    public void captureFrustum(Camera camera, Frustum frustum, boolean bl, boolean bl2, CallbackInfo ci) {
        this.frustum = frustum;
    }

    @Override
    public Frustum cullparticles_getFrustum() {
        return frustum;
    }
}
