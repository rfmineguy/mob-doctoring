package com.fischerworks.mobdoctoring;

import com.fischerworks.mobdoctoring.client.render.GhostCreeperRenderer;
import com.fischerworks.mobdoctoring.client.render.GhostSkeletonRenderer;
import com.fischerworks.mobdoctoring.client.render.GhostSpiderRenderer;
import com.fischerworks.mobdoctoring.client.render.GhostZombieRenderer;
import com.fischerworks.mobdoctoring.entities.GhostCreeperEntity;
import com.fischerworks.mobdoctoring.entities.GhostSpiderEntity;
import com.fischerworks.mobdoctoring.events.*;
import com.fischerworks.mobdoctoring.init.*;
import com.fischerworks.mobdoctoring.util.ModUtil;
import com.fischerworks.mobdoctoring.world_gen.OreGeneration;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TO BUILD JAR, RUN gradle/build/jar
// The value here should match an entry in the META-INF/mods.toml file

//use Advanced Rocketry for guide for advancements
@Mod(MobDoctoring.MOD_ID)
public class MobDoctoring {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mob_doctoring";

    public static final ItemGroup TAB = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.EMPTY_SYRINGE.get());
        }
    };

    public MobDoctoring() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        eventBus.addListener(this::doClientStuff);
        eventBus.addListener(EntityRegisterEvent::registerEvent);

        ModUtil.init();
        ItemInit.ITEMS_REG.register(eventBus);
        BlockInit.BLOCKS_REG.register(eventBus);
        TileEntityTypesInit.TILE_ENTITY_REG.register(eventBus);
        ContainerTypeInit.CONTAINER_REG.register(eventBus);
        RecipeInit.RECIPE_REG.register(eventBus);
        EffectInit.EFFECTS.register(eventBus);
        EntityInit.ENTITY_TYPE_REG.register(eventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
        MinecraftForge.EVENT_BUS.addListener(MonsterEntityEvents::entityAttackEvent);
        MinecraftForge.EVENT_BUS.addListener(MonsterEntityEvents::entityDeathEvent);
        MinecraftForge.EVENT_BUS.addListener(new LootLoadEvents()::loadLoot);
        MinecraftForge.EVENT_BUS.addListener(TradesEvent::villagerTradeEvent);
        MinecraftForge.EVENT_BUS.addListener(TradesEvent::wandererTradeEvent);
        MinecraftForge.EVENT_BUS.addListener(OverlayEvent::overlay);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

        //example potion recipe setup
        EffectInit.registerAllRecipes();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GHOST_CREEPER_ENTITY.get(), GhostCreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GHOST_SPIDER_ENTITY.get(), GhostSpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GHOST_SKELETON_ENTITY.get(), GhostSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GHOST_ZOMBIE_ENTITY.get(), GhostZombieRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
}
