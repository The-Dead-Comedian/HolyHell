package com.dead_comedian.holyhell.datagen;


import com.dead_comedian.holyhell.server.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class HolyhellRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public HolyhellRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HolyHellItems.HOLY_TEAR.get())
                .requires(HolyHellItems.SAINT_EYE.get())
                .unlockedBy("has_ingredient", has(HolyHellItems.SAINT_EYE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HolyHellItems.ENHANCED_SILK.get())
                .requires(HolyHellItems.HOLY_TEAR.get())
                .requires(Items.STRING, 4)
                .unlockedBy("has_ingredient", has(HolyHellItems.ENHANCED_SILK.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HolyHellItems.HOLY_GOLD.get())
                .requires(HolyHellItems.HOLY_TEAR.get())
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_TEAR.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HolyHellItems.BAPTIZED_PLATE.get())
                .define('A', HolyHellItems.HOLY_GOLD.get())
                .define('B', Items.IRON_INGOT)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_GOLD.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.BONE_CHANDELIER.get())
                .define('A', Items.CANDLE)
                .define('B', Items.BONE)
                .pattern("ABA")
                .pattern("BBB")
                .unlockedBy("has_ingredient", has(Items.CANDLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.BONE_PILE.get())
                .define('A', Items.BONE)
                .pattern("AA")
                .pattern("AA")
                .unlockedBy("has_ingredient", has(Items.BONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.SKULL_PILE.get())
                .define('A', Items.BONE)
                .define('B', Items.BONE_MEAL)
                .pattern("AB")
                .pattern("BA")
                .unlockedBy("has_ingredient", has(Items.BONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CANDELABRA.get(), 4)
                .define('#', Items.CANDLE)
                .define('X', HolyHellItems.HOLY_GOLD.get())
                .pattern("#")
                .pattern("X")
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_GOLD.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.TALL_CANDELABRA.get(), 4)
                .define('#', Items.CANDLE)
                .define('X', HolyHellItems.HOLY_GOLD.get())
                .pattern("#")
                .pattern("X")
                .pattern("X")
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_GOLD.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CANDLEHOLDER.get(), 4)
                .define('#', Items.CANDLE)
                .define('X', HolyHellItems.HOLY_GOLD.get())
                .pattern("###")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_GOLD.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.TALL_CANDLEHOLDER.get(), 4)
                .define('#', Items.CANDLE)
                .define('X', HolyHellItems.HOLY_GOLD.get())
                .pattern("###")
                .pattern("XXX")
                .pattern(" X ")
                .unlockedBy("has_ingredient", has(HolyHellItems.HOLY_GOLD.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.EVANGELIST_BOOTS.get())
                .define('#', HolyHellItems.BAPTIZED_PLATE.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.EVANGELIST_LEGGINGS.get())
                .define('#', HolyHellItems.BAPTIZED_PLATE.get())
                .define('Y', HolyHellItems.ENHANCED_SILK.get())
                .pattern("###")
                .pattern("Y Y")
                .pattern("# #")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.EVANGELIST_CHESTPLATE.get())
                .define('#', HolyHellItems.BAPTIZED_PLATE.get())
                .define('Y', HolyHellItems.ENHANCED_SILK.get())
                .pattern("# #")
                .pattern("#Y#")
                .pattern("###")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.EVANGELIST_HELMET.get())
                .define('#', HolyHellItems.BAPTIZED_PLATE.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.GLOBULAR_DOME.get())
                .define('X', HolyHellItems.HOLY_TEAR.get())
                .define('Y', HolyHellItems.SAINT_EYE.get())
                .define('#', Blocks.COARSE_DIRT)
                .pattern("X#X")
                .pattern("#Y#")
                .pattern("X#X")
                .unlockedBy("has_ingredient", has(HolyHellItems.SAINT_EYE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.HOLY_SHIELD.get())
                .define('X', HolyHellItems.HOLY_GOLD.get())
                .define('Y', HolyHellItems.BAPTIZED_PLATE.get())
                .pattern("X X")
                .pattern("YYY")
                .pattern(" X ")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HolyHellItems.SACRIFICIAL_KATAR.get())
                .define('X', HolyHellItems.BAPTIZED_PLATE.get())
                .define('Y', HolyHellItems.ENHANCED_SILK.get())
                .define('#', Items.STICK)

                .pattern("  X")
                .pattern("#Y ")
                .pattern(" # ")
                .unlockedBy("has_ingredient", has(HolyHellItems.BAPTIZED_PLATE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.JACK_O_LANTERN_CROSS.get().asItem())
                .requires(HolyHellBlocks.CARVED_PUMPKIN_CROSS.get().asItem())
                .requires(Blocks.TORCH.asItem())
                .unlockedBy("has_ingredient", has(Blocks.TORCH.asItem()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.JACK_O_LANTERN_EYE.get().asItem())
                .requires(HolyHellBlocks.CARVED_PUMPKIN_EYE.get().asItem())
                .requires(Blocks.TORCH.asItem())
                .unlockedBy("has_ingredient", has(Blocks.TORCH.asItem()))
                .save(recipeOutput);


        stairRecipe(HolyHellBlocks.MARBLE_STAIRS.get(), HolyHellBlocks.MARBLE.get(), recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_SLAB.get(), HolyHellBlocks.MARBLE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_WALL.get(), HolyHellBlocks.MARBLE.get());

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICKS.get(), HolyHellBlocks.MARBLE.get());
        stairRecipe(HolyHellBlocks.MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.MARBLE_BRICKS.get(), recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_SLAB.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_WALL.get(), HolyHellBlocks.MARBLE_BRICKS.get());

        smeltingResultFromBase(recipeOutput, HolyHellBlocks.CRACKED_MARBLE_BRICKS.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        stairRecipe(HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get(), recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HolyHellBlocks.MARBLE_COLUMN.get())
                .define('B', HolyHellBlocks.MARBLE_SLAB.get())
                .pattern("B")
                .pattern("B")
                .unlockedBy("has_ingredient", has(HolyHellBlocks.MARBLE_SLAB.get())).save(recipeOutput);


        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_SLAB.get(), HolyHellBlocks.MARBLE.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_STAIRS.get(), HolyHellBlocks.MARBLE.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_WALL.get(), HolyHellBlocks.MARBLE.get());


        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICKS.get(), HolyHellBlocks.MARBLE.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_SLAB.get(), HolyHellBlocks.MARBLE.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.MARBLE.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_WALL.get(), HolyHellBlocks.MARBLE.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICKS.get(), HolyHellBlocks.MARBLE.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get(), HolyHellBlocks.MARBLE.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.MARBLE.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get(), HolyHellBlocks.MARBLE.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_COLUMN.get(), HolyHellBlocks.MARBLE.get());


        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_SLAB.get(), HolyHellBlocks.MARBLE_BRICKS.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.MARBLE_BRICK_WALL.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICKS.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get(), HolyHellBlocks.MARBLE_BRICKS.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.MARBLE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get(), HolyHellBlocks.MARBLE_BRICKS.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get(), HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HolyHellBlocks.CHANDELIER.get(),4)
                .pattern("XBX")
                .pattern("BBB")
                .define('B', HolyHellItems.HOLY_GOLD.get())
                .define('X', Blocks.CANDLE)
                .unlockedBy("has_ingredient", has(Blocks.CANDLE)).save(recipeOutput);

    }

    //HELPERS
    private void stairRecipe(ItemLike result, ItemLike ingredient, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ingredient)
                .unlockedBy("has_ingredient", has(ingredient)).save(recipeOutput);
    }

}