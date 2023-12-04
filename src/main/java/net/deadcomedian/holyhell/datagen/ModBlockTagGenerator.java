package net.deadcomedian.holyhell.datagen;


        import net.deadcomedian.holyhell.HolyHell;
        import net.deadcomedian.holyhell.block.ModBlocks;

        import net.minecraft.core.HolderLookup;
        import net.minecraft.data.PackOutput;
        import net.minecraft.tags.BlockTags;

        import net.minecraftforge.common.data.BlockTagsProvider;
        import net.minecraftforge.common.data.ExistingFileHelper;
        import org.jetbrains.annotations.Nullable;

        import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HolyHell.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DIVINING_TABLE.get());




        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LEVANTIA_LOG.get())

                .add(ModBlocks.STRIPPED_LEVANTIA_LOG.get())
                .add(ModBlocks.CARVED_LEVANTIA_LOG.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.LEVANTIA_PLANK.get());
    }
}